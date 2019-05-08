package com.yc.education.service.customer;


import com.yc.education.model.customer.CustomerDataMaintainQuestion;
import com.yc.education.service.IService;


import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 15:38 2018-08-31
 */
public interface ICustomerDataMaintainQuestionService extends IService<CustomerDataMaintainQuestion>{
    /**
     * 查询所有
     * @param content
     * @return
     */
    List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestion(String content,int page,int rows);

    /**
     * 根据客户需求商品id查询
     * @param maintainId
     * @return
     */
    List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestionByCustomerDemandId(long maintainId);

    /**
     * 根据类型查询客户需求商品
     * @param maintainId
     * @return
     */
    List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestionByType( long maintainId, int type);

    /**
     * 根据客户需求商品id删除
     * @param maintainId
     * @return
     */
    int deleteCustomerDataMaintainQuestionByCustomerDemandId( long maintainId);

}
