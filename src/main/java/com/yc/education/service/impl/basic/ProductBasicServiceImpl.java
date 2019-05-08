package com.yc.education.service.impl.basic;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.basic.ProductBasicMapper;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductBasicServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/5 10:03
 * @Version 1.0
 */
@Service("ProductBasicService")
public class ProductBasicServiceImpl extends BaseService<ProductBasic> implements ProductBasicService {

    @Autowired
    private ProductBasicMapper productBasicMapper;


    @Override
    public List<ProductBasic> listQueryInventoryCost(String productno, String productnoEnd, String productname, String productnameEnd, String unit, String unitEnd) {
        try {
            return productBasicMapper.listQueryInventoryCost(productno, productnoEnd, productname, productnameEnd, unit, unitEnd);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String selectMaxIdnum() {
        return productBasicMapper.selectMaxIdnum();
    }

    @Override
    public List<ProductBasic> selectProductBasic(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return productBasicMapper.selectProductBasic();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ProductBasic> selectProductBasic() {
        return productBasicMapper.selectProductBasic();
    }

    @Override
    public List<ProductBasic> selectProductBasic(String idnumOrName, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return productBasicMapper.selectProductBasicByIdnumOrName(idnumOrName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ProductBasic> selectProductBasic(String idnumOrName) {
        return productBasicMapper.selectProductBasicByIdnumOrName(idnumOrName);
    }

    @Override
    public List<ProductBasic> selectProductBasic(int isstop, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return productBasicMapper.selectProductBasicByIsstop(isstop);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ProductBasic> selectProductBasic(int isstop) {
        return productBasicMapper.selectProductBasicByIsstop(isstop);
    }

    @Override
    public ProductBasic selectProductBasicByIsnum(String idnum) {
        return productBasicMapper.selectProductBasicByIsnum(idnum);
    }

    @Override
    public List<ProductBasic> selectProductBasicSearch(String isnum, String proname, long basicunit, long protype) {
        return productBasicMapper.selectProductBasicSearch(isnum,proname,basicunit,protype);
    }
}
