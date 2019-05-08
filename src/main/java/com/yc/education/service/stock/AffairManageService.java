package com.yc.education.service.stock;

import java.util.Map;

/**
 * @ClassName AffairManageService
 * @Description TODO 统一处理
 * @Author QuZhangJing
 * @Date 2019/4/15 13:36
 * @Version 1.0
 */
public interface AffairManageService {

    /**
     * 单据操作：采购入库单
     * 根据入库产品修改库存数
     * des: 审核以后修改采购入库单产品库存数量
     * 步骤:
     * 1、修改入库数量
     * 2、修改已入库数量
     * 3、修改在途库存 在途数量 和 (订单数量)未入库数量
     */
    void savePurchaseStock(long purchaseStockId,boolean flag);


}
