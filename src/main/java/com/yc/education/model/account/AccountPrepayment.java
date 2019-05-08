package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_prepayment")
public class AccountPrepayment {
    /**
     * 账款--预付账款
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Description 加载表格数据
     * @Author BlueSky
     * @Date 11:26 2019/4/12
     **/
    @Transient
    private Integer no;

    /**
     * String - 付款日期
     */
    @Transient
    private String createDateStr;

    /**
     * 付款日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 付款编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 客户编号描述
     */
    @Column(name = "customer_no_str")
    private String customerNoStr;

    /**
     * 付款币别
     */
    private String currency;

    /**
     * 被转换的付款币别
     */
    @Column(name = "currency_two")
    private String currencyTwo;

    /**
     * 汇率
     */
    private String rate;

    /**
     * 付款方式
     */
    @Column(name = "payment_type")
    private String paymentType;

    /**
     * 付款金额
     */
    @Column(name = "payment_money")
    private BigDecimal paymentMoney;

    /**
     * 经办人
     */
    @Column(name = "process_people")
    private String processPeople;

    /**
     * 经办人描述
     */
    @Column(name = "process_people_str")
    private String processPeopleStr;

    /**
     * 凭证编号
     */
    @Column(name = "token_no")
    private String tokenNo;

    /**
     * 制单人
     */
    @Column(name = "made_people")
    private String madePeople;

    /**
     * 预付金额
     */
    @Column(name = "prepayment_money")
    private BigDecimal prepaymentMoney;

    /**
     * 发票号码
     */
    @Column(name = "invoice_no")
    private String invoiceNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最后修改人
     */
    @Column(name = "last_update")
    private String lastUpdate;

    /**
     * 最后修改人描述
     */
    @Column(name = "last_update_str")
    private String lastUpdateStr;

    /**
     * 审核人
     */
    private String audit;

    /**
     * 审核人描述
     */
    @Column(name = "audit_str")
    private String auditStr;

    /**
     * 单据审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取账款--预付账款
     *
     * @return id - 账款--预付账款
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款--预付账款
     *
     * @param id 账款--预付账款
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取付款日期
     *
     * @return create_date - 付款日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置付款日期
     *
     * @param createDate 付款日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取付款编号
     *
     * @return order_no - 付款编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置付款编号
     *
     * @param orderNo 付款编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * 获取客户编号描述
     *
     * @return customer_no_str - 客户编号描述
     */
    public String getCustomerNoStr() {
        return customerNoStr;
    }

    /**
     * 设置客户编号描述
     *
     * @param customerNoStr 客户编号描述
     */
    public void setCustomerNoStr(String customerNoStr) {
        this.customerNoStr = customerNoStr;
    }

    /**
     * 获取付款币别
     *
     * @return currency - 付款币别
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置付款币别
     *
     * @param currency 付款币别
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取被转换的付款币别
     *
     * @return currency_two - 被转换的付款币别
     */
    public String getCurrencyTwo() {
        return currencyTwo;
    }

    /**
     * 设置被转换的付款币别
     *
     * @param currencyTwo 被转换的付款币别
     */
    public void setCurrencyTwo(String currencyTwo) {
        this.currencyTwo = currencyTwo;
    }

    /**
     * 获取汇率
     *
     * @return rate - 汇率
     */
    public String getRate() {
        return rate;
    }

    /**
     * 设置汇率
     *
     * @param rate 汇率
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * 获取付款方式
     *
     * @return payment_type - 付款方式
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * 设置付款方式
     *
     * @param paymentType 付款方式
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 获取付款金额
     *
     * @return payment_money - 付款金额
     */
    public BigDecimal getPaymentMoney() {
        return paymentMoney;
    }

    /**
     * 设置付款金额
     *
     * @param paymentMoney 付款金额
     */
    public void setPaymentMoney(BigDecimal paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    /**
     * 获取经办人
     *
     * @return process_people - 经办人
     */
    public String getProcessPeople() {
        return processPeople;
    }

    /**
     * 设置经办人
     *
     * @param processPeople 经办人
     */
    public void setProcessPeople(String processPeople) {
        this.processPeople = processPeople;
    }

    /**
     * 获取经办人描述
     *
     * @return process_people_str - 经办人描述
     */
    public String getProcessPeopleStr() {
        return processPeopleStr;
    }

    /**
     * 设置经办人描述
     *
     * @param processPeopleStr 经办人描述
     */
    public void setProcessPeopleStr(String processPeopleStr) {
        this.processPeopleStr = processPeopleStr;
    }

    /**
     * 获取凭证编号
     *
     * @return token_no - 凭证编号
     */
    public String getTokenNo() {
        return tokenNo;
    }

    /**
     * 设置凭证编号
     *
     * @param tokenNo 凭证编号
     */
    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    /**
     * 获取制单人
     *
     * @return made_people - 制单人
     */
    public String getMadePeople() {
        return madePeople;
    }

    /**
     * 设置制单人
     *
     * @param madePeople 制单人
     */
    public void setMadePeople(String madePeople) {
        this.madePeople = madePeople;
    }

    /**
     * 获取预付金额
     *
     * @return prepayment_money - 预付金额
     */
    public BigDecimal getPrepaymentMoney() {
        return prepaymentMoney;
    }

    /**
     * 设置预付金额
     *
     * @param prepaymentMoney 预付金额
     */
    public void setPrepaymentMoney(BigDecimal prepaymentMoney) {
        this.prepaymentMoney = prepaymentMoney;
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
     * 获取最后修改人
     *
     * @return last_update - 最后修改人
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 设置最后修改人
     *
     * @param lastUpdate 最后修改人
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * 获取最后修改人描述
     *
     * @return last_update_str - 最后修改人描述
     */
    public String getLastUpdateStr() {
        return lastUpdateStr;
    }

    /**
     * 设置最后修改人描述
     *
     * @param lastUpdateStr 最后修改人描述
     */
    public void setLastUpdateStr(String lastUpdateStr) {
        this.lastUpdateStr = lastUpdateStr;
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
     * @return audit_str - 审核人描述
     */
    public String getAuditStr() {
        return auditStr;
    }

    /**
     * 设置审核人描述
     *
     * @param auditStr 审核人描述
     */
    public void setAuditStr(String auditStr) {
        this.auditStr = auditStr;
    }

    /**
     * 获取单据审核状态
     *
     * @return order_audit - 单据审核状态
     */
    public Boolean getOrderAudit() {
        return orderAudit;
    }

    /**
     * 设置单据审核状态
     *
     * @param orderAudit 单据审核状态
     */
    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
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

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}