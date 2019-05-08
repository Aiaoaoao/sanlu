package com.yc.education.service.purchase;

import com.yc.education.model.purchase.InquiryOrder;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName InquiryOrderService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/27 17:27
 * @Version 1.0
 */
public interface InquiryOrderService extends IService<InquiryOrder> {

    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum(String currentDate);

    //查询询价单
    List<InquiryOrder> findInquiryOrder();

    //查询询价单
    List<InquiryOrder> findInquiryOrder(int pageNum,int pageSize);

    //查询询价单
    List<InquiryOrder> findInquiryOrder(String order);

    //查询询价单
    List<InquiryOrder> findInquiryOrder(String order,int pageNum,int pageSize);


    //查询询价单
    List<InquiryOrder> findInquiryOrder(int status,int pageNum,int pageSize);


    //交易单据
    List<InquiryOrder> findInquiryOrderBySupplier(String supplier,String startTime,String endTime);

    //交易单据
    List<InquiryOrder> findInquiryOrderBySupplier(String supplier,String startTime,String endTime,int pageNum,int pageSize);

    /**
     * 查询没有审核的询价单
     * @return
     */
    List<InquiryOrder> findInquiryOrderNotSh();

    /**
     * 根据订单编号查询询价单
     * @param orders
     * @return
     */
    InquiryOrder findInquiryOrderByOrders(String orders);

}
