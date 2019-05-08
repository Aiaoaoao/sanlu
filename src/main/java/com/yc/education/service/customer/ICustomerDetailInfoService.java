package com.yc.education.service.customer;

import com.yc.education.model.customer.CustomerDetailInfo;
import com.yc.education.service.IService;

/**
 * @author BlueSky
 * @Description:
 * @Date 15:58 2018-08-24
 */
public interface ICustomerDetailInfoService extends IService<CustomerDetailInfo> {

    /**
     * 查询客户详细信息
     * @param customerid 客户id
     * @return
     */
    CustomerDetailInfo getCustomerDetailInfoByCustomerId(long customerid);

    /**
     * 查询客户详细信息
     * @param customerNo 客户编号
     * @return
     */
    CustomerDetailInfo getCustomerDetailInfoByCustomerNo(String customerNo);
}
