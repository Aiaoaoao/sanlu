package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.InquiryDescription;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InquiryDescriptionMapper extends MyMapper<InquiryDescription> {

    //根据询价单编号和类型编号查询 询价单的备注及说明
    List<InquiryDescription> findInquiryDescription(@Param("inquiryid")long inquiryid,@Param("type")int type);

}