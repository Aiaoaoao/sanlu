package com.yc.education.model.customer;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;


public class CustomerBusinessLeaderProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty customerId = new SimpleStringProperty();
    private SimpleStringProperty employeeCode = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty primaryPeople = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();
    private SimpleStringProperty assistant = new SimpleStringProperty();


    public Long getId() {
        if(id == null){
            return null;
        }else{
            return id.get();
        }
    }

    public String getAssistant() {
        if(assistant == null){
            return null;
        }else {
            return assistant.get();
        }
    }

    public SimpleStringProperty assistantProperty() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant.set(assistant);
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getCustomerId() {
        if(customerId == null){
            return null;
        }else{
            return customerId.get();
        }
    }

    public SimpleStringProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public String getEmployeeCode() {

        if(employeeCode == null){
            return null;
        }else{
            return employeeCode.get();
        }
    }

    public SimpleStringProperty employeeCodeProperty() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode.set(employeeCode);
    }

    public String getName() {
        if(name == null){
            return null;
        }else{
            return name.get();
        }
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPrimaryPeople() {
        if(primaryPeople == null){
            return null;
        }else{
            return primaryPeople.get();
        }
    }

    public SimpleStringProperty primaryPeopleProperty() {
        return primaryPeople;
    }

    public void setPrimaryPeople(String primaryPeople) {
        this.primaryPeople.set(primaryPeople);
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

    public CustomerBusinessLeaderProperty( String customerId, String employeeCode, String name, String primaryPeople, String remark,String assistant) {
        if(customerId != null){
            this.customerId = new SimpleStringProperty(customerId);
        }
        if(employeeCode != null){
            this.employeeCode = new SimpleStringProperty(employeeCode);
        }
        if(name != null){
            this.name = new SimpleStringProperty(name);
        }
        if(primaryPeople != null){
            this.primaryPeople = new SimpleStringProperty(primaryPeople);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
        if(assistant != null){
            this.assistant = new SimpleStringProperty(assistant);
        }
    }

    public CustomerBusinessLeaderProperty(Long id, String customerId, String employeeCode, String name, String primaryPeople, String remark,String assistant) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(customerId != null){
            this.customerId = new SimpleStringProperty(customerId);
        }
        if(employeeCode != null){
            this.employeeCode = new SimpleStringProperty(employeeCode);
        }
        if(name != null){
            this.name = new SimpleStringProperty(name);
        }
        if(primaryPeople != null){
            this.primaryPeople = new SimpleStringProperty(primaryPeople);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
        if(assistant != null){
            this.assistant = new SimpleStringProperty(assistant);
        }
    }

    public CustomerBusinessLeaderProperty() {
    }
}
