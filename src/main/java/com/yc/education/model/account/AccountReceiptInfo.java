package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_receipt_info")
public class AccountReceiptInfo {
    /**
     * 账款-收款单-详情
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 收款单id
     */
    private Long otherid;

    /**
     * 编号
     */
    private String no;

    /**
     * 客户
     */
    private String customer;

    /**
     * 未收款冲款
     */
    @Column(name = "receipt_not")
    private String receiptNot;

    /**
     * 日期
     */
    private Date addtime;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 美元
     */
    private String dollar;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 获取账款-收款单-详情
     *
     * @return id - 账款-收款单-详情
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款-收款单-详情
     *
     * @param id 账款-收款单-详情
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取收款单id
     *
     * @return otherid - 收款单id
     */
    public Long getOtherid() {
        return otherid;
    }

    /**
     * 设置收款单id
     *
     * @param otherid 收款单id
     */
    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    /**
     * 获取编号
     *
     * @return no - 编号
     */
    public String getNo() {
        return no;
    }

    /**
     * 设置编号
     *
     * @param no 编号
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取客户
     *
     * @return customer - 客户
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * 设置客户
     *
     * @param customer 客户
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * 获取未收款冲款
     *
     * @return receipt_not - 未收款冲款
     */
    public String getReceiptNot() {
        return receiptNot;
    }

    /**
     * 设置未收款冲款
     *
     * @param receiptNot 未收款冲款
     */
    public void setReceiptNot(String receiptNot) {
        this.receiptNot = receiptNot;
    }

    /**
     * 获取日期
     *
     * @return addtime - 日期
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置日期
     *
     * @param addtime 日期
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取美元
     *
     * @return dollar - 美元
     */
    public String getDollar() {
        return dollar;
    }

    /**
     * 设置美元
     *
     * @param dollar 美元
     */
    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}