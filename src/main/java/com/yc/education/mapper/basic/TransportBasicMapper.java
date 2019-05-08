package com.yc.education.mapper.basic;

import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.model.basic.TransportBasic;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransportBasicMapper extends MyMapper<TransportBasic> {


    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();


    /**
     * 查询所有公司
     * @return
     */
    List<TransportBasic> selectTransportBasic();


    /**
     * 根据编号查询公司
     * @param idnum
     * @return
     */
    TransportBasic selectTransportBasicByIsnum(@Param("idnum")String idnum);




    List<TransportBasic> selectTransportBasicNotSotp(@Param("types")int types);


}