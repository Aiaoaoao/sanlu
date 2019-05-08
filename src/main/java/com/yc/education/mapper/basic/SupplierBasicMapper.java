package com.yc.education.mapper.basic;

import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 供应商基本资料
 *@Author QuZhangJing
 *@Date 14:02  2018/8/22
 *@Version 1.0
 */
public interface SupplierBasicMapper extends MyMapper<SupplierBasic> {


    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();


    /**
     * 查询所有公司
     * @return
     */
    List<SupplierBasic> selectSupplierBasic();

    /**
     * 查询所有公司
     * @return
     */
    List<SupplierBasic> selectSupplierBasicByIdnumOrName(@Param("idnumOrName")String idnumOrName);

    /**
     * 询所有公司
     * @param types 0、未暂停使用  1、暂停使用
     * @return
     */
    List<SupplierBasic> selectSupplierBasicByIsStop(@Param("types")int types);



    /**
     * 根据编号查询公司
     * @param idnum
     * @return
     */
    SupplierBasic selectSupplierBasicByIsnum(@Param("idnum")String idnum);




    List<SupplierBasic> selectSupplierBasicNotSotp(@Param("types")int types);



}