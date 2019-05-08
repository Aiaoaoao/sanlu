package com.yc.education.model.stock;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 快递收件
 *@Author QuZhangJing
 *@Date 17:28  2018/11/9
 *@Version 1.0
 */
@Table(name = "express_collect")
public class ExpressCollect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 收件日期
     */
    private Date collectdate;

    @Transient
    private String collectdatatime;


    /**
     * 快递单号
     */
    private String collectorder;

    /**
     * 入库参考单据
     */
    private String seeorder;

    /**
     * 入库单号
     */
    private String stockorder;

    /**
     * 快递公司
     */
    private String company;

    /**
     * 寄件人名称
     */
    private String name;

    /**
     * 寄件人地址
     */
    private String address;

    /**
     * 本次运费类型
     */
    private Integer freighttype;

    /**
     * 本次运费
     */
    private Double freightprice;

    /**
     * 是否制定时已付 0、未付  1、已付
     */
    private Integer ispay;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 建档人
     */
    private String createpeople;

    /**
     * 建档日期
     */
    private String createdate;

    /**
     * 最后修改人
     */
    private String updatepeople;

    /**
     * 最后修改日期
     */
    private String updatedate;


    public ExpressCollect() {
    }


    public ExpressCollect(Date collectdate, String collectorder, String seeorder, String stockorder, String company, String name, String address, Integer freighttype, Double freightprice, Integer ispay, String remarks, String createpeople, String createdate, String updatepeople, String updatedate) {
        this.collectdate = collectdate;
        this.collectorder = collectorder;
        this.seeorder = seeorder;
        this.stockorder = stockorder;
        this.company = company;
        this.name = name;
        this.address = address;
        this.freighttype = freighttype;
        this.freightprice = freightprice;
        this.ispay = ispay;
        this.remarks = remarks;
        this.createpeople = createpeople;
        this.createdate = createdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
    }


    public ExpressCollect(long id,Date collectdate, String collectorder, String seeorder, String stockorder, String company, String name, String address, Integer freighttype, Double freightprice, Integer ispay, String remarks, String createpeople, String createdate, String updatepeople, String updatedate) {
        this.id = id;
        this.collectdate = collectdate;
        this.collectorder = collectorder;
        this.seeorder = seeorder;
        this.stockorder = stockorder;
        this.company = company;
        this.name = name;
        this.address = address;
        this.freighttype = freighttype;
        this.freightprice = freightprice;
        this.ispay = ispay;
        this.remarks = remarks;
        this.createpeople = createpeople;
        this.createdate = createdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
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
     * 获取收件日期
     *
     * @return collectdate - 收件日期
     */
    public Date getCollectdate() {
        return collectdate;
    }

    /**
     * 设置收件日期
     *
     * @param collectdate 收件日期
     */
    public void setCollectdate(Date collectdate) {
        this.collectdate = collectdate;
    }

    /**
     * 获取快递单号
     *
     * @return collectorder - 快递单号
     */
    public String getCollectorder() {
        return collectorder;
    }

    /**
     * 设置快递单号
     *
     * @param collectorder 快递单号
     */
    public void setCollectorder(String collectorder) {
        this.collectorder = collectorder;
    }

    /**
     * 获取入库参考单据
     *
     * @return seeorder - 入库参考单据
     */
    public String getSeeorder() {
        return seeorder;
    }

    /**
     * 设置入库参考单据
     *
     * @param seeorder 入库参考单据
     */
    public void setSeeorder(String seeorder) {
        this.seeorder = seeorder;
    }

    /**
     * 获取入库单号
     *
     * @return stockorder - 入库单号
     */
    public String getStockorder() {
        return stockorder;
    }

    /**
     * 设置入库单号
     *
     * @param stockorder 入库单号
     */
    public void setStockorder(String stockorder) {
        this.stockorder = stockorder;
    }

    /**
     * 获取快递公司
     *
     * @return company - 快递公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置快递公司
     *
     * @param company 快递公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取寄件人名称
     *
     * @return name - 寄件人名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置寄件人名称
     *
     * @param name 寄件人名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取寄件人地址
     *
     * @return address - 寄件人地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置寄件人地址
     *
     * @param address 寄件人地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取本次运费类型
     *
     * @return freighttype - 本次运费类型
     */
    public Integer getFreighttype() {
        return freighttype;
    }

    /**
     * 设置本次运费类型
     *
     * @param freighttype 本次运费类型
     */
    public void setFreighttype(Integer freighttype) {
        this.freighttype = freighttype;
    }

    /**
     * 获取本次运费
     *
     * @return freightprice - 本次运费
     */
    public Double getFreightprice() {
        return freightprice;
    }

    /**
     * 设置本次运费
     *
     * @param freightprice 本次运费
     */
    public void setFreightprice(Double freightprice) {
        this.freightprice = freightprice;
    }

    /**
     * 获取是否制定时已付 0、未付  1、已付
     *
     * @return ispay - 是否制定时已付 0、未付  1、已付
     */
    public Integer getIspay() {
        return ispay;
    }

    /**
     * 设置是否制定时已付 0、未付  1、已付
     *
     * @param ispay 是否制定时已付 0、未付  1、已付
     */
    public void setIspay(Integer ispay) {
        this.ispay = ispay;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取建档人
     *
     * @return createpeople - 建档人
     */
    public String getCreatepeople() {
        return createpeople;
    }

    /**
     * 设置建档人
     *
     * @param createpeople 建档人
     */
    public void setCreatepeople(String createpeople) {
        this.createpeople = createpeople;
    }

    /**
     * 获取建档日期
     *
     * @return createdate - 建档日期
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * 设置建档日期
     *
     * @param createdate 建档日期
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取最后修改人
     *
     * @return updatepeople - 最后修改人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后修改人
     *
     * @param updatepeople 最后修改人
     */
    public void setUpdatepeople(String updatepeople) {
        this.updatepeople = updatepeople;
    }

    /**
     * 获取最后修改日期
     *
     * @return updatedate - 最后修改日期
     */
    public String getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置最后修改日期
     *
     * @param updatedate 最后修改日期
     */
    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getCollectdatatime() {
        return collectdatatime;
    }

    public void setCollectdatatime(String collectdatatime) {
        this.collectdatatime = collectdatatime;
    }
}