package com.yc.education.service.account;

import com.yc.education.model.account.AccountReceivable;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description 应收账款冲账
 * @Author BlueSky
 * @Date 2018-12-04 16:41
 */
public interface IAccountReceivableService extends IService<AccountReceivable> {


    /**
     * 查询最近数据
     * @param benTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<AccountReceivable> listAccountReceivableByTime(String benTime,String endTime);

    /**
     * 查询最近数据
     * @param num 最近条数
     * @return
     */
    List<AccountReceivable> listAccountReceivableByNum(Integer num);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询所有的 应收账款冲账
     * @return
     */
    List<AccountReceivable> listAccountReceivable();

    /**
     * 根据 冲账编号 查询 应收账款冲账
     * @param orderno 冲账编号
     * @return
     */
    AccountReceivable getByOrderNo(String orderno);

    /**
     * 分页查询 应收账款冲账 单据
     * @param page
     * @param size
     * @return
     */
    List<AccountReceivable> listAccountReceivable(String text,int page, int size);


    /**
     * 查询待审核应收账款冲账
     * @return
     */
    List<AccountReceivable> listAccountReceivableNotSh();

}
