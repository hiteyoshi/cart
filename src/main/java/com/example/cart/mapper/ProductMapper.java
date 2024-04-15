package com.example.cart.mapper;

import com.example.cart.model.Product;
import tk.mybatis.mapper.common.Mapper;

public interface ProductMapper extends Mapper<Product>{
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);
}