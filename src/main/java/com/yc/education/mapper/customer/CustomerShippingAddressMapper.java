package com.yc.education.mapper.customer;


import com.yc.education.model.customer.CustomerShippingAddress;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/8/16 17:45
 */
@Repository
public interface CustomerShippingAddressMapper extends MyMapper<CustomerShippingAddress> {

    /**
     * 查询所有客户地址
     * @return
     */
    @Select("select * from customer_shipping_address where customerid = #{customerid} order by setting desc")
    List<CustomerShippingAddress> listAll(@Param("customerid")long customerid);

    /**
     * 设置默认地址
     * @param id
     * @return
     */
    int updateAddressDefault(@Param("id")long id);

    /**
     * 清除默认地址
     * @param customerid
     * @return
     */
    int updateClearAddressDefault(@Param("customerid")long customerid);
}
