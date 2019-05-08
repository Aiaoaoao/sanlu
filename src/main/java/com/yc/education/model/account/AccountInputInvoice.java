package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_input_invoice")
public class AccountInputInvoice {
    /**
     * 账款--进项发票
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Description 序号
     * @Author BlueSky
     * @Date 16:32 2019/4/12
     **/
    @Transient
    private Integer no;

    /**
     * @Description 冲款金额
     * @Author BlueSky
     * @Date 16:32 2019/4/12
     **/
    @Transient
    private String rushMoney;

    /**
     * @Description String - 发票日期
     * @Author BlueSky
     * @Date 11:58 2019/4/9
     **/
    @Transient
    private String invoiceDateStr;

    /**
     * @Description String - 制单日期
     * @Author BlueSky
     * @Date 11:58 2019/4/9
     **/
    @Transient
    private String createDateStr;

    /**
     * 制单日期
     */
    @Column(name = "create_date")
    private Date createDate;

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
    @Column(name = "invoce_no")
    private String invoceNo;

    /**
     * 制单人
     */
    @Column(name = "made_people")
    private String madePeople;

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
     * 结算日期
     */
    @Column(name = "payment_date")
    private Date paymentDate;

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
     * 订单审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * @Description 应收金额
     * @Author BlueSky
     * @Date 11:36 2019/4/26
     **/
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取账款--进项发票
     *
     * @return id - 账款--进项发票
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款--进项发票
     *
     * @param id 账款--进项发票
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
     * @return invoce_no - 发票号码
     */
    public String getInvoceNo() {
        return invoceNo;
    }

    /**
     * 设置发票号码
     *
     * @param invoceNo 发票号码
     */
    public void setInvoceNo(String invoceNo) {
        this.invoceNo = invoceNo;
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
     * 获取供应商编号
     *
     * @return supplier_no - 供应商编号
     */
    public String getSupplierNo() {
        return supplierNo;
    }

    /**
     * 设置供应商编号
     *
     * @param supplierNo 供应商编号
     */
    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    /**
     * 获取供应商编号描述
     *
     * @return supplier_no_str - 供应商编号描述
     */
    public String getSupplierNoStr() {
        return supplierNoStr;
    }

    /**
     * 设置供应商编号描述
     *
     * @param supplierNoStr 供应商编号描述
     */
    public void setSupplierNoStr(String supplierNoStr) {
        this.supplierNoStr = supplierNoStr;
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

    public String getInvoiceDateStr() {
        return invoiceDateStr;
    }

    public void setInvoiceDateStr(String invoiceDateStr) {
        this.invoiceDateStr = invoiceDateStr;
    }

    public String getRushMoney() {
        return rushMoney;
    }

    public void setRushMoney(String rushMoney) {
        this.rushMoney = rushMoney;
    }
}