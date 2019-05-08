package com.yc.education.mapper.basic;

import com.yc.education.model.basic.TransportContact;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransportContactMapper extends MyMapper<TransportContact> {


    /**
     * 根据运输商编号查询联系人
     * @param suppliserid 供应商自增编号
     * @return
     */
    List<TransportContact> selectTransportContactBySupplierid(@Param("suppliserid") long suppliserid);



}