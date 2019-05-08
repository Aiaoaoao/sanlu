package com.yc.education.service.account;


import com.yc.education.model.account.AccountPayableInfo;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description 应付账款冲账-详情
 * @Author BlueSky
 * @Date 2019-01-09 11:47
 */
public interface IAccountPayableInfoService extends IService<AccountPayableInfo> {

    /**
     * 根据 应付账款冲账id 查询
     * @param otherid
     * @return
     */
    List<AccountPayableInfo> listAccountPayableInfo(String otherid);


    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountPayableInfoByParentId( String otherid);
}
