package com.yc.education.service.impl.basic;

import com.yc.education.mapper.basic.TransportContactMapper;
import com.yc.education.model.basic.TransportContact;
import com.yc.education.service.basic.TransportContactService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TransportContactServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/18 15:47
 * @Version 1.0
 */
@Service("TransportContactService")
public class TransportContactServiceImpl extends BaseService<TransportContact> implements TransportContactService {

    @Autowired
    private TransportContactMapper transportContactMapper;

    @Override
    public List<TransportContact> selectTransportContactBySupplierid(long suppliserid) {
        try {
             return transportContactMapper.selectTransportContactBySupplierid(suppliserid);
        } catch (Exception e) {
            return null;
        }
    }
}
