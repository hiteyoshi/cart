package com.example.cart.mapper;

import com.example.cart.model.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}