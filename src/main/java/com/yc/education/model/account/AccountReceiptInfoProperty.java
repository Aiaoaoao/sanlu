package com.yc.education.model.account;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 账款-收款单-详情
 */
public class AccountReceiptInfoProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty customer = new SimpleStringProperty();
    private SimpleStringProperty receiptNot = new SimpleStringProperty();
    private SimpleStringProperty addtime = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty dollar = new SimpleStringProperty();
    private SimpleStringProperty orderNo = new SimpleStringProperty();


    public Long getId() {
        if(id == null){
            return null;
        }else{
            return id.get();
        }
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getNo() {

        if(no == null){
            return null;
        }else {
            return no.get();
        }
    }

    public SimpleStringProperty noProperty() {
        return no;
    }

    public void setNo(String no) {
        this.no.set(no);
    }

    public String getCustomer() {

        if(customer == null){
            return null;
        }else {
            return customer.get();
        }
    }

    public SimpleStringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    public String getReceiptNot() {

        if(receiptNot == null){
            return null;
        }else {
            return receiptNot.get();
        }
    }

    public SimpleStringProperty receiptNotProperty() {
        return receiptNot;
    }

    public void setReceiptNot(String receiptNot) {
        this.receiptNot.set(receiptNot);
    }

    public String getAddtime() {

        if(addtime == null){
            return null;
        }else {
            return addtime.get();
        }
    }

    public SimpleStringProperty addtimeProperty() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime.set(addtime);
    }

    public String getPrice() {

        if(price == null){
            return null;
        }else {
            return price.get();
        }
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getDollar() {
        if(dollar == null){
            return null;
        }else {
            return dollar.get();
        }
    }

    public SimpleStringProperty dollarProperty() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar.set(dollar);
    }

    public String getOrderNo() {

        if(orderNo == null){
            return null;
        }else {
            return orderNo.get();
        }
    }

    public SimpleStringProperty orderNoProperty() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo.set(orderNo);
    }

    public AccountReceiptInfoProperty(String no, String customer, String receiptNot, String addtime, String price, String dollar, String orderNo) {
        if(no != null){
            this.no = new SimpleStringProperty(no);
        }
        if(customer != null){
            this.customer = new SimpleStringProperty(customer);
        }
        if(receiptNot != null){
            this.receiptNot = new SimpleStringProperty(receiptNot);
        }
        if(addtime != null){
            this.addtime = new SimpleStringProperty(addtime);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price);
        }
        if(dollar != null){
            this.dollar = new SimpleStringProperty(dollar);
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
    }


    public AccountReceiptInfoProperty(Long id, String no, String customer, String receiptNot, String addtime, BigDecimal price, String dollar, String orderNo) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no);
        }
        if(customer != null){
            this.customer = new SimpleStringProperty(customer);
        }
        if(receiptNot != null){
            this.receiptNot = new SimpleStringProperty(receiptNot);
        }
        if(addtime != null){
            this.addtime = new SimpleStringProperty(addtime.toString());
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(dollar != null){
            this.dollar = new SimpleStringProperty(dollar);
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
    }

    public AccountReceiptInfoProperty() {
    }
}
