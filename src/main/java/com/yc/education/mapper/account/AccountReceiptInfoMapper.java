package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountReceiptInfo;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountReceiptInfoMapper extends MyMapper<AccountReceiptInfo> {
    /**
     * 根据 收款单id 查询
     * @param otherid
     * @return
     */
    List<AccountReceiptInfo> listAccountReceiptInfo(@Param("otherid") String otherid);

    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountReceiptInfoByParentId(@Param("otherid") String otherid);
}