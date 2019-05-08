package com.yc.education.model;

import javafx.fxml.FXML;

import javax.persistence.*;
/**
 *@Description TODO 资料设定
 *@Author QuZhangJing
 *@Date 17:43  2018/9/26
 *@Version 1.0
 */
@Table(name = "data_setting")
public class DataSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 名称
     */
    private String title;

    /**
     * 是否删除
     */
    private Integer isdel;

    /**
     * 描述
     */
    private String remarks;

    /**
     *仓库父级编号
     */
    private long previd;

    /**
     * 父级id
     */
    private Long parentid;

    //库存数量
    @Transient
    private Double productNum;

    public DataSetting() {

    }

    public DataSetting(Integer sort, String title, String remarks, Long parentid,long previd) {
        this.sort = sort;
        this.title = title;
        this.remarks = remarks;
        this.parentid = parentid;
        this.previd = previd;
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
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取名称
     *
     * @return title - 名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置名称
     *
     * @param title 名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取是否删除
     *
     * @return isdel - 是否删除
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * 设置是否删除
     *
     * @param isdel 是否删除
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     * 获取描述
     *
     * @return remarks - 描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置描述
     *
     * @param remarks 描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取父级id
     *
     * @return parentid - 父级id
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * 设置父级id
     *
     * @param parentid 父级id
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public long getPrevid() {
        return previd;
    }

    public void setPrevid(long previd) {
        this.previd = previd;
    }

    public Double getProductNum() {
        return productNum;
    }

    public void setProductNum(Double productNum) {
        this.productNum = productNum;
    }
}