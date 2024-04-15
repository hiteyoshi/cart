package com.example.cart.controller;

import com.example.cart.model.Product;
import com.example.cart.service.CollectService;
import com.example.cart.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: wsc
 * @createTime: 2024/04/15 10:56
 * @company:
 * @description:
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private CollectService collectService;

    /**
     * 将商品收藏
     * @param userId
     * @param productId
     * @return
     */
    @PostMapping("/user/{productId}/{userId}")
    public ResultMessage addCollect(@PathVariable String userId, @PathVariable String productId) {
        collectService.addCollect(userId, productId);
        resultMessage.success("001", "商品收藏成功");
        return resultMessage;
    }

    /**
     * 获取用户收藏
     * @param userId
     * @return 返回商品集合
     */
    @GetMapping("/user/{userId}")
    public ResultMessage getCollect(@PathVariable String userId) {
        List<Product> collects = collectService.getCollect(userId);
        resultMessage.success("001", collects);
        return resultMessage;
    }

    @DeleteMapping("/user/{productId}/{userId}")
    public ResultMessage deleteCollect(@PathVariable String productId, @PathVariable String userId) {
        collectService.deleteCollect(userId, productId);
        resultMessage.success("001", "删除收藏成功");
        return resultMessage;
    }
}