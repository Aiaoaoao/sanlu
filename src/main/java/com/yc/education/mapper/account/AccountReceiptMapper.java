package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountReceipt;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountReceiptMapper extends MyMapper<AccountReceipt> {

    /**
     * @Description 查询未审核的收款单或者还有剩余额度的收款单。（ps：剩余额度：收款单冲销项发票的差额）
     * @Author BlueSky
     * @Date 11:36 2019/5/7
     **/
    List<AccountReceipt> listAccountReceiptNotShiroOrBalance(@Param("customerNo") String customerNo,@Param("dateBen") String dateBen,@Param("dateEnd") String dateEnd);

    /**
     * @Author BlueSky
     * @Description //TODO 账款 - 收款单 根据客户编号和时间查询
     * @Date 21:07 2019/3/28
     * @Param [customerNo, customerNoEnd, dateBen, dateEnd]
     * @return java.util.List<com.yc.education.model.account.AccountReceipt>
     **/
    List<AccountReceipt> listAccountReceiptByWhere(@Param("customerNo") String customerNo,@Param("customerNoEnd") String customerNoEnd,@Param("dateBen") String dateBen,@Param("dateEnd") String dateEnd);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * @Description 查询所有的 收款单 + 模糊查询
     * @Author BlueSky
     * @Date 15:40 2019/4/11
     **/
    List<AccountReceipt> listAccountReceipt(@Param("text") String text);

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<AccountReceipt> listOrderNoLike(@Param("orderNo") String orderNo);

    /**
     * 根据 单号 查询 收款单
     * @param orderno 编号
     * @return
     */
    AccountReceipt getByOrderNo(@Param("orderno") String orderno);

    /**
     * 查询所有待审核的收款单
     * @return
     */
    List<AccountReceipt> listAccountReceiptNotSh();
}