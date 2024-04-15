package com.example.cart.vo;

import com.example.cart.model.Order;

/**
 * @author: wsc
 * @createTime: 2024/04/15 11:34
 * @company:
 * @description:
 */
public class OrderVo extends Order {

    private String productName;

    private String productPicture;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }
}
