package com.yc.education.model.basic;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName ProductDetailsProperty
 * @Description TODO 产品管理-规格明细 TabelView数据绑定之类
 * @Author QuZhangJing
 * @Date 2018/9/19 16:44
 * @Version 1.0
 */
public class ProductDetailsProperty {

    private final SimpleLongProperty id = new SimpleLongProperty();

    private  final SimpleStringProperty declare = new SimpleStringProperty();


    //无参构造
    public ProductDetailsProperty() {
    }

    /**
     * 有参构造
     * @param id
     * @param declare
     */
    public ProductDetailsProperty(long id,String declare) {
        setId(id);
        setDeclare(declare);
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

    public String getDeclare() {
        return declare.get();
    }

    public SimpleStringProperty declareProperty() {
        return declare;
    }

    public void setDeclare(String declare) {
        this.declare.set(declare);
    }
}
