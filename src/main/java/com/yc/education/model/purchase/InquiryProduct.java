package com.yc.education.model.purchase;

import javax.persistence.*;
/**
 *@Description TODO 报价产品
 *@Author QuZhangJing
 *@Date 11:32  2018/9/28
 *@Version 1.0
 */
@Table(name = "inquiry_product")
public class InquiryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品编号
     */
    private String proisnum;


    /**
     * 产品序号
     */
    @Transient
    private int sort;


    /**
     * 产品名称
     */
    private String proname;

    /**
     * 品类
     */
    private String protype;

    /**
     * 产品数量
     */
    private Integer pronum;

    /**
     * 产品单位
     */
    private String prounit;

    /**
     * 产品价格
     */
    private Double proprice;

    /**
     * 金额
     */
    private Double totalprice;

    /**
     * 备注
     */
    private String proremark;


    /**
     * 期望交期
     */
    private String expecteddate;


    /**
     * 询价单编号
     */
    private Long inquiryid;

    /**
     * 询价单
     */
    @Transient
    private InquiryOrder inquiryOrders;

    public InquiryProduct() {

    }

    public InquiryProduct(String proisnum, String proname, String protype, Integer pronum, String prounit, Double proprice, Double totalprice, String proremark, Long inquiryid,String expecteddate) {
        this.proisnum = proisnum;
        this.proname = proname;
        this.protype = protype;
        this.pronum = pronum;
        this.prounit = prounit;
        this.proprice = proprice;
        this.totalprice = totalprice;
        this.proremark = proremark;
        this.inquiryid = inquiryid;
        this.expecteddate = expecteddate;
    }

    public InquiryProduct(long id,String proisnum, String proname, String protype, Integer pronum, String prounit, Double proprice, Double totalprice, String proremark, Long inquiryid,String expecteddate) {
        this.id = id;
        this.proisnum = proisnum;
        this.proname = proname;
        this.protype = protype;
        this.pronum = pronum;
        this.prounit = prounit;
        this.proprice = proprice;
        this.totalprice = totalprice;
        this.proremark = proremark;
        this.inquiryid = inquiryid;
        this.expecteddate = expecteddate;
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
     * 获取产品编号
     *
     * @return proisnum - 产品编号
     */
    public String getProisnum() {
        return proisnum;
    }

    /**
     * 设置产品编号
     *
     * @param proisnum 产品编号
     */
    public void setProisnum(String proisnum) {
        this.proisnum = proisnum;
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
     * 获取品类
     *
     * @return protype - 品类
     */
    public String getProtype() {
        return protype;
    }

    /**
     * 设置品类
     *
     * @param protype 品类
     */
    public void setProtype(String protype) {
        this.protype = protype;
    }

    /**
     * 获取产品数量
     *
     * @return pronum - 产品数量
     */
    public Integer getPronum() {
        return pronum;
    }

    /**
     * 设置产品数量
     *
     * @param pronum 产品数量
     */
    public void setPronum(Integer pronum) {
        this.pronum = pronum;
    }

    /**
     * 获取产品单位
     *
     * @return prounit - 产品单位
     */
    public String getProunit() {
        return prounit;
    }

    /**
     * 设置产品单位
     *
     * @param prounit 产品单位
     */
    public void setProunit(String prounit) {
        this.prounit = prounit;
    }

    /**
     * 获取产品价格
     *
     * @return proprice - 产品价格
     */
    public Double getProprice() {
        return proprice;
    }

    /**
     * 设置产品价格
     *
     * @param proprice 产品价格
     */
    public void setProprice(Double proprice) {
        this.proprice = proprice;
    }

    /**
     * 获取金额
     *
     * @return totalprice - 金额
     */
    public Double getTotalprice() {
        return totalprice;
    }

    /**
     * 设置金额
     *
     * @param totalprice 金额
     */
    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * 获取备注
     *
     * @return proremark - 备注
     */
    public String getProremark() {
        return proremark;
    }

    /**
     * 设置备注
     *
     * @param proremark 备注
     */
    public void setProremark(String proremark) {
        this.proremark = proremark;
    }

    /**
     * 获取询价单编号
     *
     * @return inquiryid - 询价单编号
     */
    public Long getInquiryid() {
        return inquiryid;
    }

    /**
     * 设置询价单编号
     *
     * @param inquiryid 询价单编号
     */
    public void setInquiryid(Long inquiryid) {
        this.inquiryid = inquiryid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public InquiryOrder getInquiryOrders() {
        return inquiryOrders;
    }

    public void setInquiryOrders(InquiryOrder inquiryOrders) {
        this.inquiryOrders = inquiryOrders;
    }

    public String getExpecteddate() {
        return expecteddate;
    }

    public void setExpecteddate(String expecteddate) {
        this.expecteddate = expecteddate;
    }
}
