package com.yc.education.model.account;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

/**
 * 账款-销项发票-发票明细
 */
public class AccountSaleInvoiceInfoProperty {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty orderSoruce = new SimpleStringProperty();
    private SimpleStringProperty orderNo = new SimpleStringProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty customerNo = new SimpleStringProperty();
    private SimpleStringProperty customerStr = new SimpleStringProperty();
    private SimpleStringProperty productNo = new SimpleStringProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleStringProperty invoceName = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty money = new SimpleStringProperty();
    private SimpleStringProperty rate = new SimpleStringProperty();
    private SimpleStringProperty rateMoney = new SimpleStringProperty();
    private SimpleStringProperty tax = new SimpleStringProperty();
    private SimpleStringProperty rateNot = new SimpleStringProperty();
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

    public String getOrderSoruce() {
        if(orderSoruce == null){
            return null;
        }else {
            return orderSoruce.get();
        }

    }

    public SimpleStringProperty orderSoruceProperty() {
        return orderSoruce;
    }

    public void setOrderSoruce(String orderSoruce) {
        this.orderSoruce.set(orderSoruce);
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

    public String getCustomerNo() {
        if(customerNo == null){
            return null;
        }else {
            return customerNo.get();
        }

    }

    public SimpleStringProperty customerNoProperty() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo.set(customerNo);
    }

    public String getCustomerStr() {
        if(customerStr == null){
            return null;
        }else {
            return customerStr.get();
        }

    }

    public SimpleStringProperty customerStrProperty() {
        return customerStr;
    }

    public void setCustomerStr(String customerStr) {
        this.customerStr.set(customerStr);
    }

    public String getProductNo() {
        if(productNo == null){
            return null;
        }else {
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
        }else {
            return productName.get();
        }
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getInvoceName() {
        if(invoceName == null){
            return null;
        }else {
            return invoceName.get();
        }
    }

    public SimpleStringProperty invoceNameProperty() {
        return invoceName;
    }

    public void setInvoceName(String invoceName) {
        this.invoceName.set(invoceName);
    }

    public String getUnit() {
        if(unit == null){
            return null;
        }else {
            return unit.get();
        }
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public String getNum() {
        if(num == null){
            return null;
        }else {
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

    public String getMoney() {
        if(money == null){
            return null;
        }else {
            return money.get();
        }
    }

    public SimpleStringProperty moneyProperty() {
        return money;
    }

    public void setMoney(String money) {
        this.money.set(money);
    }

    public String getRate() {
        if(rate == null){
            return null;
        }else {
            return rate.get();
        }
    }

    public SimpleStringProperty rateProperty() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate.set(rate);
    }

    public String getRateMoney() {
        if(rateMoney == null){
            return null;
        }else {
            return rateMoney.get();
        }
    }

    public SimpleStringProperty rateMoneyProperty() {
        return rateMoney;
    }

    public void setRateMoney(String rateMoney) {
        this.rateMoney.set(rateMoney);
    }

    public String getTax() {
        if(tax == null){
            return null;
        }else {
            return tax.get();
        }
    }

    public SimpleStringProperty taxProperty() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax.set(tax);
    }

    public String getRateNot() {
        if(rateNot == null){
            return null;
        }else {
            return rateNot.get();
        }
    }

    public SimpleStringProperty rateNotProperty() {
        return rateNot;
    }

    public void setRateNot(String rateNot) {
        this.rateNot.set(rateNot);
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

    public AccountSaleInvoiceInfoProperty(String orderSoruce, String orderNo, String no, String customerNo, String customerStr, String productNo, String productName, String invoceName, String unit, String num, String price, String money, String rate, String rateMoney, String tax, String rateNot, String remark) {
        if(orderSoruce != null){
            this.orderSoruce = new SimpleStringProperty(orderSoruce);
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no);
        }
        if(customerNo != null){
            this.customerNo = new SimpleStringProperty(customerNo);
        }
        if(customerStr != null){
            this.customerStr = new SimpleStringProperty(customerStr);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(invoceName != null){
            this.invoceName = new SimpleStringProperty(invoceName);
        }

        if(unit != null){
            this.unit = new SimpleStringProperty(unit);
        }
        if(num != null){
            this.num = new SimpleStringProperty(num);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price);
        }
        if(money != null){
            this.money = new SimpleStringProperty(money);
        }
        if(rate != null){
            this.rate = new SimpleStringProperty(rate);
        }
        if(rateMoney != null){
            this.rateMoney = new SimpleStringProperty(rateMoney);
        }
        if(tax != null){
            this.tax = new SimpleStringProperty(tax);
        }
        if(rateNot != null){
            this.rateNot = new SimpleStringProperty(rateNot);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    public AccountSaleInvoiceInfoProperty(Long id, String orderSoruce, String orderNo, String no, String customerNo, String customerStr, String productNo, String productName, String invoceName, String unit, Integer num, BigDecimal price, BigDecimal money, String rate, BigDecimal rateMoney, String tax, BigDecimal rateNot, String remark) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(orderSoruce != null){
            this.orderSoruce = new SimpleStringProperty(orderSoruce);
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no);
        }
        if(customerNo != null){
            this.customerNo = new SimpleStringProperty(customerNo);
        }
        if(customerStr != null){
            this.customerStr = new SimpleStringProperty(customerStr);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(invoceName != null){
            this.invoceName = new SimpleStringProperty(invoceName);
        }
        if(unit != null){
            this.unit = new SimpleStringProperty(unit);
        }
        if(num != null){
            this.num = new SimpleStringProperty(num.toString());
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(money != null){
            this.money = new SimpleStringProperty(money.toString());
        }
        if(rate != null){
            this.rate = new SimpleStringProperty(rate);
        }
        if(rateMoney != null){
            this.rateMoney = new SimpleStringProperty(rateMoney.toString());
        }
        if(tax != null){
            this.tax = new SimpleStringProperty(tax);
        }
        if(rateNot != null){
            this.rateNot = new SimpleStringProperty(rateNot.toString());
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    public AccountSaleInvoiceInfoProperty() {
    }
}
