package com.yc.education.service.impl.purchase;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.purchase.ForceOrderMapper;
import com.yc.education.model.purchase.ForceOrder;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.ForceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ForceOrderServiceImpl
 * @Description TODO  强制结案
 * @Author QuZhangJing
 * @Date 2018/10/15 15:06
 * @Version 1.0
 */
@Service("ForceOrderService")
public class ForceOrderServiceImpl extends BaseService<ForceOrder> implements ForceOrderService {

    @Autowired
    private ForceOrderMapper forceOrderMapper;

    @Override
    public List<ForceOrder> findForceOrder() {
        try {
            return forceOrderMapper.findForceOrder();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ForceOrder> findForceOrder(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return forceOrderMapper.findForceOrder();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ForceOrder> findForceOrderByPurchaseOrder(String purchaseOrder) {
        try {
            return forceOrderMapper.findForceOrderByPurchaseOrder(purchaseOrder);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ForceOrder> findForceOrderBySupplier(String supplier, String startTime, String endTime) {
        try {
            return forceOrderMapper.findForceOrderBySupplier(supplier, startTime, endTime);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ForceOrder> findForceOrderBySupplier(String supplier, String startTime, String endTime, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return forceOrderMapper.findForceOrderBySupplier(supplier, startTime, endTime);
        }catch (Exception e){
            return null;
        }
    }
}
