package com.yc.education.service.account;

import com.yc.education.model.account.AccountPayable;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-12-04 16:41
 */
public interface IAccountPayableService extends IService<AccountPayable> {

    /**
     * 查询最近数据
     * @param num 最近条数
     * @return
     */
    List<AccountPayable> listAccountsPayableByNum(Integer num);

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
    List<AccountPayable> listOrderNoLike( String orderNo);

    /**
     * 查询所有的 应付账款冲账
     * @return
     */
    List<AccountPayable> listAccountsPayable();

    /**
     * 根据 冲账编号 查询 应付账款冲账
     * @param orderno 冲账编号
     * @return
     */
    AccountPayable getByOrderNo(String orderno);

    /**
     * 分页查询 应付账款冲账
     * @param page
     * @param size
     * @return
     */
    List<AccountPayable> listAccountsPayable(String text,int page, int size);

    /**
     * 查询所有待审核的应付账款冲账
     * @return
     */
    List<AccountPayable> listAccountPayableNotSh();

}
