package com.yc.education.model.basic;

import javax.persistence.*;

@Table(name = "transport_purchaser")
public class TransportPurchaser {
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
    private Integer keycontent;

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



    public TransportPurchaser() {
    }

    public TransportPurchaser(long id,String staffcode,  String staffname, int keycontent, String staffremarks) {
        this.id=id;
        this.staffcode = staffcode;
        this.staffname = staffname;
        this.keycontent = keycontent;
        this.staffremarks = staffremarks;
    }

    public TransportPurchaser(long id,Long staffid,String staffcode, String staffname, int keycontent, String staffremarks, Integer ispoint, Long supplierid) {
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
     * @return staffcode - 员工编号
     */
    public String getStaffcode() {
        return staffcode;
    }

    /**
     * 设置员工编号
     *
     * @param staffcode 员工编号
     */
    public void setStaffcode(String staffcode) {
        this.staffcode = staffcode;
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

    /**
     * 获取主要负责人
     *
     * @return keycontent - 主要负责人
     */
    public Integer getKeycontent() {
        return keycontent;
    }

    /**
     * 设置主要负责人
     *
     * @param keycontent 主要负责人
     */
    public void setKeycontent(Integer keycontent) {
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
}