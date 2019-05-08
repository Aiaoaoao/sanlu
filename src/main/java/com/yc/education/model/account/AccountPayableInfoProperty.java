package com.yc.education.model.account;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;


import java.math.BigDecimal;
import java.util.Date;


/**
 * 账款-收款单-详情
 */
public class AccountPayableInfoProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty interInvoice = new SimpleStringProperty();
    private SimpleStringProperty invoiceNo = new SimpleStringProperty();
    private SimpleStringProperty accountDate = new SimpleStringProperty();
    private SimpleStringProperty customer = new SimpleStringProperty();
    private SimpleStringProperty invoiceDate = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();
    private SimpleStringProperty money = new SimpleStringProperty(); // 账款金额

    public AccountPayableInfoProperty(String interInvoice, String invoiceNo, String accountDate, String customer, String invoiceDate, String remark) {
        if(invoiceNo != null){
            this.invoiceNo = new SimpleStringProperty(invoiceNo);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
        if(interInvoice != null){
            this.interInvoice = new SimpleStringProperty(interInvoice.toString());
        }
        if(accountDate != null){
            this.accountDate = new SimpleStringProperty(accountDate.toString());
        }
        if(customer != null){
            this.customer = new SimpleStringProperty(customer.toString());
        }
        if(invoiceDate != null){
            this.invoiceDate = new SimpleStringProperty(invoiceDate.toString());
        }
    }

    public AccountPayableInfoProperty(Long id, Integer no, String interInvoice, String invoiceNo, String accountDate, String customer, String invoiceDate, String remark, BigDecimal money) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(invoiceNo != null){
            this.invoiceNo = new SimpleStringProperty(invoiceNo);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
        if(interInvoice != null){
            this.interInvoice = new SimpleStringProperty(interInvoice);
        }
        if(accountDate != null){
            this.accountDate = new SimpleStringProperty(accountDate);
        }
        if(customer != null){
            this.customer = new SimpleStringProperty(customer);
        }
        if(invoiceDate != null){
            this.invoiceDate = new SimpleStringProperty(invoiceDate);
        }
        if(money != null){
            this.money = new SimpleStringProperty(money.toString());
        }
    }


    public Long getId() {
        if(id == null){
            return null;
        }else{
            return id.get();
        }
    }

    public String getMoney() {
        return money.get();
    }

    public SimpleStringProperty moneyProperty() {
        return money;
    }

    public void setMoney(String money) {
        this.money.set(money);
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
        }else{
            return no.get();
        }
    }

    public SimpleStringProperty noProperty() {
        return no;
    }

    public void setNo(String no) {
        this.no.set(no);
    }

    public String getInvoiceNo() {
        if(invoiceNo == null){
            return null;
        }else {
            return invoiceNo.get();
        }

    }

    public SimpleStringProperty invoiceNoProperty() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo.set(invoiceNo);
    }

    public String getRemark() {
        if(remark == null){
            return null;
        }else {
            return remark.get();
        }

    }

    public SimpleStringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }

    public String getInterInvoice() {
        if(remark == null){
            return null;
        }else {
            return remark.get();
        }
    }

    public SimpleStringProperty interInvoiceProperty() {
        return interInvoice;
    }

    public void setInterInvoice(String interInvoice) {
        this.interInvoice.set(interInvoice);
    }

    public String getAccountDate() {
        if(accountDate == null){
            return null;
        }else {
            return accountDate.get();
        }
    }

    public SimpleStringProperty accountDateProperty() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate.set(accountDate);
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

    public String getInvoiceDate() {
        if(invoiceDate == null){
            return null;
        }else {
            return invoiceDate.get();
        }
    }

    public SimpleStringProperty invoiceDateProperty() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate.set(invoiceDate);
    }
}
