package com.yc.education.service;

import com.yc.education.model.TaxRate;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TaxRateService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/28 19:58
 * @Version 1.0
 */
public interface TaxRateService extends IService<TaxRate> {


    /**
     * 根据制单日期和税率类型查询税率
     * @param orderTime
     * @param types  1、进项  2、销项
     * @return
     */
    Double selectTaxRateByOrderTime(String orderTime,int types);


    /**
     * 查询税率历史记录
     * @return
     */
    List<TaxRate> selectTaxRates();


    /**
     * 根据税率类型查询最新数据
     * @param types
     * @return
     */
    TaxRate selectTaxRatesLimit(int types);


    /**
     * 根据开始时间和结束时间查询税率区间是否存在  如果存在则进行添加操作
     * @param startTime
     * @param endTime
     * @return
     */
    TaxRate selectTaxRatesByStartTimeAndEndTime(Date startTime,Date endTime,int types);

}
