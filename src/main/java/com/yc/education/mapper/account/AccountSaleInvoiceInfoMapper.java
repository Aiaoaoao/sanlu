package com.yc.education.mapper.account;

import com.yc.education.model.account.AccountSaleInvoiceInfo;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountSaleInvoiceInfoMapper extends MyMapper<AccountSaleInvoiceInfo> {

    /**
     * @Author BlueSky
     * @Description //TODO 销售 -- 销货状态更新 ： 查询财务审核状态 （如果销项发票里面能够找到单据就说明已审核）
     * @Date 10:02 2019/4/8
     * @Param [orderNo]
     **/
    Integer getOrderAuditStatusByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据 销项发票id 查询
     * @param otherid
     * @return
     */
    List<AccountSaleInvoiceInfo> listAccountSaleInvoiceInfo(@Param("otherid") String otherid);

    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountSaleInvoiceInfoByParentId(@Param("otherid") String otherid);
}