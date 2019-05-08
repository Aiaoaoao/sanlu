package com.yc.education.service.impl.basic;

import com.yc.education.mapper.basic.ProductSupplierMapper;
import com.yc.education.model.basic.ProductSupplier;
import com.yc.education.service.basic.ProductSupplierService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductSupplierServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/6 14:54
 * @Version 1.0
 */
@Service("ProductSupplierService")
public class ProductSupplierServiceImpl extends BaseService<ProductSupplier> implements ProductSupplierService {

    @Autowired
    private ProductSupplierMapper productSupplierMapper;

    @Override
    public List<ProductSupplier> selectProducctSupplierByProid(long proid) {
        return productSupplierMapper.selectProducctSupplierByProid(proid);

    }
}
