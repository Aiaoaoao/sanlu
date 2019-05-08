package com.yc.education.service.account;

import com.yc.education.model.account.AccountPrepayment;
import com.yc.education.model.account.AccountPrepayment;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description 预付账款
 * @Author BlueSky
 * @Date 2019-01-09 11:47
 */
public interface IAccountPrepaymentService extends IService<AccountPrepayment> {

    /**
     * @Description 查询最近 num 条数据
     * @Author BlueSky
     * @Date 16:34 2019/4/9
     **/
    List<AccountPrepayment> listAccountPrepaymentByRecently( int num);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();
    /**
     * 查询所有的 预付账款
     * @return
     */
    List<AccountPrepayment> listAccountPrepayment();

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountPrepayment> listOrderNoLike(String orderNo);

    /**
     * 根据 单号 查询 预付账款
     * @param orderno 编号
     * @return
     */
    AccountPrepayment getByOrderNo(String orderno);

    /**
     * 分页查询 预付账款
     * @param page
     * @param rows
     * @return
     */
    List<AccountPrepayment> listAccountPrepayment(String text,int page, int rows);

    /**
     * 查询待审核的预付账款
     * @return
     */
    List<AccountPrepayment> listAccountPrepaymentNotSh();

}
