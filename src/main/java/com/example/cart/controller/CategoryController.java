package com.example.cart.controller;

import com.example.cart.model.Category;
import com.example.cart.service.CategoryService;
import com.example.cart.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wsc
 * @createTime: 2024/04/15 10:30
 * @company:
 * @description:
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResultMessage category() {
        List<Category> categories = categoryService.getAll();
        resultMessage.success("001", categories);
        return resultMessage;
    }
}
