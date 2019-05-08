package com.yc.education.service.account;

import com.yc.education.model.account.AccountReceipt;
import com.yc.education.service.IService;


import java.util.List;

/**
 * @Description 收款单
 * @Author BlueSky
 * @Date 2019-01-09 11:47
 */
public interface IAccountReceiptService extends IService<AccountReceipt> {

    /**
     * @Description 查询未审核的收款单或者还有剩余额度的收款单。（ps：剩余额度：收款单冲销项发票的差额）
     * @Author BlueSky
     * @Date 11:36 2019/5/7
     **/
    List<AccountReceipt> listAccountReceiptNotShiroOrBalance(String customerNo, String dateBen, String dateEnd);

    /**
     * @Author BlueSky
     * @Description //TODO 账款 - 收款单 根据客户编号和时间查询
     * @Date 21:07 2019/3/28
     * @Param [customerNo, customerNoEnd, dateBen, dateEnd]
     * @return java.util.List<com.yc.education.model.account.AccountReceipt>
     **/
    List<AccountReceipt> listAccountReceiptByWhere( String customerNo, String customerNoEnd, String dateBen, String dateEnd);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();
    /**
     * 查询所有的 收款单
     * @return
     */
    List<AccountReceipt> listAccountReceipt();

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountReceipt> listOrderNoLike( String orderNo);

    /**
     * 根据 单号 查询 收款单
     * @param orderno 冲账编号
     * @return
     */
    AccountReceipt getByOrderNo( String orderno);

    /**
     * 分页查询 收款单
     * @param page
     * @param rows
     * @return
     */
    List<AccountReceipt> listAccountReceipt(String text,int page ,int rows);

    /**
     * 查询所有待审核的收款单
     * @return
     */
    List<AccountReceipt> listAccountReceiptNotSh();
}
