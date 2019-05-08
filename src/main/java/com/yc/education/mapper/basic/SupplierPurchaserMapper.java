package com.yc.education.mapper.basic;

import com.yc.education.model.basic.SupplierPurchaser;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierPurchaserMapper extends MyMapper<SupplierPurchaser> {


    /**
     * 根据供应商编号查询采购负责人
     * @param supplierid 供应商编号
     * @return
     */
    List<SupplierPurchaser> selectSupplierPurchaseBySupplierid(@Param("supplierid")long supplierid);

    //根据供应商编号删除采购负责人
    int deleteSupplierPurchase(@Param("supplierid")long supplierid);


}