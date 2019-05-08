package com.yc.education.service.customer;

import com.yc.education.model.customer.CustomerDataMaintain;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 15:38 2018-08-31
 */
public interface ICustomerDataMaintainService extends IService<CustomerDataMaintain>{

    /**
     * 查询所有
     * @return
     */
    List<CustomerDataMaintain> listCustomerDataMaintain(String text,int page,int rows);

}
