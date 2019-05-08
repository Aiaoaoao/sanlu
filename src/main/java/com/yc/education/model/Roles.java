package com.yc.education.model;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 角色表
 *@Author QuZhangJing
 *@Date 9:52  2019/2/28
 *@Version 1.0
 */
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 添加时间
     */
    private Date addtime;

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
     * 获取添加时间
     *
     * @return addtime - 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}