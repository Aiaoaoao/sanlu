package com.yc.education.model;

import javax.persistence.*;
/**
 *@Description TODO 权限表
 *@Author QuZhangJing
 *@Date 11:56  2019/2/28
 *@Version 1.0
 */
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限编码 (自定义)
     * 格式(父级编号_当前编号_序号)
     *
     * 序号:
     *  1 - 新增
     *  2 - 删除
     *  3 - 修改
     *  4 - 查看
     *  5 - 审核
     *  6 - 取消审核
     *  7 - 打印
     *  8 - 配置
     *
     */
    private String codes;

    /**
     * 权限名称
     */
    private String title;

    /**
     * 父级编号
     */
    private Long parent;

    /**
     * 类型
     * 1、采购
     * 2、销售
     * 3、考勤管理
     * 4、库存
     * 5、基本资料
     * 6、客户关系
     * 7、统计汇总
     * 8、账款
     * 9、其他
     */
    private Integer types;

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
     * @return codes
     */
    public String getCodes() {
        return codes;
    }

    /**
     * @param codes
     */
    public void setCodes(String codes) {
        this.codes = codes;
    }

    /**
     * 获取权限名称
     *
     * @return title - 权限名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置权限名称
     *
     * @param title 权限名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取父级编号
     *
     * @return parent - 父级编号
     */
    public Long getParent() {
        return parent;
    }

    /**
     * 设置父级编号
     *
     * @param parent 父级编号
     */
    public void setParent(Long parent) {
        this.parent = parent;
    }

    /**
     * 获取类型
     *
     * @return types - 类型
     */
    public Integer getTypes() {
        return types;
    }

    /**
     * 设置类型
     *
     * @param types 类型
     */
    public void setTypes(Integer types) {
        this.types = types;
    }
}