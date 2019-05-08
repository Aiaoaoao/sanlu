package com.yc.education.model.customer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_detail_info")
public class CustomerDetailInfo {
    /**
     * 客户基本资料-详情
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户id
     */
    @Id
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 客户类别
     */
    @Column(name = "customer_category")
    private Integer customerCategory;

    /**
     * 行业
     */
    private Integer industry;

    /**
     * 客户来源
     */
    @Column(name = "customer_source")
    private Integer customerSource;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 银行账号
     */
    @Column(name = "bank_account")
    private String bankAccount;

    /**
     * 税务登记号
     */
    @Column(name = "tax_register")
    private String taxRegister;

    /**
     * 所属区域
     */
    private String area;

    /**
     * 所属区域详细
     */
    @Column(name = "area_info")
    private String areaInfo;

    /**
     * 销售单需回传（1需回传）
     */
    @Column(name = "sent_back")
    private Boolean sentBack;

    /**
     * 地区
     */
    private String region;

    /**
     * 客户等级
     */
    @Column(name = "customer_level")
    private Integer customerLevel;

    /**
     * 所属公司
     */
    @Column(name = "Company_affiliation")
    private String companyAffiliation;

    /**
     * 账款备注
     */
    @Column(name = "account_remark")
    private String accountRemark;

    /**
     * 客户属性-新加字段
     */
    @Column(name = "customer_property")
    private String customerProperty;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date addtime;

    public String getCustomerProperty() {
        return customerProperty;
    }

    public void setCustomerProperty(String customerProperty) {
        this.customerProperty = customerProperty;
    }

    /**
     * 获取客户基本资料-详情
     *
     * @return id - 客户基本资料-详情
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户基本资料-详情
     *
     * @param id 客户基本资料-详情
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
     * 获取客户类别
     *
     * @return customer_category - 客户类别
     */
    public Integer getCustomerCategory() {
        return customerCategory;
    }

    /**
     * 设置客户类别
     *
     * @param customerCategory 客户类别
     */
    public void setCustomerCategory(Integer customerCategory) {
        this.customerCategory = customerCategory;
    }

    /**
     * 获取行业
     *
     * @return industry - 行业
     */
    public Integer getIndustry() {
        return industry;
    }

    /**
     * 设置行业
     *
     * @param industry 行业
     */
    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    /**
     * 获取客户来源
     *
     * @return customer_source - 客户来源
     */
    public Integer getCustomerSource() {
        return customerSource;
    }

    /**
     * 设置客户来源
     *
     * @param customerSource 客户来源
     */
    public void setCustomerSource(Integer customerSource) {
        this.customerSource = customerSource;
    }

    /**
     * 获取开户银行
     *
     * @return bank - 开户银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置开户银行
     *
     * @param bank 开户银行
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 获取银行账号
     *
     * @return bank_account - 银行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 设置银行账号
     *
     * @param bankAccount 银行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * 获取税务登记号
     *
     * @return tax_register - 税务登记号
     */
    public String getTaxRegister() {
        return taxRegister;
    }

    /**
     * 设置税务登记号
     *
     * @param taxRegister 税务登记号
     */
    public void setTaxRegister(String taxRegister) {
        this.taxRegister = taxRegister;
    }

    /**
     * 获取所属区域
     *
     * @return area - 所属区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置所属区域
     *
     * @param area 所属区域
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取所属区域详细
     *
     * @return area_info - 所属区域详细
     */
    public String getAreaInfo() {
        return areaInfo;
    }

    /**
     * 设置所属区域详细
     *
     * @param areaInfo 所属区域详细
     */
    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    /**
     * 获取销售单需回传（1需回传）
     *
     * @return sent_back - 销售单需回传（1需回传）
     */
    public Boolean getSentBack() {
        return sentBack;
    }

    /**
     * 设置销售单需回传（1需回传）
     *
     * @param sentBack 销售单需回传（1需回传）
     */
    public void setSentBack(Boolean sentBack) {
        this.sentBack = sentBack;
    }

    /**
     * 获取地区
     *
     * @return region - 地区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置地区
     *
     * @param region 地区
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 获取客户登记
     *
     * @return customer_level - 客户登记
     */
    public Integer getCustomerLevel() {
        return customerLevel;
    }

    /**
     * 设置客户登记
     *
     * @param customerLevel 客户登记
     */
    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    /**
     * 获取所属公司
     *
     * @return Company_affiliation - 所属公司
     */
    public String getCompanyAffiliation() {
        return companyAffiliation;
    }

    /**
     * 设置所属公司
     *
     * @param companyAffiliation 所属公司
     */
    public void setCompanyAffiliation(String companyAffiliation) {
        this.companyAffiliation = companyAffiliation;
    }

    /**
     * 获取账款备注
     *
     * @return account_remark - 账款备注
     */
    public String getAccountRemark() {
        return accountRemark;
    }

    /**
     * 设置账款备注
     *
     * @param accountRemark 账款备注
     */
    public void setAccountRemark(String accountRemark) {
        this.accountRemark = accountRemark;
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
}