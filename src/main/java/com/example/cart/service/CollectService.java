package com.example.cart.service;

import com.example.cart.exception.ExceptionEnum;
import com.example.cart.exception.XmException;
import com.example.cart.mapper.CollectMapper;
import com.example.cart.model.Collect;
import com.example.cart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: wsc
 * @createTime: 2024/04/15 10:57
 * @company:
 * @description:
 */
@Service
public class CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Transactional
    public void addCollect(String userId, String productId) {
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setProductId(Integer.parseInt(productId));
        // 先看看是否数据库中已存在
        Collect one = collectMapper.selectOne(collect);
        if (one != null) {
            throw new XmException(ExceptionEnum.SAVE_COLLECT_REUSE);
        }
        // 不存在，添加收藏
        collect.setCollectTime(new Date().getTime());
        int count = collectMapper.insert(collect);
        if (count != 1) {
            throw new XmException(ExceptionEnum.SAVE_COLLECT_ERROR);
        }
    }

    public List<Product> getCollect(String userId) {
        List<Product> list = null;
        try {
            list = collectMapper.getCollect(userId);
            if (list.isEmpty()) {
                throw new XmException(ExceptionEnum.GET_COLLECT_NOT_FOUND);
            }
        } catch (XmException e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_COLLECT_ERROR);
        }
        return list;
    }

    public void deleteCollect(String userId, String productId) {
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setProductId(Integer.parseInt(productId));
        try {
            int count = collectMapper.delete(collect);
            if (count != 1) {
                throw new XmException(ExceptionEnum.DELETE_COLLECT_ERROR);
            }
        } catch (XmException e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.DELETE_COLLECT_ERROR);
        }
    }
}
