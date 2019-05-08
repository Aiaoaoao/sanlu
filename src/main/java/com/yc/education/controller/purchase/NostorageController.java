package com.yc.education.controller.purchase;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.purchase.NostorageProperty;
import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.service.purchase.PurchaseOrdersService;
import com.yc.education.service.purchase.PurchaseProductService;
import com.yc.education.util.AppConst;
import com.yc.education.util.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName NostorageController
 * @Description TODO 业务查询---采购未入库
 * @Author QuZhangJing
 * @Date 2018/10/19 11:22
 * @Version 1.0
 */
@Controller
public class NostorageController extends BaseController implements Initializable {

    @Autowired
    private PurchaseProductService purchaseProductService;//采购产品

    @Autowired
    private PurchaseOrdersService purchaseOrdersService;//采购订单

    @FXML private TableView findPurchaseView;
    @FXML private TableColumn purchaseorder;//采购单号
    @FXML private TableColumn createdate;   //制单日期
    @FXML private TableColumn comedate;     //预计到货日期
    @FXML private TableColumn supplierid;   //供应商编号
    @FXML private TableColumn supplierdes;  //供应商简称
    @FXML private TableColumn proid;        //产品编号
    @FXML private TableColumn proname;      //产品名称
    @FXML private TableColumn ordernum;     //订单数量
    @FXML private TableColumn stocknum;     //已入库数
    @FXML private TableColumn seeorder;     //参考单号
    @FXML private TableColumn overnum;      //结案数量
    @FXML private TableColumn onthewaynum;  //在途数量
    @FXML private TableColumn unpassnum;    //未发数量
    @FXML private TableColumn operation;    //操作


    @FXML private TextField ordersums; //订单数量

    @FXML private TextField stocksums; //已入库数

    @FXML private TextField forcesums; //结案数量

    @FXML private TextField onthewaysums; //在途数量

    @FXML private TextField unpasssums;  //未发数量



    @FXML private VBox purchase_find_fast;
    @FXML private VBox purchase_find_prev;
    @FXML private VBox purchase_find_next;
    @FXML private VBox purchase_find_last;


    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    //订单数量、已入库数、结案数量、在途数量、未发数量
    private int ordersum=0,overstock=0,forcesum=0,onthewaysum=0,unpasssum=0;

    //数据绑定
    private ObservableList<NostorageProperty> nostorageProperties = FXCollections.observableArrayList();



