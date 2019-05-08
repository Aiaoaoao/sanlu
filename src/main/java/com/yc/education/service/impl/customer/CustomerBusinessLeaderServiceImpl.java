package com.yc.education.service.impl.customer;

import com.yc.education.mapper.customer.CustomerBusinessLeaderMapper;
import com.yc.education.model.customer.CustomerBusinessLeader;
import com.yc.education.service.customer.ICustomerBusinessLeaderService;
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
public class CustomerBusinessLeaderServiceImpl extends BaseService<CustomerBusinessLeader> implements ICustomerBusinessLeaderService{

    @Autowired
    CustomerBusinessLeaderMapper mapper;

    @Override
    public List<CustomerBusinessLeader> listCustomerBusinessLeaderByCustomerId(long customerid) {
        try {
            return mapper.listCustomerBusinessLeaderByCustomerId(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int deleteCustomerBusinessLeaderByCustomerId(long customerid) {
        try {
            return mapper.deleteCustomerBusinessLeaderByCustomerId(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


}
