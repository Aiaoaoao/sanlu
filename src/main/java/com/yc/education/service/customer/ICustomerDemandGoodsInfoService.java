package com.yc.education.service.customer;


import com.yc.education.model.customer.CustomerDemandGoodsInfo;
import com.yc.education.service.IService;

import java.util.List;


/**
 * 客户需求商品详情
 * @Author: BlueSky
 * @Date: 2018/8/20 11:23
 */
public interface ICustomerDemandGoodsInfoService extends IService<CustomerDemandGoodsInfo>{

    /**
     * 查询所有
     * @param productName
     * @return
     */
    List<CustomerDemandGoodsInfo> listCustomerDemandGoodsInfo( String productName,int page,int rows);

    /**
     * 根据客户需求商品id查询
     * @param customerDemandId
     * @return
     */
    List<CustomerDemandGoodsInfo> listCustomerDemandGoodsInfoByCustomerDemandId(long customerDemandId);

    /**
     * 根据客户需求商品id删除
     * @param customerDemandId
     * @return
     */
    int deleteCustomerDemandGoodsInfoByCustomerDemandId(long customerDemandId);

    /**
     * 客户需求商品查询
     * @param createNo 建档编号区域
     * @param createNoEnd 建档编号结束
     * @param createDate 建档日期区域
     * @param createDateEnd 建档日期结束
     * @param customerNo 客户编号区域
     * @param customerNoEnd 客户编号结束
     * @param productNo 产品编号
     * @param productNoEnd 产品编号
     * @param material 材质
     * @param manufacture 制造方式
     * @param method 加工方法
     * @return
     */
    List<CustomerDemandGoodsInfo> listCustomerDemandGoodsWhere( String createNo, String createNoEnd, String createDate, String createDateEnd, String customerNo, String customerNoEnd, String productNo, String productNoEnd, String material, String manufacture, String method);

}
