package com.yc.education.service.customer;


import com.yc.education.model.customer.CustomerDemandGoods;
import com.yc.education.service.IService;

import java.util.List;


/**
 * 客户需求商品
 * @Author: BlueSky
 * @Date: 2018/8/20 11:23
 */
public interface ICustomerDemandGoodsService extends IService<CustomerDemandGoods>{

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
    List<CustomerDemandGoods> listCustomerDemandGoodsByManyCondition(int page,int rows ,String createNoBen,String createNoEnd,String createDateBen,String createDateEnd,String customerNoBen,String customerNoEnd);

    /**
     * 查询商品需求客户
     * @param customerName
     * @return
     */
    List<CustomerDemandGoods> listCustomerDemandGoods(String customerName,int page,int rows);
    /**
     * 检查建档编号是否已经存在
     * @param createno
     * @return
     */
    boolean getCheckCustomerDemandGoodsIsStorage(long createno);
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
     * 获取上下页客户数据
     * @param page
     * @param rows
     * @return
     */
    CustomerDemandGoods getCustomerDemandGoodsByPage(int page, int rows);

    /**
     * 统计客户需求商品数量
     * @return
     */
    int getCustomerDemandGoodsCount();
}
