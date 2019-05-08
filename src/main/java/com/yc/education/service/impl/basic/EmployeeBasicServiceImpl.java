package com.yc.education.service.impl.basic;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.basic.EmployeeBasicMapper;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.service.basic.EmployeeBasicService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeBasicServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/31 16:25
 * @Version 1.0
 */
@Service
public class EmployeeBasicServiceImpl extends BaseService<EmployeeBasic> implements EmployeeBasicService {

    @Autowired
    private EmployeeBasicMapper employeeBasicMapper;

    @Override
    public List<EmployeeBasic> listEmployeeBasic(String text, String stop, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return employeeBasicMapper.listEmployeeBasic(text,stop);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String selectMaxIdnum() {
        return employeeBasicMapper.selectMaxIdnum();
    }

    @Override
    public List<EmployeeBasic> selectEmployeeBasic(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return employeeBasicMapper.selectEmployeeBasic();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<EmployeeBasic> selectEmployeeBasic() {
        return employeeBasicMapper.selectEmployeeBasic();
    }

    @Override
    public List<EmployeeBasic> selectEmployeeBasic(String idnumAndName, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return employeeBasicMapper.selectEmployeeBasicByIdnumAndName(idnumAndName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<EmployeeBasic> selectEmployeeBasic(String idnumAndName) {
        try {
            return employeeBasicMapper.selectEmployeeBasicByIdnumAndName(idnumAndName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<EmployeeBasic> selectEmployeeBasicByIdnum(String idnum) {
        try {
            return employeeBasicMapper.selectEmployeeBasicByIdnum(idnum);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<EmployeeBasic> selectEmployeeBasicByIdnum(String idnum, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return employeeBasicMapper.selectEmployeeBasicByIdnum(idnum);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public EmployeeBasic selectEmployeeBasicByIsnum(String idnum) {
        return employeeBasicMapper.selectEmployeeBasicByIsnum(idnum);
    }

    @Override
    public List<EmployeeBasic> selectEmployeeBasicNotStop(int types) {
        return employeeBasicMapper.selectEmployeeBasicNotStop(types);
    }

    @Override
    public List<EmployeeBasic> selectEmployeeBasicNotStop(int types,int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return employeeBasicMapper.selectEmployeeBasicNotStop(types);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public EmployeeBasic selectEmployeeLogin(String idnum, String name, String password) {
        try {
            return employeeBasicMapper.selectEmployeeLogin(idnum, name, password);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public EmployeeBasic selectEmployeeByUname(String uname) {
        try {
            return employeeBasicMapper.selectEmployeeByUname(uname);
        } catch (Exception e) {
            return null;
        }
    }
}
