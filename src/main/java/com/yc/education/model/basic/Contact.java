package com.yc.education.model.basic;

/**
 * @ClassName Contact
 * @Description TODO  联系人
 * @Author QuZhangJing
 * @Date 2018-08-14 11:25
 * @Version 1.0
 */
public class Contact {


    private long id;

    private String mainContact;  //主要联系人

    private String username; //姓名

    private String department; //部门

    private String position; //职务

    private String phone; //电话

    private String fax; //传真

    private String iphone; //移动电话

    private String email; //E-mail

    private String remarks; //备注


    public Contact() {
    }

    public Contact(long id, String mainContact, String username, String department, String position, String phone, String fax, String iphone, String email, String remarks) {
        this.id = id;
        this.mainContact = mainContact;
        this.username = username;
        this.department = department;
        this.position = position;
        this.phone = phone;
        this.fax = fax;
        this.iphone = iphone;
        this.email = email;
        this.remarks = remarks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainContact() {
        return mainContact;
    }

    public void setMainContact(String mainContact) {
        this.mainContact = mainContact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
