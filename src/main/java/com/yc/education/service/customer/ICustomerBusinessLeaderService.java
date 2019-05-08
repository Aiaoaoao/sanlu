package com.yc.education.service.customer;

import com.yc.education.model.customer.CustomerBusinessLeader;
import com.yc.education.service.IService;

import java.util.List;

/**
 * 客户基本资料-业务负责人
 * @author BlueSky
 * @Description:
 * @Date 15:16 2018-08-24
 */
public interface ICustomerBusinessLeaderService extends IService<CustomerBusinessLeader>{

    /**
     * 查询某客户的业务负责人
     * @param customerid
     * @return
     */
    List<CustomerBusinessLeader> listCustomerBusinessLeaderByCustomerId(long customerid);

    /**
     * 删除某个客户的所有业务负责人
     * @param customerid
     * @return
     */
    int deleteCustomerBusinessLeaderByCustomerId(long customerid);
}
