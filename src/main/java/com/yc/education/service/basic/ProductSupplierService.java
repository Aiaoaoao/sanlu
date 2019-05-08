package com.yc.education.service.basic;

import com.yc.education.model.basic.ProductSupplier;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName ProductSupplierService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/6 14:52
 * @Version 1.0
 */
public interface ProductSupplierService extends IService<ProductSupplier> {


    /**
     * 查询产品下的供应商
     * @param proid
     * @return
     */
    List<ProductSupplier> selectProducctSupplierByProid(long proid);


}
