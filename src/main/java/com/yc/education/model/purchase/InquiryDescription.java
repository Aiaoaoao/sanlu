package com.yc.education.model.purchase;

import javax.persistence.*;
/**
 *@Description TODO  询价单之 备注及说明和报表备注
 *@Author QuZhangJing
 *@Date 18:27  2018/10/8
 *@Version 1.0
 */
@Table(name = "inquiry_description")
public class InquiryDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 描述和说明
     */
    private String des;

    /**
     * 询价单编号
     */
    private Long inquiryid;

    /**
     * 1、备注及说明   2、报表备注(打印至报表) 默认为1
     */
    private Integer type;

    public InquiryDescription() {
    }

    public InquiryDescription(long id, String des, Long inquiryid, Integer type) {
        this.id = id;
        this.des = des;
        this.inquiryid = inquiryid;
        this.type = type;
    }


    public InquiryDescription(String des, Long inquiryid, Integer type) {
        this.des = des;
        this.inquiryid = inquiryid;
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
     * 获取描述和说明
     *
     * @return des - 描述和说明
     */
    public String getDes() {
        return des;
    }

    /**
     * 设置描述和说明
     *
     * @param des 描述和说明
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * 获取询价单编号
     *
     * @return inquiryid - 询价单编号
     */
    public Long getInquiryid() {
        return inquiryid;
    }

    /**
     * 设置询价单编号
     *
     * @param inquiryid 询价单编号
     */
    public void setInquiryid(Long inquiryid) {
        this.inquiryid = inquiryid;
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