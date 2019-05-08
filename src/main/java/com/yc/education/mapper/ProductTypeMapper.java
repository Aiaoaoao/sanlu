package com.yc.education.mapper;

import com.yc.education.model.ProductType;
import com.yc.education.util.MyMapper;

import java.util.List;

public interface ProductTypeMapper extends MyMapper<ProductType> {


    /**
     * 查询所有产品大类
     * @return
     */
    List<ProductType> findProdutTypeAll();


}