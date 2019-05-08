package com.yc.education.service.customer;

import com.yc.education.model.customer.CustomerContacts;
import com.yc.education.service.IService;

import java.util.List;

/**
 * 客户基本资料-客户联系人
 * @author BlueSky
 * @Description:
 * @Date 15:16 2018-08-24
 */
public interface ICustomerContactsService extends IService<CustomerContacts>{

    /**
     * 设置客户默认发票
     * @param id
     * @return
     */
    int setCustomerDefaultInvoice(long id);

    /**
     * 清除客户默认发票地址
     * @param customerid
     * @return
     */
    int clearCustomerDefaultInvoice(long customerid);

    /**
     * 查询某客户的联系人
     * @param customerid
     * @return
     */
    List<CustomerContacts> listCustomerContactsByCustomerId(long customerid);

    /**
     * 删除某个客户的所有联系人
     * @param customerid
     * @return
     */
    int deleteCustomerContactsByCustomerId(long customerid);

}
