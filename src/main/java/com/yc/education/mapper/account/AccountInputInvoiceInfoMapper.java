package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountInputInvoiceInfo;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountInputInvoiceInfoMapper extends MyMapper<AccountInputInvoiceInfo> {

    /**
     * 根据 进项发票id 查询
     * @param otherid
     * @return
     */
    List<AccountInputInvoiceInfo> listAccountInputInvoiceInfo(@Param("otherid") String otherid);

    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountInputInvoiceInfoByParentId(@Param("otherid") String otherid);
}