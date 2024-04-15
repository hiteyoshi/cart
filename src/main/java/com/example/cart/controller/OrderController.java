package com.example.cart.controller;

import com.example.cart.service.OrderService;
import com.example.cart.util.ResultMessage;
import com.example.cart.vo.CartVo;
import com.example.cart.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: wsc
 * @createTime: 2024/04/15 11:08
 * @company:
 * @description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResultMessage addOrder(@RequestBody List<CartVo> cartVoList, @CookieValue("XM_TOKEN") String cookie) {
        // 先判断cookie是否存在，和redis校验
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        orderService.addOrder(cartVoList, userId);
        resultMessage.success("001", "下单成功");
        return resultMessage;
    }

    @GetMapping("")
    public ResultMessage getOrder(@CookieValue("XM_TOKEN") String cookie) {
        // 先判断cookie是否存在，和redis校验
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        List<List<OrderVo>> orders = orderService.getOrder(userId);
        resultMessage.success("001", orders);
        return resultMessage;
    }

}
