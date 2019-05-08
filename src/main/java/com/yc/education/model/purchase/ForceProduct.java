package com.yc.education.model.purchase;

import javax.persistence.*;
/**
 *@Description TODO 采购---强制结案采购产品
 *@Author QuZhangJing
 *@Date 14:52  2018/10/15
 *@Version 1.0
 */
@Table(name = "force_product")
public class ForceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 强制结案编号
     */
    private Long forceid;

    /**
     * 产品编号
     */
    private String pronum;

    /**
     * 产品名称
     */
    private String proname;

    /**
     * 订单数量
     */
    private Integer ordernum;

    /**
     * 强制结案数量
     */
    private Integer forcenum;

    /**
     * 强制结案日期
     */
    private String forcedate;

    /**
     * 已强制结案
     */
    private Integer forceover;

    /**
     * 已入库
     */
    private Integer stockover;

    /**
     * 在途数量
     */
    private Integer ontheway;


    public ForceProduct() {
    }

    public ForceProduct(Long forceid, String pronum, String proname, Integer ordernum, Integer forcenum, String forcedate, Integer forceover, Integer stockover, Integer ontheway) {
        this.forceid = forceid;
        this.pronum = pronum;
        this.proname = proname;
        this.ordernum = ordernum;
        this.forcenum = forcenum;
        this.forcedate = forcedate;
        this.forceover = forceover;
        this.stockover = stockover;
        this.ontheway = ontheway;
    }

    public ForceProduct(long id,Long forceid, String pronum, String proname, Integer ordernum, Integer forcenum, String forcedate, Integer forceover, Integer stockover, Integer ontheway) {
        this.id = id;
        this.forceid = forceid;
        this.pronum = pronum;
        this.proname = proname;
        this.ordernum = ordernum;
        this.forcenum = forcenum;
        this.forcedate = forcedate;
        this.forceover = forceover;
        this.stockover = stockover;
        this.ontheway = ontheway;
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
     * 获取强制结案编号
     *
     * @return forceid - 强制结案编号
     */
    public Long getForceid() {
        return forceid;
    }

    /**
     * 设置强制结案编号
     *
     * @param forceid 强制结案编号
     */
    public void setForceid(Long forceid) {
        this.forceid = forceid;
    }

    /**
     * 获取产品编号
     *
     * @return pronum - 产品编号
     */
    public String getPronum() {
        return pronum;
    }

    /**
     * 设置产品编号
     *
     * @param pronum 产品编号
     */
    public void setPronum(String pronum) {
        this.pronum = pronum;
    }

    /**
     * 获取产品名称
     *
     * @return proname - 产品名称
     */
    public String getProname() {
        return proname;
    }

    /**
     * 设置产品名称
     *
     * @param proname 产品名称
     */
    public void setProname(String proname) {
        this.proname = proname;
    }

    /**
     * 获取订单数量
     *
     * @return ordernum - 订单数量
     */
    public Integer getOrdernum() {
        return ordernum;
    }

    /**
     * 设置订单数量
     *
     * @param ordernum 订单数量
     */
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    /**
     * 获取强制结案数量
     *
     * @return forcenum - 强制结案数量
     */
    public Integer getForcenum() {
        return forcenum;
    }

    /**
     * 设置强制结案数量
     *
     * @param forcenum 强制结案数量
     */
    public void setForcenum(Integer forcenum) {
        this.forcenum = forcenum;
    }

    /**
     * 获取强制结案日期
     *
     * @return forcedate - 强制结案日期
     */
    public String getForcedate() {
        return forcedate;
    }

    /**
     * 设置强制结案日期
     *
     * @param forcedate 强制结案日期
     */
    public void setForcedate(String forcedate) {
        this.forcedate = forcedate;
    }

    /**
     * 获取已强制结案
     *
     * @return forceover - 已强制结案
     */
    public Integer getForceover() {
        return forceover;
    }

    /**
     * 设置已强制结案
     *
     * @param forceover 已强制结案
     */
    public void setForceover(Integer forceover) {
        this.forceover = forceover;
    }

    /**
     * 获取已入库
     *
     * @return stockover - 已入库
     */
    public Integer getStockover() {
        return stockover;
    }

    /**
     * 设置已入库
     *
     * @param stockover 已入库
     */
    public void setStockover(Integer stockover) {
        this.stockover = stockover;
    }

    /**
     * 获取在途数量
     *
     * @return ontheway - 在途数量
     */
    public Integer getOntheway() {
        return ontheway;
    }

    /**
     * 设置在途数量
     *
     * @param ontheway 在途数量
     */
    public void setOntheway(Integer ontheway) {
        this.ontheway = ontheway;
    }
}