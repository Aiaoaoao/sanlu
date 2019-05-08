package com.yc.education.service.impl.basic;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.basic.TransportBasicMapper;
import com.yc.education.model.basic.TransportBasic;
import com.yc.education.service.basic.TransportBasicService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TransportBasicServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/18 14:55
 * @Version 1.0
 */
@Service("TransportBasicService")
public class TransportBasicServiceImpl extends BaseService<TransportBasic> implements TransportBasicService {

    @Autowired
    private TransportBasicMapper transportBasicMapper;


    @Override
    public String selectMaxIdnum() {
    return transportBasicMapper.selectMaxIdnum();
    }

    @Override
    public List<TransportBasic> selectTransportBasic(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return transportBasicMapper.selectTransportBasic();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TransportBasic> selectTransportBasic() {
        try {
            return transportBasicMapper.selectTransportBasic();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TransportBasic selectTransportBasicByIsnum(String idnum) {
        try {
            return transportBasicMapper.selectTransportBasicByIsnum(idnum);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TransportBasic> selectTransportBasicNotSotp(int types) {
        try {
            return transportBasicMapper.selectTransportBasicNotSotp(types);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TransportBasic> selectTransportBasicNotSotp(int types,int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return transportBasicMapper.selectTransportBasicNotSotp(types);
        } catch (Exception e) {
            return null;
        }
    }
}
