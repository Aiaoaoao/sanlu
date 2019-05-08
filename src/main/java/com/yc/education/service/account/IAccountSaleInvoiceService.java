package com.yc.education.service.account;

import com.yc.education.model.account.AccountSaleInvoice;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description 销项发票
 * @Author BlueSky
 * @Date 2019-01-09 11:50
 */
public interface IAccountSaleInvoiceService extends IService<AccountSaleInvoice> {

    /**
     * @Description 根据客户编号查找该客户所有的销项发票
     * @Author BlueSky
     * @Date 16:20 2019/5/6
     **/
    List<AccountSaleInvoice> listAccountSaleInvoiceByCustomer(String cusotmerNo);

    /**
     * @Description 根据客户编号查询未冲账发票或者未冲完的发票
     * @Author BlueSky
     * @Date 21:36 2019/5/5
     **/
    List<AccountSaleInvoice> listNotRushAccountSaleInvoice(String customerNo);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询所有的 销项发票
     * @return
     */
    List<AccountSaleInvoice> listAccountSaleInvoice();

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountSaleInvoice> listOrderNoLike( String orderNo);

    /**
     * 根据 单号 查询 销项发票
     * @param orderno 冲账编号
     * @return
     */
    AccountSaleInvoice getByOrderNo(String orderno);

    /**
     * 分页查询 销项发票 + 模糊查询
     * @param page
     * @param rows
     * @return
     */
    List<AccountSaleInvoice> listAccountSaleInvoice(String text,int page,int rows);

    /**
     * 查询所有待审核的销项发票
     * @return
     */
    List<AccountSaleInvoice> listAccountSaleInvoiceNotSh();
}
