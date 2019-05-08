package com.yc.education.model;

/**
 * @ClassName RelationProperty
 * @Description TODO 关联单据数据存储
 * @Author QuZhangJing
 * @Date 2019/4/27 10:52
 * @Version 1.0
 */
public class RelationProperty {


    private Long id ;

    private int sort;

    private String orderType;

    private String orders;

    private String orderTime;

    private String createPeople;



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
