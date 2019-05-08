package com.yc.education.service.impl.purchase;

import com.yc.education.mapper.purchase.ForceProductMapper;
import com.yc.education.model.purchase.ForceProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.ForceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ForceProductServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/10/15 15:12
 * @Version 1.0
 */
@Service("ForceProductService")
public class ForceProductServiceImpl extends BaseService<ForceProduct> implements ForceProductService {

    @Autowired
    private ForceProductMapper forceProductMapper;

    @Override
    public List<ForceProduct> findForceProductByForceId(long forceid) {
        try {
            return forceProductMapper.findForceProductByForceId(forceid);
        }catch (Exception e){
            return null;
        }
    }
}
