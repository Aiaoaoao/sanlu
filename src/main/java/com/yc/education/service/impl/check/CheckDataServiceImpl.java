package com.yc.education.service.impl.check;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.check.CheckDataMapper;
import com.yc.education.model.check.CheckData;
import com.yc.education.service.check.CheckDataService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CheckDataServiceImpl
 * @Description TODO 考勤资料结转
 * @Author QuZhangJing
 * @Date 2019/2/20 16:32
 * @Version 1.0
 */
@Service("CheckDataService")
public class CheckDataServiceImpl extends BaseService<CheckData> implements CheckDataService {

    @Autowired
    private CheckDataMapper checkDataMapper;

    @Override
    public List<CheckData> findCheckData() {
        try {
            return checkDataMapper.findCheckData();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckData> findCheckData(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return checkDataMapper.findCheckData();
        } catch (Exception e) {
            return null;
        }
    }


}
