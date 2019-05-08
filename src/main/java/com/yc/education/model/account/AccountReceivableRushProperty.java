package com.yc.education.model.account;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.util.Date;


public class AccountReceivableRushProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty saleNo = new SimpleStringProperty();
    private SimpleStringProperty invoiceNo = new SimpleStringProperty();
    private SimpleStringProperty accountDate = new SimpleStringProperty();
    private SimpleStringProperty totalMoney = new SimpleStringProperty();
    private SimpleStringProperty receive = new SimpleStringProperty();
    private SimpleStringProperty discount = new SimpleStringProperty();
    private SimpleStringProperty rushMoney = new SimpleStringProperty();


    public AccountReceivableRushProperty( String saleNo, String  invoiceNo, String accountDate, String totalMoney, String receive, String discount, String rushMoney) {
        if(saleNo != null){
            this.saleNo = new SimpleStringProperty(saleNo);
        }
        if(invoiceNo != null){
            this.invoiceNo = new SimpleStringProperty(invoiceNo);
        }
        if(accountDate != null){
            this.accountDate = new SimpleStringProperty(accountDate);
        }
        if(totalMoney != null){
            this.totalMoney = new SimpleStringProperty(totalMoney);
        }
        if(receive != null){
            this.receive = new SimpleStringProperty(receive);
        }
        if(discount != null){
            this.discount = new SimpleStringProperty(discount);
        }
        if(rushMoney != null){
            this.rushMoney = new SimpleStringProperty(rushMoney);
        }
    }

    public AccountReceivableRushProperty(Long id, String saleNo, String  invoiceNo, Date accountDate, BigDecimal totalMoney, BigDecimal receive, String discount, BigDecimal rushMoney) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(saleNo != null){
            this.saleNo = new SimpleStringProperty(saleNo);
        }
        if(invoiceNo != null){
            this.invoiceNo = new SimpleStringProperty(invoiceNo);
        }
        if(accountDate != null){
            this.accountDate = new SimpleStringProperty(accountDate.toString());
        }
        if(totalMoney != null){
            this.totalMoney = new SimpleStringProperty(totalMoney.toString());
        }
        if(receive != null){
            this.receive = new SimpleStringProperty(receive.toString());
        }
        if(discount != null){
            this.discount = new SimpleStringProperty(discount);
        }
        if(rushMoney != null){
            this.rushMoney = new SimpleStringProperty(rushMoney.toString());
        }
    }

    public AccountReceivableRushProperty(Long id) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
    }

    public AccountReceivableRushProperty() {
    }


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

    public void setId(Long id) {
        if(id != null){
            this.id.set(id);
        }
    }

    public String getSaleNo() {
        if(saleNo == null){
            return null;
        }else{
            return saleNo.get();
        }
    }

    public SimpleStringProperty saleNoProperty() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        if(saleNo != null){
            this.saleNo.set(saleNo);
        }
    }

    public String getInvoiceNo() {
        if(invoiceNo == null){
            return null;
        }else{
            return invoiceNo.get();
        }
    }

    public SimpleStringProperty invoiceNoProperty() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        if(invoiceNo != null){
            this.invoiceNo.set(invoiceNo);
        }
    }

    public String getAccountDate() {
        if(accountDate == null){
            return null;
        }else{
            return accountDate.get();
        }
    }

    public SimpleStringProperty accountDateProperty() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        if(accountDate != null){
            this.accountDate.set(accountDate);
        }
    }

    public String getTotalMoney() {
        if(totalMoney == null){
            return null;
        }else{
            return totalMoney.get();
        }
    }

    public SimpleStringProperty totalMoneyProperty() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        if(totalMoney != null){
            this.totalMoney.set(totalMoney);
        }
    }

    public String getReceive() {
        if(receive == null){
            return null;
        }else{
            return receive.get();
        }
    }

    public SimpleStringProperty receiveProperty() {
        return receive;
    }

    public void setReceive(String receive) {
        if(receive != null){
            this.receive.set(receive);
        }
    }

    public String getDiscount() {
        if(discount == null){
            return null;
        }else{
            return discount.get();
        }
    }

    public SimpleStringProperty discountProperty() {
        return discount;
    }

    public void setDiscount(String discount) {
        if(discount != null){
            this.discount.set(discount);
        }
    }

    public String getRushMoney() {
        if(rushMoney == null){
            return null;
        }else{
            return rushMoney.get();
        }
    }

    public SimpleStringProperty rushMoneyProperty() {
        return rushMoney;
    }

    public void setRushMoney(String rushMoney) {
        if(rushMoney != null){
            this.rushMoney.set(rushMoney);
        }
    }
}
