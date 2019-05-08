package com.yc.education.service.purchase;

import com.yc.education.model.purchase.InquiryProduct;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName InquiryProductService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/28 11:53
 * @Version 1.0
 */
public interface InquiryProductService extends IService<InquiryProduct> {

    //根据询价单查询询价单产品
    List<InquiryProduct> findInquiryProductByInquiryid(long inquiryid);

    Double findInquiryProductPriceSUM(long inquiryid);

    /**
     * 根据产品名称和时间筛选查询询价单
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<InquiryProduct> selectInquiryProdutByProductNameAndStartTimeAndEndTime(String productName,String startTime,String endTime);

}
