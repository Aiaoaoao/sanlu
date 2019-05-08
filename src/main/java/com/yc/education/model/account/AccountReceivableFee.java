package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_receivable_fee")
public class AccountReceivableFee {
    /**
     * 应收账款冲款-收款方式及收费金额
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 应收账款冲账id
     */
    private Long otherid;

    /**
     * 收款单号
     */
    @Column(name = "receive_no")
    private String receiveNo;

    /**
     * 可冲金额
     */
    @Column(name = "rush_money_can")
    private BigDecimal rushMoneyCan;

    /**
     * 本次冲减
     */
    @Column(name = "rush_money_now")
    private BigDecimal rushMoneyNow;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取应收账款冲款-收款方式及收费金额
     *
     * @return id - 应收账款冲款-收款方式及收费金额
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置应收账款冲款-收款方式及收费金额
     *
     * @param id 应收账款冲款-收款方式及收费金额
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
     * 获取收款单号
     *
     * @return receive_no - 收款单号
     */
    public String getReceiveNo() {
        return receiveNo;
    }

    /**
     * 设置收款单号
     *
     * @param receiveNo 收款单号
     */
    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    /**
     * 获取可冲金额
     *
     * @return rush_money_can - 可冲金额
     */
    public BigDecimal getRushMoneyCan() {
        return rushMoneyCan;
    }

    /**
     * 设置可冲金额
     *
     * @param rushMoneyCan 可冲金额
     */
    public void setRushMoneyCan(BigDecimal rushMoneyCan) {
        this.rushMoneyCan = rushMoneyCan;
    }

    /**
     * 获取本次冲减
     *
     * @return rush_money_now - 本次冲减
     */
    public BigDecimal getRushMoneyNow() {
        return rushMoneyNow;
    }

    /**
     * 设置本次冲减
     *
     * @param rushMoneyNow 本次冲减
     */
    public void setRushMoneyNow(BigDecimal rushMoneyNow) {
        this.rushMoneyNow = rushMoneyNow;
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
}