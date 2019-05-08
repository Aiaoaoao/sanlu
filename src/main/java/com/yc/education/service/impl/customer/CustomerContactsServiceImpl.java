package com.yc.education.service.impl.customer;

import com.yc.education.mapper.customer.CustomerContactsMapper;
import com.yc.education.model.customer.CustomerContacts;
import com.yc.education.service.customer.ICustomerContactsService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 10:33 2018-08-24
 */
@Service
public class CustomerContactsServiceImpl extends BaseService<CustomerContacts> implements ICustomerContactsService{

    @Autowired
    CustomerContactsMapper mapper;

    @Override
    public int setCustomerDefaultInvoice(long id) {
        try {
            return mapper.setCustomerDefaultInvoice(id);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int clearCustomerDefaultInvoice(long customerid) {
        try {
            return mapper.clearCustomerDefaultInvoice(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<CustomerContacts> listCustomerContactsByCustomerId(long customerid) {
        try {
            return mapper.listCustomerContactsByCustomerId(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteCustomerContactsByCustomerId(long customerid) {
        try {
            return mapper.deleteCustomerContactsByCustomerId(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
