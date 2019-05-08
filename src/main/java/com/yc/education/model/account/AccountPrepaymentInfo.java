package com.yc.education.model.account;

import java.util.Date;
import javax.persistence.*;

@Table(name = "account_prepayment_info")
public class AccountPrepaymentInfo {
    /**
     * 账款--预付款
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 预付款id
     */
    private Long otherid;

    /**
     * 预付款账号
     */
    @Column(name = "prepayment_account")
    private String prepaymentAccount;

    /**
     * 供应商简称
     */
    @Column(name = "supplier_short")
    private String supplierShort;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 单据审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    /**
     * 装箱单号
     */
    @Column(name = "enter_box")
    private String enterBox;


    /**
     * 添加时间
     */
    private Date addtime;


    public String getEnterBox() {
        return enterBox;
    }

    public void setEnterBox(String enterBox) {
        this.enterBox = enterBox;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取账款--预付款
     *
     * @return id - 账款--预付款
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款--预付款
     *
     * @param id 账款--预付款
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取预付款id
     *
     * @return otherid - 预付款id
     */
    public Long getOtherid() {
        return otherid;
    }

    /**
     * 设置预付款id
     *
     * @param otherid 预付款id
     */
    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    /**
     * 获取预付款账号
     *
     * @return prepayment_account - 预付款账号
     */
    public String getPrepaymentAccount() {
        return prepaymentAccount;
    }

    /**
     * 设置预付款账号
     *
     * @param prepaymentAccount 预付款账号
     */
    public void setPrepaymentAccount(String prepaymentAccount) {
        this.prepaymentAccount = prepaymentAccount;
    }

    /**
     * 获取供应商简称
     *
     * @return supplier_short - 供应商简称
     */
    public String getSupplierShort() {
        return supplierShort;
    }

    /**
     * 设置供应商简称
     *
     * @param supplierShort 供应商简称
     */
    public void setSupplierShort(String supplierShort) {
        this.supplierShort = supplierShort;
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
}