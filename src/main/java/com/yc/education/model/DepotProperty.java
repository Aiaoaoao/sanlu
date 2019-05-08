package com.yc.education.model;

/**
 * @ClassName DepotProperty
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/4/30 10:25
 * @Version 1.0
 */
public class DepotProperty {

    //库位编号
    private String depotOrder;
    //库位名称
    private String depotName;
    //楼层
    private String depotFloor;

    public DepotProperty() {

    }


    public DepotProperty(String depotOrder, String depotName, String depotFloor) {
        this.depotOrder = depotOrder;
        this.depotName = depotName;
        this.depotFloor = depotFloor;
    }

    public String getDepotOrder() {
        return depotOrder;
    }

    public void setDepotOrder(String depotOrder) {
        this.depotOrder = depotOrder;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getDepotFloor() {
        return depotFloor;
    }

    public void setDepotFloor(String depotFloor) {
        this.depotFloor = depotFloor;
    }
}
