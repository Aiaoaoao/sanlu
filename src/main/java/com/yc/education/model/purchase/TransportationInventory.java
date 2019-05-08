package com.yc.education.model.purchase;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO  在途库存
 *@Author QuZhangJing
 *@Date 11:07  2018/10/17
 *@Version 1.0
 */
@Table(name = "transportation_inventory")
public class TransportationInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 装箱单号
     */
    private String orders;

    /**
     * 发货日期
     */
    private Date senddate;

    /**
     * 发货日期
     */
    @Transient
    private String senddates;


    /**
     * 发票号码
     */
    private String invoicenum;

    /**
     * 预计到货日期
     */
    private Date comedate;

    public TransportationInventory() {
    }

    public TransportationInventory(long id,String orders, Date senddate, String invoicenum, Date comedate) {
        this.id = id;
        this.orders = orders;
        this.senddate = senddate;
        this.invoicenum = invoicenum;
        this.comedate = comedate;
    }

    public TransportationInventory(String orders, Date senddate, String invoicenum, Date comedate) {
        this.orders = orders;
        this.senddate = senddate;
        this.invoicenum = invoicenum;
        this.comedate = comedate;
    }

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
     * 获取装箱单号
     *
     * @return orders - 装箱单号
     */
    public String getOrders() {
        return orders;
    }

    /**
     * 设置装箱单号
     *
     * @param orders 装箱单号
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * 获取发货日期
     *
     * @return senddate - 发货日期
     */
    public Date getSenddate() {
        return senddate;
    }

    /**
     * 设置发货日期
     *
     * @param senddate 发货日期
     */
    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    /**
     * 获取发票号码
     *
     * @return invoicenum - 发票号码
     */
    public String getInvoicenum() {
        return invoicenum;
    }

    /**
     * 设置发票号码
     *
     * @param invoicenum 发票号码
     */
    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
    }

    /**
     * 获取预计到货日期
     *
     * @return comedate - 预计到货日期
     */
    public Date getComedate() {
        return comedate;
    }

    /**
     * 设置预计到货日期
     *
     * @param comedate 预计到货日期
     */
    public void setComedate(Date comedate) {
        this.comedate = comedate;
    }

    public String getSenddates() {
        return senddates;
    }

    public void setSenddates(String senddates) {
        this.senddates = senddates;
    }
}