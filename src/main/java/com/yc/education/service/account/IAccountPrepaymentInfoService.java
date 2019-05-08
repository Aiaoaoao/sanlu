package com.yc.education.service.account;


import com.yc.education.model.account.AccountPrepaymentInfo;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description 预付账款-详情
 * @Author BlueSky
 * @Date 2019-01-09 11:47
 */
public interface IAccountPrepaymentInfoService extends IService<AccountPrepaymentInfo> {
    /**
     * 根据 预付账款id 查询
     * @param otherid
     * @return
     */
    List<AccountPrepaymentInfo> listAccountPrepaymentInfo(String otherid);

    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountPrepaymentInfoByParentId( String otherid);
}
