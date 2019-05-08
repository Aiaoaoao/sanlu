package com.yc.education.mapper.customer;

import com.yc.education.model.customer.CustomerBasic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface CustomerBasicMapper extends Mapper<CustomerBasic> {

    /**
     * 查询客户
     * @param customerid
     * @return
     */
    CustomerBasic getCustomerBasicByCustomerId(@Param("customerid") long customerid);


}