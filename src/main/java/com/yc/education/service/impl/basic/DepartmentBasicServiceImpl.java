package com.yc.education.service.impl.basic;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.basic.DepartmentBasicMapper;
import com.yc.education.model.basic.DepartmentBasic;
import com.yc.education.service.basic.DepartmentBasicService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DepartmentBasicServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/4 10:33
 * @Version 1.0
 */
@Service("DepartmentBasicService")
public class DepartmentBasicServiceImpl extends BaseService<DepartmentBasic> implements DepartmentBasicService {

    @Autowired
    private DepartmentBasicMapper departmentBasicMapper;

    @Override
    public String selectMaxIdnum() {
        return departmentBasicMapper.selectMaxIdnum();
    }

    @Override
    public List<DepartmentBasic> selectDepartmentBasic(int pageNum, int pageSzie) {
        try {
            PageHelper.startPage(pageNum,pageSzie);
            return departmentBasicMapper.selectDepartmentBasic("");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DepartmentBasic selectDepartmentBasicByIsnum(String idnum) {
        return departmentBasicMapper.selectDepartmentBasicByIsnum(idnum);
    }

    @Override
    public List<DepartmentBasic> selectDepartmentBasicByParentId(long parentid) {
        return departmentBasicMapper.selectDepartmentBasicByParentId(parentid);
    }

    @Override
    public List<DepartmentBasic> selectDepartmentBasic() {
        return departmentBasicMapper.selectDepartmentBasic("");
    }

    @Override
    public DepartmentBasic selectDepartmentBasicByDepName(String depname) {
        return departmentBasicMapper.selectDepartmentBasicByDepName(depname);
    }

    @Override
    public List<DepartmentBasic> selectDepartmentBasic(String orderAndName, int pageNum, int pageSzie) {
        try {
            PageHelper.startPage(pageNum,pageSzie);
            return departmentBasicMapper.selectDepartmentBasic(orderAndName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DepartmentBasic> selectDepartmentBasic(String orderAndName) {
        return departmentBasicMapper.selectDepartmentBasic(orderAndName);
    }
}
