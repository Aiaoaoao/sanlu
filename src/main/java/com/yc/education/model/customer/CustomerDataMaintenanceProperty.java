package com.yc.education.model.customer;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;


public class CustomerDataMaintenanceProperty {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty customerId = new SimpleStringProperty();
    private SimpleStringProperty documentNo = new SimpleStringProperty();
    private SimpleStringProperty createDate = new SimpleStringProperty();
    private SimpleStringProperty leaderPeople = new SimpleStringProperty();
    private SimpleStringProperty purchasePeople = new SimpleStringProperty();
    private SimpleStringProperty contact = new SimpleStringProperty();
    private SimpleStringProperty telephone = new SimpleStringProperty();
    private SimpleStringProperty fax = new SimpleStringProperty();
    private SimpleStringProperty startupYear = new SimpleStringProperty();
    private SimpleStringProperty employeeNumber = new SimpleStringProperty();
    private SimpleStringProperty lastYearBusiness = new SimpleStringProperty();
    private SimpleStringProperty yearPlan = new SimpleStringProperty();
    private SimpleStringProperty industry = new SimpleStringProperty();


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

    public String getDocumentNo() {
        if(documentNo == null){
            return null;
        }else{
            return documentNo.get();
        }
    }

    public SimpleStringProperty documentNoProperty() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo.set(documentNo);
    }

    public String getCreateDate() {
        if(createDate == null){
            return null;
        }else{
            return createDate.get();
        }
    }

    public SimpleStringProperty createDateProperty() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }

    public String getLeaderPeople() {
        if(leaderPeople == null){
            return null;
        }else{
            return leaderPeople.get();
        }
    }

    public SimpleStringProperty leaderPeopleProperty() {
        return leaderPeople;
    }

    public void setLeaderPeople(String leaderPeople) {
        this.leaderPeople.set(leaderPeople);
    }

    public String getPurchasePeople() {
        if(purchasePeople == null){
            return null;
        }else{
            return purchasePeople.get();
        }
    }

    public SimpleStringProperty purchasePeopleProperty() {
        return purchasePeople;
    }

    public void setPurchasePeople(String purchasePeople) {
        this.purchasePeople.set(purchasePeople);
    }

    public String getContact() {
        if(contact == null){
            return null;
        }else{
            return contact.get();
        }
    }

    public SimpleStringProperty contactProperty() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public String getTelephone() {
        if(telephone == null){
            return null;
        }else {
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

    public String getStartupYear() {
        if(startupYear == null){
            return null;
        }else{
            return startupYear.get();
        }
    }

    public SimpleStringProperty startupYearProperty() {
        return startupYear;
    }

    public void setStartupYear(String startupYear) {
        this.startupYear.set(startupYear);
    }

    public String getEmployeeNumber() {
        if(employeeNumber == null){
            return null;
        }else{
            return employeeNumber.get();
        }
    }

    public SimpleStringProperty employeeNumberProperty() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber.set(employeeNumber);
    }

    public String getLastYearBusiness() {
        if(lastYearBusiness == null){
            return null;
        }else{
            return lastYearBusiness.get();
        }
    }

    public SimpleStringProperty lastYearBusinessProperty() {
        return lastYearBusiness;
    }

    public void setLastYearBusiness(String lastYearBusiness) {
        this.lastYearBusiness.set(lastYearBusiness);
    }

    public String getYearPlan() {
        if(yearPlan == null){
            return null;
        }else{
            return yearPlan.get();
        }
    }

    public SimpleStringProperty yearPlanProperty() {
        return yearPlan;
    }

    public void setYearPlan(String yearPlan) {
        this.yearPlan.set(yearPlan);
    }

    public String getIndustry() {
        if(industry == null){
            return null;
        }else{
            return industry.get();
        }
    }

    public SimpleStringProperty industryProperty() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry.set(industry);
    }

    public CustomerDataMaintenanceProperty() {
    }

    public CustomerDataMaintenanceProperty(Long id, String customerId, String documentNo, String createDate, String leaderPeople, String purchasePeople, String contact, String telephone, String fax, String startupYear, String employeeNumber, String lastYearBusiness, String yearPlan, String industry) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(customerId != null){
            this.customerId = new SimpleStringProperty(customerId);
        }
        if(documentNo != null){
            this.documentNo = new SimpleStringProperty(documentNo);
        }
        if(createDate != null){
            this.createDate = new SimpleStringProperty(createDate);
        }
        if(leaderPeople != null){
            this.leaderPeople = new SimpleStringProperty(leaderPeople);
        }
        if(purchasePeople != null){
            this.purchasePeople = new SimpleStringProperty(purchasePeople);
        }
        if(contact != null){
            this.contact = new SimpleStringProperty(contact);
        }
        if(telephone != null){
            this.telephone = new SimpleStringProperty(telephone);
        }
        if(fax != null){
            this.fax = new SimpleStringProperty(fax);
        }
        if(startupYear != null){
            this.startupYear = new SimpleStringProperty(startupYear);
        }
        if(employeeNumber != null){
            this.employeeNumber = new SimpleStringProperty(employeeNumber);
        }
        if(lastYearBusiness != null){
            this.lastYearBusiness = new SimpleStringProperty(lastYearBusiness);
        }
        if(yearPlan != null){
            this.yearPlan = new SimpleStringProperty(yearPlan);
        }
        if(industry != null){
            this.industry = new SimpleStringProperty(industry);
        }
    }


    public CustomerDataMaintenanceProperty(String customerId, String documentNo, String createDate, String leaderPeople, String purchasePeople, String contact, String telephone, String fax, String startupYear, String employeeNumber, String lastYearBusiness, String yearPlan, String industry) {
        if(customerId != null){
            this.customerId = new SimpleStringProperty(customerId);
        }
        if(documentNo != null){
            this.documentNo = new SimpleStringProperty(documentNo);
        }
        if(createDate != null){
            this.createDate = new SimpleStringProperty(createDate);
        }
        if(leaderPeople != null){
            this.leaderPeople = new SimpleStringProperty(leaderPeople);
        }
        if(purchasePeople != null){
            this.purchasePeople = new SimpleStringProperty(purchasePeople);
        }
        if(contact != null){
            this.contact = new SimpleStringProperty(contact);
        }
        if(telephone != null){
            this.telephone = new SimpleStringProperty(telephone);
        }
        if(fax != null){
            this.fax = new SimpleStringProperty(fax);
        }
        if(startupYear != null){
            this.startupYear = new SimpleStringProperty(startupYear);
        }
        if(employeeNumber != null){
            this.employeeNumber = new SimpleStringProperty(employeeNumber);
        }
        if(lastYearBusiness != null){
            this.lastYearBusiness = new SimpleStringProperty(lastYearBusiness);
        }
        if(yearPlan != null){
            this.yearPlan = new SimpleStringProperty(yearPlan);
        }
        if(industry != null){
            this.industry = new SimpleStringProperty(industry);
        }
    }



}
