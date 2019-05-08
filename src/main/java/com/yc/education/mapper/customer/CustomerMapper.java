package com.yc.education.mapper.customer;

import com.yc.education.model.customer.Customer;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper extends MyMapper<Customer> {

    /**
     * 查询所有未被禁用的客户
     * @return
     */
    List<Customer> listGeneralCustomer();

    /**
     * 查询现有客户（小窗口）
     * @param stopUse 1：停用、0：正常
     * @return
     */
    List<Customer> listExistCustomer(@Param("text")String text,@Param("stopUse")String stopUse);

    /**
     * 查询所有客户
     * @param state 客户账户状态 （0、正常，1、删除）
     * @return
     */
    List<Customer> listCustomerAll(@Param("state")String state);

    /**
     * @Author BlueSky
     * @Description //TODO 查询客户
     * @Date 11:40 2018/8/20
     * @Param []
     * @return com.yc.education.model.customer.Customer
     **/
    Customer getCustomer(@Param("code")String code);


    /**
     * 统计客户数量
     * @return
     */
    int getCustomerCount();
}