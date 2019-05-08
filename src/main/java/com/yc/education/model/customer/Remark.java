package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "remark")
public class Remark {
    /**
     *  备注及说明
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 外键
     */
    private Long otherid;

    /**
     * 类型（1、客户基本资料，2、报价单，3、订货单，4、销货单）
     */
    private Integer typeid;

    /**
     * 备注及说明
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取客户基本资料-备注及说明
     *
     * @return id - 客户基本资料-备注及说明
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户基本资料-备注及说明
     *
     * @param id 客户基本资料-备注及说明
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * 获取备注及说明
     *
     * @return remark - 备注及说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注及说明
     *
     * @param remark 备注及说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getOtherid() {
        return otherid;
    }

    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Remark(Long otherid, Integer typeid, String remark, Date addtime) {
        this.otherid = otherid;
        this.typeid = typeid;
        this.remark = remark;
        this.addtime = addtime;
    }

    public Remark(String remark) {
        this.remark = remark;
    }


    public Remark() {
    }
}