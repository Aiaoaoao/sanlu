package com.yc.education.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName PermissionsProperty
 * @Description TODO 权限管理
 * @Author QuZhangJing
 * @Date 2019/3/1 13:35
 * @Version 1.0
 */
public class PermissionsProperty {

    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty title = new SimpleStringProperty();

    private final SimpleBooleanProperty insert = new SimpleBooleanProperty();

    private final SimpleBooleanProperty delete = new SimpleBooleanProperty();

    private final SimpleBooleanProperty update = new SimpleBooleanProperty();

    private final SimpleBooleanProperty select = new SimpleBooleanProperty();

    private final SimpleBooleanProperty sh = new SimpleBooleanProperty();

    private final SimpleBooleanProperty changesh = new SimpleBooleanProperty();

    private final SimpleBooleanProperty cope = new SimpleBooleanProperty();

    private final SimpleBooleanProperty reset = new SimpleBooleanProperty();

    private final SimpleBooleanProperty qx = new SimpleBooleanProperty();


    public PermissionsProperty() {

    }

    public PermissionsProperty(long id,
                               String title,
                               boolean insert,
                               boolean delete,
                               boolean update,
                               boolean select,
                               boolean sh,
                               boolean changesh,
                               boolean cope,
                               boolean reset,
                               boolean qx
                               ) {

        setId(id);
        setTitle(title);
        setInsert(insert);
        setDelete(delete);
        setUpdate(update);
        setSelect(select);
        setSh(sh);
        setChangesh(changesh);
        setCope(cope);
        setReset(reset);
        setQx(qx);
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

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public boolean isInsert() {
        return insert.get();
    }

    public SimpleBooleanProperty insertProperty() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert.set(insert);
    }

    public boolean isDelete() {
        return delete.get();
    }

    public SimpleBooleanProperty deleteProperty() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete.set(delete);
    }

    public boolean isUpdate() {
        return update.get();
    }

    public SimpleBooleanProperty updateProperty() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update.set(update);
    }

    public boolean isSelect() {
        return select.get();
    }

    public SimpleBooleanProperty selectProperty() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select.set(select);
    }

    public boolean isSh() {
        return sh.get();
    }

    public SimpleBooleanProperty shProperty() {
        return sh;
    }

    public void setSh(boolean sh) {
        this.sh.set(sh);
    }

    public boolean isChangesh() {
        return changesh.get();
    }

    public SimpleBooleanProperty changeshProperty() {
        return changesh;
    }

    public void setChangesh(boolean changesh) {
        this.changesh.set(changesh);
    }

    public boolean isCope() {
        return cope.get();
    }

    public SimpleBooleanProperty copeProperty() {
        return cope;
    }

    public void setCope(boolean cope) {
        this.cope.set(cope);
    }

    public boolean isReset() {
        return reset.get();
    }

    public SimpleBooleanProperty resetProperty() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset.set(reset);
    }

    public boolean isQx() {
        return qx.get();
    }

    public SimpleBooleanProperty qxProperty() {
        return qx;
    }

    public void setQx(boolean qx) {
        this.qx.set(qx);
    }
}
