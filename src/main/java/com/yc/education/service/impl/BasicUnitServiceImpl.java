package com.yc.education.service.impl;

import com.yc.education.mapper.BasicUnitMapper;
import com.yc.education.model.BasicUnit;
import com.yc.education.service.BasicUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BasicUnitServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/20 17:47
 * @Version 1.0
 */
@Service("BasicUnitService")
public class BasicUnitServiceImpl extends BaseService<BasicUnit> implements BasicUnitService {

    @Autowired
    private BasicUnitMapper basicUnitMapper;

    @Override
    public List<BasicUnit> findBasicUnitAll() {
        try {
            return basicUnitMapper.findBasicUnitAll();
        } catch (Exception e) {
            return null;
        }
    }
}
