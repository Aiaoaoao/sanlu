package com.yc.education.mapper.basic;

import com.yc.education.model.basic.SupplierContact;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierContactMapper extends MyMapper<SupplierContact> {


    /**
     * 根据供应商编号查询联系人
     * @param suppliserid 供应商自增编号
     * @return
     */
    List<SupplierContact> selectSupplierContactBySupplierid(@Param("suppliserid")long suppliserid);

    //根据供应商删除
    int deleteSupplierContactBySupplierid(@Param("suppliserid")long suppliserid);


}