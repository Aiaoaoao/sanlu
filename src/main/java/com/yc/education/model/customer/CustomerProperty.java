package com.yc.education.model.customer;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class CustomerProperty {

    /**
     * 客户id
     */
    private SimpleLongProperty id = new SimpleLongProperty();

    /**
     * 客户类型
     */
    private SimpleStringProperty customerType = new SimpleStringProperty();

    /**
     * 客户等级
     */
    private SimpleStringProperty customerLevel = new SimpleStringProperty();

    /**
     * 编号
     */
    private SimpleStringProperty customerCode = new SimpleStringProperty();

    /**
     * 简称
     */
    private SimpleStringProperty shortName = new SimpleStringProperty();

    private SimpleStringProperty name = new SimpleStringProperty();

    /**
     * 添加时间
     */
    private Date addtime ;

    private SimpleStringProperty remark = new SimpleStringProperty();

    /**
     * 一般客户
     */
    private SimpleBooleanProperty generalCustomer = new SimpleBooleanProperty();


    public CustomerProperty() {
    }

    public CustomerProperty(Long id, String customerType, String customerLevel, String customerCode, String shortName, String name,Date addtime, Boolean generalCustomer,String remark) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(customerType != null){
            this.customerType = new SimpleStringProperty(customerType);
        }
        if(customerLevel != null){
            this.customerLevel = new SimpleStringProperty(customerLevel);
        }
        if(customerCode != null){
            this.customerCode = new SimpleStringProperty(customerCode);
        }
        if(shortName != null){
            this.shortName = new SimpleStringProperty(shortName);
        }
        if(name != null){
            this.name = new SimpleStringProperty(name);
        }
        if(addtime != null){
            this.addtime = addtime;
        }
        if(generalCustomer != null){
            this.generalCustomer = new SimpleBooleanProperty(generalCustomer);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    public long getId() {
        if(id==null){
            return 0L;
        }else {
            return id.get();
        }
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getCustomerType() {
        if(customerType==null){
            return null;
        }else {
            return customerType.get();
        }
    }

    public SimpleStringProperty customerTypeProperty() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType.set(customerType);
    }

    public String getCustomerLevel() {
        if(customerLevel==null){
            return null;
        }else {
            return customerLevel.get();
        }
    }

    public SimpleStringProperty customerLevelProperty() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel.set(customerLevel);
    }

    public String getCustomerCode() {
        if(customerCode==null){
            return null;
        }else {
            return customerCode.get();
        }
    }

    public SimpleStringProperty customerCodeProperty() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode.set(customerCode);
    }

    public String getShortName() {
        if(shortName==null){
            return null;
        }else {
            return shortName.get();
        }
    }

    public SimpleStringProperty shortNameProperty() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName.set(shortName);
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public boolean isGeneralCustomer() {
        if(generalCustomer==null){
            return false;
        }else {
            return generalCustomer.get();
        }
    }

    public SimpleBooleanProperty generalCustomerProperty() {
        return generalCustomer;
    }

    public void setGeneralCustomer(boolean generalCustomer) {
        this.generalCustomer.set(generalCustomer);
    }

    public String getRemark() {
        if(remark==null){
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
