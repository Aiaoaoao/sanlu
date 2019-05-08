package com.yc.education.model.basic;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 仓库库位
 *@Author QuZhangJing
 *@Date 15:24  2018/9/4
 *@Version 1.0
 */
@Table(name = "depot_basic")
public class DepotBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编号
     */
    private String isnum;

    /**
     * 仓库名称
     */
    private String depname;

    /**
     * 楼层
     */
    private String depfloor;

    /**
     * 隶属仓库
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
     * 建档日期
     */
    private String adddate;

    /**
     * 最后更新人
     */
    private String updatepeople;

    /**
     * 最后修改日期
     */
    private String updatedate;

    /**
     * 库存数量
     */
    @Transient
    private int stockNum;


    public DepotBasic() {
    }

    public DepotBasic(long id, String isnum, String depname, String depfloor, Long parentid, String remarks, String addpeople, String adddate, String updatepeople, String updatedate) {
        this.id = id;
        this.isnum = isnum;
        this.depname = depname;
        this.depfloor = depfloor;
        this.parentid = parentid;
        this.remarks = remarks;
        this.addpeople = addpeople;
        this.adddate = adddate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
    }

    public DepotBasic(String isnum, String depname, String depfloor, Long parentid, String remarks, String addpeople, String adddate, String updatepeople, String updatedate) {
        this.isnum = isnum;
        this.depname = depname;
        this.depfloor = depfloor;
        this.parentid = parentid;
        this.remarks = remarks;
        this.addpeople = addpeople;
        this.adddate = adddate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
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
     * 获取编号
     *
     * @return isnum - 编号
     */
    public String getIsnum() {
        return isnum;
    }

    /**
     * 设置编号
     *
     * @param isnum 编号
     */
    public void setIsnum(String isnum) {
        this.isnum = isnum;
    }

    /**
     * 获取仓库名称
     *
     * @return depname - 仓库名称
     */
    public String getDepname() {
        return depname;
    }

    /**
     * 设置仓库名称
     *
     * @param depname 仓库名称
     */
    public void setDepname(String depname) {
        this.depname = depname;
    }

    /**
     * 获取楼层
     *
     * @return depfloor - 楼层
     */
    public String getDepfloor() {
        return depfloor;
    }

    /**
     * 设置楼层
     *
     * @param depfloor 楼层
     */
    public void setDepfloor(String depfloor) {
        this.depfloor = depfloor;
    }

    /**
     * 获取隶属仓库
     *
     * @return parentid - 隶属仓库
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * 设置隶属仓库
     *
     * @param parentid 隶属仓库
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
     * 获取最后更新人
     *
     * @return updatepeople - 最后更新人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后更新人
     *
     * @param updatepeople 最后更新人
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

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }
}