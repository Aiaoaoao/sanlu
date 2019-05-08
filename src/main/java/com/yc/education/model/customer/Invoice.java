package com.yc.education.model.customer;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Invoice")
public class Invoice {

    /**
     * 客户资料维护-车床
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
     * 常用
     */
    private Boolean usual;

    /**
     * 发票抬头
     */
    private String title;

    /**
     * 发票地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 联络人
     */
    private String contact;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;
    /**
     * 停用时间
     */
    private Date disdate;
    /**
     * 停用
     */
    private Boolean disabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Boolean getUsual() {
        return usual;
    }

    public void setUsual(Boolean usual) {
        this.usual = usual;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getDisdate() {
        return disdate;
    }

    public void setDisdate(Date disdate) {
        this.disdate = disdate;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}