package com.yc.education.model.customer;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;


public class CustomerDataMaintainCarProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleLongProperty maintainId = new SimpleLongProperty();
    private SimpleStringProperty latheType = new SimpleStringProperty();
    private SimpleStringProperty brand = new SimpleStringProperty();
    private SimpleStringProperty models = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();

    public CustomerDataMaintainCarProperty() {
    }

    public CustomerDataMaintainCarProperty(Long id, Long maintainId, String latheType, String brand, String models, String num) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(maintainId != null){
            this.maintainId = new SimpleLongProperty(maintainId);
        }
        if(latheType != null){
            this.latheType = new SimpleStringProperty(latheType);
        }
        if(brand != null){
            this.brand = new SimpleStringProperty(brand);
        }
        if(models != null){
            this.models = new SimpleStringProperty(models);
        }
        if(num != null){
            this.num = new SimpleStringProperty(num);
        }
    }

    public CustomerDataMaintainCarProperty(String latheType, String brand, String models, String num) {
        if(latheType != null){
            this.latheType = new SimpleStringProperty(latheType);
        }
        if(brand != null){
            this.brand = new SimpleStringProperty(brand);
        }
        if(models != null){
            this.models = new SimpleStringProperty(models);
        }
        if(num != null){
            this.num = new SimpleStringProperty(num);
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

    public Long getMaintainId() {
        if(maintainId == null){
            return null;
        }else{
            return maintainId.get();
        }
    }

    public SimpleLongProperty maintainIdProperty() {
        return maintainId;
    }

    public void setMaintainId(long maintainId) {
        this.maintainId.set(maintainId);
    }

    public String getLatheType() {
        if(latheType == null){
            return null;
        }else{
            return latheType.get();
        }
    }

    public SimpleStringProperty latheTypeProperty() {
        return latheType;
    }

    public void setLatheType(String latheType) {
        this.latheType.set(latheType);
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

    public String getModels() {
        if(models == null){
            return null;
        }else{
            return models.get();
        }
    }

    public SimpleStringProperty modelsProperty() {
        return models;
    }

    public void setModels(String models) {
        this.models.set(models);
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
