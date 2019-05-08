package com.yc.education.service.account;


import com.yc.education.model.account.AccountInputInvoiceInfo;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description 账款-进项发票-详情
 * @Author BlueSky
 * @Date 2019-01-09 11:47
 */
public interface IAccountInputInvoiceInfoService extends IService<AccountInputInvoiceInfo> {
    /**
     * 根据 进项发票id 查询
     * @param otherid
     * @return
     */
    List<AccountInputInvoiceInfo> listAccountInputInvoiceInfo(String otherid);

    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountInputInvoiceInfoByParentId( String otherid);
}
