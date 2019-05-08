package com.yc.education.mapper.customer;

import com.yc.education.model.customer.CustomerBusinessLeader;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerBusinessLeaderMapper extends MyMapper<CustomerBusinessLeader> {

    /**
     * 查询某客户的业务负责人
     * @param customerid
     * @return
     */
    List<CustomerBusinessLeader> listCustomerBusinessLeaderByCustomerId(@Param("customerid")long customerid);

    /**
     * 删除某个客户的所有业务负责人
     * @param customerid
     * @return
     */
    int deleteCustomerBusinessLeaderByCustomerId(@Param("customerid") long customerid);
}