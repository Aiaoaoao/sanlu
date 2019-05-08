package com.yc.education.service.account;

import com.yc.education.model.account.AccountSaleInvoiceInfo;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description 销项发票-发票明细
 * @Author BlueSky
 * @Date 2019-01-09 11:50
 */
public interface IAccountSaleInvoiceInfoService extends IService<AccountSaleInvoiceInfo> {


    /**
     * @Author BlueSky
     * @Description //TODO 销售 -- 销货状态更新 ： 查询财务审核状态 （如果销项发票里面能够找到单据就说明已审核）
     * @Date 10:02 2019/4/8
     * @Param [orderNo]
     **/
    Integer getOrderAuditStatusByOrderNo( String orderNo);

    /**
     * 根据 销项发票id 查询
     * @param otherid
     * @return
     */
    List<AccountSaleInvoiceInfo> listAccountSaleInvoiceInfo( String otherid);

    /**
     * 根据外键id删除
     * @param otherid
     * @return
     */
    int deleteAccountSaleInvoiceInfoByParentId( String otherid);
}
