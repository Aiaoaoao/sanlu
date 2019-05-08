package com.yc.education.service.basic;

import com.yc.education.model.basic.SupplierPurchaser;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName SupplierPurchaserService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/28 12:02
 * @Version 1.0
 */
public interface SupplierPurchaserService extends IService<SupplierPurchaser> {

    /**
     * 根据供应商编号查询采购负责人
     * @param supplierid 供应商编号
     * @return
     */
    List<SupplierPurchaser> selectSupplierPurchaseBySupplierid(long supplierid);


    //根据供应商编号删除采购负责人
    int deleteSupplierPurchase(long supplierid);


}
