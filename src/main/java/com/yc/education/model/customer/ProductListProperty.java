package com.yc.education.model.customer;


import com.yc.education.util.DateUtils;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户基本资料 -- 交易产品
 */
public class ProductListProperty {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleStringProperty single = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty tax = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();


    public ProductListProperty(Long id, String no, String name,  Date date, String type,  String single, Integer num, BigDecimal price ,String tax,String remark) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no);
        }

        if(name != null){
            this.name = new SimpleStringProperty(name);
        }

        if(date != null){
            this.date = new SimpleStringProperty(DateUtils.getSpecifyDate(date,DateUtils.FORMAT_YYYY_MM_DD));
        }
        if(type != null){
            this.type = new SimpleStringProperty(type);
        }
        if(single != null){
            this.single = new SimpleStringProperty(single);
        }
        if(num != null){
            this.num = new SimpleStringProperty(num.toString());
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(tax != null){
            this.tax = new SimpleStringProperty(tax);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
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

    public String getName() {
        if(name == null){
            return null;
        }else{
            return name.get();
        }
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getSingle() {
        if(single == null){
            return null;
        }else{
            return single.get();
        }
    }

    public SimpleStringProperty singleProperty() {
        return single;
    }

    public void setSingle(String single) {
        this.single.set(single);
    }

    public String getNum() {
        if(num == null){
            return null;
        }else{
            return num.get();
        }
    }

    public SimpleStringProperty numProperty() {
        return num;
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public String getPrice() {
        if(price == null){
            return null;
        }else{
            return price.get();
        }
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getTax() {
        if(tax == null){
            return null;
        }else{
            return tax.get();
        }
    }

    public SimpleStringProperty taxProperty() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax.set(tax);
    }

    public String getRemark() {
        if(remark == null){
            return null;
        }else{
            return remark.get();
        }
    }

    public SimpleStringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }
}
