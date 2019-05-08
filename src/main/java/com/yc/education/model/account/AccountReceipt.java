package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


@Table(name = "account_receipt")
public class AccountReceipt {
    /**
     * 账款-收款单
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * no
     */
    @Transient
    private Integer no;

    /**
     * string 格式时间
     */
    @Transient
    private String createDateStr;
    /**
     * string 未收款冲账
     */
    @Transient
    private String notReceiptMoneyStr;
    /**
     * string 单据审核状态
     */
    @Transient
    private String orderAuditStr;
    /**
     * string 金额
     */
    @Transient
    private String moneyStr;

    /**
     * 收款日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 收款编号
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
     * 收款币别
     */
    private String currency;

    /**
     * 交易类型
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * 汇率
     */
    private String rate;

    /**
     * 收款方式（支票、现金）
     */
    @Column(name = "receipt_method")
    private String receiptMethod;

    /**
     * 收款类型（收款、退款）
     */
    @Column(name = "receipt_type")
    private String receiptType;

    /**
     * 收款金额
     */
    @Column(name = "receipt_money")
    private String receiptMoney;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 收款人
     */
    @Column(name = "receipt_people")
    private String receiptPeople;

    /**
     * 收款人描述
     */
    @Column(name = "receipt_people_str")
    private String receiptPeopleStr;

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
     * 凭证日期
     */
    @Column(name = "token_date")
    private Date tokenDate;

    /**
     * 备注
     */
    private String remark;

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
     * 未收款冲账
     */
    @Column(name = "not_receipt_money")
    private Boolean notReceiptMoney;

    /**
     * 单据审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    /**
     * @Description 冲账余额
     * @Author BlueSky
     * @Date 16:29 2019/5/6
     **/
    private BigDecimal balance;

    /**
     * 添加时间
     */
    private Date addtime;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getNotReceiptMoney() {
        return notReceiptMoney;
    }

    public void setNotReceiptMoney(Boolean notReceiptMoney) {
        this.notReceiptMoney = notReceiptMoney;
    }

    /**
     * 获取账款-收款单
     *
     * @return id - 账款-收款单
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款-收款单
     *
     * @param id 账款-收款单
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取收款日期
     *
     * @return create_date - 收款日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置收款日期
     *
     * @param createDate 收款日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取收款编号
     *
     * @return order_no - 收款编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置收款编号
     *
     * @param orderNo 收款编号
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
     * 获取收款币别
     *
     * @return currency - 收款币别
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置收款币别
     *
     * @param currency 收款币别
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取交易类型
     *
     * @return currency_type - 交易类型
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * 设置交易类型
     *
     * @param currencyType 交易类型
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
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
     * 获取收款方式（支票、现金）
     *
     * @return receipt_method - 收款方式（支票、现金）
     */
    public String getReceiptMethod() {
        return receiptMethod;
    }

    /**
     * 设置收款方式（支票、现金）
     *
     * @param receiptMethod 收款方式（支票、现金）
     */
    public void setReceiptMethod(String receiptMethod) {
        this.receiptMethod = receiptMethod;
    }

    /**
     * 获取收款类型（收款、退款）
     *
     * @return receipt_type - 收款类型（收款、退款）
     */
    public String getReceiptType() {
        return receiptType;
    }

    /**
     * 设置收款类型（收款、退款）
     *
     * @param receiptType 收款类型（收款、退款）
     */
    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    /**
     * 获取收款金额
     *
     * @return receipt_money - 收款金额
     */
    public String getReceiptMoney() {
        return receiptMoney;
    }

    /**
     * 设置收款金额
     *
     * @param receiptMoney 收款金额
     */
    public void setReceiptMoney(String receiptMoney) {
        this.receiptMoney = receiptMoney;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取收款人
     *
     * @return receipt_people - 收款人
     */
    public String getReceiptPeople() {
        return receiptPeople;
    }

    /**
     * 设置收款人
     *
     * @param receiptPeople 收款人
     */
    public void setReceiptPeople(String receiptPeople) {
        this.receiptPeople = receiptPeople;
    }

    /**
     * 获取收款人描述
     *
     * @return receipt_people_str - 收款人描述
     */
    public String getReceiptPeopleStr() {
        return receiptPeopleStr;
    }

    /**
     * 设置收款人描述
     *
     * @param receiptPeopleStr 收款人描述
     */
    public void setReceiptPeopleStr(String receiptPeopleStr) {
        this.receiptPeopleStr = receiptPeopleStr;
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
     * 获取凭证日期
     *
     * @return token_date - 凭证日期
     */
    public Date getTokenDate() {
        return tokenDate;
    }

    /**
     * 设置凭证日期
     *
     * @param tokenDate 凭证日期
     */
    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
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
     * @return order_audit
     */
    public Boolean getOrderAudit() {
        return orderAudit;
    }

    /**
     * @param orderAudit
     */
    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
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

    public String getNotReceiptMoneyStr() {
        return notReceiptMoneyStr;
    }

    public void setNotReceiptMoneyStr(String notReceiptMoneyStr) {
        this.notReceiptMoneyStr = notReceiptMoneyStr;
    }

    public String getOrderAuditStr() {
        return orderAuditStr;
    }

    public void setOrderAuditStr(String orderAuditStr) {
        this.orderAuditStr = orderAuditStr;
    }

    public String getMoneyStr() {
        return moneyStr;
    }

    public void setMoneyStr(String moneyStr) {
        this.moneyStr = moneyStr;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}