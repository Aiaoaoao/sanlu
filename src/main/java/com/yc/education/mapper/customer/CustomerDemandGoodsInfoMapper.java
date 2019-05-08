package com.yc.education.mapper.customer;

import com.yc.education.model.customer.CustomerDemandGoodsInfo;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomerDemandGoodsInfoMapper extends MyMapper<CustomerDemandGoodsInfo> {

    /**
     * 查询所有
     * @param productName
     * @return
     */
    List<CustomerDemandGoodsInfo> listCustomerDemandGoodsInfo(@Param("productName") String productName);

    /**
     * 根据客户需求商品id查询
     * @param customerDemandId
     * @return
     */
    List<CustomerDemandGoodsInfo> listCustomerDemandGoodsInfoByCustomerDemandId(@Param("customerDemandId") long customerDemandId);

    /**
     * 根据客户需求商品id删除
     * @param customerDemandId
     * @return
     */
    int deleteCustomerDemandGoodsInfoByCustomerDemandId(@Param("customerDemandId") long customerDemandId);

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
    List<CustomerDemandGoodsInfo> listCustomerDemandGoodsWhere(@Param("createNo") String createNo,@Param("createNoEnd") String createNoEnd,@Param("createDate") String createDate,@Param("createDateEnd") String createDateEnd,@Param("customerNo") String customerNo,@Param("customerNoEnd") String customerNoEnd,@Param("productNo") String productNo,@Param("productNoEnd") String productNoEnd,@Param("material") String material,@Param("manufacture") String manufacture,@Param("method") String method);

}