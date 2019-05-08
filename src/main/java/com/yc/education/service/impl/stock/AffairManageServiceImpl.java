package com.yc.education.service.impl.stock;

import com.yc.education.model.ProductStock;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.model.purchase.TransportationInventory;
import com.yc.education.model.purchase.TransportationProduct;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.purchase.PurchaseOrdersService;
import com.yc.education.service.purchase.PurchaseProductService;
import com.yc.education.service.purchase.TransportationInventoryService;
import com.yc.education.service.purchase.TransportationProductService;
import com.yc.education.service.stock.AffairManageService;
import com.yc.education.service.stock.PurchaseStockProductService;
import com.yc.education.service.stock.PurchaseStockService;
import com.yc.education.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AffairManageServiceImpl
 * @Description TODO  采购入库数量 变动
 * @Author QuZhangJing
 * @Date 2019/4/15 13:40
 * @Version 1.0
 */
@Service
public class AffairManageServiceImpl implements AffairManageService {

    @Autowired
    private PurchaseStockService purchaseStockService;

    @Autowired
    private PurchaseStockProductService purchaseStockProductService;

    @Autowired
    private PurchaseOrdersService purchaseOrdersService;

    @Autowired
    private PurchaseProductService purchaseProductService;

    @Autowired
    private ProductBasicService productBasicService;

    @Autowired
    private ProductStockService productStockService;

    @Autowired
    private TransportationInventoryService transportationInventoryService; //在途库存

    @Autowired
    private TransportationProductService transportationProductService;//在途库存产品



