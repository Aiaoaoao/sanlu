package com.yc.education.mapper.basic;

import com.yc.education.model.basic.DepartmentBasic;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 部门
 *@Author QuZhangJing
 *@Date 10:24  2018/9/4
 *@Version 1.0
 */
public interface DepartmentBasicMapper extends MyMapper<DepartmentBasic> {

    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();

    /**
     * 查询所有部门
     * @return
     */
    List<DepartmentBasic> selectDepartmentBasic(@Param("orderAndName")String orderAndName);

    /**
     * 根据编号查询部门
     * @param idnum
     * @return
     */
    DepartmentBasic selectDepartmentBasicByIsnum(String idnum);

    /**
     * 根据parentid查询部门
     * @param parentid
     * @return
     */
    List<DepartmentBasic>  selectDepartmentBasicByParentId(@Param("parentid")long parentid);


    /**
     * 根据名称查询
     * @param depname
     * @return
     */
    DepartmentBasic selectDepartmentBasicByDepName(@Param("depname")String depname);



}