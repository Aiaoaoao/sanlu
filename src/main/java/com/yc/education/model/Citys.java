package com.yc.education.model;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO  省、市、区
 *@Author QuZhangJing
 *@Date 11:10  2018/11/14
 *@Version 1.0
 */
public class Citys {
    /**
     * 唯一编号
     */
    @Id
    @Column(name = "districts_id")
    private Integer districtsId;

    /**
     * 地区编号
     */
    @Column(name = "addr_code")
    private String addrCode;

    /**
     * 地区名称
     */
    @Column(name = "addr_name")
    private String addrName;

    /**
     * 父编号
     */
    @Column(name = "father_code")
    private String fatherCode;

    /**
     * 类型，01：省，02：市，03：区县
     */
    private String type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取唯一编号
     *
     * @return districts_id - 唯一编号
     */
    public Integer getDistrictsId() {
        return districtsId;
    }

    /**
     * 设置唯一编号
     *
     * @param districtsId 唯一编号
     */
    public void setDistrictsId(Integer districtsId) {
        this.districtsId = districtsId;
    }

    /**
     * 获取地区编号
     *
     * @return addr_code - 地区编号
     */
    public String getAddrCode() {
        return addrCode;
    }

    /**
     * 设置地区编号
     *
     * @param addrCode 地区编号
     */
    public void setAddrCode(String addrCode) {
        this.addrCode = addrCode;
    }

    /**
     * 获取地区名称
     *
     * @return addr_name - 地区名称
     */
    public String getAddrName() {
        return addrName;
    }

    /**
     * 设置地区名称
     *
     * @param addrName 地区名称
     */
    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    /**
     * 获取父编号
     *
     * @return father_code - 父编号
     */
    public String getFatherCode() {
        return fatherCode;
    }

    /**
     * 设置父编号
     *
     * @param fatherCode 父编号
     */
    public void setFatherCode(String fatherCode) {
        this.fatherCode = fatherCode;
    }

    /**
     * 获取类型，01：省，02：市，03：区县
     *
     * @return type - 类型，01：省，02：市，03：区县
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型，01：省，02：市，03：区县
     *
     * @param type 类型，01：省，02：市，03：区县
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}