package com.yc.education.service.purchase;

import com.yc.education.model.purchase.ForceProduct;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName ForceProductService
 * @Description TODO 强制结案采购产品
 * @Author QuZhangJing
 * @Date 2018/10/15 15:05
 * @Version 1.0
 */
public interface ForceProductService extends IService<ForceProduct> {

    //根据强制结案编号查询强制结案采购产品
    List<ForceProduct> findForceProductByForceId(long forceid);


}
