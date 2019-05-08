package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_receivable_rush")
public class AccountReceivableRush {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 应收账款冲账id
     */
    private Long otherid;

    /**
     * 销项发票编号
     */
    @Column(name = "sale_no")
    private String saleNo;

    /**
     * 发票号码
     */
    @Column(name = "invoice_no")
    private String invoiceNo;

    /**
     * 账款日期
     */
    @Column(name = "account_date")
    private Date accountDate;

    /**
     * 单据总金额
     */
    @Column(name = "total_money")
    private BigDecimal totalMoney;

    /**
     * 本次应收
     */
    private BigDecimal receive;

    /**
     * 本次折让
     */
    private String discount;

    /**
     * 已冲减金
     */
    @Column(name = "rush_money")
    private BigDecimal rushMoney;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取应收账款冲账id
     *
     * @return otherid - 应收账款冲账id
     */
    public Long getOtherid() {
        return otherid;
    }

    /**
     * 设置应收账款冲账id
     *
     * @param otherid 应收账款冲账id
     */
    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    /**
     * 获取销项发票编号
     *
     * @return sale_no - 销项发票编号
     */
    public String getSaleNo() {
        return saleNo;
    }

    /**
     * 设置销项发票编号
     *
     * @param saleNo 销项发票编号
     */
    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
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
     * 获取账款日期
     *
     * @return account_date - 账款日期
     */
    public Date getAccountDate() {
        return accountDate;
    }

    /**
     * 设置账款日期
     *
     * @param accountDate 账款日期
     */
    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    /**
     * 获取单据总金额
     *
     * @return total_money - 单据总金额
     */
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    /**
     * 设置单据总金额
     *
     * @param totalMoney 单据总金额
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 获取本次应收
     *
     * @return receive - 本次应收
     */
    public BigDecimal getReceive() {
        return receive;
    }

    /**
     * 设置本次应收
     *
     * @param receive 本次应收
     */
    public void setReceive(BigDecimal receive) {
        this.receive = receive;
    }

    /**
     * 获取本次折让
     *
     * @return discount - 本次折让
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * 设置本次折让
     *
     * @param discount 本次折让
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     * 获取已冲减金
     *
     * @return rush_money - 已冲减金
     */
    public BigDecimal getRushMoney() {
        return rushMoney;
    }

    /**
     * 设置已冲减金
     *
     * @param rushMoney 已冲减金
     */
    public void setRushMoney(BigDecimal rushMoney) {
        this.rushMoney = rushMoney;
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