package com.yc.education.mapper.customer;

import com.yc.education.model.customer.CustomerContacts;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CustomerContactsMapper extends MyMapper<CustomerContacts> {

    /**
     * 设置客户默认发票
     * @param id
     * @return
     */
    int setCustomerDefaultInvoice(@Param("id")long id);

    /**
     * 清除客户默认发票地址
     * @param customerid
     * @return
     */
    int clearCustomerDefaultInvoice(@Param("customerid")long customerid);

    /**
     * 查询某客户的联系人
     * @param customerid
     * @return
     */
    List<CustomerContacts> listCustomerContactsByCustomerId(@Param("customerid")long customerid);

    /**
     * 删除某个客户的所有联系人
     * @param customerid
     * @return
     */
    int deleteCustomerContactsByCustomerId(@Param("customerid") long customerid);
}