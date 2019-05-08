package com.yc.education.model.purchase;

import javax.persistence.*;
/**
 *@Description TODO 采购订单之备注及说明和报表备注
 *@Author QuZhangJing
 *@Date 10:05  2018/12/13
 *@Version 1.0
 */
@Table(name = "purchase_description")
public class PurchaseDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 描述和备注
     */
    private String des;

    /**
     * 采购订单编号
     */
    private Long purchaseid;

    /**
     * 1、备注及说明   2、报表备注(打印至报表) 默认为1
     */
    private Integer type;


    public PurchaseDescription(){}


    public PurchaseDescription(long id,String des, Long purchaseid, Integer type) {
        this.id = id;
        this.des = des;
        this.purchaseid = purchaseid;
        this.type = type;
    }

    public PurchaseDescription(String des, Long purchaseid, Integer type) {
        this.des = des;
        this.purchaseid = purchaseid;
        this.type = type;
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
     * 获取描述和备注
     *
     * @return des - 描述和备注
     */
    public String getDes() {
        return des;
    }

    /**
     * 设置描述和备注
     *
     * @param des 描述和备注
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * 获取采购订单编号
     *
     * @return purchaseid - 采购订单编号
     */
    public Long getPurchaseid() {
        return purchaseid;
    }

    /**
     * 设置采购订单编号
     *
     * @param purchaseid 采购订单编号
     */
    public void setPurchaseid(Long purchaseid) {
        this.purchaseid = purchaseid;
    }

    /**
     * 获取1、备注及说明   2、报表备注(打印至报表) 默认为1
     *
     * @return type - 1、备注及说明   2、报表备注(打印至报表) 默认为1
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1、备注及说明   2、报表备注(打印至报表) 默认为1
     *
     * @param type 1、备注及说明   2、报表备注(打印至报表) 默认为1
     */
    public void setType(Integer type) {
        this.type = type;
    }
}