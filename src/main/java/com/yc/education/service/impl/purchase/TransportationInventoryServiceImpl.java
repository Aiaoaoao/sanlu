package com.yc.education.service.impl.purchase;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.purchase.TransportationInventoryMapper;
import com.yc.education.model.purchase.TransportationInventory;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.TransportationInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TransportationInventoryServiceImpl
 * @Description TODO 在途库存
 * @Author QuZhangJing
 * @Date 2018/10/17 11:26
 * @Version 1.0
 */
@Service("TransportationInventoryService")
public class TransportationInventoryServiceImpl extends BaseService<TransportationInventory> implements TransportationInventoryService {

    @Autowired
    private TransportationInventoryMapper transportationInventoryMapper;


    @Override
    public List<TransportationInventory> findTransportationInventory() {
        try {
            return transportationInventoryMapper.findTransportationInventory();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TransportationInventory> findTransportationInventory(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return transportationInventoryMapper.findTransportationInventory();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TransportationInventory> findTransportationInventory(String orders, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return transportationInventoryMapper.findTransportationInventoryByOrder(orders);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TransportationInventory findTransportationInventoryByBoxNum(String boxnum) {
        try {
            return transportationInventoryMapper.findTransportationInventoryByBoxNum(boxnum);
        } catch (Exception e) {
            return null;
        }
    }
}
