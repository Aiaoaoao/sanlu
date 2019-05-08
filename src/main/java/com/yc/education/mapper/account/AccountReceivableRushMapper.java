package com.yc.education.mapper.account;


import com.yc.education.model.account.AccountReceivableRush;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountReceivableRushMapper extends MyMapper<AccountReceivableRush> {

    /**
     * 根据 应收账款冲账id 查询
     * @param otherid
     * @return
     */
    List<AccountReceivableRush> listAccountReceivableRush(@Param("otherid") String otherid);

    /**
     * 通过外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountReceivableRushByParentId(@Param("otherid") String otherid);
}