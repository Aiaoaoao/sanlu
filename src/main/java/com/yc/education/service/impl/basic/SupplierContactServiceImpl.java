package com.yc.education.service.impl.basic;

import com.yc.education.mapper.basic.SupplierContactMapper;
import com.yc.education.model.basic.SupplierContact;
import com.yc.education.service.basic.SupplierContactService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SupplierContactServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/28 12:04
 * @Version 1.0
 */

@Service("SupplierContactService")
public class SupplierContactServiceImpl extends BaseService<SupplierContact> implements SupplierContactService {

    @Autowired
    private SupplierContactMapper supplierContactMapper;

    @Override
    public List<SupplierContact> selectSupplierContactBySupplierid(long suppliserid) {
        try {
            return supplierContactMapper.selectSupplierContactBySupplierid(suppliserid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteSupplierContactBySupplierid(long suppliserid) {
        try {
            return supplierContactMapper.deleteSupplierContactBySupplierid(suppliserid);
        }catch (Exception e){
            return 0;
        }
    }


}
