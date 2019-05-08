package com.yc.education.service.basic;

import com.yc.education.model.basic.SupplierContact;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName SupplierContactService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/28 12:01
 * @Version 1.0
 */
public interface SupplierContactService extends IService<SupplierContact> {



    /**
     * 根据供应商编号查询联系人
     * @param suppliserid 供应商自增编号
     * @return
     */
    List<SupplierContact> selectSupplierContactBySupplierid(long suppliserid);



    //根据供应商删除
    int deleteSupplierContactBySupplierid(long suppliserid);



}
