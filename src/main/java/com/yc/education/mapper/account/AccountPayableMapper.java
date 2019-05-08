package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountPayable;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountPayableMapper extends MyMapper<AccountPayable> {

    /**
     * 查询最近数据
     * @param num 最近条数
     * @return
     */
    List<AccountPayable> listAccountsPayableByNum(@Param("num")Integer num);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountPayable> listOrderNoLike(@Param("orderNo") String orderNo);

    /**
     * 查询所有的 应付账款冲账
     * @return
     */
    List<AccountPayable> listAccountsPayable(@Param("text") String text);

    /**
     * 根据 冲账编号 查询 应付账款冲账
     * @param orderno 冲账编号
     * @return
     */
    AccountPayable getByOrderNo(@Param("orderno")String orderno);

    /**
     * 查询所有待审核的应付账款冲账
     * @return
     */
    List<AccountPayable> listAccountPayableNotSh();
}