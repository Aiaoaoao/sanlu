package com.yc.education.service.basic;

import com.yc.education.model.basic.ProductDetails;
import com.yc.education.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ProductDetailsService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/6 14:53
 * @Version 1.0
 */
public interface ProductDetailsService extends IService<ProductDetails> {



    /**
     * 查询产品下的规格明细
     * @param proid
     * @return
     */
    List<ProductDetails> selectProductDetailsByProid(long proid);


}
