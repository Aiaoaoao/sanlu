package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_sale_invoice")
public class AccountSaleInvoice {

    /**
     * String - 单据金额
     **/
    @Transient
    private String moneyStr;
    /**
     * String - 收款金额
     **/
    @Transient
    private String receiveMoneyStr;
    /**
     * String - 差额
     **/
    @Transient
    private String reduceMoneyStr;
    /**
     * String - 单据作废状态
     **/
    @Transient
    private String orderCancelStr;
    /**
     * String - no
     **/
    @Transient
    private Integer no;
    /**
     * String - 发票日期
     **/
    @Transient
    private String invoiceDateStr;

    /**
     * String - 单据审核状态
     **/
    @Transient
    private String orderAuditStr;

    /**
     * 账款--销项发票
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建日期
     */
    @Transient
    private String createDateStr;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 销售单单号
     */
    @Column(name = "sale_goods_no")
    private String saleGoodsNo;

    /**
     * 单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 发票类型
     */
    @Column(name = "invoice_type")
    private String invoiceType;

    /**
     * 发票日期
     */
    @Column(name = "invoice_date")
    private Date invoiceDate;

    /**
     * 发票号码
     */
    @Column(name = "invoice_no")
    private String invoiceNo;

    /**
     * 制单人
     */
    @Column(name = "made_people")
    private String madePeople;

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
     * 发票抬头
     */
    @Column(name = "invoice_title")
    private String invoiceTitle;

    /**
     * 发票地址
     */
    @Column(name = "invoice_address")
    private String invoiceAddress;

    /**
     * 币别
     */
    private String currency;

    /**
     * 纳税人登记号
     */
    @Column(name = "taxpayer_no")
    private String taxpayerNo;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 银行账号
     */
    @Column(name = "bank_no")
    private String bankNo;

    /**
     * 结算方式
     */
    @Column(name = "payment_method")
    private String paymentMethod;

    /**
     * 结算日期
     */
    @Column(name = "payment_date")
    private Date paymentDate;

    /**
     * 所属公司
     */
    private String company;

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
     * 订单审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    private Date addtime;

    /**
     * @Description 发票金额
     * @Author BlueSky
     * @Date 21:01 2019/5/5
     **/
    private BigDecimal money;

    /**
     * @Description 发票金额
     * @Author BlueSky
     * @Date 21:01 2019/5/5
     **/
    @Column(name = "receive_money")
    private BigDecimal receiveMoney;

    /**
     * 单据作废
     */
    @Column(name = "order_cancel")
    private Boolean orderCancel;

    public String getMoneyStr() {
        return moneyStr;
    }

    public void setMoneyStr(String moneyStr) {
        this.moneyStr = moneyStr;
    }

    public String getReceiveMoneyStr() {
        return receiveMoneyStr;
    }

    public void setReceiveMoneyStr(String receiveMoneyStr) {
        this.receiveMoneyStr = receiveMoneyStr;
    }

    public String getReduceMoneyStr() {
        return reduceMoneyStr;
    }

    public void setReduceMoneyStr(String reduceMoneyStr) {
        this.reduceMoneyStr = reduceMoneyStr;
    }

    public String getInvoiceDateStr() {
        return invoiceDateStr;
    }

    public void setInvoiceDateStr(String invoiceDateStr) {
        this.invoiceDateStr = invoiceDateStr;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public BigDecimal getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Boolean getOrderCancel() {
        return orderCancel;
    }

    public void setOrderCancel(Boolean orderCancel) {
        this.orderCancel = orderCancel;
    }

    /**
     * 获取账款--销项发票
     *
     * @return id - 账款--销项发票
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款--销项发票
     *
     * @param id 账款--销项发票
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取创建日期
     *
     * @return create_date - 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取单号
     *
     * @return order_no - 单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置单号
     *
     * @param orderNo 单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取发票类型
     *
     * @return invoice_type - 发票类型
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * 设置发票类型
     *
     * @param invoiceType 发票类型
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 获取发票日期
     *
     * @return invoice_date - 发票日期
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * 设置发票日期
     *
     * @param invoiceDate 发票日期
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
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
     * 获取发票抬头
     *
     * @return invoice_title - 发票抬头
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * 设置发票抬头
     *
     * @param invoiceTitle 发票抬头
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    /**
     * 获取发票地址
     *
     * @return invoice_address - 发票地址
     */
    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    /**
     * 设置发票地址
     *
     * @param invoiceAddress 发票地址
     */
    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
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
     * 获取纳税人登记号
     *
     * @return taxpayer_no - 纳税人登记号
     */
    public String getTaxpayerNo() {
        return taxpayerNo;
    }

    /**
     * 设置纳税人登记号
     *
     * @param taxpayerNo 纳税人登记号
     */
    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo;
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
     * @return bank_no - 银行账号
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * 设置银行账号
     *
     * @param bankNo 银行账号
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
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
     * 获取结算日期
     *
     * @return payment_date - 结算日期
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * 设置结算日期
     *
     * @param paymentDate 结算日期
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 获取所属公司
     *
     * @return company - 所属公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置所属公司
     *
     * @param company 所属公司
     */
    public void setCompany(String company) {
        this.company = company;
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
     * 获取订单审核状态
     *
     * @return order_audit - 订单审核状态
     */
    public Boolean getOrderAudit() {
        return orderAudit;
    }

    /**
     * 设置订单审核状态
     *
     * @param orderAudit 订单审核状态
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

    public String getSaleGoodsNo() {
        return saleGoodsNo;
    }

    public void setSaleGoodsNo(String saleGoodsNo) {
        this.saleGoodsNo = saleGoodsNo;
    }

    public String getOrderCancelStr() {
        return orderCancelStr;
    }

    public void setOrderCancelStr(String orderCancelStr) {
        this.orderCancelStr = orderCancelStr;
    }

    public String getOrderAuditStr() {
        return orderAuditStr;
    }

    public void setOrderAuditStr(String orderAuditStr) {
        this.orderAuditStr = orderAuditStr;
    }
}