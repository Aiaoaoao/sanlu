package com.yc.education.model.stock;

import javax.persistence.*;

/**
 * 快递寄件出库单
 */
@Table(name = "express_mail_outorder")
public class ExpressMailOutorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String outorder;

    private String orders;

    private String remarks;

    private Long mailid;

    @Transient
    private int sort;


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
     * @return outorder
     */
    public String getOutorder() {
        return outorder;
    }

    /**
     * @param outorder
     */
    public void setOutorder(String outorder) {
        this.outorder = outorder;
    }

    /**
     * @return orders
     */
    public String getOrders() {
        return orders;
    }

    /**
     * @param orders
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return mailid
     */
    public Long getMailid() {
        return mailid;
    }

    /**
     * @param mailid
     */
    public void setMailid(Long mailid) {
        this.mailid = mailid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}