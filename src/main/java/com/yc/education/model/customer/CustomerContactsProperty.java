package com.yc.education.model.customer;


import com.yc.education.controller.customer.CustomerBasicInfoController;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


public class CustomerContactsProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty customerId = new SimpleStringProperty();
    private SimpleStringProperty primaryContacts = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty department = new SimpleStringProperty();
    private SimpleStringProperty position = new SimpleStringProperty();
    private SimpleStringProperty telephone = new SimpleStringProperty();
    private SimpleStringProperty fax = new SimpleStringProperty();
    private SimpleStringProperty mobilePhone = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();
    private SimpleObjectProperty<CustomerBasicInfoController.ParticipationUsual> participation = new SimpleObjectProperty<CustomerBasicInfoController.ParticipationUsual>();


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

    public String getPrimaryContacts() {
        if(primaryContacts == null){
            return null;
        }else{
            return primaryContacts.get();
        }
    }

    public SimpleStringProperty primaryContactsProperty() {
        return primaryContacts;
    }

    public void setPrimaryContacts(String primaryContacts) {
        this.primaryContacts.set(primaryContacts);
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

    public String getDepartment() {
        if(department == null){
            return null;
        }else{
            return department.get();
        }
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getPosition() {
        if(position == null){
            return null;
        }else{
            return position.get();
        }
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getTelephone() {
        if(telephone == null){
            return null;
        }else{
            return telephone.get();
        }
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getFax() {
        if(fax == null){
            return null;
        }else{
            return fax.get();
        }
    }

    public SimpleStringProperty faxProperty() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax.set(fax);
    }

    public String getMobilePhone() {
        if(mobilePhone == null){
            return null;
        }else{
            return mobilePhone.get();
        }
    }

    public SimpleStringProperty mobilePhoneProperty() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone.set(mobilePhone);
    }

    public String getEmail() {
        if(email == null){
            return null;
        }else{
            return email.get();
        }
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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

    public CustomerContactsProperty(Long id, String customerId, Boolean primaryContacts, String name, String department, String position, String telephone, String fax, String mobilePhone, String email, String remark) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(customerId != null){
            this.customerId = new SimpleStringProperty(customerId);
        }
        if(primaryContacts != null && primaryContacts){
            this.participation.setValue(CustomerBasicInfoController.ParticipationUsual.常用);
        }
        if(name != null){
            this.name = new SimpleStringProperty(name);
        }
        if(department != null){
            this.department = new SimpleStringProperty(department);
        }
        if(position != null){
            this.position = new SimpleStringProperty(position);
        }
        if(telephone != null){
            this.telephone = new SimpleStringProperty(telephone);
        }
        if(fax != null){
            this.fax = new SimpleStringProperty(fax);
        }
        if(mobilePhone != null){
            this.mobilePhone = new SimpleStringProperty(mobilePhone);
        }
        if(email != null){
            this.email = new SimpleStringProperty(email);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    public CustomerContactsProperty(String customerId, String primaryContacts, String name, String department, String position, String telephone, String fax, String mobilePhone, String email, String remark) {
        if(customerId != null){
            this.customerId = new SimpleStringProperty(customerId);
        }
        if(primaryContacts != null){
            this.primaryContacts = new SimpleStringProperty(primaryContacts);
        }
        if(name != null){
            this.name = new SimpleStringProperty(name);
        }
        if(department != null){
            this.department = new SimpleStringProperty(department);
        }
        if(position != null){
            this.position = new SimpleStringProperty(position);
        }
        if(telephone != null){
            this.telephone = new SimpleStringProperty(telephone);
        }
        if(fax != null){
            this.fax = new SimpleStringProperty(fax);
        }
        if(mobilePhone != null){
            this.mobilePhone = new SimpleStringProperty(mobilePhone);
        }
        if(email != null){
            this.email = new SimpleStringProperty(email);
        }
        if(remark != null){
            this.remark = new SimpleStringProperty(remark);
        }
    }

    public CustomerBasicInfoController.ParticipationUsual getParticipation() {
        if(participation.get()==null){
            return null;
        }else {
            return participation.get();
        }
    }

    public SimpleObjectProperty<CustomerBasicInfoController.ParticipationUsual> participationProperty() {
        return participation;
    }

    public void setParticipation(CustomerBasicInfoController.ParticipationUsual participation) {
        this.participation.set(participation);
    }
}
