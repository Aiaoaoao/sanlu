package com.yc.education.mapper;

import com.yc.education.model.TaxRate;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface TaxRateMapper extends MyMapper<TaxRate> {


    /**
     * 根据制单日期和税率类型查询税率
     * @param orderTime
     * @param types  1、进项  2、销项
     * @return
     */
    Double selectTaxRateByOrderTime(@Param("orderTime")String orderTime,@Param("types")int types);


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
    TaxRate selectTaxRatesLimit(@Param("types")int types);

    /**
     * 根据开始时间和结束时间查询税率区间是否存在  如果存在则进行添加操作
     * @param startTime
     * @param endTime
     * @return
     */
    TaxRate selectTaxRatesByStartTimeAndEndTime(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("types")int types);



}