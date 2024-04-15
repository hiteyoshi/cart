package com.example.cart.mapper;

import com.example.cart.model.Category;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category>{
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}