package com.yc.education.service.customer;

import com.yc.education.model.customer.Invoice;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 15:38 2018-08-31
 */
public interface IInvoiceService extends IService<Invoice>{

    /**
     * 设置常用、停用
     * @param id
     * @param type 1:常用、2:停用
     * @return
     */
    int updateInvoiceDefault(long id,String type,String date);

    /**
     * 清除默认
     * @param customerid
     * @param type 1:常用、2:停用
     * @return
     */
    int updateClearInvoiceDefault(long customerid,String type);

    /**
     * 查询所有
     * @return
     */
    List<Invoice> listInvoice();

    /**
     * 通过客户id查询发票
     * @return
     */
    List<Invoice> listInvoiceByCustomerId(long customerid);

    /**
     * 通过客户id删除发票
     * @param customerid
     * @return
     */
    int deleteInvoiceByCustomerId(long customerid);
}
