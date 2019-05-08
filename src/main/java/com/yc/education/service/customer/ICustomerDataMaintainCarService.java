package com.yc.education.service.customer;



import com.yc.education.model.customer.CustomerDataMaintainCar;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 15:38 2018-08-31
 */
public interface ICustomerDataMaintainCarService extends IService<CustomerDataMaintainCar>{

    /**
     * 查询所有
     * @param brand
     * @return
     */
    List<CustomerDataMaintainCar> listCustomerDataMaintainCar(String brand,int page,int rows);

    /**
     * 通过客户资料id查询车床信息
     * @return
     */
    List<CustomerDataMaintainCar> listCustomerDataMaintainCarByMaintainId(long maintainId);

    /**
     * 通过客户资料id删除车床信息
     * @param maintainId
     * @return
     */
    int deleteCustomerDataMaintainCarByMaintainId(long maintainId);
}
