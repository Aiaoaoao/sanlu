package com.yc.education.model.basic;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 部门表
 *@Author QuZhangJing
 *@Date 10:23  2018/9/4
 *@Version 1.0
 */
@Table(name = "department_basic")
public class DepartmentBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编号
     */
    private String idnum;

    /**
     * 部门名称
     */
    private String depname;

    /**
     * 隶属部门
     */
    private Long parentid;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 建档人
     */
    private String addpeople;

    /**
     * 建档时间
     */
    private String adddate;

    /**
     * 最后修改人
     */
    private String updatepeople;

    /**
     * 最后修改时间
     */
    private String updatedate;

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
     * 获取编号
     *
     * @return idnum - 编号
     */
    public String getIdnum() {
        return idnum;
    }

    /**
     * 设置编号
     *
     * @param idnum 编号
     */
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    /**
     * 获取部门名称
     *
     * @return depname - 部门名称
     */
    public String getDepname() {
        return depname;
    }

    /**
     * 设置部门名称
     *
     * @param depname 部门名称
     */
    public void setDepname(String depname) {
        this.depname = depname;
    }

    /**
     * 获取隶属部门
     *
     * @return parentid - 隶属部门
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * 设置隶属部门
     *
     * @param parentid 隶属部门
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
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

    /**
     * 获取建档人
     *
     * @return addpeople - 建档人
     */
    public String getAddpeople() {
        return addpeople;
    }

    /**
     * 设置建档人
     *
     * @param addpeople 建档人
     */
    public void setAddpeople(String addpeople) {
        this.addpeople = addpeople;
    }



    /**
     * 获取最后修改人
     *
     * @return updatepeople - 最后修改人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后修改人
     *
     * @param updatepeople 最后修改人
     */
    public void setUpdatepeople(String updatepeople) {
        this.updatepeople = updatepeople;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }
}