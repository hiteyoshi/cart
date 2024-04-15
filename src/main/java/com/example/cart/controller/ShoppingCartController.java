package com.example.cart.controller;

import com.example.cart.service.ShoppingCartService;
import com.example.cart.util.ResultMessage;
import com.example.cart.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: wsc
 * @createTime: 2024/04/15 13:46
 * @company:
 * @description:
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController{

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private ShoppingCartService cartService;

    /**
     * 获取购物车信息
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public ResultMessage cart(@PathVariable String userId) {
        List<CartVo> carts = cartService.getCartByUserId(userId);
        resultMessage.success("001", carts);
        return resultMessage;
    }

    /**
     * 添加购物车
     * @param productId
     * @param userId
     * @return
     */
    @PostMapping("/product/user/{productId}/{userId}")
    public ResultMessage cart(@PathVariable String productId, @PathVariable String userId) {
        CartVo cartVo = cartService.addCart(productId, userId);
        if (cartVo != null) {
            resultMessage.success("001", "添加购物车成功", cartVo);
        }else {
            resultMessage.success("002", "该商品已经在购物车，数量+1");
        }
        return resultMessage;
    }

    @PutMapping("/user/num/{cartId}/{userId}/{num}")
    public ResultMessage cart(@PathVariable String cartId, @PathVariable String userId, @PathVariable String num) {
        cartService.updateCartNum(cartId, userId, num);
        resultMessage.success("001", "更新成功");
        return resultMessage;
    }

    @DeleteMapping("/user/{cartId}/{userId}")
    public ResultMessage deleteCart(@PathVariable String cartId, @PathVariable String userId) {
        cartService.deleteCart(cartId, userId);
        resultMessage.success("001", "删除成功");
        return resultMessage;
    }
}
