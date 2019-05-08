package com.yc.education.service.impl.check;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.check.CheckOrderMapper;
import com.yc.education.model.check.CheckOrder;
import com.yc.education.service.check.CheckOrderService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CheckOrderServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/22 16:42
 * @Version 1.0
 */
@Service("CheckOrderService")
public class CheckOrderServiceImpl extends BaseService<CheckOrder> implements CheckOrderService {

    @Autowired
    private CheckOrderMapper checkOrderMapper ;


    @Override
    public String selectMaxIdnum() {
        try {
            return checkOrderMapper.selectMaxIdnum();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckOrder> findCheckOrder() {
        try {
            return checkOrderMapper.findCheckOrder();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckOrder> findCheckOrder(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return checkOrderMapper.findCheckOrder();
        } catch (Exception e) {
            return null;
        }
    }
}
