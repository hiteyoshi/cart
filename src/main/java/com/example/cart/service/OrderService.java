package com.example.cart.service;

import com.example.cart.exception.ExceptionEnum;
import com.example.cart.exception.XmException;
import com.example.cart.mapper.OrderMapper;
import com.example.cart.mapper.ProductMapper;
import com.example.cart.mapper.SeckillProductMapper;
import com.example.cart.mapper.ShoppingCartMapper;
import com.example.cart.model.Order;
import com.example.cart.model.Product;
import com.example.cart.model.SeckillProduct;
import com.example.cart.model.ShoppingCart;
import com.example.cart.util.IdWorker;
import com.example.cart.vo.CartVo;
import com.example.cart.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wsc
 * @createTime: 2024/04/15 11:09
 * @company:
 * @description:
 */
@Service
public class OrderService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ShoppingCartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SeckillProductMapper seckillProductMapper;

    private final static String SECKILL_PRODUCT_USER_LIST = "seckill:product:user:list";

    @Transactional
    public void addOrder(List<CartVo> cartVoList, Integer userId) {
        // 先添加订单
        String orderId = idWorker.nextId() + ""; // 订单id
        long time = new Date().getTime(); // 订单生成时间
        for (CartVo cartVo : cartVoList) {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(time);
            order.setProductNum(cartVo.getNum());
            order.setProductId(cartVo.getProductId());
            order.setProductPrice(cartVo.getPrice());
            order.setUserId(userId);
            try {
                orderMapper.insert(order);
            } catch (Exception e) {
                e.printStackTrace();
                throw new XmException(ExceptionEnum.ADD_ORDER_ERROR);
            }
            // 减去商品库存,记录卖出商品数量
            // TODO : 此处会产生多线程问题，即不同用户同时对这个商品操作，此时会导致数量不一致问题
            Product product = productMapper.selectByPrimaryKey(cartVo.getProductId());
            product.setProductNum(product.getProductNum() - cartVo.getNum());
            product.setProductSales(product.getProductSales() + cartVo.getNum());
            productMapper.updateByPrimaryKey(product);
        }
        // 删除购物车
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(userId);
        try {
            int count = cartMapper.delete(cart);
            if (count == 0) {
                throw new XmException(ExceptionEnum.ADD_ORDER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.ADD_ORDER_ERROR);
        }

    }

    public List<List<OrderVo>> getOrder(Integer userId) {
        List<OrderVo> list = null;
        ArrayList<List<OrderVo>> ret = new ArrayList<>();
        try {
            list = orderMapper.getOrderVoByUserId(userId);
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_ORDER_NOT_FOUND);
            }
            // 将同一个订单放在一组
            Map<String, List<OrderVo>> collect = list.stream().collect(Collectors.groupingBy(Order::getOrderId));
            Collection<List<OrderVo>> values = collect.values();
            ret.addAll(values);
        } catch (XmException e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_ORDER_ERROR);
        }
        return ret;
    }

    @Transactional
    public void addSeckillOrder(String seckillId, String userId) {
        // 订单id
        String orderId = idWorker.nextId() + "";
        // 商品id
        SeckillProduct seckillProduct = new SeckillProduct();
        seckillProduct.setSeckillId(Integer.parseInt(seckillId));
        SeckillProduct one = seckillProductMapper.selectOne(seckillProduct);
        Integer productId = one.getProductId();
        // 秒杀价格
        Double price = one.getSeckillPrice();

        // 订单封装
        Order order = new Order();
        order.setOrderId(orderId);
        order.setProductId(productId);
        order.setProductNum(1);
        order.setUserId(Integer.parseInt(userId));
        order.setOrderTime(new Date().getTime());
        order.setProductPrice(price);

        try {
            orderMapper.insert(order);
            // 减库存
            seckillProductMapper.decrStock(one.getSeckillId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.ADD_ORDER_ERROR);
        }

        // 订单创建成功, 将用户写入redis, 防止多次抢购
        redisTemplate.opsForList().leftPush(SECKILL_PRODUCT_USER_LIST + seckillId, userId);

    }
}
