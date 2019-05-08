package com.yc.education.model;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 基本单位
 *@Author QuZhangJing
 *@Date 17:42  2018/9/20
 *@Version 1.0
 */
@Table(name = "basic_unit")
public class BasicUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String remarks;

    /**
     * 添加时间
     */
    private Date addtime;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
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