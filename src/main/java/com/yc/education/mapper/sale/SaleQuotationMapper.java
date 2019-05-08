package com.yc.education.mapper.sale;

import com.yc.education.model.sale.SaleQuotation;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SaleQuotationMapper extends MyMapper<SaleQuotation> {

    /**
     * @Description 订单号、客户名称 模糊查询
     * @Author BlueSky
     * @Date 9:55 2019/4/11
     **/
    List<SaleQuotation> listOrderNoLike( @Param("orderNo")String orderNo);

    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleQuotation> listTimeWhere(@Param("ben")String ben,@Param("end")String end);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询所有报价单
     * @return
     */
    List<SaleQuotation> listSaleQuotationAll(@Param("text")String text,@Param("audit")String audit);

    /**
     * 根据报价单号查询报价单
     * @param offerno
     * @return
     */
    SaleQuotation getSaleQuotation(@Param("offerno")String offerno);

    /**
     * 最后一条数据
     * @return
     */
    SaleQuotation getLastSaleQuotation();

    /**
     * 第一条数据
     * @return
     */
    SaleQuotation getFirstSaleQuotation();

    /**
     * 获取上下页数据
     * @return
     */
    SaleQuotation getSaleQuotationByPage(@Param("page")int page, @Param("rows")int rows);

    /**
     * 统计报价单数量
     * @return
     */
    int getSaleQuotationCount();


    List<SaleQuotation> listSaleQuotationAllByStatus(@Param("status")int status);

    /**
     * 查询待审核的报价单
     * @return
     */
    List<SaleQuotation> listSaleQuotationNotSh();

}