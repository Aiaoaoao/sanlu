package com.yc.education.model.check;

import javax.persistence.*;
/**
 *@Description TODO 申请人 (考勤管理)
 *@Author QuZhangJing
 *@Date 14:10  2019/2/14
 *@Version 1.0
 */
@Table(name = "check_order_employee")
public class CheckOrderEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 员工编号
     */
    private String employeeorder;

    /**
     * 员工姓名
     */
    private String employeename;

    /**
     * 请假/加班/出差申请单ID
     */
    private Long checkid;

    /**
     * 无参构造函数
     */
    public CheckOrderEmployee(){

    }

    public CheckOrderEmployee(Integer sort, String employeeorder, String employeename, Long checkid) {
        this.sort = sort;
        this.employeeorder = employeeorder;
        this.employeename = employeename;
        this.checkid = checkid;
    }

    public CheckOrderEmployee(Long id, Integer sort, String employeeorder, String employeename, Long checkid) {
        this.id = id;
        this.sort = sort;
        this.employeeorder = employeeorder;
        this.employeename = employeename;
        this.checkid = checkid;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取员工编号
     *
     * @return employeeorder - 员工编号
     */
    public String getEmployeeorder() {
        return employeeorder;
    }

    /**
     * 设置员工编号
     *
     * @param employeeorder 员工编号
     */
    public void setEmployeeorder(String employeeorder) {
        this.employeeorder = employeeorder;
    }

    /**
     * 获取员工姓名
     *
     * @return employeename - 员工姓名
     */
    public String getEmployeename() {
        return employeename;
    }

    /**
     * 设置员工姓名
     *
     * @param employeename 员工姓名
     */
    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    /**
     * 获取请假/加班/出差申请单ID
     *
     * @return checkid - 请假/加班/出差申请单ID
     */
    public Long getCheckid() {
        return checkid;
    }

    /**
     * 设置请假/加班/出差申请单ID
     *
     * @param checkid 请假/加班/出差申请单ID
     */
    public void setCheckid(Long checkid) {
        this.checkid = checkid;
    }
}