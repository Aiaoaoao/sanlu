package com.yc.education.service.account;

import com.yc.education.model.account.AccountInputInvoice;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description 进项发票
 * @Author BlueSky
 * @Date 2019-01-09 11:47
 */
public interface IAccountInputInvoiceService extends IService<AccountInputInvoice> {

    /**
     * @Description 账款 - 查询 - 应付账款 ：条件查询未冲账单据
     * @Author BlueSky
     * @Date 12:01 2019/4/26
     **/
    List<AccountInputInvoice> listAccountInputInvoiceByDate( String dateBen, String dateEnd,String supplierBen, String supplierEnd,String invoiceNoBen, String invoiceNoEnd,int page, int rows);

    /**
     * @Description 根据区域时间查询未冲账单据
     * @Author BlueSky
     * @Date 17:17 2019/4/12
     **/
    List<AccountInputInvoice> listAccountInputInvoiceByDate( String dateBen, String dateEnd);
    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();
    /**
     * 查询所有的 进项发票
     * @return
     */
    List<AccountInputInvoice> listAccountInputInvoice();

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountInputInvoice> listOrderNoLike(String orderNo);

    /**
     * 根据 单号 查询 进项发票
     * @param orderno 冲账编号
     * @return
     */
    AccountInputInvoice getByOrderNo(String orderno);

    /**
     * 分页查询 进项发票
     * @param page
     * @param rows
     * @return
     */
    List<AccountInputInvoice> listAccountInputInvoice(String text,int page, int rows);

    /**
     * 查询所有待审核的进项发票
     * @return
     */
    List<AccountInputInvoice> listAccountInputInvoiceNotSh();
}
