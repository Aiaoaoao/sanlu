package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountSaleInvoice;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountSaleInvoiceMapper extends MyMapper<AccountSaleInvoice> {


    /**
     * @Description 根据客户编号查找该客户所有的销项发票
     * @Author BlueSky
     * @Date 16:20 2019/5/6
     **/
    List<AccountSaleInvoice> listAccountSaleInvoiceByCustomer(@Param("customerNo")String cusotmerNo);

    /**
     * @Description 根据客户编号查询未冲账发票或者未冲完的发票
     * @Author BlueSky
     * @Date 21:36 2019/5/5
     **/
    List<AccountSaleInvoice> listNotRushAccountSaleInvoice(@Param("customerNo")String customerNo);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询所有的 销项发票
     * @return
     */
    List<AccountSaleInvoice> listAccountSaleInvoice(@Param("text")String text);

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountSaleInvoice> listOrderNoLike(@Param("orderNo") String orderNo);

    /**
     * 根据 单号 查询 销项发票
     * @param orderno 冲账编号
     * @return
     */
    AccountSaleInvoice getByOrderNo(@Param("orderno") String orderno);

    /**
     * 查询所有待审核的销项发票
     * @return
     */
    List<AccountSaleInvoice> listAccountSaleInvoiceNotSh();
}