package com.yc.education.model.customer;


import com.yc.education.util.DateUtils;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户基本资料 -- 交易单据
 */
public class ProductOrderProperty {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty reference = new SimpleStringProperty();
    private SimpleStringProperty business = new SimpleStringProperty();
    private SimpleStringProperty total = new SimpleStringProperty();


    public ProductOrderProperty(Long id, Date date, String type, String no, String reference, String business, BigDecimal total) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(date != null){
            this.date = new SimpleStringProperty(DateUtils.getSpecifyDate(date,DateUtils.FORMAT_YYYY_MM_DD));
        }
        if(type != null){
            this.type = new SimpleStringProperty(type);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no);
        }
        if(reference != null){
            this.reference = new SimpleStringProperty(reference);
        }
        if(business != null){
            this.business = new SimpleStringProperty(business);
        }
        if(total != null){
            this.total = new SimpleStringProperty(total.toString());
        }
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

    public String getDate() {
        if(date == null){
            return null;
        }else{
            return date.get();
        }
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getType() {
        if(type == null){
            return null;
        }else{
            return type.get();
        }
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    public String getReference() {
        if(reference == null){
            return null;
        }else{
            return reference.get();
        }
    }

    public SimpleStringProperty referenceProperty() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference.set(reference);
    }

    public String getBusiness() {
        if(business == null){
            return null;
        }else{
            return business.get();
        }
    }

    public SimpleStringProperty businessProperty() {
        return business;
    }

    public void setBusiness(String business) {
        this.business.set(business);
    }

    public String getTotal() {
        if(total == null){
            return null;
        }else{
            return total.get();
        }
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
}
