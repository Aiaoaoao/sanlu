package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.PurchaseDescription;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO
 *@Author QuZhangJing
 *@Date 10:08  2018/12/13
 *@Version 1.0
 */
public interface PurchaseDescriptionMapper extends MyMapper<PurchaseDescription> {

    //根据询价单编号和类型编号查询 询价单的备注及说明
    List<PurchaseDescription> findPurchaseDescription(@Param("purchaseid") long purchaseid, @Param("type")int type);

}