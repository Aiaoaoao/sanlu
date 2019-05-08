package com.yc.education.model.account;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 账款-收款单-详情
 */
public class AccountInputInvoiceInfoProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty orderSource = new SimpleStringProperty();
    private SimpleStringProperty orderNo = new SimpleStringProperty();
    private SimpleStringProperty orderNum = new SimpleStringProperty();
    private SimpleStringProperty productNo = new SimpleStringProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleStringProperty invoiceName = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty tax = new SimpleStringProperty();
    private SimpleStringProperty money = new SimpleStringProperty();
    private SimpleStringProperty taxMoney = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();

    public AccountInputInvoiceInfoProperty(Long id, Integer no,String orderSource, String orderNo, String orderNum, String productNo, String productName, String invoiceName, String unit, Integer num, BigDecimal price,BigDecimal money, String tax, BigDecimal taxMoney, String remark) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(orderSource != null){
            this.orderSource = new SimpleStringProperty(orderSource);
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(orderNum != null){
            this.orderNum = new SimpleStringProperty(orderNum);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(invoiceName != null){
            this.invoiceName = new SimpleStringProperty(invoiceName);
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
        if(tax != null){
            this.tax = new SimpleStringProperty(tax);
        }
        if(taxMoney != null){
            this.taxMoney = new SimpleStringProperty(taxMoney.toString());
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }


    public AccountInputInvoiceInfoProperty(Integer no,String orderSource, String orderNo, String orderNum, String productNo, String productName, String invoiceName, String unit,String num, String price,String money, String tax, String taxMoney, String remark) {
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(orderSource != null){
            this.orderSource = new SimpleStringProperty(orderSource);
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(orderNum != null){
            this.orderNum = new SimpleStringProperty(orderNum);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(invoiceName != null){
            this.invoiceName = new SimpleStringProperty(invoiceName);
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
        if(tax != null){
            this.tax = new SimpleStringProperty(tax);
        }
        if(taxMoney != null){
            this.taxMoney = new SimpleStringProperty(taxMoney);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }


    public AccountInputInvoiceInfoProperty() {
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

    public String getOrderSource() {
        if(orderSource == null){
            return null;
        }else {
            return orderSource.get();
        }

    }

    public SimpleStringProperty orderSourceProperty() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource.set(orderSource);
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

    public String getOrderNum() {
        if(orderNum == null){
            return null;
        }else {
            return orderNum.get();
        }

    }

    public SimpleStringProperty orderNumProperty() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum.set(orderNum);
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

    public String getInvoiceName() {
        if(invoiceName == null){
            return null;
        }else {
            return invoiceName.get();
        }

    }

    public SimpleStringProperty invoiceNameProperty() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName.set(invoiceName);
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

    public String getTaxMoney() {
        if(taxMoney == null){
            return null;
        }else {
            return taxMoney.get();
        }

    }

    public SimpleStringProperty taxMoneyProperty() {
        return taxMoney;
    }

    public void setTaxMoney(String taxMoney) {
        this.taxMoney.set(taxMoney);
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
}
