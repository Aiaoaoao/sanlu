package com.yc.education.model;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO  税率更新记录表
 *@Author QuZhangJing
 *@Date 19:04  2019/3/28
 *@Version 1.0
 */
@Table(name = "tax_rate")
public class TaxRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 税率
     */
    private Double nums;

    /**
     * 税率类型  1、进项  2、销项
     */
    private Integer types;

    /**
     * 记录时间
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
     * 获取开始时间
     *
     * @return starttime - 开始时间
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * 设置开始时间
     *
     * @param starttime 开始时间
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 获取结束时间
     *
     * @return endtime - 结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 设置结束时间
     *
     * @param endtime 结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 获取税率
     *
     * @return nums - 税率
     */
    public Double getNums() {
        return nums;
    }

    /**
     * 设置税率
     *
     * @param nums 税率
     */
    public void setNums(Double nums) {
        this.nums = nums;
    }

    /**
     * 获取税率类型  1、进项  2、销项
     *
     * @return types - 税率类型  1、进项  2、销项
     */
    public Integer getTypes() {
        return types;
    }

    /**
     * 设置税率类型  1、进项  2、销项
     *
     * @param types 税率类型  1、进项  2、销项
     */
    public void setTypes(Integer types) {
        this.types = types;
    }

    /**
     * 获取记录时间
     *
     * @return addtime - 记录时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置记录时间
     *
     * @param addtime 记录时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}