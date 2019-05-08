package com.yc.education.mapper.basic;

import com.yc.education.model.basic.CompanyBasic;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 员工档案
 *@Author QuZhangJing
 *@Date 15:58  2018/8/31
 *@Version 1.0
 */

public interface EmployeeBasicMapper extends MyMapper<EmployeeBasic> {


    /**
     * @Description 模糊查询员工姓名、编号
     * @param stop 是否停用 1：停用
     * @Author BlueSky
     * @Date 17:09 2019/4/22
     **/
    List<EmployeeBasic> listEmployeeBasic(@Param("text")String text,@Param("stop")String stop);

    /**
     * 查询最大编号
     * @return
     */
    String  selectMaxIdnum();


    /**
     * 查询所有员工 不包括暂停使用的
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasic();


    List<EmployeeBasic> selectEmployeeBasicByIdnumAndName(@Param("idnumAndName")String idnumAndName);

    /**
     * 查询所有员工 不包括暂停使用的
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasicByIdnum(@Param("idnum")String idnum);


    /**
     * 根据编号查询员工
     * @param idnum
     * @return
     */
    EmployeeBasic selectEmployeeBasicByIsnum(@Param("idnum") String idnum);



    /**
     * 查询所有员工 包括暂停使用的
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasicNotStop(@Param("types")int types);


    /**
     * 根据用户编号和用户名称和用户密码查询用户是否存在
     * @param idnum
     * @param name
     * @param password
     * @return
     */
    EmployeeBasic selectEmployeeLogin(@Param("idnum") String idnum,@Param("name") String name,@Param("password") String password);


    /**
     * 根据员工名称查询员工信息
     * @param uname
     * @return
     */
    EmployeeBasic selectEmployeeByUname(@Param("uname")String uname);



}