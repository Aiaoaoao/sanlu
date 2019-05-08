package com.yc.education.model.basic;

import javax.persistence.*;
/**
 *@Description TODO 产品-规格明细
 *@Author QuZhangJing
 *@Date 14:44  2018/9/6
 *@Version 1.0
 */
@Table(name = "product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 说明
     */
    @Column(name = "declares")
    private String declare;

    /**
     * 产品id
     */
    private Long proid;


    public ProductDetails() {
    }

    public ProductDetails(long id,String declare) {
        this.id = id;
        this.declare = declare;
    }

    public ProductDetails(long id,String declare,long proid) {
        this.id = id;
        this.declare = declare;
        this.proid = proid;
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
     * 获取说明
     *
     * @return declare - 说明
     */
    public String getDeclare() {
        return declare;
    }

    /**
     * 设置说明
     *
     * @param declare 说明
     */
    public void setDeclare(String declare) {
        this.declare = declare;
    }

    /**
     * 获取产品id
     *
     * @return proid - 产品id
     */
    public Long getProid() {
        return proid;
    }

    /**
     * 设置产品id
     *
     * @param proid 产品id
     */
    public void setProid(Long proid) {
        this.proid = proid;
    }


}