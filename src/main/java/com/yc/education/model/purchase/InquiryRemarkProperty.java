package com.yc.education.model.purchase;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName InquiryRemarkProperty
 * @Description TODO 报表备注
 * @Author QuZhangJing
 * @Date 2018/10/9 9:40
 * @Version 1.0
 */
public class InquiryRemarkProperty {

    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty content = new SimpleStringProperty();


    public InquiryRemarkProperty() {
    }

    public InquiryRemarkProperty(long id,String content) {
        setId(id);
        setContent(content);
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

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

}
