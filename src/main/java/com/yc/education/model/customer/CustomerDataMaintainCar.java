package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_data_maintain_car")
public class CustomerDataMaintainCar {

    /**
     * 客户资料维护-车床
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
     * 车床类型
     */
    @Column(name = "lathe_type")
    private String latheType;

    /**
     * 厂牌
     */
    private String brand;

    /**
     * 型号
     */
    private String models;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取客户资料维护-车床
     *
     * @return id - 客户资料维护-车床
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户资料维护-车床
     *
     * @param id 客户资料维护-车床
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
     * 获取车床类型
     *
     * @return lathe_type - 车床类型
     */
    public String getLatheType() {
        return latheType;
    }

    /**
     * 设置车床类型
     *
     * @param latheType 车床类型
     */
    public void setLatheType(String latheType) {
        this.latheType = latheType;
    }

    /**
     * 获取厂牌
     *
     * @return brand - 厂牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置厂牌
     *
     * @param brand 厂牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取型号
     *
     * @return models - 型号
     */
    public String getModels() {
        return models;
    }

    /**
     * 设置型号
     *
     * @param models 型号
     */
    public void setModels(String models) {
        this.models = models;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
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