    @Transactional
    public void savePurchaseStock(long purchaseStockId,boolean flag) {

        Map<Boolean,String> result =  new HashMap<Boolean, String>();

        try {
            //采购入库单编号
//        long purchaseStockId =  (long)stockorder.getUserData();
            //采购入库单信息
            PurchaseStock purchaseStock = purchaseStockService.selectByKey(purchaseStockId);

            if(purchaseStock == null) {
                NumberUtil.resultMap.put(false,"系统繁忙");
            }
            //采购入库单产品信息
            List<PurchaseStockProduct> purchaseStockProducts = purchaseStockProductService.findStockProductBypurchaseStockId(purchaseStock.getId());

            if(purchaseStockProducts == null) {
                NumberUtil.resultMap.put(false,"系统繁忙");
            }

            for (PurchaseStockProduct purchaseStockProduct:purchaseStockProducts) {

                //根据库位和产品名称查询产品库存
                ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(" ",purchaseStockProduct.getProname());;

                if(productStock != null && flag && purchaseStockProduct.getDepotnum() !=  null ){
                    productStock.setDepot(purchaseStockProduct.getDepotnum());
                    productStockService.updateNotNull(productStock);

                }else{
                    productStock = productStockService.findProductStockByDepotAndIsnum(purchaseStockProduct.getDepotnum(),purchaseStockProduct.getProname());
                }


                List<TransportationProduct> transportationProduct = transportationProductService.findTransportarionProductByPurchaseOrders(purchaseStockProduct.getSeeorder(),purchaseStockProduct.getProname());

                if(productStock != null){
                    //仓库有该产品库存

                    if(flag){
                        //库存数量
                        productStock.setStocknum(productStock.getStocknum()+purchaseStockProduct.getStocknum());
                        //可用库存
                        productStock.setUsablenum(productStock.getUsablenum()+purchaseStockProduct.getStocknum());

                        if(transportationProduct != null && transportationProduct.size() > 0){
                            //在途库存
                            productStock.setOnthewaynum(productStock.getOnthewaynum() - purchaseStockProduct.getStocknum() );
                        }
                        //采购未入
                        productStock.setPurchasenum(productStock.getPurchasenum() - purchaseStockProduct.getStocknum());
                    }else{
                        //库存数量
                        productStock.setStocknum(productStock.getStocknum()-purchaseStockProduct.getStocknum());
                        //可用库存
                        productStock.setUsablenum(productStock.getUsablenum()-purchaseStockProduct.getStocknum());

                        if(transportationProduct != null && transportationProduct.size() > 0){
                            //在途库存
                            productStock.setOnthewaynum(productStock.getOnthewaynum() + purchaseStockProduct.getStocknum() );
                        }
                        //采购未入
                        productStock.setPurchasenum(productStock.getPurchasenum() + purchaseStockProduct.getStocknum());
                    }


                    productStockService.updateNotNull(productStock);


                }else{

                    //仓库无该产品库存
                    productStock = new ProductStock();
                    //产品编号
                    productStock.setProductorder(purchaseStockProduct.getPronum());
                    //产品名称
                    productStock.setProductname(purchaseStockProduct.getProname());
                    //可用数量
                    productStock.setUsablenum(purchaseStockProduct.getStocknum());
                    //库存数量
                    productStock.setStocknum(purchaseStockProduct.getStocknum());
                    //在途数量
    //                    productStock.setOnthewaynum();
                    productStock.setOnthewaynum(0);
                    //销售未出
                    productStock.setSalenum(0);
                    //销售未入
                    productStock.setBacknum(0);
                    //采购未入
    //                    productStock.setPurchasenum();
                    productStock.setPurchasenum(0);
                    //标准售价
                    productStock.setParprice(0.00);
                    //进价
                    productStock.setPurchaseprice(purchaseStockProduct.getPrice());
                    //库位
                    productStock.setDepot(purchaseStockProduct.getDepotnum());

                    productStockService.save(productStock);

                }


                if("采购订单".equals(purchaseStockProduct.getSourseorder())){

                    //减少订单未入库量 增加已入库量
                    PurchaseOrders purchaseOrders = purchaseOrdersService.findPurchaseByOrders(purchaseStockProduct.getOrders());

                    List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProduct(purchaseOrders.getId());

                    for (PurchaseProduct purchaseProduct:purchaseProducts) {

                        if(purchaseProduct.getProorders().equals(purchaseStockProduct.getProname())){

                            if(flag){
                                //修改采购订单已入库数量
                                purchaseProduct.setStockover(purchaseProduct.getStockover() + purchaseStockProduct.getStocknum());

//                        purchaseProduct.setNum(purchaseProduct.getNum() - purchaseStockProduct.getStocknum() );

                                if(transportationProduct != null && transportationProduct.size()>0){
                                    purchaseProduct.setOntheway(purchaseProduct.getOntheway() - purchaseStockProduct.getStocknum());
                                }
                            }else{
                                //修改采购订单已入库数量
                                purchaseProduct.setStockover(purchaseProduct.getStockover() - purchaseStockProduct.getStocknum());

//                        purchaseProduct.setNum(purchaseProduct.getNum() - purchaseStockProduct.getStocknum() );

                                if(transportationProduct != null && transportationProduct.size()>0){
                                    purchaseProduct.setOntheway(purchaseProduct.getOntheway() + purchaseStockProduct.getStocknum());
                                }
                            }

                            //减少订货数量
//                        purchaseProduct.setNum(purchaseProduct.getNum() - purchaseStockProduct.getStocknum());
                            purchaseProductService.updateNotNull(purchaseProduct);

                        }


                    }



                }else if("在途库存".equals(purchaseStockProduct.getSourseorder())){

                    //修改在途库存  减少已在途数量 和 订货数量    增加已入库数量
                TransportationInventory transportationInventory = transportationInventoryService.findTransportationInventoryByBoxNum(purchaseStock.getBoxnum());

                    List<TransportationProduct> transportationProducts = transportationProductService.findTransportationProductByParentid(transportationInventory.getId());


                    if(transportationProducts != null && transportationProducts.size() > 0){

                        for (TransportationProduct transportationProductModel:transportationProducts) {

                            if(purchaseStockProduct.getProname().equals(transportationProductModel.getPronum())){

                                PurchaseProduct purchaseProduct = purchaseProductService.findPurchaseProductByOrdersAndProductName(transportationProductModel.getPurchaseorder(),transportationProductModel.getPronum());

                                if(flag){
                                    //增加已入库数量
                                    transportationProductModel.setStockover(transportationProductModel.getStockover()+purchaseStockProduct.getStocknum());

                                    //减少订货数量
                                    transportationProductModel.setTotalnum(transportationProductModel.getTotalnum() - purchaseStockProduct.getStocknum());

                                    transportationProductModel.setOntheway(transportationProductModel.getOntheway() - purchaseStockProduct.getStocknum() );

                                    purchaseProduct.setStockover(purchaseProduct.getStockover() + purchaseStockProduct.getStocknum());

                                    purchaseProduct.setOntheway(purchaseProduct.getOntheway() - purchaseStockProduct.getStocknum());

                                }else{

                                    //增加已入库数量
                                    transportationProductModel.setStockover(transportationProductModel.getStockover()-purchaseStockProduct.getStocknum());

                                    //减少订货数量
                                    transportationProductModel.setTotalnum(transportationProductModel.getTotalnum() + purchaseStockProduct.getStocknum());

                                    transportationProductModel.setOntheway(transportationProductModel.getOntheway() + purchaseStockProduct.getStocknum() );


                                    purchaseProduct.setStockover(purchaseProduct.getStockover() - purchaseStockProduct.getStocknum());

                                    purchaseProduct.setOntheway(purchaseProduct.getOntheway() + purchaseStockProduct.getStocknum());

                                }
                                transportationProductService.updateNotNull(transportationProductModel);

                                purchaseProductService.updateNotNull(purchaseProduct);

                            }

                        }

//                       List<TransportationProduct> transportationProductList =  transportationProductService.findTransportarionProductByPurchaseOrders(transportationProducts.get(0).getPurchaseorder());
//
//
//                       for (TransportationProduct transportationProductModel:transportationProductList) {
//                           //减少订货数量
//                           transportationProductModel.setTotalnum(transportationProductModel.getTotalnum() - purchaseStockProduct.getStocknum());
//
//                           transportationProductModel.setOntheway(transportationProductModel.getOntheway() - purchaseStockProduct.getStocknum() );
//
//                           transportationProductService.updateNotNull(transportationProductModel);
//
//                       }


                    }


                }












                //修改产品基本资料库位

                ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(productStock.getProductorder());

                String place = productBasic.getInventoryplace(); //产品所有库位

                String idnum = purchaseStockProduct.getDepotnum(); //库位编号

                int index =  place.indexOf(idnum);

                if(index == -1 && !"".equals(place)){
                    productBasic.setInventoryplace(productBasic.getInventoryplace()+","+idnum);
                }else if(index == -1 && "".equals(place)){
                    productBasic.setInventoryplace(idnum);
                }

                productBasicService.updateNotNull(productBasic);

            }

        } catch (Exception e) {
            result.put(false,"抛出异常，数据进行回滚!");
            NumberUtil.resultMap.put(false,"抛出异常，数据进行回滚!");
            throw new RuntimeException();
        }

    }


}
