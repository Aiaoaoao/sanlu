package com.yc.education.service.basic;

import com.yc.education.model.basic.CompanyBasic;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName CompanyBasicService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/30 16:16
 * @Version 1.0
 */
public interface CompanyBasicService extends IService<CompanyBasic> {


    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();


    /**
     * 查询所有供应商
     * @return
     */
    List<CompanyBasic> selectCompanyBasic(int types,int pageNum,int pageSize);


    /**
     * 查询所有供应商
     * @return
     */
    List<CompanyBasic> selectCompanyBasic(int types);


    /**
     * 根据编号查询供应商
     * @param idnum
     * @return
     */
    CompanyBasic selectCompanyBasicByIsnum(String idnum);


    List<CompanyBasic> selectCompanyBasic(String orderAndName,int pageNum,int pageSize);

    List<CompanyBasic> selectCompanyBasic(String orderAndName);



}
