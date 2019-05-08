package com.yc.education.mapper.customer;

import com.yc.education.model.customer.Invoice;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface InvoiceMapper extends MyMapper<Invoice> {


    /**
     * 设置常用、停用
     * @param id
     * @param type 1:常用、2:停用
     * @return
     */
    int updateInvoiceDefault(@Param("id")long id,@Param("type")String type,@Param("date")String date);

    /**
     * 清除默认
     * @param customerid
     * @param type 1:常用、2:停用
     * @return
     */
    int updateClearInvoiceDefault(@Param("customerid")long customerid,@Param("type")String type);

    /**
     * 查询所有
     * @return
     */
    List<Invoice> listInvoice();

    /**
     * 通过客户id查询发票
     * @return
     */
    List<Invoice> listInvoiceByCustomerId(@Param("customerid") long customerid);

    /**
     * 通过客户id删除发票
     * @param customerid
     * @return
     */
    int deleteInvoiceByCustomerId(@Param("customerid") long customerid);
}