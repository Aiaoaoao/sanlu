package com.yc.education.mapper.purchase;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.yc.education.model.purchase.TransportationInventory;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransportationInventoryMapper extends MyMapper<TransportationInventory> {


    //查询所有在途库存
    List<TransportationInventory> findTransportationInventory();


    //根据装箱号查询所有在途库存
    List<TransportationInventory> findTransportationInventoryByOrder(@Param("orders")String orders);

    //根据装箱单号查询在途库存
    TransportationInventory findTransportationInventoryByBoxNum(@Param("boxnum")String boxnum);


}