package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountCoastPurchase;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountCoastPurchaseMapper extends MyMapper<AccountCoastPurchase> {

    /**
     * 根据 核算成本id 查询 采购成本列表
     * @param otherid
     * @return
     */
    List<AccountCoastPurchase> listAccountCoastPurchase(@Param("otherid") String otherid);
}