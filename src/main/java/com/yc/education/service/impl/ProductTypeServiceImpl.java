package com.yc.education.service.impl;

import com.yc.education.mapper.ProductTypeMapper;
import com.yc.education.model.ProductType;
import com.yc.education.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductTypeServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/20 17:49
 * @Version 1.0
 */
@Service("ProductTypeService")
public class ProductTypeServiceImpl extends BaseService<ProductType> implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> findProdutTypeAll() {
        try {
            return productTypeMapper.findProdutTypeAll();
        } catch (Exception e) {
            return null;
        }
    }

}
