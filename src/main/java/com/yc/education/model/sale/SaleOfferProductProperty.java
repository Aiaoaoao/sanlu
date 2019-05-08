package com.yc.education.model.sale;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

/**
 * 报价产品
 */
public class SaleOfferProductProperty {

    /**
     *
     */
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty no = new SimpleStringProperty();    // 序号
    private SimpleStringProperty productNo = new SimpleStringProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleStringProperty category = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty pricing = new SimpleStringProperty();
    private SimpleStringProperty discount = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty money = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();
    private SimpleStringProperty userid = new SimpleStringProperty();

    public SaleOfferProductProperty(Long id, String productNo, String productName, String category, Integer num, String unit, BigDecimal pricing, String discount, BigDecimal price, BigDecimal money, String remark, Long userid) {
        this.id = new SimpleLongProperty(id);
        this.productNo = new SimpleStringProperty(productNo);
        this.productName = new SimpleStringProperty(productName);
        this.category = new SimpleStringProperty(category);
        this.num = new SimpleStringProperty(num.toString());
        this.unit = new SimpleStringProperty(unit);
        this.pricing = new SimpleStringProperty(pricing.toString());
        this.discount = new SimpleStringProperty(discount);
        this.price = new SimpleStringProperty(price.toString());
        this.money = new SimpleStringProperty(money.toString());
        this.remark = new SimpleStringProperty(remark);
        this.userid = new SimpleStringProperty(userid.toString());
    }

    public SaleOfferProductProperty(String productNo, String productName, String category, Integer num, String unit, BigDecimal pricing, String discount, BigDecimal price, BigDecimal money, String remark) {
        this.productNo = new SimpleStringProperty(productNo);
        this.productName = new SimpleStringProperty(productName);
        this.category = new SimpleStringProperty(category);
        this.num = new SimpleStringProperty(num.toString());
        this.unit = new SimpleStringProperty(unit);
        this.pricing = new SimpleStringProperty(pricing.toString());
        this.discount = new SimpleStringProperty(discount);
        this.price = new SimpleStringProperty(price.toString());
        this.money = new SimpleStringProperty(money.toString());
        this.remark = new SimpleStringProperty(remark);
    }

    // 多个 no
    public SaleOfferProductProperty(Long id,Integer no, String productNo, String productName, String category, Integer num, String unit, BigDecimal pricing, String discount, BigDecimal price, BigDecimal money, String remark, Long userid) {
        this.id = new SimpleLongProperty(id);
        this.no = new SimpleStringProperty(no.toString());
        this.productNo = new SimpleStringProperty(productNo);
        this.productName = new SimpleStringProperty(productName);
        this.category = new SimpleStringProperty(category);
        this.num = new SimpleStringProperty(num.toString());
        this.unit = new SimpleStringProperty(unit);
        this.pricing = new SimpleStringProperty(pricing.toString());
        this.discount = new SimpleStringProperty(discount);
        this.price = new SimpleStringProperty(price.toString());
        this.money = new SimpleStringProperty(money.toString());
        this.remark = new SimpleStringProperty(remark);
        this.userid = new SimpleStringProperty(userid.toString());
    }

    // 多个 no
    public SaleOfferProductProperty(String no,String productNo, String productName, String category, Integer num, String unit, BigDecimal pricing, String discount, BigDecimal price, BigDecimal money, String remark) {
        this.no = new SimpleStringProperty(no);
        this.productNo = new SimpleStringProperty(productNo);
        this.productName = new SimpleStringProperty(productName);
        this.category = new SimpleStringProperty(category);
        this.num = new SimpleStringProperty(num.toString());
        this.unit = new SimpleStringProperty(unit);
        this.pricing = new SimpleStringProperty(pricing.toString());
        this.discount = new SimpleStringProperty(discount);
        this.price = new SimpleStringProperty(price.toString());
        this.money = new SimpleStringProperty(money.toString());
        this.remark = new SimpleStringProperty(remark);
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

    public SaleOfferProductProperty() {
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

    public String getUserid() {
        if(userid == null){
            return null;
        }else{
            return userid.get();
        }
    }

    public SimpleStringProperty useridProperty() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid.set(userid);
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
}
