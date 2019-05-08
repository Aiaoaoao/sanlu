package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountReceivable;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountReceivableMapper extends MyMapper<AccountReceivable> {

    /**
     * 查询最近数据
     * @param benTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<AccountReceivable> listAccountReceivableByTime(@Param("benTime")String benTime,@Param("endTime")String endTime);

    /**
     * 查询最近数据
     * @param num 最近条数
     * @return
     */
    List<AccountReceivable> listAccountReceivableByNum(@Param("num")Integer num);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * @Description 查询所有的 应收账款冲账 + 模糊查询
     * @Author BlueSky
     * @Date 15:08 2019/4/11
     **/
    List<AccountReceivable> listAccountReceivable(@Param("text")String text);

    /**
     * 根据 冲账编号 查询 应收账款冲账
     * @param orderno 冲账编号
     * @return
     */
    AccountReceivable getByOrderNo(@Param("orderno")String orderno);

    /**
     * 查询待审核应收账款冲账
     * @return
     */
    List<AccountReceivable> listAccountReceivableNotSh();
}