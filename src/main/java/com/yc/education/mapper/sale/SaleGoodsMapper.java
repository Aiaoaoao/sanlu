package com.yc.education.mapper.sale;


import com.yc.education.model.sale.SaleGoods;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SaleGoodsMapper extends MyMapper<SaleGoods> {

    /**
     * @Description 根据客户查询所有已审核的销货单
     * @param state 空：忽略该字段、非空：查询未冲完的销货单
     * @Author BlueSky
     * @Date 17:08 2019/5/6
     **/
    List<SaleGoods> listSaleGoodsByCustomer(@Param("state") String state,@Param("customerNo") String customerNo);
    /**
     * @Description 查询所有销货单未出库完的订单
     * @Author BlueSky
     * @Date 17:29 2019/4/23
     **/
    List<SaleGoods> listSaleGoodsByOrderNotOutbound();

    /***
     * 根据客户编号查询最近一次销货数据
     * @param customerNo
     * @return
     */
    SaleGoods getLastSaleGoodsByCustomerNo(@Param("customerNo") String customerNo);

    /**
     * 通过销售单号查询销售单据
     * @param saleno
     * @return
     */
    SaleGoods findBySaleNo(@Param("saleno") String saleno);

    /**
     * 库存 --》 销货出库单 --》 查询导入到销货出库单的销货单据（已审核 and 未导入的单据）
     * @return
     */
    List<SaleGoods> listImportToSaleOutboundOrder();

    /**
     * 账款 --》 业务查询 --》 销项发票 --》 查询销货单未开票的单据
     * @return
     */
    List<SaleGoods> listSaleInvoiceNotProcess();

    /**
     * 账款 --》业务查询 --》销项成本明细
     * @param saledate  销货日期
     * @param saledateEnd   销货日期
     * @param saleno    销货单号
     * @param salenoEnd   销货单号
     * @param customerno    客户单号
     * @param customernoEnd   客户单号
     * @param productno     产品单号
     * @param productnoEnd  产品单号
     * @param category      产品大类
     * @param categoryEnd   产品大类
     * @return
     */
    List<SaleGoods> listSaleGoodsBySaleCost( @Param("saledate") String saledate, @Param("saledateEnd") String saledateEnd, @Param("saleno") String saleno, @Param("salenoEnd") String salenoEnd, @Param("customerno") String customerno, @Param("customernoEnd") String customernoEnd, @Param("productno") String productno, @Param("productnoEnd") String productnoEnd, @Param("category") String category, @Param("categoryEnd") String categoryEnd);

    /**
     * 给核销订单查询销售订单中未处理的单据
     * @return
     */
    List<SaleGoods> listSaleGoodsToInvoice();

    /**
     * 订单号模糊查询
     * @param orderNo
     * @return
     */
    List<SaleGoods> listOrderNoLike(@Param("orderNo") String orderNo);

    /**
     * 销售 -> 业务查询 -> 销货发货跟踪
     * @param customerNo 客户编号
     * @param customerNoEnd 客户编号
     * @param saleNo 销售编号
     * @param saleNoEnd 销售编号
     * @param auditDate 审核日期
     * @param auditDateEnd 审核日期
     * @return
     */
    List<SaleGoods> listSaleGoodsTrack(@Param("customerNo") String customerNo,@Param("customerNoEnd") String customerNoEnd,@Param("saleNo") String saleNo,@Param("saleNoEnd") String saleNoEnd,@Param("auditDate") String auditDate,@Param("auditDateEnd") String auditDateEnd,@Param("stockOutNo") String stockOutNo,@Param("stockOutNoEnd") String stockOutNoEnd,@Param("stockAuditDate") String stockAuditDate,@Param("stockAuditDateEnd") String stockAuditDateEnd);


    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleGoods> listTimeWhere(@Param("ben")String ben,@Param("end")String end);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 销货单状态更新
     * 差 财务复核状态、回传状态 条件
     * @param customerno 客户编号
     * @param begintime 创建时间-开始
     * @param endtime 创建时间-结束
     * @param verifystate 审核状态
     * @param deliverystate 发送状态
     * @param financialstate 财务复核状态
     * @param backstate 回传状态 (1/0)
     * @return
     */
    List<SaleGoods> listSaleGoodsByStates(@Param("customerno") String customerno,@Param("begintime") String begintime,@Param("endtime") String endtime,@Param("verifystate") String verifystate,@Param("deliverystate") String deliverystate,@Param("financialstate") String financialstate,@Param("backstate") String backstate);

    /**
     * @Description 查询全部订单 + 模糊查询
     * @Author BlueSky
     * @Date 10:11 2019/4/11
     **/
    List<SaleGoods> listSaleGoodsAll(@Param("text") String text,@Param("audit")String audit);

    /**
     * 根据订货单号查询
     * @param orderno
     * @return
     */
    SaleGoods getSaleGoods(@Param("orderno") String orderno);

    /**
     * 最后一条数据
     * @return
     */
    SaleGoods getLastSaleGoods();

    /**
     * 第一条数据
     * @return
     */
    SaleGoods getFirstSaleGoods();

    /**
     * 获取上下页数据
     * @return
     */
    SaleGoods getSaleGoodsByPage(@Param("page") int page, @Param("rows")int rows);

    /**
     * 查询所有或者已审核单据
     * @param status
     * @return
     */
    List<SaleGoods> listSaleGoodsAllByStatus(@Param("status")int status);

    /**
     * 查询没有审核的单据
     * @return
     */
    List<SaleGoods> listSaleGoodsNotSh();

}