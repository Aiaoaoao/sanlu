package com.yc.education.service.impl.basic;

import com.yc.education.mapper.basic.ProductDetailsMapper;
import com.yc.education.model.basic.ProductDetails;
import com.yc.education.service.basic.ProductDetailsService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductDetailsServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/6 14:55
 * @Version 1.0
 */
@Service("ProductDetailsService")
public class ProductDetailsServiceImpl extends BaseService<ProductDetails> implements ProductDetailsService {

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public List<ProductDetails> selectProductDetailsByProid(long proid) {
        return productDetailsMapper.selectProductDetailsByProid(proid);
    }


}
