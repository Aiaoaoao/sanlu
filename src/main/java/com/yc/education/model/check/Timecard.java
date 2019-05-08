package com.yc.education.model.check;

import javax.persistence.*;
/**
 *@Description TODO 原始考勤资料
 *@Author QuZhangJing
 *@Date 11:41  2019/2/18
 *@Version 1.0
 */
public class Timecard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 员工编号
     */
    private String employeeorder;

    /**
     * 员工名称
     */
    private String employeename;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 状态
     */
    private Integer status;

    public Timecard() {

    }

    public Timecard(Long id, String employeeorder, String employeename, String createtime, Integer status) {
        this.id = id;
        this.employeeorder = employeeorder;
        this.employeename = employeename;
        this.createtime = createtime;
        this.status = status;
    }

    public Timecard(String employeeorder, String employeename, String createtime, Integer status) {
        this.employeeorder = employeeorder;
        this.employeename = employeename;
        this.createtime = createtime;
        this.status = status;
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
     * 获取员工名称
     *
     * @return employeename - 员工名称
     */
    public String getEmployeename() {
        return employeename;
    }

    /**
     * 设置员工名称
     *
     * @param employeename 员工名称
     */
    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}