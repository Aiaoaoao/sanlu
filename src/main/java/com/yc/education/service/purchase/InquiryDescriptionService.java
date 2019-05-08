package com.yc.education.service.purchase;

import com.yc.education.model.purchase.InquiryDescription;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName InquiryDescriptionService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/10/9 9:25
 * @Version 1.0
 */
public interface InquiryDescriptionService extends IService<InquiryDescription> {

    //根据询价单编号和类型编号查询 询价单的备注及说明
    List<InquiryDescription> findInquiryDescription(long inquiryid,int type);

}
