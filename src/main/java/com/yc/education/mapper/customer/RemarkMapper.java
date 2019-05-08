package com.yc.education.mapper.customer;

import com.yc.education.model.customer.Remark;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RemarkMapper extends MyMapper<Remark> {

    /**
     * （废除）
     * 查询某客户的备注及说明
     * @param customerid
     * @return
     */
    List<Remark> listCustomerRemarkByCustomerId(@Param("customerid")long customerid);

    /**
     * （废除）
     * 删除某个客户的所有备注及说明
     * @param customerid
     * @return
     */
    int deleteCustomerRemarkByCustomerId(@Param("customerid") long customerid);

    /**
     * 查找某个订单相关的备注及说明
     * @param otherid
     * @param type 1、客户基本资料，2、报价单，3、订货单，4、退货单
     * @return
     */
    List<Remark> listRemark(@Param("otherid")long otherid,@Param("type")String type);

    /**
     * 删除某个订单相关的备注及说明
     * @param otherid
     * @param type 1、客户基本资料，2、报价单，3、订货单，4、退货单
     * @return
     */
    int deleteRemark(@Param("otherid") long otherid,@Param("type")String type);

}