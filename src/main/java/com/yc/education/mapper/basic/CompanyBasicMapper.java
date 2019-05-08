package com.yc.education.mapper.basic;

import com.yc.education.model.basic.CompanyBasic;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Description TODO 公司基本资料
 *@Author QuZhangJing
 *@Date 15:42  2018/8/30
 *@Version 1.0
 */
@Repository
public interface CompanyBasicMapper extends MyMapper<CompanyBasic> {






    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();


    /**
     * 查询所有供应商
     * @return
     */
    List<CompanyBasic> selectCompanyBasic(@Param("types")int types);


    /**
     * 根据编号查询供应商
     * @param idnum
     * @return
     */
    CompanyBasic selectCompanyBasicByIsnum(@Param("idnum") String idnum);


    List<CompanyBasic> selectCompanyBasicByOrderAndName(@Param("orderAndName")String orderAndName);


    List<CompanyBasic> selectCompanyBasicNotStop();




}