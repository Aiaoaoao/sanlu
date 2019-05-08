package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_contacts")
public class CustomerContacts {
    /**
     * 客户基本资料-联系人
     * 被改为客户联系人
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
     * 主要联系人
     */
    @Column(name = "primary_contacts")
    private Boolean primaryContacts;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门
     */
    private String department;

    /**
     * 职务
     */
    private String position;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 移动电话
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取客户基本资料-联系人
     *
     * @return id - 客户基本资料-联系人
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户基本资料-联系人
     *
     * @param id 客户基本资料-联系人
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
     * 获取主要联系人
     *
     * @return primary_contacts - 主要联系人
     */
    public Boolean getPrimaryContacts() {
        return primaryContacts;
    }

    /**
     * 设置主要联系人
     *
     * @param primaryContacts 主要联系人
     */
    public void setPrimaryContacts(Boolean primaryContacts) {
        this.primaryContacts = primaryContacts;
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
     * 获取部门
     *
     * @return department - 部门
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置部门
     *
     * @param department 部门
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 获取职务
     *
     * @return position - 职务
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职务
     *
     * @param position 职务
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取电话
     *
     * @return telephone - 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话
     *
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取移动电话
     *
     * @return mobile_phone - 移动电话
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置移动电话
     *
     * @param mobilePhone 移动电话
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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

    public CustomerContacts() {
    }

    public CustomerContacts( Boolean primaryContacts, String name, String department, String position, String telephone, String fax, String mobilePhone, String email, String remark, Date addtime) {
        this.primaryContacts = primaryContacts;
        this.name = name;
        this.department = department;
        this.position = position;
        this.telephone = telephone;
        this.fax = fax;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.remark = remark;
        this.addtime = addtime;
    }

    public CustomerContacts(Long customerId, Boolean primaryContacts, String name, String department, String position, String telephone, String fax, String mobilePhone, String email, String remark, Date addtime) {
        this.customerId = customerId;
        this.primaryContacts = primaryContacts;
        this.name = name;
        this.department = department;
        this.position = position;
        this.telephone = telephone;
        this.fax = fax;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.remark = remark;
        this.addtime = addtime;
    }
}