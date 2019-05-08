package com.yc.education.service.sale;

import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface ISaleGoodsService extends IService<SaleGoods> {

    /**
     * @Description 根据客户查询所有已审核的销货单
     * @param state 空：忽略该字段、非空：查询未冲完的销货单
     * @Author BlueSky
     * @Date 17:08 2019/5/6
     **/
    List<SaleGoods> listSaleGoodsByCustomer( String state, String customerNo);

    /**
     * @Description 查询所有销货单未出库完的订单
     * @Author BlueSky
     * @Date 17:29 2019/4/23
     **/
    List<SaleGoods> listSaleGoodsByOrderNotOutbound(int page, int rows);

    /***
     * 根据客户编号查询最近一次销货数据
     * @param customerNo
     * @return
     */
    SaleGoods getLastSaleGoodsByCustomerNo( String customerNo);

    /**
     * 通过销售单号查询销售单据
     * @param saleno
     * @return
     */
    SaleGoods findBySaleNo(String saleno);

    /**
     * 库存 --》 销货出库单 --》 查询导入到销货出库单的销货单据（已审核 and 未导入的单据）
     * @return
     */
    List<SaleGoods> listImportToSaleOutboundOrder();

    /**
     * 账款 --》 业务查询 --》 销项发票 --》 查询销货单未开票的单据
     * @return
     */
    List<SaleGoods> listSaleInvoiceNotProcess(int page, int rows);

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
    List<SaleGoods> listSaleGoodsBySaleCost( String saledate, String saledateEnd, String saleno, String salenoEnd, String customerno, String customernoEnd, String productno, String productnoEnd, String category, String categoryEnd);

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
    List<SaleGoods> listOrderNoLike( String orderNo);

    /**
     * 销售 -> 业务查询 -> 销货发货跟踪
     * @param customerNo 客户编号
     * @param customerNoEnd 客户编号
     * @param saleNo 销售编号
     * @param saleNoEnd 销售编号
     * @param auditDate 审核日期
     * @param auditDateEnd 审核日期
     * @param stockOutNo 销货出库编号
     * @param stockOutNoEnd 销货出库编号
     * @param stockAuditDate 销货出库审核日期
     * @param stockAuditDateEnd 销货出库审核日期
     * @return
     */
    List<SaleGoods> listSaleGoodsTrack( int page,int rows,String customerNo, String customerNoEnd, String saleNo, String saleNoEnd, String auditDate, String auditDateEnd, String stockOutNo, String stockOutNoEnd, String stockAuditDate, String stockAuditDateEnd);



    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleGoods> listTimeWhere(String ben,String end);

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
    List<SaleGoods> listSaleGoodsByStates( String customerno, String begintime, String endtime, String verifystate, String deliverystate, String financialstate, String backstate);

    /**
     * 查询全部订单
     * @return
     */
    List<SaleGoods> listSaleGoodsAll();

    /**
     * 查询全部订单
     * @param text 订单号、客户名称
     * @param audit 审核状态 1：已审核
     * @param page
     * @param rows
     * @return
     */
    List<SaleGoods> listSaleGoodsByPage( String text,String audit,int page, int rows);

    /**
     * 根据销售单号查询
     * @param orderno
     * @return
     */
    SaleGoods getSaleGoods(String orderno);


    /**
     * 统计订单数量
     * @return
     */
    int getSaleGoodsCount();


    /**
     * 查询全部订单
     * @return
     */
    List<SaleGoods> listSaleGoodsAll(int status);
    /**
     * 查询全部订单
     * @return
     */
    List<SaleGoods> listSaleGoodsAll(int status,int pageNum,int pageSize);

    /**
     * 查询没有审核的单据
     * @return
     */
    List<SaleGoods> listSaleGoodsNotSh();
}
