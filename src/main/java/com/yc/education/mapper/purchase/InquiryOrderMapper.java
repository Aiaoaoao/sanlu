package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.InquiryOrder;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InquiryOrderMapper extends MyMapper<InquiryOrder> {


    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum(String currentDate);


    //查询询价单
    List<InquiryOrder> findInquiryOrder();


    List<InquiryOrder> findInquiryOrderByOrder(@Param("orders")String orders);

    List<InquiryOrder> findInquiryOrderByStatus(@Param("status")int status);


    //交易单据
    List<InquiryOrder> findInquiryOrderBySupplier(@Param("supplier")String supplier,@Param("startTime")String startTime,@Param("endTime")String endTime);

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
    InquiryOrder findInquiryOrderByOrders(@Param("orders")String orders);

}