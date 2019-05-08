package com.yc.education.model.sale;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

/**
 * 报价单导入到订货单
 */
public class SaleQuotationImportProductProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty productNo = new SimpleStringProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty money = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();
    private SimpleBooleanProperty checked = new SimpleBooleanProperty();

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

    public String getProductNo() {
        if(productNo == null){
            return null;
        }else{
            return productNo.get();
        }
    }

    public SimpleStringProperty productNoProperty() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo.set(productNo);
    }

    public String getMoney() {
        if(money == null){
            return null;
        }else{
            return money.get();
        }
    }

    public SimpleStringProperty moneyProperty() {
        return money;
    }

    public void setMoney(String money) {
        this.money.set(money);
    }

    public String getProductName() {
        if(productName == null){
            return null;
        }else{
            return productName.get();
        }
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
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

    public String getUnit() {
        if(unit == null){
            return null;
        }else{
            return unit.get();
        }
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
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

    public Boolean isChecked() {
        if(checked == null){
            return null;
        }else{
            return checked.get();
        }
    }

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    public SaleQuotationImportProductProperty() {
    }


    public SaleQuotationImportProductProperty(Long id, String productNo, String productName, Integer num, String unit, BigDecimal price, BigDecimal money, String remark, Boolean checked) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(num != null){
            this.num = new SimpleStringProperty(num.toString());
        }
        if(unit != null){
            this.unit = new SimpleStringProperty(unit);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(money != null){
            this.money = new SimpleStringProperty(money.toString());
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
        if(checked != null){
            this.checked = new SimpleBooleanProperty(checked);
        }
    }

}
