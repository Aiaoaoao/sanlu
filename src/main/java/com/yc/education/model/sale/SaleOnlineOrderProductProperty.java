package com.yc.education.model.sale;

import javafx.beans.property.*;

import java.math.BigDecimal;
import java.util.Date;

public class SaleOnlineOrderProductProperty {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty no = new SimpleStringProperty();    // 序号
    private SimpleLongProperty onlineOrderId = new SimpleLongProperty();
    private SimpleStringProperty customerNo = new SimpleStringProperty(); 
    private SimpleStringProperty productNo = new SimpleStringProperty(); 
    private SimpleStringProperty productName = new SimpleStringProperty(); 
    private SimpleStringProperty category = new SimpleStringProperty(); 
    private SimpleStringProperty num = new SimpleStringProperty(); 
    private SimpleStringProperty unit = new SimpleStringProperty(); 
    private SimpleStringProperty price = new SimpleStringProperty(); 
    private SimpleStringProperty money = new SimpleStringProperty(); 
    private SimpleStringProperty usableNum = new SimpleStringProperty(); 
    private SimpleStringProperty ifstock = new SimpleStringProperty(); 
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

    public Long getOnlineOrderId() {
        if(onlineOrderId == null){
            return null;
        }else{
            return onlineOrderId.get();
        }
    }

    public SimpleLongProperty onlineOrderIdProperty() {
        return onlineOrderId;
    }

    public void setOnlineOrderId(long onlineOrderId) {
        this.onlineOrderId.set(onlineOrderId);
    }

    public String getCustomerNo() {
        if(customerNo == null){
            return null;
        }else{
            return customerNo.get();
        }
    }

    public SimpleStringProperty customerNoProperty() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo.set(customerNo);
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

    public String getCategory() {

        if(category == null){
            return null;
        }else{
            return category.get();
        }
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
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

    public String getUsableNum() {
        if(usableNum == null){
            return null;
        }else{
            return usableNum.get();
        }
    }

    public SimpleStringProperty usableNumProperty() {
        return usableNum;
    }

    public void setUsableNum(String usableNum) {
        this.usableNum.set(usableNum);
    }

    public String getIfstock() {

        if(ifstock == null){
            return null;
        }else{
            return ifstock.get();
        }
    }

    public SimpleStringProperty ifstockProperty() {
        return ifstock;
    }

    public void setIfstock(String ifstock) {
        this.ifstock.set(ifstock);
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

    public SaleOnlineOrderProductProperty() {
    }

    // 多个 no
    public SaleOnlineOrderProductProperty( Integer no, String customerNo, String productNo, String productName, String category, Integer num, String unit, BigDecimal price, BigDecimal money, Integer usableNum, String remark) {
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(customerNo != null){
            this.customerNo = new SimpleStringProperty(customerNo);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(category != null){
            this.category = new SimpleStringProperty(category);
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
        if(usableNum != null){
            if(usableNum > 0){
                this.ifstock = new SimpleStringProperty("是");
            }else{
                this.ifstock = new SimpleStringProperty("否");
            }
            this.usableNum = new SimpleStringProperty(usableNum.toString());
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    // 加复选框
    public SaleOnlineOrderProductProperty(Long id, Integer no, String productNo, String productName, String category, Integer num, String unit, BigDecimal price, BigDecimal money, Integer usableNum, String remark,Boolean checked) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(category != null){
            this.category = new SimpleStringProperty(category);
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
        if(usableNum != null){
            if(usableNum > 0){
                this.ifstock = new SimpleStringProperty("是");
            }else{
                this.ifstock = new SimpleStringProperty("否");
            }
            this.usableNum = new SimpleStringProperty(usableNum.toString());
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
        if(checked != null){
            this.checked = new SimpleBooleanProperty(checked);
        }
    }

    // 多个 no
    public SaleOnlineOrderProductProperty(Long id,Integer no, Long onlineOrderId, String customerNo, String productNo, String productName, String category, Integer num, String unit, BigDecimal price, BigDecimal money, Integer usableNum, String remark ) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(onlineOrderId != null){
            this.onlineOrderId = new SimpleLongProperty(onlineOrderId);
        }
        if(customerNo != null){
            this.customerNo = new SimpleStringProperty(customerNo);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(category != null){
            this.category = new SimpleStringProperty(category);
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
        if(usableNum != null){
            if(usableNum > 0){
                this.ifstock = new SimpleStringProperty("是");
            }else{
                this.ifstock = new SimpleStringProperty("否");
            }
            this.usableNum = new SimpleStringProperty(usableNum.toString());
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }
}
