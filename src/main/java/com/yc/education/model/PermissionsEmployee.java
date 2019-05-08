package com.yc.education.model;

import javax.persistence.*;
/**
 *@Description TODO 权限员工
 *@Author QuZhangJing
 *@Date 10:11  2019/3/1
 *@Version 1.0
 */
@Table(name = "permissions_employee")
public class PermissionsEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 员工编号
     */
    private Long uid;

    /**
     * 员工编号
     */
    private String idnum;

    /**
     * 员工名称
     */
    private String uname;

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
     * 获取员工编号
     *
     * @return uid - 员工编号
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置员工编号
     *
     * @param uid 员工编号
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取员工编号
     *
     * @return idnum - 员工编号
     */
    public String getIdnum() {
        return idnum;
    }

    /**
     * 设置员工编号
     *
     * @param idnum 员工编号
     */
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    /**
     * 获取员工名称
     *
     * @return uname - 员工名称
     */
    public String getUname() {
        return uname;
    }

    /**
     * 设置员工名称
     *
     * @param uname 员工名称
     */
    public void setUname(String uname) {
        this.uname = uname;
    }
}