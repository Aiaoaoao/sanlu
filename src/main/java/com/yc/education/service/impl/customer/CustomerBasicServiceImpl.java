package com.yc.education.service.impl.customer;


import com.yc.education.mapper.customer.CustomerBasicMapper;
import com.yc.education.model.customer.CustomerBasic;
import com.yc.education.service.customer.ICustomerBasicService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: BlueSky
 * @Date: 2018/8/20 11:24
 */
@Service
public class CustomerBasicServiceImpl extends BaseService<CustomerBasic> implements ICustomerBasicService {

    @Autowired
    private CustomerBasicMapper mapper;


    @Override
    public CustomerBasic getCustomerBasicByCustomerId(long customerid) {
        try{
            return mapper.getCustomerBasicByCustomerId(customerid);
        }catch (Exception e){
            return null;
        }
    }
}
