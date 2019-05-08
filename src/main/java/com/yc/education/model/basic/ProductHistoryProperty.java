package com.yc.education.model.basic;

import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

/**
 * @ClassName ProductHistoryProperty
 * @Description TODO 产品历史交易记录
 * @Author QuZhangJing
 * @Date 2019/3/26 16:44
 * @Version 1.0
 */
public class ProductHistoryProperty {

    private  final SimpleStringProperty createDate = new SimpleStringProperty();

    private  final SimpleStringProperty orderType = new SimpleStringProperty();

    private  final SimpleStringProperty orderNo = new SimpleStringProperty();

    private  final SimpleStringProperty supplierName = new SimpleStringProperty();

    private  final SimpleStringProperty productName = new SimpleStringProperty();

    private  final SimpleStringProperty productNum = new SimpleStringProperty();

    private  final SimpleStringProperty productPrice = new SimpleStringProperty();

    private  final SimpleStringProperty taxType = new SimpleStringProperty();

    private  final SimpleStringProperty productRemarks = new SimpleStringProperty();

    public ProductHistoryProperty() {
    }

    public ProductHistoryProperty(String createDate,String orderType,String orderNo , String supplierName,String productName,String productNum,Object productPrice,String taxType,String productRemarks) {
    setCreateDate(createDate);
    setOrderType(orderType);
    setOrderNo(orderNo);
    setSupplierName(supplierName);
    setProductName(productName);
    setProductNum(productNum);
    setProductPrice(String.valueOf(productPrice));
    setTaxType(taxType);
    setProductRemarks(productRemarks);
    }


    public String getCreateDate() {
        return createDate.get();
    }

    public SimpleStringProperty createDateProperty() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }

    public String getOrderType() {
        return orderType.get();
    }

    public SimpleStringProperty orderTypeProperty() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType.set(orderType);
    }

    public String getOrderNo() {
        return orderNo.get();
    }

    public SimpleStringProperty orderNoProperty() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo.set(orderNo);
    }

    public String getSupplierName() {
        return supplierName.get();
    }

    public SimpleStringProperty supplierNameProperty() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName.set(supplierName);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getProductNum() {
        return productNum.get();
    }

    public SimpleStringProperty productNumProperty() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum.set(productNum);
    }

    public String getProductPrice() {
        return productPrice.get();
    }

    public SimpleStringProperty productPriceProperty() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice.set(productPrice);
    }

    public String getTaxType() {
        return taxType.get();
    }

    public SimpleStringProperty taxTypeProperty() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType.set(taxType);
    }

    public String getProductRemarks() {
        return productRemarks.get();
    }

    public SimpleStringProperty productRemarksProperty() {
        return productRemarks;
    }

    public void setProductRemarks(String productRemarks) {
        this.productRemarks.set(productRemarks);
    }
}
