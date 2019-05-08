package com.yc.education.service.impl.stock;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.stock.StockChangeProductMapper;
import com.yc.education.model.stock.StockChangeProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.StockChangeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StockChangeProductServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/5 20:37
 * @Version 1.0
 */
@Service("StockChangeProductService")
public class StockChangeProductServiceImpl extends BaseService<StockChangeProduct> implements StockChangeProductService {

    @Autowired
    private StockChangeProductMapper stockChangeProductMapper;


    @Override
    public List<StockChangeProduct> findStockChangeProduct(long stockChangeId) {
        try{
            return stockChangeProductMapper.findStockChangeProduct(stockChangeId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<StockChangeProduct> findStockChangeProductByProductNumAndChangeTypeAndChangeDate(String proTextStart, String proTextEnd, int changeTypeStart, int changeTypeEnd, String changeDateStart, String changeDateEnd) {
        try{
            return stockChangeProductMapper.findStockChangeProductByProductNumAndChangeTypeAndChangeDate(proTextStart, proTextEnd, changeTypeStart, changeTypeEnd, changeDateStart, changeDateEnd);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<StockChangeProduct> findStockChangeProductByProductNumAndChangeTypeAndChangeDate(String proTextStart, String proTextEnd, int changeTypeStart, int changeTypeEnd, String changeDateStart, String changeDateEnd, int pageNum, int pageSize) {
        try{
            PageHelper.startPage(pageNum,pageSize);
            return stockChangeProductMapper.findStockChangeProductByProductNumAndChangeTypeAndChangeDate(proTextStart, proTextEnd, changeTypeStart, changeTypeEnd, changeDateStart, changeDateEnd);
        }catch (Exception e){
            return null;
        }
    }
}
