package com.yc.education.mapper.sale;


import com.yc.education.model.sale.ReportRemark;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ReportRemarkMapper extends MyMapper<ReportRemark> {

    /**
     * 查找某个订单相关的报表备注
     * @param otherid
     * @param type 1、报价单，2、订货单，3、退货单
     * @return
     */
    List<ReportRemark> listReportRemark(@Param("otherid") long otherid, @Param("type")String type);

    /**
     * 删除某个订单相关的报表备注
     * @param otherid
     * @param type 1、报价单，2、订货单，3、退货单
     * @return
     */
    int deleteReportRemark(@Param("otherid") long otherid,@Param("type")String type);
}