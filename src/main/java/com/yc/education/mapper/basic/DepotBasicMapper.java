package com.yc.education.mapper.basic;

import com.yc.education.model.basic.DepotBasic;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepotBasicMapper extends MyMapper<DepotBasic> {

    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum( );
    /**
     * 查询所有部门
     * @return
     */
    List<DepotBasic> selectDepotBasic(@Param("orderOrName")String orderOrName);
    /**
     * 根据编号查询部门
     * @param idnum
     * @return
     */
    DepotBasic selectDepotBasicByIsnum(String idnum);


    DepotBasic selectDepotBasicByIsnumAndorderOrName(@Param("idnum")String idnum,@Param("orderOrName")String orderOrName);

    /**
     * 根据parentid查询部门
     * @param parentid
     * @return
     */
    List<DepotBasic> selectDepotBasicByParentId(@Param("parentid")long parentid);



    List<DepotBasic> selectDepotBasicByParentIdAndOrderOrName(@Param("parentid")long parentid,@Param("orderOrName")String orderOrName);
    /**
     * 根据名称查询
     * @param depname
     * @return
     */
    DepotBasic selectDepotBasicByDepName(@Param("depname") String depname);

}