package com.yc.education.mapper.basic;

import com.yc.education.model.basic.TransportPurchaser;
import com.yc.education.util.MyMapper;

import java.util.List;

public interface TransportPurchaserMapper extends MyMapper<TransportPurchaser> {


    /**
     * 根据供应商编号查询采购负责人
     * @param supplierid 供应商编号
     * @return
     */
    List<TransportPurchaser> selectTransportPurchaseBySupplierid(long supplierid);




}