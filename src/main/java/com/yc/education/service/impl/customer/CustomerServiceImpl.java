package com.yc.education.service.impl.customer;

import com.yc.education.mapper.customer.CustomerMapper;
import com.yc.education.model.customer.Customer;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/8/20 11:24
 */
@Service
public class CustomerServiceImpl extends BaseService<Customer> implements ICustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Override
    public List<Customer> listGeneralCustomer() {
        try {
            return  mapper.listGeneralCustomer();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Customer> listExistCustomer(String text,String stopUse,int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listExistCustomer(text,stopUse);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Customer> listCustomerAll(String state, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listCustomerAll(state);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Customer getCustomer(String code) {
        try {
            return  mapper.getCustomer(code);
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public int getCustomerCount() {
        try {
            return  mapper.getCustomerCount();
        }catch (Exception e){
            return 0;
        }
    }
}
