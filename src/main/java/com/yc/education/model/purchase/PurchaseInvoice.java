package com.yc.education.model.purchase;

import javax.persistence.*;
/**
 *@Description TODO  应付账款（采购账款）
 *@Author QuZhangJing
 *@Date 14:44  2018/12/10
 *@Version 1.0
 */
@Table(name = "purchase_invoice")
public class PurchaseInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 供应商编号
     */
    private String supplierorder;

    /**
     * 供应商简称
     */
    private String supplierdes;

    /**
     * 发票号码
     */
    private String invoicenumber;

    /**
     * 发票日期
     */
    private String invoicedata;

    /**
     * 开票金额
     */
    private Double invoiceprice;

    /**
     * 已冲金额
     */
    private Double offsetprice;

    /**
     * 应付金额
     */
    private Double meetprice;

    /**
     * 采购订单编号
     */
    private String purchaseorder;

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
     * 获取供应商编号
     *
     * @return supplierorder - 供应商编号
     */
    public String getSupplierorder() {
        return supplierorder;
    }

    /**
     * 设置供应商编号
     *
     * @param supplierorder 供应商编号
     */
    public void setSupplierorder(String supplierorder) {
        this.supplierorder = supplierorder;
    }

    /**
     * 获取供应商简称
     *
     * @return supplierdes - 供应商简称
     */
    public String getSupplierdes() {
        return supplierdes;
    }

    /**
     * 设置供应商简称
     *
     * @param supplierdes 供应商简称
     */
    public void setSupplierdes(String supplierdes) {
        this.supplierdes = supplierdes;
    }

    /**
     * 获取发票号码
     *
     * @return invoicenumber - 发票号码
     */
    public String getInvoicenumber() {
        return invoicenumber;
    }

    /**
     * 设置发票号码
     *
     * @param invoicenumber 发票号码
     */
    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    /**
     * 获取发票日期
     *
     * @return invoicedata - 发票日期
     */
    public String getInvoicedata() {
        return invoicedata;
    }

    /**
     * 设置发票日期
     *
     * @param invoicedata 发票日期
     */
    public void setInvoicedata(String invoicedata) {
        this.invoicedata = invoicedata;
    }

    /**
     * 获取开票金额
     *
     * @return invoiceprice - 开票金额
     */
    public Double getInvoiceprice() {
        return invoiceprice;
    }

    /**
     * 设置开票金额
     *
     * @param invoiceprice 开票金额
     */
    public void setInvoiceprice(Double invoiceprice) {
        this.invoiceprice = invoiceprice;
    }

    /**
     * 获取已冲金额
     *
     * @return offsetprice - 已冲金额
     */
    public Double getOffsetprice() {
        return offsetprice;
    }

    /**
     * 设置已冲金额
     *
     * @param offsetprice 已冲金额
     */
    public void setOffsetprice(Double offsetprice) {
        this.offsetprice = offsetprice;
    }

    /**
     * 获取应付金额
     *
     * @return meetprice - 应付金额
     */
    public Double getMeetprice() {
        return meetprice;
    }

    /**
     * 设置应付金额
     *
     * @param meetprice 应付金额
     */
    public void setMeetprice(Double meetprice) {
        this.meetprice = meetprice;
    }

    /**
     * 获取采购订单编号
     *
     * @return purchaseorder - 采购订单编号
     */
    public String getPurchaseorder() {
        return purchaseorder;
    }

    /**
     * 设置采购订单编号
     *
     * @param purchaseorder 采购订单编号
     */
    public void setPurchaseorder(String purchaseorder) {
        this.purchaseorder = purchaseorder;
    }
}