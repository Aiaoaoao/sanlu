package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_payable_info")
public class AccountPayableInfo {
    /**
     * 应付账款冲账--收款方式详情
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 应付账款冲账id
     */
    private Long otherid;

    /**
     * 付款方式
     */
    @Column(name = "payment_method")
    private String paymentMethod;

    /**
     * 付款金额
     */
    @Column(name = "payment_money")
    private BigDecimal paymentMoney;

    /**
     * 供应商
     */
    private String supplier;

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
     * 创建时间
     */
    private Date createtime;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取应付账款冲账--收款方式详情
     *
     * @return id - 应付账款冲账--收款方式详情
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置应付账款冲账--收款方式详情
     *
     * @param id 应付账款冲账--收款方式详情
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取应付账款冲账id
     *
     * @return otherid - 应付账款冲账id
     */
    public Long getOtherid() {
        return otherid;
    }

    /**
     * 设置应付账款冲账id
     *
     * @param otherid 应付账款冲账id
     */
    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    /**
     * 获取付款方式
     *
     * @return payment_method - 付款方式
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 设置付款方式
     *
     * @param paymentMethod 付款方式
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
     * 获取供应商
     *
     * @return supplier - 供应商
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * 设置供应商
     *
     * @param supplier 供应商
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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