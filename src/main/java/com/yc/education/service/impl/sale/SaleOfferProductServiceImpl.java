package com.yc.education.service.impl.sale;


import com.yc.education.mapper.sale.SaleOfferProductMapper;
import com.yc.education.model.sale.SaleOfferProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISaleOfferProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BlueSky
 * @Description:
 * @Date 10:33 2018-08-24
 */
@Service
public class SaleOfferProductServiceImpl extends BaseService<SaleOfferProduct> implements ISaleOfferProductService {

    @Autowired
    SaleOfferProductMapper mapper;

    @Override
    public List<SaleOfferProduct> listTimeWhere(String ben, String end) {
        try {
            return mapper.listTimeWhere(ben,end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleOfferProduct> listSaleOfferProduct(Long quotationid) {
        try {
            return mapper.listSaleOfferProduct(quotationid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteSaleOfferProduct(Long quotationid) {
        try {
            return mapper.deleteSaleOfferProduct(quotationid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<SaleOfferProduct> selectSaleOfferProductsByProductNumAndStartTimeAndEndTime(String productNum, String startTime, String endTime) {
        try {
            return mapper.selectSaleOfferProductsByProductNumAndStartTimeAndEndTime(productNum, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }


}
