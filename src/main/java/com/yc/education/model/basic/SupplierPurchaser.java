package com.yc.education.model.basic;

import javax.persistence.*;
/**
 *@Description TODO 供应商_采购负责人
 *@Author QuZhangJing
 *@Date 11:49  2018/8/28
 *@Version 1.0
 */
@Table(name = "supplier_purchaser")
public class SupplierPurchaser {
    /**
     * 自增编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 员工编号
     */
    private String staffcode;

    /**
     * 员工编号
     */
    private Long staffid;

    /**
     * 姓名
     */
    private String staffname;

    /**
     * 主要负责人
     */
    private int keycontent;

    /**
     * 备注
     */
    private String staffremarks;

    /**
     * 是否是主要负责人
     */
    private Integer ispoint;

    /**
     * 供应商编号
     */
    private Long supplierid;


    public SupplierPurchaser() {
    }

    public SupplierPurchaser(long id,String staffcode,  String staffname, int keycontent, String staffremarks) {
        this.id=id;
        this.staffcode = staffcode;
        this.staffname = staffname;
        this.keycontent = keycontent;
        this.staffremarks = staffremarks;
    }

    public SupplierPurchaser(long id,Long staffid,String staffcode, String staffname, int keycontent, String staffremarks, Integer ispoint, Long supplierid) {
        this.id=id;
        this.staffcode = staffcode;
        this.staffid = staffid;
        this.staffname = staffname;
        this.keycontent = keycontent;
        this.staffremarks = staffremarks;
        this.ispoint = ispoint;
        this.supplierid = supplierid;
    }

    /**
     * 获取自增编号
     *
     * @return id - 自增编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增编号
     *
     * @param id 自增编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取员工编号
     *
     * @return staffid - 员工编号
     */
    public Long getStaffid() {
        return staffid;
    }

    /**
     * 设置员工编号
     *
     * @param staffid 员工编号
     */
    public void setStaffid(Long staffid) {
        this.staffid = staffid;
    }

    /**
     * 获取姓名
     *
     * @return staffname - 姓名
     */
    public String getStaffname() {
        return staffname;
    }

    /**
     * 设置姓名
     *
     * @param staffname 姓名
     */
    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public int getKeycontent() {
        return keycontent;
    }

    public void setKeycontent(int keycontent) {
        this.keycontent = keycontent;
    }

    /**
     * 获取备注
     *
     * @return staffremarks - 备注
     */
    public String getStaffremarks() {
        return staffremarks;
    }

    /**
     * 设置备注
     *
     * @param staffremarks 备注
     */
    public void setStaffremarks(String staffremarks) {
        this.staffremarks = staffremarks;
    }

    /**
     * 获取是否是主要负责人
     *
     * @return ispoint - 是否是主要负责人
     */
    public Integer getIspoint() {
        return ispoint;
    }

    /**
     * 设置是否是主要负责人
     *
     * @param ispoint 是否是主要负责人
     */
    public void setIspoint(Integer ispoint) {
        this.ispoint = ispoint;
    }

    /**
     * 获取供应商编号
     *
     * @return supplierid - 供应商编号
     */
    public Long getSupplierid() {
        return supplierid;
    }

    /**
     * 设置供应商编号
     *
     * @param supplierid 供应商编号
     */
    public void setSupplierid(Long supplierid) {
        this.supplierid = supplierid;
    }

    public String getStaffcode() {
        return staffcode;
    }

    public void setStaffcode(String staffcode) {
        this.staffcode = staffcode;
    }
}