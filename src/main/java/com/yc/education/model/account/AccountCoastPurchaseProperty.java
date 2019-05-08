package com.yc.education.model.account;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

/**
 * @Description 成本核算-采购成本
 * @Author BlueSky
 * @Date 2019-01-18 11:24
 */
public class AccountCoastPurchaseProperty {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleIntegerProperty no = new SimpleIntegerProperty();
    private SimpleStringProperty productNo = new SimpleStringProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleStringProperty warehousePosition = new SimpleStringProperty();
    private SimpleStringProperty warehouseNum = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty dollar = new SimpleStringProperty();
    private SimpleStringProperty orderNo = new SimpleStringProperty();
    private SimpleStringProperty rmbMoney = new SimpleStringProperty();
    private SimpleStringProperty usdMoney = new SimpleStringProperty();

    private SimpleDoubleProperty tempProductPrice = new SimpleDoubleProperty();     //单项产品货款
    private SimpleDoubleProperty tempGoodsTotalPrice = new SimpleDoubleProperty();  //货物总价
    private SimpleDoubleProperty tempGoodsRate = new SimpleDoubleProperty();        //占货款比例
    private SimpleDoubleProperty tempGoodsCost = new SimpleDoubleProperty();        //某商品成本
    private SimpleDoubleProperty tempUnitCost = new SimpleDoubleProperty();         //单位成本

    public AccountCoastPurchaseProperty(Long id,Integer no, String productNo, String productName, String warehousePosition, Integer warehouseNum, BigDecimal price, BigDecimal dollar, String orderNo, BigDecimal rmbMoney,BigDecimal usdMoney) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleIntegerProperty(no);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(warehousePosition != null){
            this.warehousePosition = new SimpleStringProperty(warehousePosition);
        }
        if(warehouseNum != null){
            this.warehouseNum = new SimpleStringProperty(warehouseNum.toString());
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(dollar != null){
            this.dollar = new SimpleStringProperty(dollar.toString());
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(rmbMoney != null){
            this.rmbMoney = new SimpleStringProperty(rmbMoney.toString());
        }
        if(usdMoney != null){
            this.usdMoney = new SimpleStringProperty(usdMoney.toString());
        }

    }

    public AccountCoastPurchaseProperty(  Integer no,String productNo, String productName, String warehousePosition, String warehouseNum, String price, String dollar, String orderNo, String rmbMoney,String usdMoney) {
        if(no != null){
            this.no = new SimpleIntegerProperty(no);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(warehousePosition != null){
            this.warehousePosition = new SimpleStringProperty(warehousePosition);
        }
        if(warehouseNum != null){
            this.warehouseNum = new SimpleStringProperty(warehouseNum);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price);
        }
        if(dollar != null){
            this.dollar = new SimpleStringProperty(dollar);
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(rmbMoney != null){
            this.rmbMoney = new SimpleStringProperty(rmbMoney);
        }
        if(usdMoney != null){
            this.usdMoney = new SimpleStringProperty(usdMoney.toString());
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

    public String getUsdMoney() {
        return usdMoney.get();
    }

    public SimpleStringProperty usdMoneyProperty() {
        return usdMoney;
    }

    public void setUsdMoney(String usdMoney) {
        this.usdMoney.set(usdMoney);
    }

    public int getNo() {
        return no.get();
    }

    public SimpleIntegerProperty noProperty() {
        return no;
    }

    public void setNo(int no) {
        this.no.set(no);
    }

    public String getRmbMoney() {
        if(rmbMoney == null){
            return null;
        }else {
            return rmbMoney.get();
        }
    }

    public SimpleStringProperty rmbMoneyProperty() {
        return rmbMoney;
    }

    public void setRmbMoney(String rmbMoney) {
        this.rmbMoney.set(rmbMoney);
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

    public String getWarehousePosition() {
        if(warehousePosition == null){
            return null;
        }else {
            return warehousePosition.get();
        }

    }

    public SimpleStringProperty warehousePositionProperty() {
        return warehousePosition;
    }

    public void setWarehousePosition(String warehousePosition) {
        this.warehousePosition.set(warehousePosition);
    }

    public String getWarehouseNum() {
        if(warehouseNum == null){
            return null;
        }else {
            return warehouseNum.get();
        }

    }

    public SimpleStringProperty warehouseNumProperty() {
        return warehouseNum;
    }

    public void setWarehouseNum(String warehouseNum) {
        this.warehouseNum.set(warehouseNum);
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

    public String getDollar() {
        if(dollar == null){
            return null;
        }else {
            return dollar.get();
        }

    }

    public SimpleStringProperty dollarProperty() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar.set(dollar);
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

    public Double getTempProductPrice() {
        return tempProductPrice.get();
    }

    public SimpleDoubleProperty tempProductPriceProperty() {
        return tempProductPrice;
    }

    public void setTempProductPrice(double tempProductPrice) {
        this.tempProductPrice.set(tempProductPrice);
    }

    public Double getTempGoodsTotalPrice() {
        return tempGoodsTotalPrice.get();
    }

    public SimpleDoubleProperty tempGoodsTotalPriceProperty() {
        return tempGoodsTotalPrice;
    }

    public void setTempGoodsTotalPrice(double tempGoodsTotalPrice) {
        this.tempGoodsTotalPrice.set(tempGoodsTotalPrice);
    }

    public Double getTempGoodsRate() {
        return tempGoodsRate.get();
    }

    public SimpleDoubleProperty tempGoodsRateProperty() {
        return tempGoodsRate;
    }

    public void setTempGoodsRate(double tempGoodsRate) {
        this.tempGoodsRate.set(tempGoodsRate);
    }

    public Double getTempGoodsCost() {
        return tempGoodsCost.get();
    }

    public SimpleDoubleProperty tempGoodsCostProperty() {
        return tempGoodsCost;
    }

    public void setTempGoodsCost(double tempGoodsCost) {
        this.tempGoodsCost.set(tempGoodsCost);
    }

    public Double getTempUnitCost() {
        return tempUnitCost.get();
    }

    public SimpleDoubleProperty tempUnitCostProperty() {
        return tempUnitCost;
    }

    public void setTempUnitCost(double tempUnitCost) {
        this.tempUnitCost.set(tempUnitCost);
    }
}
