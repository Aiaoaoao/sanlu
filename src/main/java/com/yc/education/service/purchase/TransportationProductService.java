package com.yc.education.service.purchase;

import com.yc.education.model.purchase.TransportationProduct;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName TransportationProductService
 * @Description TODO 在途产品
 * @Author QuZhangJing
 * @Date 2018/10/17 11:25
 * @Version 1.0
 */
public interface TransportationProductService extends IService<TransportationProduct> {


    //根据在途库存编号查询在途产品
    List<TransportationProduct> findTransportationProductByParentid(long parentid);
    
    //根据采购订单编号查询在途产品
    List<TransportationProduct> findTransportarionProductByPurchaseOrders(String order);
    //根据采购订单编号和产品名称查询在途产品
    List<TransportationProduct> findTransportarionProductByPurchaseOrders(String order,String pronum);
    //根据在途库存编号 和 产品编号 查询在途产品
    TransportationProduct findTransportationProductByTransportationInventoryOrder(String boxnum,String pronum);



}
