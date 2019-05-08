package com.yc.education.service.impl.customer;

import com.yc.education.mapper.customer.CustomerShippingAddressMapper;
import com.yc.education.model.customer.CustomerShippingAddress;
import com.yc.education.service.customer.ICustomerShippingAddressService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/8/16 17:52
 */
@Service
public class CustomerShippingAddressServiceImpl extends BaseService<CustomerShippingAddress> implements ICustomerShippingAddressService {



    @Autowired
    private CustomerShippingAddressMapper mapper;

    @Override
    public List<CustomerShippingAddress> listAll(long customerid) {
        try {
            return mapper.listAll(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int updateAddressDefault(long id) {
        try {
            return mapper.updateAddressDefault(id);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateClearAddressDefault(long customerid) {
        try {
            return mapper.updateClearAddressDefault(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
