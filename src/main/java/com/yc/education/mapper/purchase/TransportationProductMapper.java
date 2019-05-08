package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.TransportationProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransportationProductMapper extends MyMapper<TransportationProduct> {



    //根据在途库存编号查询在途产品
    List<TransportationProduct> findTransportationProductByParentid(@Param("parentid")long parentid);

    //根据采购订单编号查询在途产品
    List<TransportationProduct> findTransportarionProductByPurchaseOrders(@Param("order")String order);


    //根据采购订单编号和产品名称查询在途产品
    List<TransportationProduct> findTransportarionProductByPurchaseOrdersAndProNum(@Param("order")String order,@Param("pronum")String pronum);


    //根据在途库存编号 和 产品编号 查询在途产品
    TransportationProduct findTransportationProductByTransportationInventoryOrder(@Param("boxnum")String boxnum,@Param("pronum")String pronum);

}