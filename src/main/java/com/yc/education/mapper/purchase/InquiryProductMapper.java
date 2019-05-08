package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.InquiryProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InquiryProductMapper extends MyMapper<InquiryProduct> {

     //根据询价单查询询价单产品
    List<InquiryProduct> findInquiryProductByInquiryid(@Param("inquiryid")long inquiryid);

    Double findInquiryProductPriceSUM(@Param("inquiryid")long inquiryid);

    /**
     * 根据产品名称和时间筛选查询询价单
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<InquiryProduct> selectInquiryProdutByProductNameAndStartTimeAndEndTime(@Param("productName")String productName,@Param("startTime")String startTime,@Param("endTime")String endTime);

}