package com.yc.education.service.impl.basic;

import com.yc.education.mapper.basic.RegionEmployeeMapper;
import com.yc.education.model.basic.RegionEmployee;
import com.yc.education.service.basic.RegionEmployeeService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RegionEmployeeServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/6 20:05
 * @Version 1.0
 */
@Service("RegionEmployeeService")
public class RegionEmployeeServiceImpl extends BaseService<RegionEmployee> implements RegionEmployeeService {

    @Autowired
    private RegionEmployeeMapper regionEmployeeMapper;

    @Override
    public List<RegionEmployee> selectRegionEmployeeByRegionid(long regionid) {
        return regionEmployeeMapper.selectRegionEmployeeByRegionid(regionid);
    }
}
