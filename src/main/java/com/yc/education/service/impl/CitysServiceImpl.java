package com.yc.education.service.impl;

import com.yc.education.mapper.CitysMapper;
import com.yc.education.model.Citys;
import com.yc.education.service.CitysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CitysServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/14 11:19
 * @Version 1.0
 */
@Service("CitysService")
public class CitysServiceImpl extends BaseService<Citys> implements CitysService {

    @Autowired
    private CitysMapper citysMapper;

    @Override
    public List<Citys> selectCitysByFatherCode(String fatherCode) {
        try {
            return citysMapper.selectCitysByFatherCode(fatherCode);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Citys selectCitysByAddrName(String name,String type) {
        try {
            return citysMapper.selectCitysByAddrName(name,type);
        } catch (Exception e) {
            return null;
        }
    }
}
