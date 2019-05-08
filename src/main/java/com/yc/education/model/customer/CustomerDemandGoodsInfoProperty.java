package com.yc.education.model.customer;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;


public class CustomerDemandGoodsInfoProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty productNo = new SimpleStringProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleStringProperty brand = new SimpleStringProperty();
    private SimpleStringProperty quantityDemand = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty price = new SimpleStringProperty();
    private SimpleStringProperty productCategories = new SimpleStringProperty();
    private SimpleStringProperty productNature = new SimpleStringProperty();
    private SimpleStringProperty manufactureMethod = new SimpleStringProperty();
    private SimpleStringProperty processMethod = new SimpleStringProperty();
    private SimpleStringProperty processMaterial = new SimpleStringProperty();
    private SimpleStringProperty material = new SimpleStringProperty();
    private SimpleStringProperty continuity = new SimpleStringProperty();
    private SimpleStringProperty cutOil = new SimpleStringProperty();
    private SimpleStringProperty useAmount = new SimpleStringProperty();
    private SimpleStringProperty cutSpeed = new SimpleStringProperty();


    public CustomerDemandGoodsInfoProperty() {
    }

    public CustomerDemandGoodsInfoProperty(Long id, String productNo, String productName, String brand, Integer quantityDemand, String unit,  BigDecimal price, String productCategories, String productNature, String manufactureMethod, String processMethod, String processMaterial, String material,String continuity, String cutOil, Integer useAmount, String cutSpeed) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(brand != null){
            this.brand = new SimpleStringProperty(brand);
        }
        if(quantityDemand != null){
            this.quantityDemand = new SimpleStringProperty(quantityDemand.toString());
        }
        if(unit != null){
            this.unit = new SimpleStringProperty(unit);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price.toString());
        }
        if(productCategories != null){
            this.productCategories = new SimpleStringProperty(productCategories);
        }
        if(productNature != null){
            this.productNature = new SimpleStringProperty(productNature);
        }
        if(manufactureMethod != null){
            this.manufactureMethod = new SimpleStringProperty(manufactureMethod);
        }
        if(processMethod != null){
            this.processMethod = new SimpleStringProperty(processMethod);
        }
        if(processMaterial != null){
            this.processMaterial = new SimpleStringProperty(processMaterial);
        }
        if(material != null){
            this.material = new SimpleStringProperty(material);
        }
        if(continuity != null){
            this.continuity = new SimpleStringProperty(continuity);
        }
        if(cutOil != null){
            this.cutOil = new SimpleStringProperty(cutOil);
        }
        if(useAmount != null){
            this.useAmount = new SimpleStringProperty(useAmount.toString());
        }
        if(cutSpeed != null){
            this.cutSpeed = new SimpleStringProperty(cutSpeed);
        }
    }

    public CustomerDemandGoodsInfoProperty(Long id, String productNo, String productName, String brand, String quantityDemand, String unit,String price, String productCategories, String productNature, String manufactureMethod, String processMethod,String processMaterial, String material, String continuity, String cutOil, String useAmount, String cutSpeed) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(brand != null){
            this.brand = new SimpleStringProperty(brand);
        }
        if(quantityDemand != null){
            this.quantityDemand = new SimpleStringProperty(quantityDemand);
        }
        if(unit != null){
            this.unit = new SimpleStringProperty(unit);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price);
        }
        if(productCategories != null){
            this.productCategories = new SimpleStringProperty(productCategories);
        }
        if(productNature != null){
            this.productNature = new SimpleStringProperty(productNature);
        }
        if(manufactureMethod != null){
            this.manufactureMethod = new SimpleStringProperty(manufactureMethod);
        }
        if(processMethod != null){
            this.processMethod = new SimpleStringProperty(processMethod);
        }
        if(processMaterial != null){
            this.processMaterial = new SimpleStringProperty(processMaterial);
        }
        if(material != null){
            this.material = new SimpleStringProperty(material);
        }
        if(continuity != null){
            this.continuity = new SimpleStringProperty(continuity);
        }
        if(cutOil != null){
            this.cutOil = new SimpleStringProperty(cutOil);
        }
        if(useAmount != null){
            this.useAmount = new SimpleStringProperty(useAmount);
        }
        if(cutSpeed != null){
            this.cutSpeed = new SimpleStringProperty(cutSpeed);
        }
    }

    public CustomerDemandGoodsInfoProperty(String productNo, String productName, String brand, String quantityDemand,String unit, String price, String productCategories, String productNature, String manufactureMethod, String processMethod, String processMaterial, String material, String continuity, String cutOil, String useAmount, String cutSpeed) {

        if(productNo != null){
            this.productNo = new SimpleStringProperty(productNo);
        }
        if(productName != null){
            this.productName = new SimpleStringProperty(productName);
        }
        if(brand != null){
            this.brand = new SimpleStringProperty(brand);
        }
        if(quantityDemand != null){
            this.quantityDemand = new SimpleStringProperty(quantityDemand);
        }
        if(unit != null){
            this.unit = new SimpleStringProperty(unit);
        }
        if(price != null){
            this.price = new SimpleStringProperty(price);
        }
        if(productCategories != null){
            this.productCategories = new SimpleStringProperty(productCategories);
        }
        if(productNature != null){
            this.productNature = new SimpleStringProperty(productNature);
        }
        if(manufactureMethod != null){
            this.manufactureMethod = new SimpleStringProperty(manufactureMethod);
        }
        if(processMethod != null){
            this.processMethod = new SimpleStringProperty(processMethod);
        }
        if(processMaterial != null){
            this.processMaterial = new SimpleStringProperty(processMaterial);
        }
        if(material != null){
            this.material = new SimpleStringProperty(material);
        }
        if(continuity != null){
            this.continuity = new SimpleStringProperty(continuity);
        }
        if(cutOil != null){
            this.cutOil = new SimpleStringProperty(cutOil);
        }
        if(useAmount != null){
            this.useAmount = new SimpleStringProperty(useAmount);
        }
        if(cutSpeed != null){
            this.cutSpeed = new SimpleStringProperty(cutSpeed);
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


    public String getProcessMaterial() {
        if(processMaterial == null){
            return null;
        }else{
            return processMaterial.get();
        }
    }

    public SimpleStringProperty processMaterialProperty() {
        return processMaterial;
    }

    public void setProcessMaterial(String processMaterial) {
        this.processMaterial.set(processMaterial);
    }

    public String getMaterial() {
        if(material == null){
            return null;
        }else{
            return material.get();
        }
    }

    public SimpleStringProperty materialProperty() {
        return material;
    }

    public void setMaterial(String material) {
        this.material.set(material);
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

    public String getBrand() {
        if(brand == null){
            return null;
        }else{
            return brand.get();
        }
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getQuantityDemand() {
        if(quantityDemand == null){
            return null;
        }else{
            return quantityDemand.get();
        }
    }

    public SimpleStringProperty quantityDemandProperty() {
        return quantityDemand;
    }

    public void setQuantityDemand(String quantityDemand) {
        this.quantityDemand.set(quantityDemand);
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

    public String getProductCategories() {
        if(productCategories == null){
            return null;
        }else{
            return productCategories.get();
        }
    }

    public SimpleStringProperty productCategoriesProperty() {
        return productCategories;
    }

    public void setProductCategories(String productCategories) {
        this.productCategories.set(productCategories);
    }

    public String getProductNature() {
        if(productNature == null){
            return null;
        }else{
            return productNature.get();
        }
    }

    public SimpleStringProperty productNatureProperty() {
        return productNature;
    }

    public void setProductNature(String productNature) {
        this.productNature.set(productNature);
    }

    public String getManufactureMethod() {
        if(manufactureMethod == null){
            return null;
        }else{
            return manufactureMethod.get();
        }
    }

    public SimpleStringProperty manufactureMethodProperty() {
        return manufactureMethod;
    }

    public void setManufactureMethod(String manufactureMethod) {
        this.manufactureMethod.set(manufactureMethod);
    }

    public String getProcessMethod() {
        if(processMethod == null){
            return null;
        }else{
            return processMethod.get();
        }
    }

    public SimpleStringProperty processMethodProperty() {
        return processMethod;
    }

    public void setProcessMethod(String processMethod) {
        this.processMethod.set(processMethod);
    }

    public String getContinuity() {
        if(continuity == null){
            return null;
        }else{
            return continuity.get();
        }
    }

    public SimpleStringProperty continuityProperty() {
        return continuity;
    }

    public void setContinuity(String continuity) {
        this.continuity.set(continuity);
    }

    public String getCutOil() {
        if(cutOil == null){
            return null;
        }else{
            return cutOil.get();
        }
    }

    public SimpleStringProperty cutOilProperty() {
        return cutOil;
    }

    public void setCutOil(String cutOil) {
        this.cutOil.set(cutOil);
    }

    public String getUseAmount() {
        if(useAmount == null){
            return null;
        }else{
            return useAmount.get();
        }
    }

    public SimpleStringProperty useAmountProperty() {
        return useAmount;
    }

    public void setUseAmount(String useAmount) {
        this.useAmount.set(useAmount);
    }

    public String getCutSpeed() {
        if(cutSpeed == null){
            return null;
        }else{
            return cutSpeed.get();
        }
    }

    public SimpleStringProperty cutSpeedProperty() {
        return cutSpeed;
    }

    public void setCutSpeed(String cutSpeed) {
        this.cutSpeed.set(cutSpeed);
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
}
