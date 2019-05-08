package com.yc.education.service.purchase;

import com.yc.education.model.purchase.TransportationInventory;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName TransportationInventoryService
 * @Description TODO 在途库存
 * @Author QuZhangJing
 * @Date 2018/10/17 11:23
 * @Version 1.0
 */
public interface TransportationInventoryService extends IService<TransportationInventory> {


    //查询所有在途库存
    List<TransportationInventory> findTransportationInventory();

    List<TransportationInventory> findTransportationInventory(int pageNum,int pageSize);

    List<TransportationInventory> findTransportationInventory(String orders,int pageNum,int pageSize);


    //根据装箱单号查询在途库存
    TransportationInventory findTransportationInventoryByBoxNum(String boxnum);


}
