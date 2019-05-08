package com.yc.education.model;

import javax.persistence.*;
/**
 *@Description TODO 员工---角色关系表
 *@Author QuZhangJing
 *@Date 9:52  2019/2/28
 *@Version 1.0
 */
@Table(name = "role_employee")
public class RoleEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 用户编号
     */
    private String idnum;

    /**
     * 角色id
     */
    private Long roleid;

    /**
     * 角色名称
     */
    private String rolename;

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
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取用户编号
     *
     * @return idnum - 用户编号
     */
    public String getIdnum() {
        return idnum;
    }



    /**
     * 设置用户编号
     *
     * @param idnum 用户编号
     */
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    /**
     * 获取角色id
     *
     * @return roleid - 角色id
     */
    public Long getRoleid() {
        return roleid;
    }

    /**
     * 设置角色id
     *
     * @param roleid 角色id
     */
    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取角色名称
     *
     * @return rolename - 角色名称
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置角色名称
     *
     * @param rolename 角色名称
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}