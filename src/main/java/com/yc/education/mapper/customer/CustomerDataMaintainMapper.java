package com.yc.education.mapper.customer;


import com.yc.education.model.customer.CustomerDataMaintain;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomerDataMaintainMapper extends MyMapper<CustomerDataMaintain> {

    /**
     * 查询所有
     * @return
     */
    List<CustomerDataMaintain> listCustomerDataMaintain(@Param("text") String text);

}