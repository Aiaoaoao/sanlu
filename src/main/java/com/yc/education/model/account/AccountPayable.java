package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_payable")
public class AccountPayable {
    /**
     * 账款--应付账款冲账
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 表格加序号
     */
    @Transient
    private Integer no;

    /**
     * 冲账日期 String
     */
    @Transient
    private String createDateStr;

    /**
     * 冲账日期
     */
    @Column(name = "rush_date")
    private Date rushDate;

    /**
     * 冲账编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 早起单据
     */
    @Column(name = "early_order")
    private Boolean earlyOrder;

    /**
     * 供应商编号
     */
    @Column(name = "supplier_no")
    private String supplierNo;

    /**
     * 供应商编号描述
     */
    @Column(name = "supplier_no_str")
    private String supplierNoStr;

    /**
     * 币别
     */
    private String currency;

    /**
     * 被冲但觉币别
     */
    @Column(name = "rush_currency")
    private String rushCurrency;

    /**
     * 汇率
     */
    @Column(name = "exchange_rate")
    private String exchangeRate;

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
     * 制单人
     */
    @Column(name = "made_poeple")
    private String madePoeple;

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
     * @Description 账款累计
     * @Author BlueSky
     * @Date 21:51 2019/4/27
     **/
    private BigDecimal money;

    public Boolean getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取账款--应付账款冲账
     *
     * @return id - 账款--应付账款冲账
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款--应付账款冲账
     *
     * @param id 账款--应付账款冲账
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
     * 获取早起单据
     *
     * @return early_order - 早起单据
     */
    public Boolean getEarlyOrder() {
        return earlyOrder;
    }

    /**
     * 设置早起单据
     *
     * @param earlyOrder 早起单据
     */
    public void setEarlyOrder(Boolean earlyOrder) {
        this.earlyOrder = earlyOrder;
    }

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public String getSupplierNoStr() {
        return supplierNoStr;
    }

    public void setSupplierNoStr(String supplierNoStr) {
        this.supplierNoStr = supplierNoStr;
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
     * 获取被冲但觉币别
     *
     * @return rush_currency - 被冲但觉币别
     */
    public String getRushCurrency() {
        return rushCurrency;
    }

    /**
     * 设置被冲但觉币别
     *
     * @param rushCurrency 被冲但觉币别
     */
    public void setRushCurrency(String rushCurrency) {
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
     * 获取制单人
     *
     * @return made_poeple - 制单人
     */
    public String getMadePoeple() {
        return madePoeple;
    }

    /**
     * 设置制单人
     *
     * @param madePoeple 制单人
     */
    public void setMadePoeple(String madePoeple) {
        this.madePoeple = madePoeple;
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