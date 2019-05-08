package com.yc.education.service.customer;

import com.yc.education.model.customer.CustomerShippingAddress;
import com.yc.education.service.IService;
import java.util.*;

/**
 * @Author: BlueSky
 * @Date: 2018/8/16 17:47
 */
public interface ICustomerShippingAddressService extends IService<CustomerShippingAddress> {

    /**
     * 根据客户id查询送货地址
     * @param customerid
     * @return
     */
    List<CustomerShippingAddress> listAll(long customerid);

    /**
     * 设置默认地址
     * @param id
     * @return
     */
    int updateAddressDefault(long id);

    /**
     * 清除默认地址
     * @param customerid
     * @return
     */
    int updateClearAddressDefault(long customerid);
}