    public void findDepotPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        LoadData(pageNum);
    }

    public void LoadData(int pageNum){

         List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProductNotStock(pageNum,21);


        PageInfo<PurchaseProduct> pageInfo = new PageInfo<>(purchaseProducts);

        purchase_find_fast.setUserData(1); //首页

        purchase_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        purchase_find_next.setUserData(pageInfo.getNextPage());//下一页

        purchase_find_last.setUserData(pageInfo.getPages());//尾页


        // Button
        javafx.util.Callback<TableColumn<PurchaseProduct, String>, TableCell<PurchaseProduct, String>> buttonFactory
                = new Callback<TableColumn<PurchaseProduct, String>, TableCell<PurchaseProduct, String>>() {
            @Override
            public TableCell call(final TableColumn<PurchaseProduct, String> param) {
                final TableCell<PurchaseProduct, String> cell = new TableCell<PurchaseProduct, String>() {

                    final Button btn1 = new Button("导出");

                    @Override
                    public void updateItem(String ite, boolean empty) {
                        super.updateItem(ite, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn1.setOnAction((ActionEvent t)
                                    -> {
                                int selectdIndex = getTableRow().getIndex();
                                // 导出到库存异动单
                                exportPurchaseStock(selectdIndex);
                            });
                            btn1.setMaxWidth(40);
                            btn1.setTextFill(Color.WHITE);
                            setGraphic(btn1);
                            setText(null);
                        }
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        };
        operation.setCellFactory(buttonFactory);




        purchaseorder.setCellValueFactory(new PropertyValueFactory("orders"));
        createdate.setCellValueFactory(new PropertyValueFactory("createdate"));
        comedate.setCellValueFactory(new PropertyValueFactory("comedate"));
        supplierid.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        supplierdes.setCellValueFactory(new PropertyValueFactory("supplierdes"));
        proid.setCellValueFactory(new PropertyValueFactory("proid"));
        proname.setCellValueFactory(new PropertyValueFactory("proname"));
        ordernum.setCellValueFactory(new PropertyValueFactory("ordernum"));
        stocknum.setCellValueFactory(new PropertyValueFactory("stocknum"));
        seeorder.setCellValueFactory(new PropertyValueFactory("seeorder"));
        overnum.setCellValueFactory(new PropertyValueFactory("overnum"));
        onthewaynum.setCellValueFactory(new PropertyValueFactory("onthewaynum"));
        unpassnum.setCellValueFactory(new PropertyValueFactory("unpassnum"));
        operation.setCellValueFactory(new PropertyValueFactory("operation"));


        ordersum = 0;

        overstock = 0;

        forcesum = 0;

        onthewaysum = 0;

        unpasssum = 0;

        nostorageProperties.clear();

        if(purchaseProducts.size()>0){
                for(PurchaseProduct purchaseProduct : purchaseProducts){

//                    int unpass =  purchaseProduct.getNum()-purchaseProduct.getStockover()-purchaseProduct.getForcenum()-purchaseProduct.getOntheway();
                    //未发数量
                    int unpass =  purchaseProduct.getNum()-purchaseProduct.getStockover()-purchaseProduct.getOntheway()- purchaseProduct.getForcenum();

                    ordersum += purchaseProduct.getNum();

                    overstock += purchaseProduct.getStockover();

                    forcesum += purchaseProduct.getForcenum();

                    onthewaysum += purchaseProduct.getOntheway();

                    unpasssum += unpass;

                    NostorageProperty nostorageProperty = new NostorageProperty(purchaseProduct.getPurchaseOrders().getOrders(),
                            new SimpleDateFormat("yyyy-MM-dd").format(purchaseProduct.getPurchaseOrders().getCreatedate()),
                            new SimpleDateFormat("yyyy-MM-dd").format(purchaseProduct.getPurchaseOrders().getComedate()),
                            purchaseProduct.getPurchaseOrders().getSuppliernum(),
                            purchaseProduct.getPurchaseOrders().getSupplierdes(),
                            purchaseProduct.getProorders(),
                            purchaseProduct.getProname(),
                            purchaseProduct.getNum(),
                            purchaseProduct.getStockover(),
                            purchaseProduct.getPurchaseOrders().getSeeorders(),
                            purchaseProduct.getForcenum(),
                            purchaseProduct.getOntheway(),
                            unpass,
                            "操作"
                            );
                    nostorageProperties.add(nostorageProperty);
                }
        }

        ordersums.setText(ordersum+"");
        stocksums.setText(overstock+"");
        forcesums.setText(forcesum+"");
        onthewaysums.setText(onthewaysum+"");
        unpasssums.setText(unpasssum+"");

        findPurchaseView.setItems(nostorageProperties);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadData(1);
    }


    /**
     * 导出采购订单
     */
    public void exportPurchaseStock(int selectdIndex){

//        alert_informationDialog(""+selectdIndex);



        for (int i=0,len = nostorageProperties.size();i<len;i++) {
            if(selectdIndex == i){
//                alert_informationDialog(""+nostorageProperties.get(i).getProname());

                PurchaseOrders purchaseOrders =  purchaseOrdersService.findPurchaseByOrders(nostorageProperties.get(i).getOrders());


                if(purchaseOrders != null){
                    if(purchaseOrders.getShstatus() == 1){

                        changeHomeBtn(2,3,1);

                        StageManager.purchaseOrders =purchaseOrders;

                        StageManager.purchaseProducts =  purchaseProductService.findPurchaseProduct(purchaseOrders.getId());

                        Pane pane1 = StageManager.ORDERCONTENT.get("publicPane");

                        pane1.getChildren().setAll(loader.load("/fxml/stock/purchase_stock.fxml"));
                    }else{
                        alert_informationDialog(AppConst.ALERT_EXAMINE);
                    }
                }else{
                    alert_informationDialog("暂未单据导出!");
                }


            }
        }


    }


}
