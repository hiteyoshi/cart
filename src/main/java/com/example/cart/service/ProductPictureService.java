package com.example.cart.service;

import com.example.cart.exception.ExceptionEnum;
import com.example.cart.exception.XmException;
import com.example.cart.mapper.ProductPictureMapper;
import com.example.cart.model.ProductPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import java.util.List;

/**
 * @author: wsc
 * @createTime: 2024/04/15 12:07
 * @company:
 * @description:
 */
@Service
public class ProductPictureService {

    @Autowired
    private ProductPictureMapper productPictureMapper;

    public List<ProductPicture> getProductPictureByProductId(String productId) {
        ProductPicture picture = new ProductPicture();
        picture.setProductId(Integer.parseInt(productId));
        List<ProductPicture> list = null;
        try {
            list = productPictureMapper.select(picture);
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_PICTURE_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_PICTURE_ERROR);
        }
        return list;
    }
}
