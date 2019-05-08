package com.yc.education.mapper.customer;

import com.yc.education.model.customer.CustomerDataMaintainQuestion;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomerDataMaintainQuestionMapper extends MyMapper<CustomerDataMaintainQuestion> {
    /**
     * 查询所有
     * @param content
     * @return
     */
    List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestion(@Param("content") String content);

    /**
     * 根据客户需求商品id查询
     * @param maintainId
     * @return
     */
    List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestionByCustomerDemandId(@Param("maintainId") long maintainId);

    /**
     * 根据类型查询客户需求商品
     * @param maintainId
     * @return
     */
    List<CustomerDataMaintainQuestion> listCustomerDataMaintainQuestionByType(@Param("maintainId") long maintainId,@Param("type") int type);

    /**
     * 根据客户需求商品id删除
     * @param maintainId
     * @return
     */
    int deleteCustomerDataMaintainQuestionByCustomerDemandId(@Param("maintainId") long maintainId);
}