package com.yc.education.service.impl.sale;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.sale.SaleOnlineOrderMapper;
import com.yc.education.mapper.sale.SaleOnlineOrderProductMapper;
import com.yc.education.model.sale.SaleOnlineOrder;
import com.yc.education.model.sale.SaleOnlineOrderProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISaleOnlineOrderProductService;
import com.yc.education.service.sale.ISaleOnlineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/9/26 15:24
 */
@Service
public class SaleOnlineOrderProductServiceImpl extends BaseService<SaleOnlineOrderProduct> implements ISaleOnlineOrderProductService {

    @Autowired
    private SaleOnlineOrderProductMapper mapper;

    @Override
    public List<SaleOnlineOrderProduct> listByOnlineOrderAll(String orderid) {
        try {
            return  mapper.listByOnlineOrderAll(orderid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleOnlineOrderProduct> listByOnlineOrderByPage(String orderno, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listByOnlineOrderAll(orderno);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteSaleOnlineOrderProductByParentId(Long otherid) {
        try {
            return  mapper.deleteSaleOnlineOrderProductByParentId(otherid);
        }catch (Exception e){
            return 0;
        }
    }
}
