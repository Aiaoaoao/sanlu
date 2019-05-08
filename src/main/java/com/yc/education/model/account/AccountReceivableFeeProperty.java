package com.yc.education.model.account;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;


public class AccountReceivableFeeProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty receiveNo = new SimpleStringProperty();
    private SimpleStringProperty rushMoneyCan = new SimpleStringProperty();
    private SimpleStringProperty rushMoneyNow = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();


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

    public String getReceiveNo() {
        if(receiveNo == null){
            return null;
        }else {
            return receiveNo.get();
        }
    }

    public SimpleStringProperty receiveNoProperty() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo.set(receiveNo);
    }

    public String getRushMoneyCan() {
        if(rushMoneyCan == null){
            return null;
        }else {
            return rushMoneyCan.get();
        }
    }

    public SimpleStringProperty rushMoneyCanProperty() {
        return rushMoneyCan;
    }

    public void setRushMoneyCan(String rushMoneyCan) {
        this.rushMoneyCan.set(rushMoneyCan);
    }

    public String getRushMoneyNow() {
        if(rushMoneyNow == null){
            return null;
        }else {
            return rushMoneyNow.get();
        }
    }

    public SimpleStringProperty rushMoneyNowProperty() {
        return rushMoneyNow;
    }

    public void setRushMoneyNow(String rushMoneyNow) {
        this.rushMoneyNow.set(rushMoneyNow);
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

    public AccountReceivableFeeProperty( String receiveNo, String  rushMoneyCan, String rushMoneyNow, String remark) {
        if(receiveNo != null){
            this.receiveNo = new SimpleStringProperty(receiveNo);
        }
        if(rushMoneyCan != null){
            this.rushMoneyCan = new SimpleStringProperty(rushMoneyCan);
        }
        if(rushMoneyNow != null){
            this.rushMoneyNow = new SimpleStringProperty(rushMoneyNow);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    public AccountReceivableFeeProperty(Long id, String receiveNo, String  rushMoneyCan, String rushMoneyNow, String remark) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(receiveNo != null){
            this.receiveNo = new SimpleStringProperty(receiveNo);
        }
        if(rushMoneyCan != null){
            this.rushMoneyCan = new SimpleStringProperty(rushMoneyCan);
        }
        if(rushMoneyNow != null){
            this.rushMoneyNow = new SimpleStringProperty(rushMoneyNow);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    public AccountReceivableFeeProperty() {
    }
}
