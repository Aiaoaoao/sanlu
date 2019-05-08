package com.yc.education.service.basic;

import com.yc.education.model.basic.DepotBasic;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName DepotBasicService
 * @Description TODO 仓库库位
 * @Author QuZhangJing
 * @Date 2018/9/4 15:29
 * @Version 1.0
 */
public interface DepotBasicService extends IService<DepotBasic> {


    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();

    /**
     * 查询所有仓库
     * @return
     */
    List<DepotBasic> selectDepotBasic(String orderOrName,int pageNum,int pageSize);

    /**
     * 查询所有仓库
     * @return
     */
    List<DepotBasic> selectDepotBasic(String orderOrName);
    /**
     * 查询所有仓库
     * @return
     */
    List<DepotBasic> selectDepotBasic();

    /**
     * 查询所有仓库
     * @return
     */
    List<DepotBasic> selectDepotBasic(int pageNum,int pageSize);
    /**
     * 根据编号查询仓库
     * @param idnum
     * @return
     */
    DepotBasic selectDepotBasicByIsnum(String idnum);

    DepotBasic selectDepotBasicByIsnum(String idnum,String orderAndName,int pageNum,int pageSize);
    /**
     * 根据parentid查询仓库
     * @param parentid
     * @return
     */
    List<DepotBasic> selectDepotBasicByParentId(long parentid);


    List<DepotBasic> selectDepotBasicByParentId(long parentid,String orderOrName,int pageNum,int pageSize);
    /**
     * 根据名称查询仓库
     * @param depname
     * @return
     */
    DepotBasic selectDepotBasicByDepName(String depname);

}
