package com.yc.education.service.basic;

import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SupplierBasicService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/22 14:20
 * @Version 1.0
 */
public interface SupplierBasicService extends IService<SupplierBasic> {


    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();

    /**
     * 查询所有供应商 不包括暂停使用供应商
     * @return
     */
    List<SupplierBasic> selectSupplierBasic(int pageNum,int pageSize);

    /**
     * 查询所有供应商 不包括暂停使用供应商
     * @return
     */
    List<SupplierBasic> selectSupplierBasic();


    /**
     * 查询所有供应商 不包括暂停使用供应商
     * @return
     */
    List<SupplierBasic> selectSupplierBasic(String idnumOrName,int pageNum,int pageSize);

    /**
     * 查询所有供应商 不包括暂停使用供应商
     * @return
     */
    List<SupplierBasic> selectSupplierBasic(String idnumOrName);



    /**
     * 根据编号查询供应商
     * @param idnum
     * @return
     */
    SupplierBasic selectSupplierBasicByIsnum(String idnum);


    /**
     * 查询所有供应商  包括暂停使用供应商
     * @return
     */
    List<SupplierBasic> selectSupplierBasicNotSotp(int types);

    /**
     * 查询所有供应商  包括暂停使用供应商
     * @return
     */
    List<SupplierBasic> selectSupplierBasicNotSotp(int types,int pageNum,int pageSize);


    /**
     * 询所有公司
     * @param types 0、未暂停使用  1、暂停使用
     * @return
     */
    List<SupplierBasic> selectSupplierBasicByIsStop(int types);


    /**
     * 询所有公司
     * @param types 0、未暂停使用  1、暂停使用
     * @return
     */
    List<SupplierBasic> selectSupplierBasicByIsStop(int types,int pageNum,int pageSize);



}
