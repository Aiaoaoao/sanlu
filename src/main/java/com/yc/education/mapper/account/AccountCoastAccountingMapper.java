package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountCoastAccounting;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountCoastAccountingMapper extends MyMapper<AccountCoastAccounting> {

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询所有的成本核算单据
     * @return
     */
    List<AccountCoastAccounting> listAccountCoastAccounting(@Param("text") String text);

    /**
     * 根据入库单号查询成本核算单据
     * @param orderno 入库单号
     * @return
     */
    AccountCoastAccounting getByOrderNo(@Param("orderno")String orderno);

}