package com.yc.education.model.basic;

import javax.persistence.*;
/**
 *@Description TODO 产品- 供应商
 *@Author QuZhangJing
 *@Date 14:45  2018/9/6
 *@Version 1.0
 */
@Table(name = "product_supplier")
public class ProductSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品id
     */
    private Long proid;

    /**
     * 供应商编号
     */
    private String supplierid;

    /**
     * 供应商简称
     */
    private String supdes;

    /**
     * 主要供应
     */
    private String supply;

    /**
     * 备注
     */
    private String remarks;


    public ProductSupplier() {
    }

    public ProductSupplier(Long id, String supplierid, String supdes, String supply, String remarks) {
        this.id = id;
        this.supplierid = supplierid;
        this.supdes = supdes;
        this.supply = supply;
        this.remarks = remarks;
    }

    public ProductSupplier(Long id, String supplierid, String supdes, String supply, String remarks,long proid) {
        this.id = id;
        this.supplierid = supplierid;
        this.supdes = supdes;
        this.supply = supply;
        this.remarks = remarks;
        this.proid = proid;
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
     * 获取产品id
     *
     * @return proid - 产品id
     */
    public Long getProid() {
        return proid;
    }

    /**
     * 设置产品id
     *
     * @param proid 产品id
     */
    public void setProid(Long proid) {
        this.proid = proid;
    }

    /**
     * 获取供应商编号
     *
     * @return supplierid - 供应商编号
     */
    public String getSupplierid() {
        return supplierid;
    }

    /**
     * 设置供应商编号
     *
     * @param supplierid 供应商编号
     */
    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid;
    }

    /**
     * 获取供应商简称
     *
     * @return supdes - 供应商简称
     */
    public String getSupdes() {
        return supdes;
    }

    /**
     * 设置供应商简称
     *
     * @param supdes 供应商简称
     */
    public void setSupdes(String supdes) {
        this.supdes = supdes;
    }

    /**
     * 获取主要供应
     *
     * @return supply - 主要供应
     */
    public String getSupply() {
        return supply;
    }

    /**
     * 设置主要供应
     *
     * @param supply 主要供应
     */
    public void setSupply(String supply) {
        this.supply = supply;
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
}