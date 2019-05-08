package com.yc.education.model.basic;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO   公司基本资料
 *@Author QuZhangJing
 *@Date 15:41  2018/8/30
 *@Version 1.0
 */
@Table(name = "company_basic")
public class CompanyBasic {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编号
     */
    private String idnum;

    /**
     * 公司简称
     */
    private String comdes;

    /**
     * 公司名称
     */
    private String comname;

    /**
     * 注册地址
     */
    private String regadd;

    /**
     * 国家类型
     */
    private Integer country;

    /**
     * 地区
     */
    private String area;

    /**
     * 邮政编码
     */
    private String postalcode;

    /**
     * 电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 送货地址
     */
    private String address;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 建档人
     */
    private String addpeople;

    /**
     * 建档日期
     */
    private String adddate;

    /**
     * 最后修改人
     */
    private String updatepeople;

    /**
     * 最后修改时间
     */
    private String updatedate;

    /**
     * 是否暂停使用
     */
    private Integer isstop;

    /**
     * 暂停描述
     */
    private String stopdes;

    /**
     * 开户银行
     */
    private String openbank;

    /**
     * 银行账号
     */
    private String bankaccount;

    /**
     * 税务登记号
     */
    private String taxaccount;

    /**
     * 发票抬头
     */
    private String invoicehead;

    /**
     * 发票地址
     */
    private String invoiceadd;

    /**
     * 禁止来往
     */
    private Integer comeandgo;

    @Transient
    private int sort;


    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取编号
     *
     * @return idnum - 编号
     */
    public String getIdnum() {
        return idnum;
    }

    /**
     * 设置编号
     *
     * @param idnum 编号
     */
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    /**
     * 获取公司简称
     *
     * @return comdes - 公司简称
     */
    public String getComdes() {
        return comdes;
    }

    /**
     * 设置公司简称
     *
     * @param comdes 公司简称
     */
    public void setComdes(String comdes) {
        this.comdes = comdes;
    }

    /**
     * 获取公司名称
     *
     * @return comname - 公司名称
     */
    public String getComname() {
        return comname;
    }

    /**
     * 设置公司名称
     *
     * @param comname 公司名称
     */
    public void setComname(String comname) {
        this.comname = comname;
    }

    /**
     * 获取注册地址
     *
     * @return regadd - 注册地址
     */
    public String getRegadd() {
        return regadd;
    }

    /**
     * 设置注册地址
     *
     * @param regadd 注册地址
     */
    public void setRegadd(String regadd) {
        this.regadd = regadd;
    }

    /**
     * 获取国家类型
     *
     * @return country - 国家类型
     */
    public Integer getCountry() {
        return country;
    }

    /**
     * 设置国家类型
     *
     * @param country 国家类型
     */
    public void setCountry(Integer country) {
        this.country = country;
    }

    /**
     * 获取地区
     *
     * @return area - 地区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置地区
     *
     * @param area 地区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取邮政编码
     *
     * @return postalcode - 邮政编码
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * 设置邮政编码
     *
     * @param postalcode 邮政编码
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取送货地址
     *
     * @return address - 送货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置送货地址
     *
     * @param address 送货地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取建档人
     *
     * @return addpeople - 建档人
     */
    public String getAddpeople() {
        return addpeople;
    }

    /**
     * 设置建档人
     *
     * @param addpeople 建档人
     */
    public void setAddpeople(String addpeople) {
        this.addpeople = addpeople;
    }


    /**
     * 获取最后修改人
     *
     * @return updatepeople - 最后修改人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后修改人
     *
     * @param updatepeople 最后修改人
     */
    public void setUpdatepeople(String updatepeople) {
        this.updatepeople = updatepeople;
    }



    /**
     * 获取是否暂停使用
     *
     * @return isstop - 是否暂停使用
     */
    public Integer getIsstop() {
        return isstop;
    }

    /**
     * 设置是否暂停使用
     *
     * @param isstop 是否暂停使用
     */
    public void setIsstop(Integer isstop) {
        this.isstop = isstop;
    }

    /**
     * 获取暂停描述
     *
     * @return stopdes - 暂停描述
     */
    public String getStopdes() {
        return stopdes;
    }

    /**
     * 设置暂停描述
     *
     * @param stopdes 暂停描述
     */
    public void setStopdes(String stopdes) {
        this.stopdes = stopdes;
    }

    /**
     * 获取开户银行
     *
     * @return openbank - 开户银行
     */
    public String getOpenbank() {
        return openbank;
    }

    /**
     * 设置开户银行
     *
     * @param openbank 开户银行
     */
    public void setOpenbank(String openbank) {
        this.openbank = openbank;
    }

    /**
     * 获取银行账号
     *
     * @return bankaccount - 银行账号
     */
    public String getBankaccount() {
        return bankaccount;
    }

    /**
     * 设置银行账号
     *
     * @param bankaccount 银行账号
     */
    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    /**
     * 获取税务登记号
     *
     * @return taxaccount - 税务登记号
     */
    public String getTaxaccount() {
        return taxaccount;
    }

    /**
     * 设置税务登记号
     *
     * @param taxaccount 税务登记号
     */
    public void setTaxaccount(String taxaccount) {
        this.taxaccount = taxaccount;
    }

    /**
     * 获取发票抬头
     *
     * @return invoicehead - 发票抬头
     */
    public String getInvoicehead() {
        return invoicehead;
    }

    /**
     * 设置发票抬头
     *
     * @param invoicehead 发票抬头
     */
    public void setInvoicehead(String invoicehead) {
        this.invoicehead = invoicehead;
    }

    /**
     * 获取发票地址
     *
     * @return invoiceadd - 发票地址
     */
    public String getInvoiceadd() {
        return invoiceadd;
    }

    /**
     * 设置发票地址
     *
     * @param invoiceadd 发票地址
     */
    public void setInvoiceadd(String invoiceadd) {
        this.invoiceadd = invoiceadd;
    }



    /**
     * 获取禁止来往
     *
     * @return comandgo - 禁止来往
     */

    public Integer getComeandgo() {
        return comeandgo;
    }

    /**
     * 设置禁止来往
     *
     * @param comeandgo 禁止来往
     */
    public void setComeandgo(Integer comeandgo) {
        this.comeandgo = comeandgo;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}