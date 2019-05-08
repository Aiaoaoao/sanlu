package com.yc.education.service.basic;

import com.yc.education.model.basic.DepartmentBasic;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName DepartmentBasicService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/4 10:32
 * @Version 1.0
 */
public interface DepartmentBasicService extends IService<DepartmentBasic> {

    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();

    /**
     * 查询所有部门
     * @return
     */
    List<DepartmentBasic> selectDepartmentBasic(int pageNum,int pageSzie);

    /**
     * 根据编号查询部门
     * @param idnum
     * @return
     */
    DepartmentBasic selectDepartmentBasicByIsnum(String idnum);

    /**
     * 根据parentid查询部门
     * @param parentid
     * @return
     */
    List<DepartmentBasic>  selectDepartmentBasicByParentId(long parentid);


    /**
     * 查询所有部门
     * @return
     */
    List<DepartmentBasic> selectDepartmentBasic();


    /**
     * 根据名称查询
     * @param depname
     * @return
     */
    DepartmentBasic selectDepartmentBasicByDepName(String depname);

    /**
     * 查询所有部门
     * @return
     */
    List<DepartmentBasic> selectDepartmentBasic(String orderAndName,int pageNum,int pageSzie);

    /**
     * 查询所有部门
     * @return
     */
    List<DepartmentBasic> selectDepartmentBasic(String orderAndName);

}
