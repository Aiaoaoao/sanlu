package com.yc.education.service.customer;

import com.yc.education.model.customer.Remark;
import com.yc.education.service.IService;

import java.util.List;

/**
 * 备注及说明
 * @author BlueSky
 * @Description:
 * @Date 15:16 2018-08-24
 */
public interface IRemarkService extends IService<Remark>{

    /**
     * 查询某客户的备注及说明
     * @param customerid
     * @return
     */
    List<Remark> listCustomerRemarkByCustomerId(long customerid);

    /**
     * 删除某个客户的所有备注及说明
     * @param customerid
     * @return
     */
    int deleteCustomerRemarkByCustomerId( long customerid);

    /**
     * 查找某个订单相关的备注及说明
     * @param otherid 单据id
     * @param type 1、客户基本资料，2、报价单，3、订货单，4、销货单
     * @return
     */
    List<Remark> listRemark(long otherid,String type);

    /**
     * 删除某个订单相关的备注及说明
     * @param otherid
     * @param type 1、客户基本资料，2、报价单，3、订货单，4、销货单
     * @return
     */
    int deleteRemark(long otherid,String type);
}
