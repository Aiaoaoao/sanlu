package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountPayableInfo;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountPayableInfoMapper extends MyMapper<AccountPayableInfo> {

    /**
     * 根据 应付账款冲账id 查询
     * @param otherid
     * @return
     */
    List<AccountPayableInfo> listAccountPayableInfo(@Param("otherid") String otherid);

    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountPayableInfoByParentId(@Param("otherid") String otherid);


}