package com.yc.education.model.sale;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class SalePurchaseOrderProductProperty {

    private SimpleBooleanProperty checked = new SimpleBooleanProperty(); // 是否勾选
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty purchaseOrderId = new SimpleStringProperty();
    private SimpleStringProperty productNo = new SimpleStringProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleStringProperty category = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty pricing = new SimpleStringProperty();
    private SimpleStringProperty discount = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty money = new SimpleStringProperty();
    private SimpleStringProperty orderNo = new SimpleStringProperty();
    private SimpleStringProperty productSource = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();
    private SimpleStringProperty warehousePosition = new SimpleStringProperty();
    private SimpleStringProperty floor = new SimpleStringProperty();

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

    public String getWarehousePosition() {
        return warehousePosition.get();
    }

    public SimpleStringProperty warehousePositionProperty() {
        return warehousePosition;
    }

    public void setWarehousePosition(String warehousePosition) {
        this.warehousePosition.set(warehousePosition);
    }

    public String getFloor() {
        return floor.get();
    }

    public SimpleStringProperty floorProperty() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor.set(floor);
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

    public String getPricing() {

        if(pricing == null){
            return null;
        }else{
            return pricing.get();
        }
    }

    public SimpleStringProperty pricingProperty() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing.set(pricing);
    }

    public String getDiscount() {
        if(discount == null){
            return null;
        }else{
            return discount.get();
        }
    }

    public SimpleStringProperty discountProperty() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount.set(discount);
    }

    public String getPurchaseOrderId() {
        if(purchaseOrderId == null){
            return null;
        }else{
            return purchaseOrderId.get();
        }
    }

    public SimpleStringProperty purchaseOrderIdProperty() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId.set(purchaseOrderId);
    }

    public String getOrderNo() {
        if(orderNo == null){
            return null;
        }else{
            return orderNo.get();
        }
    }

    public SimpleStringProperty orderNoProperty() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo.set(orderNo);
    }

    public String getProductSource() {
        if(productSource == null){
            return null;
        }else{
            return productSource.get();
        }
    }

    public SimpleStringProperty productSourceProperty() {
        return productSource;
    }

    public void setProductSource(String productSource) {
        this.productSource.set(productSource);
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

    public SalePurchaseOrderProductProperty() {
    }

    //  多个序号 no
    public SalePurchaseOrderProductProperty(Long id ,Integer no,Long purchaseOrderId, String productNo, String productName, String category, Integer num, String unit,BigDecimal pricing,String discount, BigDecimal price, BigDecimal money, String orderNo, String productSource, String warehousePosition, String floor, String remark) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(purchaseOrderId != null){
            this.purchaseOrderId = new SimpleStringProperty(purchaseOrderId.toString());
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
        if(pricing != null){
            this.pricing = new SimpleStringProperty(pricing.toString());
        }
        if(discount != null){
            this.discount = new SimpleStringProperty(discount);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(money != null){
            this.money = new SimpleStringProperty(money.toString());
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(productSource != null){
            this.productSource = new SimpleStringProperty(productSource);
        }
        if(warehousePosition != null){
            this.warehousePosition = new SimpleStringProperty(warehousePosition);
        }
        if(floor != null){
            this.floor = new SimpleStringProperty(floor);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    // 多个序号 no
    public SalePurchaseOrderProductProperty( Integer no, String productNo, String productName, String category, Integer num, String unit,BigDecimal pricing,String discount, BigDecimal price, BigDecimal money, String orderNo, String productSource, String warehousePosition, String floor, String remark) {
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
        if(pricing != null){
            this.pricing = new SimpleStringProperty(pricing.toString());
        }
        if(discount != null){
            this.discount = new SimpleStringProperty(discount);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(money != null){
            this.money = new SimpleStringProperty(money.toString());
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(productSource != null){
            this.productSource = new SimpleStringProperty(productSource);
        }
        if(warehousePosition != null){
            this.warehousePosition = new SimpleStringProperty(warehousePosition);
        }
        if(floor != null){
            this.floor = new SimpleStringProperty(floor);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    public SalePurchaseOrderProductProperty(Long id ,Long purchaseOrderId, String productNo, String productName, String category, Integer num, String unit,BigDecimal pricing,String discount, BigDecimal price, BigDecimal money, String orderNo, String productSource, String warehousePosition, String floor, String remark, Boolean checked) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(purchaseOrderId != null){
            this.purchaseOrderId = new SimpleStringProperty(purchaseOrderId.toString());
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
        if(pricing != null){
            this.pricing = new SimpleStringProperty(pricing.toString());
        }
        if(discount != null){
            this.discount = new SimpleStringProperty(discount);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(money != null){
            this.money = new SimpleStringProperty(money.toString());
        }
        if(orderNo != null){
            this.orderNo = new SimpleStringProperty(orderNo);
        }
        if(productSource != null){
            this.productSource = new SimpleStringProperty(productSource);
        }
        if(warehousePosition != null){
            this.warehousePosition = new SimpleStringProperty(warehousePosition);
        }
        if(floor != null){
            this.floor = new SimpleStringProperty(floor);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
        if(checked != null){
            this.checked = new SimpleBooleanProperty(checked);
        }
    }

}
