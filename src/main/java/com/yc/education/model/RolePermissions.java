package com.yc.education.model;

import javax.persistence.*;
/**
 *@Description TODO 角色-权限关系
 *@Author QuZhangJing
 *@Date 11:56  2019/2/28
 *@Version 1.0
 */
@Table(name = "role_permissions")
public class RolePermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色编号
     */
    private Long roleid;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 权限编码
     */
    private String permissionscodes;

    /**
     * 权限id
     */
    private Long permissionsid;

    /**
     * 用户编号
     */
    private Long uid;

    /**
     * 用户唯一编码
     */
    private String idnum;

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
     * 获取角色编号
     *
     * @return roleid - 角色编号
     */
    public Long getRoleid() {
        return roleid;
    }

    /**
     * 设置角色编号
     *
     * @param roleid 角色编号
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

    /**
     * 获取权限编码
     *
     * @return permissionscodes - 权限编码
     */
    public String getPermissionscodes() {
        return permissionscodes;
    }

    /**
     * 设置权限编码
     *
     * @param permissionscodes 权限编码
     */
    public void setPermissionscodes(String permissionscodes) {
        this.permissionscodes = permissionscodes;
    }

    /**
     * 获取权限id
     *
     * @return permissionsid - 权限id
     */
    public Long getPermissionsid() {
        return permissionsid;
    }

    /**
     * 设置权限id
     *
     * @param permissionsid 权限id
     */
    public void setPermissionsid(Long permissionsid) {
        this.permissionsid = permissionsid;
    }

    /**
     * 获取用户编号
     *
     * @return uid - 用户编号
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户编号
     *
     * @param uid 用户编号
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取用户唯一编码
     *
     * @return idnum - 用户唯一编码
     */
    public String getIdnum() {
        return idnum;
    }

    /**
     * 设置用户唯一编码
     *
     * @param idnum 用户唯一编码
     */
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
}