package com.yc.education.model.basic;

import javax.persistence.*;

@Table(name = "region_basic")
public class RegionBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 区域编码
     */
    private String isnum;

    /**
     * 区域设定
     */
    private String area;

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
     * 获取区域编码
     *
     * @return isnum - 区域编码
     */
    public String getIsnum() {
        return isnum;
    }

    /**
     * 设置区域编码
     *
     * @param isnum 区域编码
     */
    public void setIsnum(String isnum) {
        this.isnum = isnum;
    }

    /**
     * 获取区域设定
     *
     * @return area - 区域设定
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区域设定
     *
     * @param area 区域设定
     */
    public void setArea(String area) {
        this.area = area;
    }
}