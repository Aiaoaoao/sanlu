package com.yc.education.service.sale;


import com.yc.education.model.sale.ReportRemark;
import com.yc.education.service.IService;

import java.util.List;

/**
 *  报表备注
 * @author BlueSky
 * @Description:
 * @Date 15:16 2018-08-24
 */
public interface IReportRemarkService extends IService<ReportRemark>{

    /**
     * 查找某个订单相关的报表备注
     * @param otherid
     * @param type 1、报价单，2、订货单，3、退货单
     * @return
     */
    List<ReportRemark> listReportRemark(long otherid,String type);

    /**
     * 删除某个订单相关的报表备注
     * @param otherid
     * @param type 1、报价单，2、订货单，3、退货单
     * @return
     */
    int deleteReportRemark(long otherid,String type);
}
