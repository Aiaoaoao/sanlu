package com.yc.education.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName SelectEmployeesProperty
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/4 16:23
 * @Version 1.0
 */
public class AddEmployeesProperty {

    private final SimpleBooleanProperty cecked = new SimpleBooleanProperty();

    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty order = new SimpleStringProperty();

    private final SimpleStringProperty name = new SimpleStringProperty();


    public AddEmployeesProperty() {

    }

    public AddEmployeesProperty(boolean cecked, long id, String order, String name) {
        setCecked(cecked);
        setId(id);
        setOrder(order);
        setName(name);
    }

    public boolean isCecked() {
        return cecked.get();
    }

    public SimpleBooleanProperty ceckedProperty() {
        return cecked;
    }

    public void setCecked(boolean cecked) {
        this.cecked.set(cecked);
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

    public String getOrder() {
        return order.get();
    }

    public SimpleStringProperty orderProperty() {
        return order;
    }

    public void setOrder(String order) {
        this.order.set(order);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
