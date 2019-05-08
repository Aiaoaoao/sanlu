package com.yc.education.service.impl.sale;

import com.yc.education.mapper.sale.ReportRemarkMapper;
import com.yc.education.model.sale.ReportRemark;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.IReportRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 10:33 2018-08-24
 */
@Service
public class ReportRemarkServiceImpl extends BaseService<ReportRemark> implements IReportRemarkService {

    @Autowired
    ReportRemarkMapper mapper;


    @Override
    public List<ReportRemark> listReportRemark(long otherid, String type) {
        try {
            return mapper.listReportRemark(otherid, type);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteReportRemark(long otherid, String type) {
        try {
            return mapper.deleteReportRemark(otherid, type);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
