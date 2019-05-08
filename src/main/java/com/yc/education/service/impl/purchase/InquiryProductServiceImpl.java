package com.yc.education.service.impl.purchase;

import com.yc.education.mapper.purchase.InquiryProductMapper;
import com.yc.education.model.purchase.InquiryProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.InquiryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName InquiryProductServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/28 11:55
 * @Version 1.0
 */
@Service("InquiryProductService")
public class InquiryProductServiceImpl extends BaseService<InquiryProduct> implements InquiryProductService {

    @Autowired
    private InquiryProductMapper inquiryProductMapper;

    @Override
    public List<InquiryProduct> findInquiryProductByInquiryid(long inquiryid) {
        try {
            return inquiryProductMapper.findInquiryProductByInquiryid(inquiryid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Double findInquiryProductPriceSUM(long inquiryid) {
        try {
            return inquiryProductMapper.findInquiryProductPriceSUM(inquiryid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InquiryProduct> selectInquiryProdutByProductNameAndStartTimeAndEndTime(String productName, String startTime, String endTime) {
        try {
            return inquiryProductMapper.selectInquiryProdutByProductNameAndStartTimeAndEndTime(productName, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }
}
