package com.yc.education.service.impl.purchase;

import com.yc.education.mapper.purchase.TransportationProductMapper;
import com.yc.education.model.purchase.TransportationProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.TransportationProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TransportationProductServiceImpl
 * @Description TODO 在途产品
 * @Author QuZhangJing
 * @Date 2018/10/17 11:28
 * @Version 1.0
 */
@Service("TransportationProductService")
public class TransportationProductServiceImpl extends BaseService<TransportationProduct> implements TransportationProductService {

    @Autowired
    private TransportationProductMapper transportationProductMapper;


    @Override
    public List<TransportationProduct> findTransportationProductByParentid(long parentid) {
        try {
            return transportationProductMapper.findTransportationProductByParentid(parentid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TransportationProduct> findTransportarionProductByPurchaseOrders(String order) {
        try {
            return transportationProductMapper.findTransportarionProductByPurchaseOrders(order);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TransportationProduct> findTransportarionProductByPurchaseOrders(String order, String pronum) {
        try {
            return transportationProductMapper.findTransportarionProductByPurchaseOrdersAndProNum(order, pronum);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TransportationProduct findTransportationProductByTransportationInventoryOrder(String boxnum, String pronum) {
        try {
            return transportationProductMapper.findTransportationProductByTransportationInventoryOrder(boxnum, pronum);
        } catch (Exception e) {
            return null;
        }
    }

}
