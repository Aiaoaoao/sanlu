package com.yc.education.model.stock;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName PurchaseStockProductProperty
 * @Description TODO 采购入库单产品之TabelView数据绑定
 * @Author QuZhangJing
 * @Date 2018/10/25 15:47
 * @Version 1.0
 */
public class PurchaseStockProductProperty{


    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty sourseorder = new SimpleStringProperty();

    private final SimpleStringProperty orders = new SimpleStringProperty();

    private final SimpleStringProperty sort = new SimpleStringProperty();

    private final SimpleStringProperty seeorder = new SimpleStringProperty();

    private final SimpleStringProperty pronum = new SimpleStringProperty();

    private final SimpleStringProperty proname = new SimpleStringProperty();

    private final SimpleStringProperty stocknum = new SimpleStringProperty();

    private final SimpleStringProperty price = new SimpleStringProperty();


    private final SimpleStringProperty units = new SimpleStringProperty();

    private final SimpleStringProperty depotnum = new SimpleStringProperty();

    private final SimpleStringProperty depotname = new SimpleStringProperty();

    private final SimpleStringProperty floor = new SimpleStringProperty();

    private final SimpleStringProperty boxnum = new SimpleStringProperty();

    private final SimpleStringProperty remarks = new SimpleStringProperty();

    public PurchaseStockProductProperty(long id,String sourseorder,String orders,int sort,String seeorder,String pronum,String proname,int stocknum,double price,String units,String depotnum,String depotname,String floor,String boxnum,String remarks) {
        setId(id);
        setSourseorder(sourseorder);
        setOrders(orders);
        setSort(sort+"");
        setSeeorder(seeorder);
        setPronum(pronum);
        setProname(proname);
        setStocknum(stocknum+"");
        setPrice(price+"");
        setUnits(units);
        setDepotnum(depotnum);
        setDepotname(depotname);
        setFloor(floor);
        setBoxnum(boxnum);
        setRemarks(remarks);
    }



    public PurchaseStockProductProperty() {
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

    public String getSourseorder() {
        return sourseorder.get();
    }

    public SimpleStringProperty sourseorderProperty() {
        return sourseorder;
    }

    public void setSourseorder(String sourseorder) {
        this.sourseorder.set(sourseorder);
    }

    public String getOrders() {
        return orders.get();
    }

    public SimpleStringProperty ordersProperty() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders.set(orders);
    }


    public String getSeeorder() {
        return seeorder.get();
    }

    public SimpleStringProperty seeorderProperty() {
        return seeorder;
    }

    public void setSeeorder(String seeorder) {
        this.seeorder.set(seeorder);
    }

    public String getPronum() {
        return pronum.get();
    }

    public SimpleStringProperty pronumProperty() {
        return pronum;
    }

    public void setPronum(String pronum) {
        this.pronum.set(pronum);
    }

    public String getProname() {
        return proname.get();
    }

    public SimpleStringProperty pronameProperty() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname.set(proname);
    }


    public String getUnits() {
        return units.get();
    }

    public SimpleStringProperty unitsProperty() {
        return units;
    }

    public void setUnits(String units) {
        this.units.set(units);
    }

    public String getDepotnum() {
        return depotnum.get();
    }

    public SimpleStringProperty depotnumProperty() {
        return depotnum;
    }

    public void setDepotnum(String depotnum) {
        this.depotnum.set(depotnum);
    }

    public String getDepotname() {
        return depotname.get();
    }

    public SimpleStringProperty depotnameProperty() {
        return depotname;
    }

    public void setDepotname(String depotname) {
        this.depotname.set(depotname);
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

    public int getSort() {
        return Integer.parseInt(sort.get());
    }

    public SimpleStringProperty sortProperty() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort.set(sort);
    }

    public int getStocknum() {
        return Integer.parseInt(stocknum.get());
    }

    public SimpleStringProperty stocknumProperty() {
        return stocknum;
    }

    public void setStocknum(String stocknum) {
        this.stocknum.set(stocknum);
    }

    public Double getPrice() {
        if (price.get() == null || "".equals(price.get())) {
            return 0.00;
        } else {
            return Double.valueOf(price.get());
        }
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getFloor() {
        return floor.get();
    }

    public SimpleStringProperty floorProperty() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    public String getBoxnum() {
        return boxnum.get();
    }

    public SimpleStringProperty boxnumProperty() {
        return boxnum;
    }

    public void setBoxnum(String boxnum) {
        this.boxnum.set(boxnum);
    }
}
