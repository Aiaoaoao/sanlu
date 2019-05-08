package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_data_maintain_production")
public class CustomerDataMaintainProduction {
    /**
     * 客户资料维护-生产资料
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户资料维护id
     */
    @Column(name = "maintain_id")
    private Long maintainId;

    /**
     * 主要生产类别
     */
    @Column(name = "production_type")
    private String productionType;

    /**
     * 卫星工厂
     */
    private String factory;

    /**
     * 上游厂商
     */
    private String vendor;

    /**
     * 系统添加时间
     */
    private Date addtime;

    /**
     * 获取客户资料维护-生产资料
     *
     * @return id - 客户资料维护-生产资料
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户资料维护-生产资料
     *
     * @param id 客户资料维护-生产资料
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户资料维护id
     *
     * @return maintain_id - 客户资料维护id
     */
    public Long getMaintainId() {
        return maintainId;
    }

    /**
     * 设置客户资料维护id
     *
     * @param maintainId 客户资料维护id
     */
    public void setMaintainId(Long maintainId) {
        this.maintainId = maintainId;
    }

    /**
     * 获取主要生产类别
     *
     * @return production_type - 主要生产类别
     */
    public String getProductionType() {
        return productionType;
    }

    /**
     * 设置主要生产类别
     *
     * @param productionType 主要生产类别
     */
    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    /**
     * 获取卫星工厂
     *
     * @return factory - 卫星工厂
     */
    public String getFactory() {
        return factory;
    }

    /**
     * 设置卫星工厂
     *
     * @param factory 卫星工厂
     */
    public void setFactory(String factory) {
        this.factory = factory;
    }

    /**
     * 获取上游厂商
     *
     * @return vendor - 上游厂商
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * 设置上游厂商
     *
     * @param vendor 上游厂商
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * 获取系统添加时间
     *
     * @return addtime - 系统添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置系统添加时间
     *
     * @param addtime 系统添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}