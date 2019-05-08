package com.yc.education.mapper.customer;

import com.yc.education.model.customer.CustomerDataMaintainProduction;
import com.yc.education.model.customer.CustomerDataMaintainProduction;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomerDataMaintainProductionMapper extends MyMapper<CustomerDataMaintainProduction> {
    /**
     * 查询所有
     * @return
     */
    List<CustomerDataMaintainProduction> listCustomerDataMaintainProduction();

    /**
     * 通过客户资料id查询详情
     * @return
     */
    List<CustomerDataMaintainProduction> listCustomerDataMaintainProductionByMaintainId(@Param("maintainId") long maintainId);

    /**
     * 通过客户资料id删除详情
     * @param maintainId
     * @return
     */
    int deleteCustomerDataMaintainProductionByMaintainId(@Param("maintainId") long maintainId);
}