package com.yc.education.service.impl.customer;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.customer.CustomerDataMaintainMapper;
import com.yc.education.mapper.customer.CustomerDataMaintainProductionMapper;
import com.yc.education.model.customer.CustomerDataMaintain;
import com.yc.education.model.customer.CustomerDataMaintainProduction;
import com.yc.education.service.customer.ICustomerDataMaintainProductionService;
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
public class CustomerDataMaintainProductionServiceImpl extends BaseService<CustomerDataMaintainProduction> implements ICustomerDataMaintainProductionService {

    @Autowired
    CustomerDataMaintainProductionMapper mapper;


    @Override
    public List<CustomerDataMaintainProduction> listCustomerDataMaintainProduction(int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listCustomerDataMaintainProduction();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerDataMaintainProduction> listCustomerDataMaintainProductionByMaintainId(long maintainId) {
        try {
            return mapper.listCustomerDataMaintainProductionByMaintainId(maintainId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteCustomerDataMaintainProductionByMaintainId(long maintainId) {
        try {
            return mapper.deleteCustomerDataMaintainProductionByMaintainId(maintainId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
