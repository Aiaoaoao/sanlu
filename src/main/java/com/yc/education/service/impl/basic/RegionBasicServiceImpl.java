package com.yc.education.service.impl.basic;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.basic.RegionBasicMapper;
import com.yc.education.model.basic.RegionBasic;
import com.yc.education.service.basic.RegionBasicService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RegionBasicServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/6 20:01
 * @Version 1.0
 */
@Service("RegionBasicService")
public class RegionBasicServiceImpl extends BaseService<RegionBasic> implements RegionBasicService {

    @Autowired
    private RegionBasicMapper regionBasicMapper;

    @Override
    public List<RegionBasic> selectProductBasic(int pageNum,int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return regionBasicMapper.selectProductBasic();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<RegionBasic> selectProductBasic() {
        return regionBasicMapper.selectProductBasic();
    }

    @Override
    public List<RegionBasic> selectProductBasic(String orderAndName, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return regionBasicMapper.selectProductBasicByOrderAndName(orderAndName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<RegionBasic> selectProductBasic(String orderAndName) {
        try {
            return regionBasicMapper.selectProductBasicByOrderAndName(orderAndName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RegionBasic selectProductBasicByIsnum(String isnum) {
        return regionBasicMapper.selectProductBasicByIsnum(isnum);
    }
}
