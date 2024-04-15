package com.example.cart.controller;

import com.example.cart.model.ProductPicture;
import com.example.cart.service.ProductPictureService;
import com.example.cart.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wsc
 * @createTime: 2024/04/15 12:06
 * @company:
 * @description:
 */
@RestController
@RequestMapping("/productPicture")
public class ProductPictureController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private ProductPictureService productPictureService;

    @GetMapping("/product/{productId}")
    public ResultMessage productPicture(@PathVariable String productId) {
        List<ProductPicture> products = productPictureService.getProductPictureByProductId(productId);
        resultMessage.success("001", products);
        return resultMessage;
    }

}
