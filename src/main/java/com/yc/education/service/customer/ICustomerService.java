package com.yc.education.service.customer;

import com.yc.education.model.customer.Customer;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/8/20 11:23
 */
public interface ICustomerService extends IService<Customer>{

    /**
     * 查询所有未被禁用的客户
     * @return
     */
    List<Customer> listGeneralCustomer();

    /**
     * 查询现有客户（小窗口）
     * @param stopUse 空：全部、0：正常
     * @return
     */
    List<Customer> listExistCustomer(String text,String stopUse,int page, int rows);

    /**
     * 查询所有客户
     * @param state  1：停用、0：正常
     * @param page 当前页
     * @param rows 行数
     * @return
     */
    List<Customer> listCustomerAll(String state,int page, int rows);

    /**
     * @Author BlueSky
     * @Description //TODO 查询客户
     * @Date 11:41 2018/8/20
     * @Param [code]
     * @return com.yc.education.model.customer.Customer
     **/
    Customer getCustomer(String code);


    /**
     * 统计客户数量
     * @return
     */
    int getCustomerCount();
}
