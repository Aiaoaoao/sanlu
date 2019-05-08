package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountReceivableFee;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountReceivableFeeMapper extends MyMapper<AccountReceivableFee> {

    /**
     * 根据 应收账款冲账id 查询
     * @param otherid
     * @return
     */
    List<AccountReceivableFee> listAccountReceivableFee(@Param("otherid") String otherid);

    /**
     * 通过外键删除
     * @param otherid
     * @return
     */
    int deleteAccountReceivableFeeByParentId(@Param("otherid") String otherid);

}