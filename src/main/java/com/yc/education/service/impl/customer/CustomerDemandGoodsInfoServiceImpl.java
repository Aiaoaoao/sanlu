package com.yc.education.service.impl.customer;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.customer.CustomerDemandGoodsInfoMapper;
import com.yc.education.mapper.customer.CustomerDemandGoodsMapper;
import com.yc.education.model.customer.CustomerDemandGoods;
import com.yc.education.model.customer.CustomerDemandGoodsInfo;
import com.yc.education.service.customer.ICustomerDemandGoodsInfoService;
import com.yc.education.service.customer.ICustomerDemandGoodsService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: BlueSky
 * @Date: 2018/8/20 11:24
 */
@Service
public class CustomerDemandGoodsInfoServiceImpl extends BaseService<CustomerDemandGoodsInfo> implements ICustomerDemandGoodsInfoService {

    @Autowired
    private CustomerDemandGoodsInfoMapper mapper;

    @Override
    public List<CustomerDemandGoodsInfo> listCustomerDemandGoodsInfo(String productName, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listCustomerDemandGoodsInfo(productName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CustomerDemandGoodsInfo> listCustomerDemandGoodsInfoByCustomerDemandId(long customerDemandId) {
        try {
            return mapper.listCustomerDemandGoodsInfoByCustomerDemandId(customerDemandId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int deleteCustomerDemandGoodsInfoByCustomerDemandId(long customerDemandId) {
        try {
            return mapper.deleteCustomerDemandGoodsInfoByCustomerDemandId(customerDemandId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<CustomerDemandGoodsInfo> listCustomerDemandGoodsWhere(String createNo, String createNoEnd, String createDate, String createDateEnd, String customerNo, String customerNoEnd, String productNo, String productNoEnd, String material, String manufacture, String method) {
        try {
            return mapper.listCustomerDemandGoodsWhere(createNo, createNoEnd, createDate, createDateEnd, customerNo, customerNoEnd, productNo, productNoEnd, material, manufacture, method);
        } catch (Exception e) {
            return null;
        }
    }
}
