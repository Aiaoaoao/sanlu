package com.yc.education.service.purchase;

import com.yc.education.model.purchase.PurchaseDescription;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName PurchaseDescriptionService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/12/13 10:12
 * @Version 1.0
 */
public interface PurchaseDescriptionService extends IService<PurchaseDescription> {

    //根据询价单编号和类型编号查询 询价单的备注及说明
    List<PurchaseDescription> findPurchaseDescription(long purchaseid, int type);

}
