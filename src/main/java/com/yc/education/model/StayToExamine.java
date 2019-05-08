package com.yc.education.model;

/**
 * @ClassName StayToExamine
 * @Description TODO  待审核单据 容器
 * @Author QuZhangJing
 * @Date 2019/4/25 15:59
 * @Version 1.0
 */
public class StayToExamine {

    private Long id;

    private int sort;

    private String orderType;

    private String orders;

    private String orderTime;

    private String createPeople;

    public StayToExamine() {

    }

    public StayToExamine(Long id, int sort, String orderType, String orders, String orderTime,String createPeople) {
        this.id = id;
        this.sort = sort;
        this.orderType = orderType;
        this.orders = orders;
        this.orderTime = orderTime;
        this.createPeople = createPeople;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCreatePeople() {
        return createPeople;
    }

    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }
}
