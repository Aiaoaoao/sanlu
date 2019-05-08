package com.yc.education.mapper.customer;

import com.yc.education.model.customer.CustomerDetailInfo;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;


public interface CustomerDetailInfoMapper extends MyMapper<CustomerDetailInfo> {

    /**
     * 查询客户详细信息
     * @param customerid 客户id
     * @return
     */
    CustomerDetailInfo getCustomerDetailInfoByCustomerId(@Param("customerid") long customerid);


    /**
     * 查询客户详细信息
     * @param customerNo 客户编号
     * @return
     */
    CustomerDetailInfo getCustomerDetailInfoByCustomerNo(@Param("customerNo") String customerNo);
}