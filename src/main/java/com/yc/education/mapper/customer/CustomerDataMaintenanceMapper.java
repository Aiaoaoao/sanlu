package com.yc.education.mapper.customer;

import com.yc.education.model.customer.CustomerDataMaintenance;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomerDataMaintenanceMapper extends MyMapper<CustomerDataMaintenance> {

    /**
     * 查询某客户的资料维护内容
     * @param customerid
     * @return
     */
    List<CustomerDataMaintenance> listCustomerDataMaintenanceByCustomerId(@Param("customerid")long customerid);

    /**
     * 删除某个客户的所有资料维护内容
     * @param customerid
     * @return
     */
    int deleteCustomerDataMaintenanceByCustomerId(@Param("customerid") long customerid);
}