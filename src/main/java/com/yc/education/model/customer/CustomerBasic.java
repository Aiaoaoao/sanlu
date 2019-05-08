package com.yc.education.model.customer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_basic")
public class CustomerBasic {
    /**
     * 客户基本资料-基本信息
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
     * 国家id
     */
    @Column(name = "country_id")
    private Long countryId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 结算方式
     */
    @Column(name = "payment_method")
    private String paymentMethod;

    /**
     * 期初额度调整金额
     */
    @Column(name = "initial_quota")
    private BigDecimal initialQuota;

    /**
     * 期初额度调整金额类型（人民币、美元）
     */
    @Column(name = "initial_quota_money_type")
    private String initialQuotaMoneyType;

    /**
     * 借款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "rush_money_date")
    private Date rushMoneyDate;

    /**
     * 送货地址
     */
    @Column(name = "shipping_address")
    private String shippingAddress;

    /**
     * 送货地址备注
     */
    @Column(name = "shipping_address_remark")
    private String shippingAddressRemark;

    /**
     * 主要联系人
     */
    @Column(name = "primary_contact")
    private String primaryContact;

    /**
     * 建档人
     */
    private String archivist;

    /**
     * 最后修改人
     */
    @Column(name = "last_modifier")
    private String lastModifier;

    /**
     * 建立日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "establish_date")
    private Date establishDate;

    /**
     * 邮政编码
     */
    @Column(name = "zip_code")
    private String zipCode;

    /**
     * 传真
     */
    private String fax;

    /**
     * 信用额度
     */
    @Column(name = "credit_line")
    private BigDecimal creditLine;

    /**
     * 信用额度类型（人民币、美元）
     */
    @Column(name = "credit_line_money_type")
    private Integer creditLineMoneyType;

    /**
     * 授信额度备注
     */
    @Column(name = "credit_line_remark")
    private String creditLineRemark;

    /**
     * 最低折扣
     */
    @Column(name = "minimum_discount")
    private Double minimumDiscount;

