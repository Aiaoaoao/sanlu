package com.yc.education.service.account;

import com.yc.education.model.account.AccountReceivableRush;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-12-07 11:41
 */
public interface IAccountReceivableRushService extends IService<AccountReceivableRush> {

    /**
     * 根据 应收账款冲账id 查询
     * @param otherid
     * @return
     */
    List<AccountReceivableRush> listAccountReceivableRush( String otherid);

    /**
     * 通过外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountReceivableRushByParentId( String otherid);
}
