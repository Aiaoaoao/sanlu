package com.yc.education.service.sale;

import com.yc.education.model.sale.SaleQuotation;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface ISaleQuotationService extends IService<SaleQuotation> {

    /**
     * @Description 订单号、客户名称 模糊查询
     * @Author BlueSky
     * @Date 9:55 2019/4/11
     **/
    List<SaleQuotation> listOrderNoLike( String orderNo);

    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleQuotation> listTimeWhere(String ben,String end);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询所有报价单
     * @param audit 1:已审核
     * @return
     */
    List<SaleQuotation> listSaleQuotationAll(String text,String audit,int page,int rows);

    /**
     * 根据报价单号查询报价单
     * @param offerno
     * @return
     */
    SaleQuotation getSaleQuotation(String offerno);

    /**
     * 统计报价单数量
     * @return
     */
    int getSaleQuotationCount();

    List<SaleQuotation> listSaleQuotationAllByStatus(int status,int page,int rows);

    /**
     * 查询待审核的报价单
     * @return
     */
    List<SaleQuotation> listSaleQuotationNotSh();

}
