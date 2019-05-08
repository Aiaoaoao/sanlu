package com.yc.education.service.basic;

import com.yc.education.model.basic.TransportContact;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName TransportContactService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/18 15:46
 * @Version 1.0
 */
public interface TransportContactService extends IService<TransportContact> {

    /**
     * 根据运输商编号查询联系人
     * @param suppliserid 供应商自增编号
     * @return
     */
    List<TransportContact> selectTransportContactBySupplierid(long suppliserid);



}
