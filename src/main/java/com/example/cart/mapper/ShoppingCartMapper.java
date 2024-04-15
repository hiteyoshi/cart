package com.example.cart.mapper;

import com.example.cart.model.ShoppingCart;
import tk.mybatis.mapper.common.Mapper;

public interface ShoppingCartMapper extends Mapper<ShoppingCart>{
    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
}