package com.yc.education.mapper.customer;



import com.yc.education.model.customer.CustomerDataMaintainCar;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomerDataMaintainCarMapper extends MyMapper<CustomerDataMaintainCar> {
    /**
     * 查询所有
     * @param brand
     * @return
     */
    List<CustomerDataMaintainCar> listCustomerDataMaintainCar(@Param("brand") String brand);

    /**
     * 通过客户资料id查询车床信息
     * @return
     */
    List<CustomerDataMaintainCar> listCustomerDataMaintainCarByMaintainId(@Param("maintainId") long maintainId);

    /**
     * 通过客户资料id删除车床信息
     * @param maintainId
     * @return
     */
    int deleteCustomerDataMaintainCarByMaintainId(@Param("maintainId") long maintainId);
}