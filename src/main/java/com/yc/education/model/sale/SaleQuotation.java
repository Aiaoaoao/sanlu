package com.yc.education.model.sale;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_quotation")
public class SaleQuotation {
    /**
     * 报价单
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 序号
     */
    @Transient
    private Integer no;
    /**
     * 制单日期
     */
    @Transient
    private String createDateStr;
    /**
     * 询价日期
     */
    @Transient
    private String enquiryDateStr;
    /**
     * 有效期至
     */
    @Transient
    private String validUntilStr;
    /**
     * 单据审核状态
     */
    @Transient
    private String auditStatus;

    /**
     * 制单日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 报价单号
     */
    @Column(name = "offer_no")
    private String offerNo;

    /**
     * 询价日期
     */
    @Column(name = "enquiry_date")
    private Date enquiryDate;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 客户编号备注
     */
    @Column(name = "customer_no_str")
    private String customerNoStr;

    /**
     * 业务负责
     */
    private String business;

    /**
     * 业务负责备注
     */
    @Column(name = "business_str")
    private String businessStr;

    /**
     * 税别
     */
    private String tax;

    /**
     * 折扣
     */
    private String discount;

    /**
     * 特价单
     */
    @Column(name = "special_offer")
    private Boolean specialOffer;

    /**
     * 应收余额
     */
    @Column(name = "amount_receivable")
    private String amountReceivable;

    /**
     * 有效期至
     */
    @Column(name = "valid_until")
    private Date validUntil;

    /**
     * 币别
     */
    private String currency;

    /**
     * 制单人
     */
    @Column(name = "single_people")
    private String singlePeople;

    /**
     * 审核人
     */
    private String audit;

    /**
     * 审核人备注
     */
    @Column(name = "audit_str")
    private String auditStr;

    /**
     * 最后修改人
     */
    @Column(name = "last_modifier")
    private String lastModifier;

    /**
     * 最后修改人备注
     */
    @Column(name = "last_modifier_str")
    private String lastModifierStr;

    /**
     * 客户姓名
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 客户类别
     */
    @Column(name = "customer_category")
    private String customerCategory;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 单据审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    @Transient
    private String status;


    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Boolean getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getEnquiryDateStr() {
        return enquiryDateStr;
    }

    public void setEnquiryDateStr(String enquiryDateStr) {
        this.enquiryDateStr = enquiryDateStr;
    }

    public String getValidUntilStr() {
        return validUntilStr;
    }

