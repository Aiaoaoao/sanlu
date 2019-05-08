package com.yc.education.service.impl.purchase;

import com.yc.education.mapper.purchase.PurchaseDescriptionMapper;
import com.yc.education.model.purchase.PurchaseDescription;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.PurchaseDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PurchasedescriptionServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/12/13 10:15
 * @Version 1.0
 */
@Service("PurchasedescriptionService")
public class PurchasedescriptionServiceImpl extends BaseService<PurchaseDescription> implements PurchaseDescriptionService {

    @Autowired
    private PurchaseDescriptionMapper purchaseDescriptionMapper;

    @Override
    public List<PurchaseDescription> findPurchaseDescription(long purchaseid, int type) {
        try {
            return purchaseDescriptionMapper.findPurchaseDescription(purchaseid,type);
        }catch (Exception e){
            return null;
        }
    }
}
