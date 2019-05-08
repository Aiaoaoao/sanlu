package com.yc.education.mapper.customer;


import com.yc.education.model.customer.CustomerDemandGoods;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 客户需求商品
 * @auther: BlueSky
 */
public interface CustomerDemandGoodsMapper extends MyMapper<CustomerDemandGoods> {

    /**
     *  客户需求商品查询（多条件）
     * @param createNoBen 建档编号开始
     * @param createNoEnd 建档编号结束
     * @param createDateBen 创建日期开始
     * @param createDateEnd 创建日期结束
     * @param customerNoBen 客户编号开始
     * @param customerNoEnd 客户编号结束
     * @return
     */
    List<CustomerDemandGoods> listCustomerDemandGoodsByManyCondition(@Param("createNoBen")String createNoBen,@Param("createNoEnd")String createNoEnd,@Param("createDateBen")String createDateBen,@Param("createDateEnd")String createDateEnd,@Param("customerNoBen")String customerNoBen,@Param("customerNoEnd")String customerNoEnd);

    /**
     * 查询商品需求客户
     * @param customerName
     * @return
     */
    List<CustomerDemandGoods> listCustomerDemandGoods(@Param("customerName") String customerName);

    /**
     * 最后一条数据
     * @return
     */
    CustomerDemandGoods getLastCustomerDemandGoods();

    /**
     * 第一条数据
     * @return
     */
    CustomerDemandGoods getFirstCustomerDemandGoods();

    /**
     * 获取上下页数据
     * @return
     */
    CustomerDemandGoods getCustomerDemandGoodsByPage(@Param("page")int page, @Param("rows")int rows);

    /**
     * 检查建档编号是否已经存在
     * @param createno
     * @return
     */
    @Select("select id from customer_demand_goods where create_no = #{createno}")
    long getCheckCustomerDemandGoodsIsStorage(@Param("createno")long createno);

    /**
     * 统计客户需求商品数量
     * @return
     */
    int getCustomerDemandGoodsCount();


}