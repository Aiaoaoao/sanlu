package com.yc.education.service.impl.customer;

import com.yc.education.mapper.customer.RemarkMapper;
import com.yc.education.model.customer.Remark;
import com.yc.education.service.customer.IRemarkService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 10:33 2018-08-24
 */
@Service
public class RemarkServiceImpl extends BaseService<Remark> implements IRemarkService {

    @Autowired
    RemarkMapper mapper;

    @Override
    public List<Remark> listCustomerRemarkByCustomerId(long customerid) {
        try {
            return mapper.listCustomerRemarkByCustomerId(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Remark> listRemark(long otherid, String type) {
        try {
            return mapper.listRemark(otherid,type);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteRemark(long otherid, String type) {
        try {
            return mapper.deleteRemark(otherid, type);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteCustomerRemarkByCustomerId(long customerid) {
        try {
            return mapper.deleteCustomerRemarkByCustomerId(customerid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


}
