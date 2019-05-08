package com.yc.education.service.basic;

import com.yc.education.model.basic.TransportPurchaser;
import com.yc.education.service.IService;
import com.yc.education.service.impl.BaseService;

import java.util.List;

/**
 * @ClassName TransportPurchaseService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/18 15:54
 * @Version 1.0
 */
public interface TransportPurchaseService extends IService<TransportPurchaser>{


    /**
     * 根据供应商编号查询采购负责人
     * @param supplierid 供应商编号
     * @return
     */
    List<TransportPurchaser> selectTransportPurchaseBySupplierid(long supplierid);


}
