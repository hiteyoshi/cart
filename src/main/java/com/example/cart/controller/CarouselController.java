package com.example.cart.controller;

import com.example.cart.model.Carousel;
import com.example.cart.service.CarouselService;
import com.example.cart.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarouselController {
    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private CarouselService carouselService;

    @GetMapping("/resources/carousel")
    public ResultMessage carousels(){
        List<Carousel> carouselList=carouselService.getCarouselList();
        resultMessage.success("001",carouselList);
        return resultMessage;
    }
}
