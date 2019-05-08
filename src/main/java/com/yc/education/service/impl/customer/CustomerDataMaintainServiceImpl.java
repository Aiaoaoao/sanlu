package com.yc.education.service.impl.customer;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.customer.CustomerDataMaintainMapper;
import com.yc.education.model.customer.CustomerDataMaintain;
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
public class CustomerDataMaintainServiceImpl extends BaseService<CustomerDataMaintain> implements ICustomerDataMaintainService {

    @Autowired
    CustomerDataMaintainMapper mapper;

    @Override
    public List<CustomerDataMaintain> listCustomerDataMaintain(String text, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listCustomerDataMaintain(text);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
