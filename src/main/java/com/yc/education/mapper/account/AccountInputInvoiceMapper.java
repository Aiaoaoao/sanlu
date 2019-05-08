package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountInputInvoice;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountInputInvoiceMapper extends MyMapper<AccountInputInvoice> {

    /**
     * @Description 根据区域时间查询
     * @Author BlueSky
     * @Date 17:17 2019/4/12
     **/
    List<AccountInputInvoice> listAccountInputInvoiceByDate(@Param("dateBen") String dateBen,@Param("dateEnd") String dateEnd,@Param("supplierBen") String supplierBen,@Param("supplierEnd") String supplierEnd,@Param("invoiceNoBen") String invoiceNoBen,@Param("invoiceNoEnd") String invoiceNoEnd);
    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();
    /**
     * 查询所有的 进项发票
     * @return
     */
    List<AccountInputInvoice> listAccountInputInvoice(@Param("text") String text);

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountInputInvoice> listOrderNoLike(@Param("orderNo") String orderNo);

    /**
     * 根据 单号 查询 进项发票
     * @param orderno 编号
     * @return
     */
    AccountInputInvoice getByOrderNo(@Param("orderno") String orderno);

    /**
     * 查询所有待审核的进项发票
     * @return
     */
    List<AccountInputInvoice> listAccountInputInvoiceNotSh();

}