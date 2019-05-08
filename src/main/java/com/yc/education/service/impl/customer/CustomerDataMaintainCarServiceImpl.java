package com.yc.education.service.impl.customer;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.customer.CustomerDataMaintainCarMapper;
import com.yc.education.mapper.customer.CustomerDataMaintainMapper;
import com.yc.education.model.customer.CustomerDataMaintain;
import com.yc.education.model.customer.CustomerDataMaintainCar;
import com.yc.education.service.customer.ICustomerDataMaintainCarService;
import com.yc.education.service.customer.ICustomerDataMaintainService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 18:50 2018-08-31
 */
@Service
public class CustomerDataMaintainCarServiceImpl extends BaseService<CustomerDataMaintainCar> implements ICustomerDataMaintainCarService {

    @Autowired
    CustomerDataMaintainCarMapper mapper;



    @Override
    public List<CustomerDataMaintainCar> listCustomerDataMaintainCar(String brand, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listCustomerDataMaintainCar(brand);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerDataMaintainCar> listCustomerDataMaintainCarByMaintainId(long maintainId) {
        try {
            return mapper.listCustomerDataMaintainCarByMaintainId(maintainId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteCustomerDataMaintainCarByMaintainId(long maintainId) {
        try {
            return mapper.deleteCustomerDataMaintainCarByMaintainId(maintainId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
