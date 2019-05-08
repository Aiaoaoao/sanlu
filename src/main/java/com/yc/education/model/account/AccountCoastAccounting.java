package com.yc.education.model.account;

import java.util.Date;
import javax.persistence.*;

@Table(name = "account_coast_accounting")
public class AccountCoastAccounting {

    /**
     * 审核时间
     */
    @Transient
    private String auditDateStr;

    public String getAuditDateStr() {
        return auditDateStr;
    }

    public void setAuditDateStr(String auditDateStr) {
        this.auditDateStr = auditDateStr;
    }

    /**
     * 账款--成本核算
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 入库单号
     */
    @Column(name = "storage_in_no")
    private String storageInNo;

    /**
     * 装箱单号
     */
    @Column(name = "install_no")
    private String installNo;

    /**
     * 审核人
     */
    private String audit;

    /**
     * 审核人描述
     */
    @Column(name = "audit_date")
    private Date auditDate;

    /**
     * 入库备注
     */
    @Column(name = "storage_in_remark")
    private String storageInRemark;

    /**
     * 核算人
     */
    @Column(name = "verify_people")
    private String verifyPeople;

    /**
     * 核算人描述
     */
    @Column(name = "verify_people_str")
    private String verifyPeopleStr;

    /**
     * 发票号码
     */
    @Column(name = "invoice_no")
    private String invoiceNo;

    private Integer currency;

    /**
     * 报关费
     */
    @Column(name = "customs_fee")
    private String customsFee;

    /**
     * 代理费
     */
    @Column(name = "proxy_fee")
    private String proxyFee;

    /**
     * 运费
     */
    @Column(name = "carriage_fee")
    private String carriageFee;

    /**
     * 手续费
     */
    @Column(name = "poundage_fee")
    private String poundageFee;

    /**
     * 其它费用
     */
    @Column(name = "other_fee")
    private String otherFee;

    /**
     * 贷款合计
     */
    @Column(name = "loan_total")
    private String loanTotal;

    /**
     * 税款合计
     */
    @Column(name = "tax_total")
    private String taxTotal;

    /**
     * 价税合计
     */
    @Column(name = "price_tax_total")
    private String priceTaxTotal;

    /**
     * 合计
     */
    private String total;

    /**
     * 当天汇率
     */
    @Column(name = "exchange_rate")
    private String exchangeRate;

    /**
     * 当天汇率描述
     */
    @Column(name = "exchange_currency")
    private String exchangeCurrency;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 订单审核状态
     */
    private Boolean orderAudit;

