package com.yc.education.model.account;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 账款-预付账款单-详情
 */
public class AccountPrepaymentInfoProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty prepaymentAccount = new SimpleStringProperty();
    private SimpleStringProperty supplierShort = new SimpleStringProperty();
    private SimpleStringProperty createDate = new SimpleStringProperty();
    private SimpleStringProperty enterBox = new SimpleStringProperty();
    private SimpleStringProperty orderAudit = new SimpleStringProperty();
    public AccountPrepaymentInfoProperty(String prepaymentAccount, String supplierShort, String createDate, String enterBox, String orderAudit) {
        if(prepaymentAccount != null){
            this.prepaymentAccount = new SimpleStringProperty(prepaymentAccount);
        }
        if(supplierShort != null){
            this.supplierShort = new SimpleStringProperty(supplierShort);
        }
        if(createDate != null){
            this.createDate = new SimpleStringProperty(createDate);
        }
        if(enterBox != null){
            this.enterBox = new SimpleStringProperty(enterBox);
        }
        if(orderAudit != null){
            this.orderAudit = new SimpleStringProperty(orderAudit);
        }
    }

    public AccountPrepaymentInfoProperty(Long id,String prepaymentAccount, String supplierShort, String createDate,String enterBox, String orderAudit) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(prepaymentAccount != null){
            this.prepaymentAccount = new SimpleStringProperty(prepaymentAccount);
        }
        if(supplierShort != null){
            this.supplierShort = new SimpleStringProperty(supplierShort);
        }
        if(createDate != null){
            this.createDate = new SimpleStringProperty(createDate);
        }
        if(enterBox != null){
            this.enterBox = new SimpleStringProperty(enterBox);
        }
        if(orderAudit != null){
            this.orderAudit = new SimpleStringProperty(orderAudit);
        }
    }



    public AccountPrepaymentInfoProperty() {
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

    public void setId(long id) {
        this.id.set(id);
    }

    public String getEnterBox() {
        if(enterBox == null){
            return null;
        }else {
            return enterBox.get();
        }
    }

    public SimpleStringProperty enterBoxProperty() {
        return enterBox;
    }

    public void setEnterBox(String enterBox) {
        this.enterBox.set(enterBox);
    }

    public String getPrepaymentAccount() {

        if(prepaymentAccount == null){
            return null;
        }else {
            return prepaymentAccount.get();
        }
    }

    public SimpleStringProperty prepaymentAccountProperty() {
        return prepaymentAccount;
    }

    public void setPrepaymentAccount(String prepaymentAccount) {
        this.prepaymentAccount.set(prepaymentAccount);
    }

    public String getSupplierShort() {

        if(supplierShort == null){
            return null;
        }else {
            return supplierShort.get();
        }
    }

    public SimpleStringProperty supplierShortProperty() {
        return supplierShort;
    }

    public void setSupplierShort(String supplierShort) {
        this.supplierShort.set(supplierShort);
    }

    public String getCreateDate() {

        if(createDate == null){
            return null;
        }else {
            return createDate.get();
        }
    }

    public SimpleStringProperty createDateProperty() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }

    public String getOrderAudit() {
        if(orderAudit == null){
            return null;
        }else {
            return orderAudit.get();
        }
    }

    public SimpleStringProperty orderAuditProperty() {
        return orderAudit;
    }

    public void setOrderAudit(String orderAudit) {
        this.orderAudit.set(orderAudit);
    }
}
