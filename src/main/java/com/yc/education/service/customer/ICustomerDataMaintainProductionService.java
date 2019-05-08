package com.yc.education.service.customer;


import com.yc.education.model.customer.CustomerDataMaintainProduction;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 15:38 2018-08-31
 */
public interface ICustomerDataMaintainProductionService extends IService<CustomerDataMaintainProduction>{

    /**
     * 查询所有
     * @return
     */
    List<CustomerDataMaintainProduction> listCustomerDataMaintainProduction(int page,int rows);

    /**
     * 通过客户资料id查询详情
     * @return
     */
    List<CustomerDataMaintainProduction> listCustomerDataMaintainProductionByMaintainId( long maintainId);

    /**
     * 通过客户资料id删除详情
     * @param maintainId
     * @return
     */
    int deleteCustomerDataMaintainProductionByMaintainId( long maintainId);
}