    public Boolean getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }


    /**
     * 获取账款--成本核算
     *
     * @return id - 账款--成本核算
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款--成本核算
     *
     * @param id 账款--成本核算
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取入库单号
     *
     * @return storage_in_no - 入库单号
     */
    public String getStorageInNo() {
        return storageInNo;
    }

    /**
     * 设置入库单号
     *
     * @param storageInNo 入库单号
     */
    public void setStorageInNo(String storageInNo) {
        this.storageInNo = storageInNo;
    }

    /**
     * 获取装箱单号
     *
     * @return install_no - 装箱单号
     */
    public String getInstallNo() {
        return installNo;
    }

    /**
     * 设置装箱单号
     *
     * @param installNo 装箱单号
     */
    public void setInstallNo(String installNo) {
        this.installNo = installNo;
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
     * 获取审核人描述
     *
     * @return audit_date - 审核人描述
     */
    public Date getAuditDate() {
        return auditDate;
    }

    /**
     * 设置审核人描述
     *
     * @param auditDate 审核人描述
     */
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * 获取入库备注
     *
     * @return storage_in_remark - 入库备注
     */
    public String getStorageInRemark() {
        return storageInRemark;
    }

    /**
     * 设置入库备注
     *
     * @param storageInRemark 入库备注
     */
    public void setStorageInRemark(String storageInRemark) {
        this.storageInRemark = storageInRemark;
    }

    /**
     * 获取核算人
     *
     * @return verify_people - 核算人
     */
    public String getVerifyPeople() {
        return verifyPeople;
    }

    /**
     * 设置核算人
     *
     * @param verifyPeople 核算人
     */
    public void setVerifyPeople(String verifyPeople) {
        this.verifyPeople = verifyPeople;
    }

    /**
     * 获取核算人描述
     *
     * @return verify_people_str - 核算人描述
     */
    public String getVerifyPeopleStr() {
        return verifyPeopleStr;
    }

    /**
     * 设置核算人描述
     *
     * @param verifyPeopleStr 核算人描述
     */
    public void setVerifyPeopleStr(String verifyPeopleStr) {
        this.verifyPeopleStr = verifyPeopleStr;
    }

    /**
     * 获取发票号码
     *
     * @return invoice_no - 发票号码
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * 设置发票号码
     *
     * @param invoiceNo 发票号码
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * @return currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * @param currency
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * 获取报关费
     *
     * @return customs_fee - 报关费
     */
    public String getCustomsFee() {
        return customsFee;
    }

    /**
     * 设置报关费
     *
     * @param customsFee 报关费
     */
    public void setCustomsFee(String customsFee) {
        this.customsFee = customsFee;
    }

    /**
     * 获取代理费
     *
     * @return proxy_fee - 代理费
     */
    public String getProxyFee() {
        return proxyFee;
    }

    /**
     * 设置代理费
     *
     * @param proxyFee 代理费
     */
    public void setProxyFee(String proxyFee) {
        this.proxyFee = proxyFee;
    }

    /**
     * 获取运费
     *
     * @return carriage_fee - 运费
     */
    public String getCarriageFee() {
        return carriageFee;
    }

    /**
     * 设置运费
     *
     * @param carriageFee 运费
     */
    public void setCarriageFee(String carriageFee) {
        this.carriageFee = carriageFee;
    }

    /**
     * 获取手续费
     *
     * @return poundage_fee - 手续费
     */
    public String getPoundageFee() {
        return poundageFee;
    }

    /**
     * 设置手续费
     *
     * @param poundageFee 手续费
     */
    public void setPoundageFee(String poundageFee) {
        this.poundageFee = poundageFee;
    }

    /**
     * 获取其它费用
     *
     * @return other_fee - 其它费用
     */
    public String getOtherFee() {
        return otherFee;
    }

    /**
     * 设置其它费用
     *
     * @param otherFee 其它费用
     */
    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    /**
     * 获取贷款合计
     *
     * @return loan_total - 贷款合计
     */
    public String getLoanTotal() {
        return loanTotal;
    }

    /**
     * 设置贷款合计
     *
     * @param loanTotal 贷款合计
     */
    public void setLoanTotal(String loanTotal) {
        this.loanTotal = loanTotal;
    }

    /**
     * 获取税款合计
     *
     * @return tax_total - 税款合计
     */
    public String getTaxTotal() {
        return taxTotal;
    }

    /**
     * 设置税款合计
     *
     * @param taxTotal 税款合计
     */
    public void setTaxTotal(String taxTotal) {
        this.taxTotal = taxTotal;
    }

    /**
     * 获取价税合计
     *
     * @return price_tax_total - 价税合计
     */
    public String getPriceTaxTotal() {
        return priceTaxTotal;
    }

    /**
     * 设置价税合计
     *
     * @param priceTaxTotal 价税合计
     */
    public void setPriceTaxTotal(String priceTaxTotal) {
        this.priceTaxTotal = priceTaxTotal;
    }

    /**
     * 获取合计
     *
     * @return total - 合计
     */
    public String getTotal() {
        return total;
    }

    /**
     * 设置合计
     *
     * @param total 合计
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * 获取当天汇率
     *
     * @return exchange_rate - 当天汇率
     */
    public String getExchangeRate() {
        return exchangeRate;
    }

    /**
     * 设置当天汇率
     *
     * @param exchangeRate 当天汇率
     */
    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * 获取当天汇率描述
     *
     * @return exchange_currency - 当天汇率描述
     */
    public String getExchangeCurrency() {
        return exchangeCurrency;
    }

    /**
     * 设置当天汇率描述
     *
     * @param exchangeCurrency 当天汇率描述
     */
    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
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