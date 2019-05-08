package com.yc.education.service.customer;


import com.yc.education.model.customer.CustomerBasic;
import com.yc.education.service.IService;



/**
 * @Author: BlueSky
 * @Date: 2018/8/20 11:23
 */
public interface ICustomerBasicService extends IService<CustomerBasic>{

    /**
     * 查询客户
     * @param customerid
     * @return
     */
    CustomerBasic getCustomerBasicByCustomerId(long customerid);


}
