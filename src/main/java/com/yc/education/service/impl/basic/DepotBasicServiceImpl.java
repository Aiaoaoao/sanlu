package com.yc.education.service.impl.basic;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.basic.DepotBasicMapper;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DepotBasicServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/4 15:31
 * @Version 1.0
 */

@Service("DepotBasicService")
public class DepotBasicServiceImpl extends BaseService<DepotBasic> implements DepotBasicService {

    @Autowired
    private DepotBasicMapper depotBasicMapper;


    @Override
    public String selectMaxIdnum() {
        return depotBasicMapper.selectMaxIdnum();
    }

    @Override
    public List<DepotBasic> selectDepotBasic(String orderOrName,int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return depotBasicMapper.selectDepotBasic(orderOrName);
        } catch (Exception e) {
           return null;
        }
    }

    @Override
    public List<DepotBasic> selectDepotBasic(String orderOrName) {
        try {
            return depotBasicMapper.selectDepotBasic(orderOrName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DepotBasic> selectDepotBasic() {
        return depotBasicMapper.selectDepotBasic("");
    }

    @Override
    public List<DepotBasic> selectDepotBasic(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return depotBasicMapper.selectDepotBasic("");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DepotBasic selectDepotBasicByIsnum(String idnum) {
        return depotBasicMapper.selectDepotBasicByIsnum(idnum);
    }

    @Override
    public DepotBasic selectDepotBasicByIsnum(String idnum, String orderAndName, int pageNum, int pageSize) {

        try {
            PageHelper.startPage(pageNum, pageSize);
            return depotBasicMapper.selectDepotBasicByIsnumAndorderOrName(idnum,orderAndName);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<DepotBasic> selectDepotBasicByParentId(long parentid) {
        try {
            return depotBasicMapper.selectDepotBasicByParentId(parentid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DepotBasic> selectDepotBasicByParentId(long parentid, String orderOrName, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return depotBasicMapper.selectDepotBasicByParentIdAndOrderOrName(parentid,orderOrName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DepotBasic selectDepotBasicByDepName(String depname) {
        try {
            return depotBasicMapper.selectDepotBasicByDepName(depname);
        } catch (Exception e) {
            return null;
        }
    }
}
