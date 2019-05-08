package com.yc.education.model.basic;

import javax.persistence.*;
/**
 *@Description TODO 供应商_联系人
 *@Author QuZhangJing
 *@Date 11:49  2018/8/28
 *@Version 1.0
 */
@Table(name = "supplier_contact")
public class SupplierContact {
    /**
     * 自增编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 主要联系人
     */
    private Integer keycontact;

    /**
     * 姓名
     */
    private String uname;

    /**
     * 部门
     */
    private String udepartment;

    /**
     * 职务
     */
    private String ujob;

    /**
     * 电话
     */
    private String uphone;

    /**
     * 传真
     */
    private String ufax;

    /**
     * 移动电话
     */
    private String umobile;

    /**
     * Email
     */
    private String uemail;

    /**
     * 备注
     */
    private String uremarks;

    /**
     * 是否主要联系人
     */
    private Integer ispoint;

    /**
     * 供应商编号
     */
    private Long supplierid;


    public SupplierContact() {
    }


    public SupplierContact(long id,Integer keycontact, String uname, String udepartment, String ujob, String uphone, String ufax, String umobile, String uemail, String uremarks) {
        this.id=id;
        this.keycontact = keycontact;
        this.uname = uname;
        this.udepartment = udepartment;
        this.ujob = ujob;
        this.uphone = uphone;
        this.ufax = ufax;
        this.umobile = umobile;
        this.uemail = uemail;
        this.uremarks = uremarks;
    }

    public SupplierContact(long id,Integer keycontact, String uname, String udepartment, String ujob, String uphone, String ufax, String umobile, String uemail, String uremarks, Integer ispoint, Long supplierid) {
        this.id=id;
        this.keycontact = keycontact;
        this.uname = uname;
        this.udepartment = udepartment;
        this.ujob = ujob;
        this.uphone = uphone;
        this.ufax = ufax;
        this.umobile = umobile;
        this.uemail = uemail;
        this.uremarks = uremarks;
        this.ispoint = ispoint;
        this.supplierid = supplierid;
    }

    /**
     * 获取自增编号
     *
     * @return id - 自增编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增编号
     *
     * @param id 自增编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKeycontact() {
        return keycontact;
    }

    public void setKeycontact(Integer keycontact) {
        this.keycontact = keycontact;
    }

    /**
     * 获取姓名
     *
     * @return uname - 姓名
     */
    public String getUname() {
        return uname;
    }

    /**
     * 设置姓名
     *
     * @param uname 姓名
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * 获取部门
     *
     * @return udepartment - 部门
     */
    public String getUdepartment() {
        return udepartment;
    }

    /**
     * 设置部门
     *
     * @param udepartment 部门
     */
    public void setUdepartment(String udepartment) {
        this.udepartment = udepartment;
    }

    /**
     * 获取职务
     *
     * @return ujob - 职务
     */
    public String getUjob() {
        return ujob;
    }

    /**
     * 设置职务
     *
     * @param ujob 职务
     */
    public void setUjob(String ujob) {
        this.ujob = ujob;
    }

    /**
     * 获取电话
     *
     * @return uphone - 电话
     */
    public String getUphone() {
        return uphone;
    }

    /**
     * 设置电话
     *
     * @param uphone 电话
     */
    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    /**
     * 获取传真
     *
     * @return ufax - 传真
     */
    public String getUfax() {
        return ufax;
    }

    /**
     * 设置传真
     *
     * @param ufax 传真
     */
    public void setUfax(String ufax) {
        this.ufax = ufax;
    }

    /**
     * 获取移动电话
     *
     * @return umobile - 移动电话
     */
    public String getUmobile() {
        return umobile;
    }

    /**
     * 设置移动电话
     *
     * @param umobile 移动电话
     */
    public void setUmobile(String umobile) {
        this.umobile = umobile;
    }

    /**
     * 获取Email
     *
     * @return uemail - Email
     */
    public String getUemail() {
        return uemail;
    }

    /**
     * 设置Email
     *
     * @param uemail Email
     */
    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    /**
     * 获取备注
     *
     * @return uremarks - 备注
     */
    public String getUremarks() {
        return uremarks;
    }

    /**
     * 设置备注
     *
     * @param uremarks 备注
     */
    public void setUremarks(String uremarks) {
        this.uremarks = uremarks;
    }

    /**
     * 获取是否主要联系人
     *
     * @return ispoint - 是否主要联系人
     */
    public Integer getIspoint() {
        return ispoint;
    }

    /**
     * 设置是否主要联系人
     *
     * @param ispoint 是否主要联系人
     */
    public void setIspoint(Integer ispoint) {
        this.ispoint = ispoint;
    }

    /**
     * 获取供应商编号
     *
     * @return supplierid - 供应商编号
     */
    public Long getSupplierid() {
        return supplierid;
    }

    /**
     * 设置供应商编号
     *
     * @param supplierid 供应商编号
     */
    public void setSupplierid(Long supplierid) {
        this.supplierid = supplierid;
    }
}