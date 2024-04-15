package com.example.cart.mapper;

import com.example.cart.model.Carousel;
import tk.mybatis.mapper.common.Mapper;


public interface CarouselMapper extends Mapper<Carousel>{
    int deleteByPrimaryKey(Integer carouselId);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer carouselId);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);
}