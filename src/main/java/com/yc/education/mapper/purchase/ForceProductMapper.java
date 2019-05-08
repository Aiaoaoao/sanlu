package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.ForceProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 强制结案--采购产品
 *@Author QuZhangJing
 *@Date 14:54  2018/10/15
 *@Version 1.0
 */
public interface ForceProductMapper extends MyMapper<ForceProduct> {

    //根据强制结案编号查询强制结案采购产品
    List<ForceProduct> findForceProductByForceId(@Param("forceid")long forceid);

}