package com.yc.education.service.account;

import com.yc.education.model.account.AccountCoastAccounting;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-12-04 16:41
 */
public interface IAccountCoastAccountingService extends IService<AccountCoastAccounting> {

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询所有的成本核算单据
     * @return
     */
    List<AccountCoastAccounting> listAccountCoastAccounting();

    /**
     * 根据入库单号查询成本核算单据
     * @param orderno 入库单号
     * @return
     */
    AccountCoastAccounting getByOrderNo(String orderno);

    /**
     * 分页查询成本核算单据
     * @param page
     * @param size
     * @return
     */
    List<AccountCoastAccounting> listAccountCoastAccounting(String text,int page,int size);

}
