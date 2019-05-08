package com.yc.education.service.purchase;

import com.yc.education.model.purchase.ForceOrder;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName ForceOrderService
 * @Description TODO 强制结案
 * @Author QuZhangJing
 * @Date 2018/10/15 15:01
 * @Version 1.0
 */
public interface ForceOrderService extends IService<ForceOrder> {

    //查询强制结案
    List<ForceOrder> findForceOrder();
    //查询强制结案PageInfo
    List<ForceOrder> findForceOrder(int pageNum,int pageSize);
    //根据采购订单查询该订单下强制结案的订单
    List<ForceOrder> findForceOrderByPurchaseOrder(String purchaseOrder);

    //交易单据
    List<ForceOrder> findForceOrderBySupplier(String supplier,String startTime,String endTime);

    List<ForceOrder> findForceOrderBySupplier(String supplier,String startTime,String endTime,int pageNum,int pageSize);


}
