package com.yc.education.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName DataSettingProperty
 * @Description TODO 资料设置之TabelView数据绑定
 * @Author QuZhangJing
 * @Date 2018/9/26 17:58
 * @Version 1.0
 */
public class DataSettingProperty {

    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty sort = new SimpleStringProperty();

    private  final SimpleStringProperty title = new SimpleStringProperty();

    private  final SimpleStringProperty remarks = new SimpleStringProperty();


    private  final SimpleStringProperty prev = new SimpleStringProperty();


    public DataSettingProperty() {
    }


    public DataSettingProperty(String sort,String title,String remarks,String prev) {
        setSort(sort);
        setTitle(title);
        setRemarks(remarks);
        setPrev(prev);
    }

    public DataSettingProperty(long id,String sort,String title,String remarks,String prev) {
        setId(id);
        setSort(sort);
        setTitle(title);
        setRemarks(remarks);
        setPrev(prev);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getSort() {
        return sort.get();
    }

    public SimpleStringProperty sortProperty() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort.set(sort);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getRemarks() {
        return remarks.get();
    }

    public SimpleStringProperty remarksProperty() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks.set(remarks);
    }

    public String getPrev() {
        return prev.get();
    }

    public SimpleStringProperty prevProperty() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev.set(prev);
    }
}
