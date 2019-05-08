package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountPrepayment;
import com.yc.education.model.account.AccountPrepayment;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountPrepaymentMapper extends MyMapper<AccountPrepayment> {

    /**
     * @Description 查询最近 num 条数据
     * @Author BlueSky
     * @Date 16:34 2019/4/9
     **/
    List<AccountPrepayment> listAccountPrepaymentByRecently(@Param("num") int num);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();
    /**
     * 查询所有的 预付账款
     * @return
     */
    List<AccountPrepayment> listAccountPrepayment(@Param("text") String text);

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountPrepayment> listOrderNoLike(@Param("orderNo") String orderNo);

    /**
     * 根据 单号 查询 预付账款
     * @param orderno 编号
     * @return
     */
    AccountPrepayment getByOrderNo(@Param("orderno") String orderno);

    /**
     * 查询待审核的预付账款
     * @return
     */
    List<AccountPrepayment> listAccountPrepaymentNotSh();
}