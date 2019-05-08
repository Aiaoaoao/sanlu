package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountPrepaymentInfo;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountPrepaymentInfoMapper extends MyMapper<AccountPrepaymentInfo> {
    /**
     * 根据 预付账款 id 查询
     * @param otherid
     * @return
     */
    List<AccountPrepaymentInfo> listAccountPrepaymentInfo(@Param("otherid") String otherid);

    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountPrepaymentInfoByParentId(@Param("otherid") String otherid);
}