package com.yc.education.model.sale;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleGoodsProductProperty implements Serializable {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty saleGoodsId= new SimpleStringProperty();
    private SimpleStringProperty productNo = new SimpleStringProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleStringProperty category = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty pricing = new SimpleStringProperty();
    private SimpleStringProperty discount = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty money = new SimpleStringProperty();
    private SimpleStringProperty warehousePosition = new SimpleStringProperty();
    private SimpleStringProperty floor = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();
    private SimpleBooleanProperty checked = new SimpleBooleanProperty();
    private SimpleStringProperty sourceOrder = new SimpleStringProperty();
    private SimpleStringProperty sourceNo = new SimpleStringProperty();
    private SimpleStringProperty weight = new SimpleStringProperty();   //产品重量


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

    public String getWeight() {
        return weight.get();
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
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

    public String getSaleGoodsId() {
        if(saleGoodsId == null){
            return null;
        }else{
            return saleGoodsId.get();
        }
    }

    public SimpleStringProperty saleGoodsIdProperty() {
        return saleGoodsId;
    }

    public void setSaleGoodsId(String saleGoodsId) {
        this.saleGoodsId.set(saleGoodsId);
    }

    public String getWarehousePosition() {
        if(warehousePosition == null){
            return null;
        }else{
            return warehousePosition.get();
        }
    }

    public SimpleStringProperty warehousePositionProperty() {
        return warehousePosition;
    }

    public void setWarehousePosition(String warehousePosition) {
        this.warehousePosition.set(warehousePosition);
    }

    public String getFloor() {
        if(floor == null){
            return null;
        }else{
            return floor.get();
        }
    }

    public SimpleStringProperty floorProperty() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    public String getSourceOrder() {
        return sourceOrder.get();
    }

    public SimpleStringProperty sourceOrderProperty() {
        return sourceOrder;
    }

    public void setSourceOrder(String sourceOrder) {
        this.sourceOrder.set(sourceOrder);
    }

    public String getSourceNo() {
        return sourceNo.get();
    }

    public SimpleStringProperty sourceNoProperty() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo.set(sourceNo);
    }

    public SaleGoodsProductProperty() {
    }

    /**
     * @Description 15 通用方法 - 不带id
     * @Author BlueSky
     * @Date 14:04 2019/4/23
     **/
    public SaleGoodsProductProperty(Integer no,String sourceOrder,String sourceNo,String productNo, String productName, String category, Integer num, String unit, BigDecimal pricing, String discount, BigDecimal price, BigDecimal money, String warehousePosition, String floor, String remark) {
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(sourceOrder != null){
            this.sourceOrder = new SimpleStringProperty(sourceOrder);
        }
        if(sourceNo != null){
            this.sourceNo = new SimpleStringProperty(sourceNo);
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

    /**
     * @Description 17 加载原始数据 + no
     * @Author BlueSky
     * @Date 11:16 2019/4/23
     **/
    public SaleGoodsProductProperty(Long id ,Integer no, String sourceOrder,String sourceNo,Long saleGoodsId, String productNo, String productName, String category, Integer num, String unit, BigDecimal pricing, String discount, BigDecimal price, BigDecimal money, String warehousePosition, String floor, String remark) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(no != null){
            this.no = new SimpleStringProperty(no.toString());
        }
        if(sourceOrder != null){
            this.sourceOrder = new SimpleStringProperty(sourceOrder);
        }
        if(sourceNo != null){
            this.sourceNo = new SimpleStringProperty(sourceNo);
        }
        if(saleGoodsId != null){
            this.saleGoodsId = new SimpleStringProperty(saleGoodsId.toString());
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

    /**
     * @Description 14 导出 + 一个复选框
     * @Author BlueSky
     * @Date 10:56 2019/4/23
     **/
    public SaleGoodsProductProperty(Long id ,  String productNo, String productName, String category, Integer num, String unit, BigDecimal pricing, String discount, BigDecimal price, BigDecimal money, String warehousePosition, String floor, String remark,Boolean checked) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
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
