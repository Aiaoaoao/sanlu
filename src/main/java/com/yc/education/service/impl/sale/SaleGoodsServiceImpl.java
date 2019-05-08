package com.yc.education.service.impl.sale;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.sale.SaleGoodsMapper;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISaleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/9/26 15:24
 */
@Service
public class SaleGoodsServiceImpl extends BaseService<SaleGoods> implements ISaleGoodsService {

    @Autowired
    private SaleGoodsMapper mapper;

    @Override
    public List<SaleGoods> listSaleGoodsByCustomer(String state,String customerNo) {
        try {
            return mapper.listSaleGoodsByCustomer(state,customerNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsByOrderNotOutbound(int page, int rows) {
        try {
//            PageHelper.startPage(page,rows);
            return mapper.listSaleGoodsByOrderNotOutbound();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SaleGoods getLastSaleGoodsByCustomerNo(String customerNo) {
        try {
            return mapper.getLastSaleGoodsByCustomerNo(customerNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SaleGoods findBySaleNo(String saleno) {
        try {
            return mapper.findBySaleNo(saleno);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoods> listImportToSaleOutboundOrder() {
        try {
            return mapper.listImportToSaleOutboundOrder();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleInvoiceNotProcess(int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listSaleInvoiceNotProcess();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsBySaleCost(String saledate, String saledateEnd, String saleno, String salenoEnd, String customerno, String customernoEnd, String productno, String productnoEnd, String category, String categoryEnd) {
        try {
            return mapper.listSaleGoodsBySaleCost(saledate, saledateEnd, saleno, salenoEnd, customerno, customernoEnd, productno, productnoEnd,category,categoryEnd);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsToInvoice() {
        try {
            return mapper.listSaleGoodsToInvoice();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoods> listOrderNoLike(String orderNo) {
        try {
            return mapper.listOrderNoLike(orderNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsTrack(int page,int rows,String customerNo, String customerNoEnd, String saleNo, String saleNoEnd, String auditDate, String auditDateEnd, String stockOutNo, String stockOutNoEnd, String stockAuditDate, String stockAuditDateEnd) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listSaleGoodsTrack(customerNo,customerNoEnd,saleNo,saleNoEnd,auditDate,auditDateEnd,stockOutNo,stockOutNoEnd,stockAuditDate,stockAuditDateEnd);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoods> listTimeWhere(String ben, String end) {
        try {
            return mapper.listTimeWhere(ben,end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getMaxOrderNo() {
        try {
            return  mapper.getMaxOrderNo();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsByStates(String customerno, String begintime, String endtime, String verifystate, String deliverystate, String financialstate, String backstate) {
        try {
            return  mapper.listSaleGoodsByStates(customerno, begintime, endtime, verifystate, deliverystate, financialstate, backstate) ;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsAll() {
        try {
            return  mapper.listSaleGoodsAll("","");
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsByPage( String text,String audit,int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listSaleGoodsAll(text,audit);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SaleGoods getSaleGoods(String orderno) {
        try {
            return  mapper.getSaleGoods(orderno);
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public int getSaleGoodsCount() {
        try {
            return  mapper.selectCount(new SaleGoods());
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsAll(int status) {
        try {
            return mapper.listSaleGoodsAllByStatus(status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsAll(int status, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return mapper.listSaleGoodsAllByStatus(status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SaleGoods> listSaleGoodsNotSh() {
        try {
            return mapper.listSaleGoodsNotSh();
        } catch (Exception e) {
            return null;
        }
    }


}