    /**
     * 联系电话
     */
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 建档日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "document_date")
    private Date documentDate;

    /**
     * 最后修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    /**
     * 暂停使用
     */
    @Column(name = "stop_use")
    private Boolean stopUse;

    /**
     * 暂停使用描述
     */
    @Column(name = "stop_use_str")
    private String stopUseStr;

    /**
     * 系统添加时间
     */
    private Date addtime;

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取客户基本资料-基本信息
     *
     * @return id - 客户基本资料-基本信息
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户基本资料-基本信息
     *
     * @param id 客户基本资料-基本信息
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
     * 获取国家id
     *
     * @return country_id - 国家id
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * 设置国家id
     *
     * @param countryId 国家id
     */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取结算方式
     *
     * @return payment_method - 结算方式
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 设置结算方式
     *
     * @param paymentMethod 结算方式
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 获取期初额度调整金额
     *
     * @return initial_quota - 期初额度调整金额
     */
    public BigDecimal getInitialQuota() {
        return initialQuota;
    }

    /**
     * 设置期初额度调整金额
     *
     * @param initialQuota 期初额度调整金额
     */
    public void setInitialQuota(BigDecimal initialQuota) {
        this.initialQuota = initialQuota;
    }

    /**
     * 获取期初额度调整金额类型（人民币、美元）
     *
     * @return initial_quota_money_type - 期初额度调整金额类型（人民币、美元）
     */
    public String getInitialQuotaMoneyType() {
        return initialQuotaMoneyType;
    }

    /**
     * 设置期初额度调整金额类型（人民币、美元）
     *
     * @param initialQuotaMoneyType 期初额度调整金额类型（人民币、美元）
     */
    public void setInitialQuotaMoneyType(String initialQuotaMoneyType) {
        this.initialQuotaMoneyType = initialQuotaMoneyType;
    }

    /**
     * 获取借款日期
     *
     * @return rushMoneyDate - 催款日期
     */
    public Date getRushMoneyDate() {
        return rushMoneyDate;
    }

    /**
     * 设置借款日期
     *
     * @param rushMoneyDate 催款日期
     */
    public void setRushMoneyDate(Date rushMoneyDate) {
        this.rushMoneyDate = rushMoneyDate;
    }

    /**
     * 获取送货地址
     *
     * @return shipping_address - 送货地址
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * 设置送货地址
     *
     * @param shippingAddress 送货地址
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * 获取送货地址备注
     *
     * @return shipping_address_remark - 送货地址备注
     */
    public String getShippingAddressRemark() {
        return shippingAddressRemark;
    }

    /**
     * 设置送货地址备注
     *
     * @param shippingAddressRemark 送货地址备注
     */
    public void setShippingAddressRemark(String shippingAddressRemark) {
        this.shippingAddressRemark = shippingAddressRemark;
    }

    /**
     * 获取主要联系人
     *
     * @return primary_contact - 主要联系人
     */
    public String getPrimaryContact() {
        return primaryContact;
    }

    /**
     * 设置主要联系人
     *
     * @param primaryContact 主要联系人
     */
    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    /**
     * 获取建档人
     *
     * @return archivist - 建档人
     */
    public String getArchivist() {
        return archivist;
    }

    /**
     * 设置建档人
     *
     * @param archivist 建档人
     */
    public void setArchivist(String archivist) {
        this.archivist = archivist;
    }

    /**
     * 获取最后修改人
     *
     * @return last_modifier - 最后修改人
     */
    public String getLastModifier() {
        return lastModifier;
    }

    /**
     * 设置最后修改人
     *
     * @param lastModifier 最后修改人
     */
    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }

    /**
     * 获取建立日期
     *
     * @return establishDate - 建立日期
     */
    public Date getEstablishDate() {
        return establishDate;
    }

    /**
     * 设置建立日期
     *
     * @param establishDate 建立日期
     */
    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    /**
     * 获取邮政编码
     *
     * @return zip_code - 邮政编码
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 设置邮政编码
     *
     * @param zipCode 邮政编码
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
     * 获取信用额度
     *
     * @return credit_line - 信用额度
     */
    public BigDecimal getCreditLine() {
        return creditLine;
    }

    /**
     * 设置信用额度
     *
     * @param creditLine 信用额度
     */
    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    /**
     * 获取信用额度类型（人民币、美元）
     *
     * @return credit_line_money_type - 信用额度类型（人民币、美元）
     */
    public Integer getCreditLineMoneyType() {
        return creditLineMoneyType;
    }

    /**
     * 设置信用额度类型（人民币、美元）
     *
     * @param creditLineMoneyType 信用额度类型（人民币、美元）
     */
    public void setCreditLineMoneyType(Integer creditLineMoneyType) {
        this.creditLineMoneyType = creditLineMoneyType;
    }

    /**
     * 获取授信额度备注
     *
     * @return credit_line_remark - 授信额度备注
     */
    public String getCreditLineRemark() {
        return creditLineRemark;
    }

    /**
     * 设置授信额度备注
     *
     * @param creditLineRemark 授信额度备注
     */
    public void setCreditLineRemark(String creditLineRemark) {
        this.creditLineRemark = creditLineRemark;
    }

    /**
     * 获取最低折扣
     *
     * @return minimum_discount - 最低折扣
     */
    public Double getMinimumDiscount() {
        return minimumDiscount;
    }

    /**
     * 设置最低折扣
     *
     * @param minimumDiscount 最低折扣
     */
    public void setMinimumDiscount(Double minimumDiscount) {
        this.minimumDiscount = minimumDiscount;
    }

    /**
     * 获取联系电话
     *
     * @return contact_number - 联系电话
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * 设置联系电话
     *
     * @param contactNumber 联系电话
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * 获取建档日期
     *
     * @return document_date - 建档日期
     */
    public Date getDocumentDate() {
        return documentDate;
    }

    /**
     * 设置建档日期
     *
     * @param documentDate 建档日期
     */
    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    /**
     * 获取最后修改日期
     *
     * @return last_modified_date - 最后修改日期
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * 设置最后修改日期
     *
     * @param lastModifiedDate 最后修改日期
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Boolean getStopUse() {
        return stopUse;
    }

    public void setStopUse(Boolean stopUse) {
        this.stopUse = stopUse;
    }

    public String getStopUseStr() {
        return stopUseStr;
    }

    public void setStopUseStr(String stopUseStr) {
        this.stopUseStr = stopUseStr;
    }
}