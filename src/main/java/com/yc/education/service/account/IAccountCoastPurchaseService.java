package com.yc.education.service.account;

import com.yc.education.model.account.AccountCoastPurchase;
import com.yc.education.service.IService;


import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2019-01-17 11:47
 */
public interface IAccountCoastPurchaseService extends IService<AccountCoastPurchase> {

    /**
     * 根据 核算成本id 查询 采购成本列表
     * @param otherid
     * @return
     */
    List<AccountCoastPurchase> listAccountCoastPurchase(String otherid);

}
