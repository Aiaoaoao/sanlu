package com.yc.education.model.customer;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * @Author: BlueSky
 * @Date: 2018/8/16 10:22
 */
@Table(name = "customer_shipping_address")
public class CustomerShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerid;
    private String type;
    private String title;
    private String address;
    private String phone;
    private String contact;
    private Boolean setting;
    private Date addtime;




    public  static  enum radio{
        设为默认
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public Boolean getSetting() {
        return setting;
    }

    public void setSetting(Boolean setting) {
        this.setting = setting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public CustomerShippingAddress(Long id, Long customerid, String type,String title, String address, String phone, String contact, Boolean setting, Date addtime) {
        this.id = id;
        this.customerid = customerid;
        this.type = type;
        this.title = title;
        this.address = address;
        this.phone = phone;
        this.contact = contact;
        this.setting = setting;
        this.addtime = addtime;
    }

    public CustomerShippingAddress() {
    }
}
