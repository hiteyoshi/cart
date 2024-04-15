package com.example.cart.service;

import com.example.cart.exception.ExceptionEnum;
import com.example.cart.exception.XmException;
import com.example.cart.mapper.CarouselMapper;
import com.example.cart.model.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    public List<Carousel> getCarouselList(){
        List<Carousel> list = null;
        try{
            list = carouselMapper.selectAll();
            if (list.isEmpty()){
                throw new XmException(ExceptionEnum.GET_CAROUSEL_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_CAROUSEL_ERROR);
        }
        return list;
    }
}
