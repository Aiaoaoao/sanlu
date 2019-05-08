package com.yc.education.service.account;

import com.yc.education.model.account.AccountReceivableFee;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-12-07 11:39
 */
public interface IAccountReceivableFeeService extends IService<AccountReceivableFee> {

    /**
     * 根据 应收账款冲账id 查询
     * @param otherid
     * @return
     */
    List<AccountReceivableFee> listAccountReceivableFee( String otherid);


    /**
     * 通过外键删除
     * @param otherid
     * @return
     */
    int deleteAccountReceivableFeeByParentId( String otherid);
}