    public void setValidUntilStr(String validUntilStr) {
        this.validUntilStr = validUntilStr;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 送货地址
     */
    private String address;

    /**
     * 获取报价单
     *
     * @return id - 报价单
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置报价单
     *
     * @param id 报价单
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取制单日期
     *
     * @return create_date - 制单日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置制单日期
     *
     * @param createDate 制单日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取报价单号
     *
     * @return offer_no - 报价单号
     */
    public String getOfferNo() {
        return offerNo;
    }

    /**
     * 设置报价单号
     *
     * @param offerNo 报价单号
     */
    public void setOfferNo(String offerNo) {
        this.offerNo = offerNo;
    }

    /**
     * 获取询价日期
     *
     * @return enquiry_date - 询价日期
     */
    public Date getEnquiryDate() {
        return enquiryDate;
    }

    /**
     * 设置询价日期
     *
     * @param enquiryDate 询价日期
     */
    public void setEnquiryDate(Date enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    /**
     * 获取客户编号
     *
     * @return customer_no - 客户编号
     */
    public String getCustomerNo() {
        return customerNo;
    }

    /**
     * 设置客户编号
     *
     * @param customerNo 客户编号
     */
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    /**
     * 获取客户编号备注
     *
     * @return customer_no_str - 客户编号备注
     */
    public String getCustomerNoStr() {
        return customerNoStr;
    }

    /**
     * 设置客户编号备注
     *
     * @param customerNoStr 客户编号备注
     */
    public void setCustomerNoStr(String customerNoStr) {
        this.customerNoStr = customerNoStr;
    }

    /**
     * 获取业务负责
     *
     * @return business - 业务负责
     */
    public String getBusiness() {
        return business;
    }

    /**
     * 设置业务负责
     *
     * @param business 业务负责
     */
    public void setBusiness(String business) {
        this.business = business;
    }

    /**
     * 获取业务负责备注
     *
     * @return business_str - 业务负责备注
     */
    public String getBusinessStr() {
        return businessStr;
    }

    /**
     * 设置业务负责备注
     *
     * @param businessStr 业务负责备注
     */
    public void setBusinessStr(String businessStr) {
        this.businessStr = businessStr;
    }

    /**
     * 获取税别
     *
     * @return tax - 税别
     */
    public String getTax() {
        return tax;
    }

    /**
     * 设置税别
     *
     * @param tax 税别
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * 获取折扣
     *
     * @return discount - 折扣
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * 设置折扣
     *
     * @param discount 折扣
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     * 获取特价单
     *
     * @return special_offer - 特价单
     */
    public Boolean getSpecialOffer() {
        return specialOffer;
    }

    /**
     * 设置特价单
     *
     * @param specialOffer 特价单
     */
    public void setSpecialOffer(Boolean specialOffer) {
        this.specialOffer = specialOffer;
    }

    /**
     * 获取应收余额
     *
     * @return amount_receivable - 应收余额
     */
    public String getAmountReceivable() {
        return amountReceivable;
    }

    /**
     * 设置应收余额
     *
     * @param amountReceivable 应收余额
     */
    public void setAmountReceivable(String amountReceivable) {
        this.amountReceivable = amountReceivable;
    }

    /**
     * 获取有效期至
     *
     * @return valid_until - 有效期至
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * 设置有效期至
     *
     * @param validUntil 有效期至
     */
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * 获取币别
     *
     * @return currency - 币别
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置币别
     *
     * @param currency 币别
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取制单人
     *
     * @return single_people - 制单人
     */
    public String getSinglePeople() {
        return singlePeople;
    }

    /**
     * 设置制单人
     *
     * @param singlePeople 制单人
     */
    public void setSinglePeople(String singlePeople) {
        this.singlePeople = singlePeople;
    }

    /**
     * 获取审核人
     *
     * @return audit - 审核人
     */
    public String getAudit() {
        return audit;
    }

    /**
     * 设置审核人
     *
     * @param audit 审核人
     */
    public void setAudit(String audit) {
        this.audit = audit;
    }

    /**
     * 获取审核人备注
     *
     * @return audit_str - 审核人备注
     */
    public String getAuditStr() {
        return auditStr;
    }

    /**
     * 设置审核人备注
     *
     * @param auditStr 审核人备注
     */
    public void setAuditStr(String auditStr) {
        this.auditStr = auditStr;
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
     * 获取最后修改人备注
     *
     * @return last_modifier_str - 最后修改人备注
     */
    public String getLastModifierStr() {
        return lastModifierStr;
    }

    /**
     * 设置最后修改人备注
     *
     * @param lastModifierStr 最后修改人备注
     */
    public void setLastModifierStr(String lastModifierStr) {
        this.lastModifierStr = lastModifierStr;
    }

    /**
     * 获取客户姓名
     *
     * @return customer_name - 客户姓名
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户姓名
     *
     * @param customerName 客户姓名
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 获取客户类别
     *
     * @return customer_category - 客户类别
     */
    public String getCustomerCategory() {
        return customerCategory;
    }

    /**
     * 设置客户类别
     *
     * @param customerCategory 客户类别
     */
    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }

    /**
     * 获取联系人
     *
     * @return contact - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取联系电话
     *
     * @return telephone - 联系电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置联系电话
     *
     * @param telephone 联系电话
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}