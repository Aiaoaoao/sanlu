package com.yc.education.service.impl;

import com.yc.education.mapper.TaxRateMapper;
import com.yc.education.model.TaxRate;
import com.yc.education.service.TaxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TaxRateServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/28 20:00
 * @Version 1.0
 */
@Service("TaxRateService")
public class TaxRateServiceImpl extends BaseService<TaxRate> implements TaxRateService {

    @Autowired
    private TaxRateMapper taxRateMapper;

    @Override
    public Double selectTaxRateByOrderTime(String orderTime, int types) {
        try {
            return taxRateMapper.selectTaxRateByOrderTime(orderTime, types);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TaxRate> selectTaxRates() {
        try {
            return  taxRateMapper.selectTaxRates();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TaxRate selectTaxRatesLimit(int types) {
        try {
            return taxRateMapper.selectTaxRatesLimit(types);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TaxRate selectTaxRatesByStartTimeAndEndTime(Date startTime, Date endTime,int types) {
        try {
            return taxRateMapper.selectTaxRatesByStartTimeAndEndTime(startTime, endTime,types);
        } catch (Exception e) {
            return null;
        }
    }
}
