package com.yc.education.service.impl.customer;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.customer.CustomerDataMaintainMapper;
import com.yc.education.mapper.customer.CustomerDataMaintainQuestionMapper;
import com.yc.education.model.customer.CustomerDataMaintain;
import com.yc.education.model.customer.CustomerDataMaintainQuestion;
import com.yc.education.service.customer.ICustomerDataMaintainQuestionService;
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
public class CustomerDataMaintainQuestionServiceImpl extends BaseService<CustomerDataMaintainQuestion> implements ICustomerDataMaintainQuestionService {

    @Autowired
    CustomerDataMaintainQuestionMapper mapper;



    @Override
    public List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestion(String content, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listCustomerDataMaintainQuestion(content);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestionByCustomerDemandId(long maintainId) {
        try {
            return mapper.listCustomerDataMaintainQuestionByCustomerDemandId(maintainId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestionByType(long maintainId, int type) {
        try {
            return mapper.listCustomerDataMaintainQuestionByType(maintainId,type);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteCustomerDataMaintainQuestionByCustomerDemandId(long maintainId) {
        try {
            return mapper.deleteCustomerDataMaintainQuestionByCustomerDemandId(maintainId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
