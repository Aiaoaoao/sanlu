package com.yc.education.service.impl.basic;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.basic.SupplierBasicMapper;
import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.service.basic.SupplierBasicService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SupplierBasicServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/22 14:21
 * @Version 1.0
 */
@Service("SupplierBasicService")
public class SupplierBasicServiceImpl extends BaseService<SupplierBasic> implements SupplierBasicService {

    @Autowired
    private SupplierBasicMapper supplierBasicMapper;

    @Override
    public String selectMaxIdnum() {
        return supplierBasicMapper.selectMaxIdnum();
    }

    @Override
    public List<SupplierBasic> selectSupplierBasic(int pageNum, int pageSize) {
       try {
           PageHelper.startPage(pageNum,pageSize);
           return supplierBasicMapper.selectSupplierBasic();
       }catch (Exception e){
           return null;
       }
    }

    @Override
    public List<SupplierBasic> selectSupplierBasic() {
        return supplierBasicMapper.selectSupplierBasic();
    }

    @Override
    public List<SupplierBasic> selectSupplierBasic(String idnumOrName, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return supplierBasicMapper.selectSupplierBasicByIdnumOrName(idnumOrName);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SupplierBasic> selectSupplierBasic(String idnumOrName) {
        return supplierBasicMapper.selectSupplierBasicByIdnumOrName(idnumOrName);
    }

    @Override
    public SupplierBasic selectSupplierBasicByIsnum(String idnum) {
        return supplierBasicMapper.selectSupplierBasicByIsnum(idnum);
    }

    @Override
    public List<SupplierBasic> selectSupplierBasicNotSotp(int types) {
        return supplierBasicMapper.selectSupplierBasicNotSotp(types);
    }

    @Override
    public List<SupplierBasic> selectSupplierBasicNotSotp(int types,int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return supplierBasicMapper.selectSupplierBasicNotSotp(types);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SupplierBasic> selectSupplierBasicByIsStop(int types) {
        try {
            return supplierBasicMapper.selectSupplierBasicByIsStop(types);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SupplierBasic> selectSupplierBasicByIsStop(int types, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return supplierBasicMapper.selectSupplierBasicByIsStop(types);
        } catch (Exception e) {
            return null;
        }
    }
}
