package com.yc.education.model.basic;

import javax.persistence.*;

@Table(name = "region_employee")
public class RegionEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 区域编号
     */
    private Long regionid;

    /**
     * 员工编号
     */
    private String empisnum;

    /**
     * 员工姓名
     */
    private String empname;

    /**
     * 备注
     */
    private String remarks;


    public RegionEmployee() {
    }

    public RegionEmployee(long id,String empisnum, String empname, String remarks) {
        this.id = id;
        this.empisnum = empisnum;
        this.empname = empname;
        this.remarks = remarks;
    }

    public RegionEmployee(long id,Long regionid, String empisnum, String empname, String remarks) {
        this.id = id;
        this.regionid = regionid;
        this.empisnum = empisnum;
        this.empname = empname;
        this.remarks = remarks;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取区域编号
     *
     * @return regionid - 区域编号
     */
    public Long getRegionid() {
        return regionid;
    }

    /**
     * 设置区域编号
     *
     * @param regionid 区域编号
     */
    public void setRegionid(Long regionid) {
        this.regionid = regionid;
    }

    /**
     * 获取员工编号
     *
     * @return empisnum - 员工编号
     */
    public String getEmpisnum() {
        return empisnum;
    }

    /**
     * 设置员工编号
     *
     * @param empisnum 员工编号
     */
    public void setEmpisnum(String empisnum) {
        this.empisnum = empisnum;
    }

    /**
     * 获取员工姓名
     *
     * @return empname - 员工姓名
     */
    public String getEmpname() {
        return empname;
    }

    /**
     * 设置员工姓名
     *
     * @param empname 员工姓名
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}