package com.yc.education.service.impl.basic;

import com.yc.education.mapper.basic.SupplierPurchaserMapper;
import com.yc.education.model.basic.SupplierPurchaser;
import com.yc.education.service.basic.SupplierPurchaserService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SupplierPurchaseServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/28 13:42
 * @Version 1.0
 */
@Service("SupplierPurchaseService")
public class SupplierPurchaseServiceImpl extends BaseService<SupplierPurchaser> implements SupplierPurchaserService {

    @Autowired
    private SupplierPurchaserMapper supplierPurchaserMapper;

    @Override
    public List<SupplierPurchaser> selectSupplierPurchaseBySupplierid(long supplierid) {
        try {
            return supplierPurchaserMapper.selectSupplierPurchaseBySupplierid(supplierid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteSupplierPurchase(long supplierid) {
        try {
            return supplierPurchaserMapper.deleteSupplierPurchase(supplierid);
        }catch (Exception e){
            return 0;
        }
    }
}
