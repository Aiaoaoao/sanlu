package com.yc.education.service.basic;

import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName EmployeeBasicService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/31 16:23
 * @Version 1.0
 */
public interface EmployeeBasicService extends IService<EmployeeBasic> {

    /**
     * @Description 模糊查询员工姓名、编号
     * @param stop 是否停用 1：停用
     * @Author BlueSky
     * @Date 17:09 2019/4/22
     **/
    List<EmployeeBasic> listEmployeeBasic(String text,String stop,int pageNum,int pageSize);

    /**
     * 查询最大编号
     * @return
     */
    String  selectMaxIdnum();


    /**
     * 查询所有员工
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasic(int pageNum,int pageSize);

    /**
     * 查询所有员工
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasic();


    /**
     * 查询所有员工
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasic(String idnumAndName,int pageNum,int pageSize);

    /**
     * 查询所有员工
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasic(String idnumAndName);



    /**
     * 查询所有员工 不包括暂停使用的
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasicByIdnum(String idnum);


    /**
     * 查询所有员工 不包括暂停使用的
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasicByIdnum(String idnum,int pageNum,int pageSize);


    /**
     * 根据编号查询员工
     * @param idnum
     * @return
     */
    EmployeeBasic selectEmployeeBasicByIsnum(String idnum);



    /**
     * 查询所有员工 包括暂停使用的
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasicNotStop(int types);
    /**
     * 查询所有员工 包括暂停使用的
     * @return
     */
    List<EmployeeBasic> selectEmployeeBasicNotStop(int types,int pageNum,int pageSize);


    /**
     * 根据用户编号和用户名称和用户密码查询用户是否存在
     * @param idnum
     * @param name
     * @param password
     * @return
     */
    EmployeeBasic selectEmployeeLogin( String idnum, String name, String password);


    /**
     * 根据员工名称查询员工信息
     * @param uname
     * @return
     */
    EmployeeBasic selectEmployeeByUname(String uname);


}
