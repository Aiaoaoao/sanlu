package com.yc.education.service;

import com.yc.education.model.ProductType;

import java.util.List;

/**
 * @ClassName ProductTypeService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/20 17:49
 * @Version 1.0
 */
public interface ProductTypeService extends  IService<ProductType> {

    /**
     * 查询所有产品大类
     * @return
     */
    List<ProductType> findProdutTypeAll();

}
