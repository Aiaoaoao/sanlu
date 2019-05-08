package com.yc.education.service.customer;

import com.yc.education.model.customer.CustomerDataMaintenance;
import com.yc.education.service.IService;

import java.util.List;

/**
 * 客户基本资料-业务负责人
 * @author BlueSky
 * @Description:
 * @Date 15:16 2018-08-24
 */
public interface ICustomerDataMaintenanceService extends IService<CustomerDataMaintenance>{


    /**
     * 查询某客户的资料维护内容
     * @param customerid
     * @return
     */
    List<CustomerDataMaintenance> listCustomerDataMaintenanceByCustomerId(long customerid);

    /**
     * 删除某个客户的所有资料维护内容
     * @param customerid
     * @return
     */
    int deleteCustomerDataMaintenanceByCustomerId(long customerid);
}
