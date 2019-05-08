package com.yc.education.service.impl.stock;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.stock.CheckStockProductMapper;
import com.yc.education.model.stock.CheckStockProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.CheckStockProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CheckStockProductServiceImpl
 * @Description TODO 盘库作业产品
 * @Author QuZhangJing
 * @Date 2018/11/9 9:58
 * @Version 1.0
 */
@Service("CheckStockProductService")
public class CheckStockProductServiceImpl extends BaseService<CheckStockProduct> implements CheckStockProductService {

    @Autowired
    private CheckStockProductMapper checkStockProductMapper;


    @Override
    public List<CheckStockProduct> findCheckStockProduct(long checkid) {
        try {
            return checkStockProductMapper.findCheckStockProduct(checkid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckStockProduct> findCheckStockProductByProductOrderAndProductType(String proTextStart, String proTextEnd, int changeTypeStart, int changeTypeEnd, String changeDateStart, String changeDateEnd) {
        try {
            return checkStockProductMapper.findCheckStockProductByProductOrderAndProductType(proTextStart, proTextEnd, changeTypeStart, changeTypeEnd, changeDateStart, changeDateEnd);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckStockProduct> findCheckStockProductByProductOrderAndProductType(String proTextStart, String proTextEnd, int changeTypeStart, int changeTypeEnd, String changeDateStart, String changeDateEnd, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return checkStockProductMapper.findCheckStockProductByProductOrderAndProductType(proTextStart, proTextEnd, changeTypeStart, changeTypeEnd, changeDateStart, changeDateEnd);
        } catch (Exception e) {
            return null;
        }
    }
}
