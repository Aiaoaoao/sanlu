package com.yc.education.mapper.basic;

import com.yc.education.model.basic.ProductSupplier;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductSupplierMapper extends MyMapper<ProductSupplier> {


    /**
     * 查询产品下的供应商
     * @param proid
     * @return
     */
    List<ProductSupplier>  selectProducctSupplierByProid(@Param("proid")long proid);







}