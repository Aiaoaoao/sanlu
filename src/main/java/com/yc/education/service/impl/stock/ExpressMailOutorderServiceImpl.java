package com.yc.education.service.impl.stock;

import com.yc.education.mapper.stock.ExpressMailOutorderMapper;
import com.yc.education.model.stock.ExpressMailOutorder;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.ExpressMailOutorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ExpressMailOutorderServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/4/28 22:51
 * @Version 1.0
 */
@Service("ExpressMailOutorderService")
public class ExpressMailOutorderServiceImpl extends BaseService<ExpressMailOutorder> implements ExpressMailOutorderService {

    @Autowired
    private ExpressMailOutorderMapper expressMailOutorderMapper;

    @Override
    public List<ExpressMailOutorder> findExpressMailOutorders(long mailid) {
        try {
            return expressMailOutorderMapper.findExpressMailOutorders(mailid);
        } catch (Exception e) {
            return null;
        }
    }
}
