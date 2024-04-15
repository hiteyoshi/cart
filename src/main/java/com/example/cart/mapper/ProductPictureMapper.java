package com.example.cart.mapper;

import com.example.cart.model.ProductPicture;
import tk.mybatis.mapper.common.Mapper;

public interface ProductPictureMapper extends Mapper<ProductPicture>{
    int deleteByPrimaryKey(Integer id);

    int insert(ProductPicture record);

    int insertSelective(ProductPicture record);

    ProductPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductPicture record);

    int updateByPrimaryKeyWithBLOBs(ProductPicture record);

    int updateByPrimaryKey(ProductPicture record);
}