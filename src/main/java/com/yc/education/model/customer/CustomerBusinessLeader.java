package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_business_leader")
public class CustomerBusinessLeader {
    /**
     * 客户基本资料-业务负责人
     * 被改为业务负责人
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户id
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 员工编号
     */
    @Column(name = "employee_code")
    private String employeeCode;

    /**
     * 姓名
     */
    private String name;

    /**
     * 主要负责人
     */
    @Column(name = "primary_people")
    private String primaryPeople;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务助理
     */
    private String assistant;

    /**
     * 添加时间
     */
    private Date addtime;

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    /**
     * 获取客户基本资料-业务负责人
     *
     * @return id - 客户基本资料-业务负责人
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户基本资料-业务负责人
     *
     * @param id 客户基本资料-业务负责人
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户id
     *
     * @return customer_id - 客户id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户id
     *
     * @param customerId 客户id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取员工编号
     *
     * @return employee_code - 员工编号
     */
    public String getEmployeeCode() {
        return employeeCode;
    }

    /**
     * 设置员工编号
     *
     * @param employeeCode 员工编号
     */
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取主要负责人
     *
     * @return primary_people - 主要负责人
     */
    public String getPrimaryPeople() {
        return primaryPeople;
    }

    /**
     * 设置主要负责人
     *
     * @param primaryPeople 主要负责人
     */
    public void setPrimaryPeople(String primaryPeople) {
        this.primaryPeople = primaryPeople;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取添加时间
     *
     * @return addtime - 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public CustomerBusinessLeader() {
    }

    public CustomerBusinessLeader( String employeeCode, String name, String primaryPeople, String remark, Date addtime) {
        this.employeeCode = employeeCode;
        this.name = name;
        this.primaryPeople = primaryPeople;
        this.remark = remark;
        this.addtime = addtime;
    }

    public CustomerBusinessLeader(Long customerId, String employeeCode, String name, String primaryPeople, String remark, Date addtime) {
        this.customerId = customerId;
        this.employeeCode = employeeCode;
        this.name = name;
        this.primaryPeople = primaryPeople;
        this.remark = remark;
        this.addtime = addtime;
    }
}