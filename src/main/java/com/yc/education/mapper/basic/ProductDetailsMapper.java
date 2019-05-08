package com.yc.education.mapper.basic;

import com.yc.education.model.basic.ProductDetails;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDetailsMapper extends MyMapper<ProductDetails> {





    /**
     * 查询产品下的规格明细
     * @param proid
     * @return
     */
    List<ProductDetails> selectProductDetailsByProid(@Param("proid")long proid);



}