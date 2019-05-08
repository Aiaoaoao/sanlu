package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_receivable")
public class AccountReceivable {

    /**
     * String - 编号
     */
    @Transient
    private Integer no;

    /**
     * String - 收款金额
     */
    @Transient
    private String receiveMoneyStr;
    /**
     * String - 可冲金额
     */
    @Transient
    private String canRushStr;
    /**
     * String - 冲款日期
     */
    @Transient
    private String rushDateStr;
    /**
     * String - 订单审核状态
     */
    @Transient
    private String orderAuditStr;

    /**
     * 账款-应收账款冲款
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 冲账日期
     */
    @Column(name = "rush_date")
    private Date rushDate;

    /**
     * 冲款编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 前期单据
     */
    @Column(name = "early_order")
    private Boolean earlyOrder;

    /**
     * 前期余额
     */
    @Column(name = "early_balance")
    private String earlyBalance;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 客户编号描述
     */
    @Column(name = "customer_str")
    private String customerStr;

    /**
     * 币别
     */
    private Integer currency;

    /**
     * 被冲款币别
     */
    @Column(name = "rush_currency")
    private Integer rushCurrency;

    /**
     * 汇率
     */
    @Column(name = "exchange_rate")
    private String exchangeRate;

    /**
     * 收款人
     */
    @Column(name = "receive_people")
    private Long receivePeople;

    /**
     * 收款人描述
     */
    @Column(name = "receive_people_str")
    private String receivePeopleStr;

    /**
     * 制单人
     */
    @Column(name = "made_people")
    private String madePeople;

    /**
     * 收款方式
     */
    @Column(name = "receive_method")
    private Integer receiveMethod;

    /**
     * 收款金额
     */
    @Column(name = "receive_money")
    private BigDecimal receiveMoney;

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
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 订单审核状态
     */
    private Boolean orderAudit;

    /**
     * 可冲金额
     */
    @Column(name = "can_rush")
    private BigDecimal canRush;

    public Boolean getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }

    public BigDecimal getCanRush() {
        return canRush;
    }

    public void setCanRush(BigDecimal canRush) {
        this.canRush = canRush;
    }

    /**
     * 获取账款-应收账款冲款
     *
     * @return id - 账款-应收账款冲款
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款-应收账款冲款
     *
     * @param id 账款-应收账款冲款
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取冲账日期
     *
     * @return rush_date - 冲账日期
     */
    public Date getRushDate() {
        return rushDate;
    }

    /**
     * 设置冲账日期
     *
     * @param rushDate 冲账日期
     */
    public void setRushDate(Date rushDate) {
        this.rushDate = rushDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取前期单据
     *
     * @return early_order - 前期单据
     */
    public Boolean getEarlyOrder() {
        return earlyOrder;
    }

    /**
     * 设置前期单据
     *
     * @param earlyOrder 前期单据
     */
    public void setEarlyOrder(Boolean earlyOrder) {
        this.earlyOrder = earlyOrder;
    }

    /**
     * 获取前期余额
     *
     * @return early_balance - 前期余额
     */
    public String getEarlyBalance() {
        return earlyBalance;
    }

    /**
     * 设置前期余额
     *
     * @param earlyBalance 前期余额
     */
    public void setEarlyBalance(String earlyBalance) {
        this.earlyBalance = earlyBalance;
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
     * @return customer_str - 客户编号描述
     */
    public String getCustomerStr() {
        return customerStr;
    }

    /**
     * 设置客户编号描述
     *
     * @param customerStr 客户编号描述
     */
    public void setCustomerStr(String customerStr) {
        this.customerStr = customerStr;
    }

    /**
     * 获取币别
     *
     * @return currency - 币别
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * 设置币别
     *
     * @param currency 币别
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * 获取被冲款币别
     *
     * @return rush_currency - 被冲款币别
     */
    public Integer getRushCurrency() {
        return rushCurrency;
    }

    /**
     * 设置被冲款币别
     *
     * @param rushCurrency 被冲款币别
     */
    public void setRushCurrency(Integer rushCurrency) {
        this.rushCurrency = rushCurrency;
    }

    /**
     * 获取汇率
     *
     * @return exchange_rate - 汇率
     */
    public String getExchangeRate() {
        return exchangeRate;
    }

    /**
     * 设置汇率
     *
     * @param exchangeRate 汇率
     */
    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * 获取收款人
     *
     * @return receive_people - 收款人
     */
    public Long getReceivePeople() {
        return receivePeople;
    }

    /**
     * 设置收款人
     *
     * @param receivePeople 收款人
     */
    public void setReceivePeople(Long receivePeople) {
        this.receivePeople = receivePeople;
    }

    /**
     * 获取收款人描述
     *
     * @return receive_people_str - 收款人描述
     */
    public String getReceivePeopleStr() {
        return receivePeopleStr;
    }

    /**
     * 设置收款人描述
     *
     * @param receivePeopleStr 收款人描述
     */
    public void setReceivePeopleStr(String receivePeopleStr) {
        this.receivePeopleStr = receivePeopleStr;
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
     * 获取收款方式
     *
     * @return receive_method - 收款方式
     */
    public Integer getReceiveMethod() {
        return receiveMethod;
    }

    /**
     * 设置收款方式
     *
     * @param receiveMethod 收款方式
     */
    public void setReceiveMethod(Integer receiveMethod) {
        this.receiveMethod = receiveMethod;
    }

    /**
     * @return receive_money
     */
    public BigDecimal getReceiveMoney() {
        return receiveMoney;
    }

    /**
     * @param receiveMoney
     */
    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
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

    public String getRushDateStr() {
        return rushDateStr;
    }

    public void setRushDateStr(String rushDateStr) {
        this.rushDateStr = rushDateStr;
    }

    public String getOrderAuditStr() {
        return orderAuditStr;
    }

    public void setOrderAuditStr(String orderAuditStr) {
        this.orderAuditStr = orderAuditStr;
    }

    public String getReceiveMoneyStr() {
        return receiveMoneyStr;
    }

    public void setReceiveMoneyStr(String receiveMoneyStr) {
        this.receiveMoneyStr = receiveMoneyStr;
    }

    public String getCanRushStr() {
        return canRushStr;
    }

    public void setCanRushStr(String canRushStr) {
        this.canRushStr = canRushStr;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}