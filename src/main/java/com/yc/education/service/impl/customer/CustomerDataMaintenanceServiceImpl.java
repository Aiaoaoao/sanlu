package com.yc.education.service.impl.customer;

import com.yc.education.mapper.customer.CustomerDataMaintenanceMapper;
import com.yc.education.model.customer.CustomerDataMaintenance;
import com.yc.education.service.customer.ICustomerDataMaintenanceService;
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
public class CustomerDataMaintenanceServiceImpl extends BaseService<CustomerDataMaintenance> implements ICustomerDataMaintenanceService{

    @Autowired
    CustomerDataMaintenanceMapper mapper;

    @Override
    public List<CustomerDataMaintenance> listCustomerDataMaintenanceByCustomerId(long customerid) {
        try {
            return mapper.listCustomerDataMaintenanceByCustomerId(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int deleteCustomerDataMaintenanceByCustomerId(long customerid) {
        try {
            return mapper.deleteCustomerDataMaintenanceByCustomerId(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


}
