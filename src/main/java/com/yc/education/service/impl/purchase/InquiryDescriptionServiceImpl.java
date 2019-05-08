package com.yc.education.service.impl.purchase;

import com.yc.education.mapper.purchase.InquiryDescriptionMapper;
import com.yc.education.model.purchase.InquiryDescription;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.InquiryDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName InquiryDescriptionServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/10/9 9:27
 * @Version 1.0
 */
@Service("InquiryDescriptionService")
public class InquiryDescriptionServiceImpl extends BaseService<InquiryDescription> implements InquiryDescriptionService {

    @Autowired
    private InquiryDescriptionMapper inquiryDescriptionMapper;

    @Override
    public List<InquiryDescription> findInquiryDescription(long inquiryid, int type) {
        try {
            return inquiryDescriptionMapper.findInquiryDescription(inquiryid,type);
        } catch (Exception e) {
            return null;
        }
    }
}
