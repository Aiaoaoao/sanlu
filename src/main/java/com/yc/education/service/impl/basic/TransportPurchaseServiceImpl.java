package com.yc.education.service.impl.basic;

import com.yc.education.mapper.basic.TransportPurchaserMapper;
import com.yc.education.model.basic.TransportPurchaser;
import com.yc.education.service.basic.TransportPurchaseService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TransportPurchaseServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/18 15:55
 * @Version 1.0
 */
@Service("TransportPurchaseService")
public class TransportPurchaseServiceImpl extends BaseService<TransportPurchaser> implements TransportPurchaseService {

    @Autowired
    private TransportPurchaserMapper transportPurchaserMapper;

    @Override
    public List<TransportPurchaser> selectTransportPurchaseBySupplierid(long supplierid) {
        try {
            return transportPurchaserMapper.selectTransportPurchaseBySupplierid(supplierid);
        } catch (Exception e) {
            return null;
        }
    }
}
