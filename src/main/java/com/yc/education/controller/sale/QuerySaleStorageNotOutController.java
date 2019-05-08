package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.ProductStock;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.util.AppConst;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 销售 - 业务查询 - 销货未出库
 */
@Controller
public class QuerySaleStorageNotOutController extends BaseController implements Initializable {


    @Autowired
    ISaleGoodsService iSaleGoodsService;
    @Autowired
    ISaleGoodsProductService iSaleGoodsProductService;
    @Autowired
    DataSettingService iDataSettingService; // 基本信息
    @Autowired
    private ProductStockService iProductStockService; //产品库存 Service



    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML TableView tableview;

    @FXML TableColumn col_id;
    @FXML TableColumn col_no;
    @FXML TableColumn col_sale_no;
    @FXML TableColumn col_create_date;
    @FXML TableColumn col_customer_no;
    @FXML TableColumn col_customer_name;
    @FXML TableColumn col_payment;      //结算方式
    @FXML TableColumn col_delivery;     //发货状态
    @FXML TableColumn col_state;
    @FXML TableColumn col_operation;

    @FXML TableView tableview_product;

    @FXML TableColumn colp_id;
    @FXML TableColumn colp_no;
    @FXML TableColumn colp_product_no;
    @FXML TableColumn colp_product_name;
    @FXML TableColumn colp_num;
    @FXML TableColumn colp_out_num;
    @FXML TableColumn colp_usable_num;
    @FXML TableColumn colp_stock_num;

    // 订单编号
    private static String  orderid = "";
    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<SaleGoods> SalePurchaseOrderList = iSaleGoodsService.listSaleGoodsByOrderNotOutbound(page, AppConst.ROWS);
        if(SalePurchaseOrderList != null && SalePurchaseOrderList.size() >0){
            PageInfo<SaleGoods> pageInfo = new PageInfo<>(SalePurchaseOrderList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(SalePurchaseOrderList);
        }
    }


    /**
     * 分页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        if(node.getUserData() != null){
            int page =Integer.parseInt(String.valueOf(node.getUserData()));
            setMenuValue(page);
        }
    }

    /**
     * 初始化加载数据
     */
    private void loadData(List<SaleGoods> list ){

        if(list != null){
            List<DataSetting> transportList = iDataSettingService.findDataSetting(30L); // 发货状态
            if(list != null){
                int rows = 1;
                for (SaleGoods p : list) {
                    p.setNo(rows++);
                    p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                    if(transportList != null && transportList.size() >0){
                        for (int i = 1; i <= transportList.size(); i++) {
                            if(p.getCarryMethod() != null && (i+"").equals(p.getCarryMethod().toString())){
                                p.setCarryStr(transportList.get(i-1).getTitle());
                                break;
                            }
                        }
                    }
                    if(p.getOrderAudit() == null || !p.getOrderAudit()){
                        p.setAuditStatus("未审核");
                    }else{
                        p.setAuditStatus("已审核");
                    }
                }
            }

            // Button
            Callback<TableColumn<SaleGoods, String>, TableCell<SaleGoods, String>> buttonFactory
                    = new Callback<TableColumn<SaleGoods, String>, TableCell<SaleGoods, String>>() {
                @Override
                public TableCell call(final TableColumn<SaleGoods, String> param) {
                    final TableCell<SaleGoods, String> cell = new TableCell<SaleGoods, String>() {

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
                                    importOut(selectdIndex);
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
            col_operation.setCellFactory(buttonFactory);

            // 查询客户集合
            final ObservableList<SaleGoods> data = FXCollections.observableArrayList(list);
            col_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_no.setCellValueFactory(new PropertyValueFactory("no"));
            col_sale_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
            col_create_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
            col_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
            col_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
            col_payment.setCellValueFactory(new PropertyValueFactory("payment"));
            col_delivery.setCellValueFactory(new PropertyValueFactory("deliveryStatus"));
            col_state.setCellValueFactory(new PropertyValueFactory("auditStatus"));


            tableview.setItems(data);

            // 选择行 保存数据
            tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleGoods>() {
                @Override
                public void changed(ObservableValue<? extends SaleGoods> observableValue, SaleGoods oldItem, SaleGoods newItem) {
                    if(newItem.getId() != null && !("".equals(newItem.getId())) && newItem.getCreateDate() != null && !("".equals(newItem.getCreateDate()))){

                        List<SaleGoodsProduct> listProduct = iSaleGoodsProductService.listSaleGoodsProduct(newItem.getId().toString());
                        if(listProduct != null){
                            for (int i = 0; i < listProduct.size(); i++) {
                                listProduct.get(i).setNo(i+1);
                                ProductStock productStock = iProductStockService.findProductStockReturnNum(listProduct.get(i).getProductNo());
                                if(productStock != null){
                                    listProduct.get(i).setUsableNum(productStock.getUsablenum()==null?"0":productStock.getUsablenum().toString());
                                    listProduct.get(i).setStockNum(productStock.getStocknum()==null?"0":productStock.getStocknum().toString());
                                }else{
                                    listProduct.get(i).setUsableNum("0");
                                    listProduct.get(i).setStockNum("0");
                                }
                            }
                        }
                        // 查询客户集合
                        final ObservableList<SaleGoodsProduct> data = FXCollections.observableArrayList(listProduct);
                        colp_id.setCellValueFactory(new PropertyValueFactory("id"));
                        colp_no.setCellValueFactory(new PropertyValueFactory("no"));
                        colp_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
                        colp_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
                        colp_num.setCellValueFactory(new PropertyValueFactory("num"));
                        colp_out_num.setCellValueFactory(new PropertyValueFactory("outboundNum"));
                        colp_usable_num.setCellValueFactory(new PropertyValueFactory("usableNum"));
                        colp_stock_num.setCellValueFactory(new PropertyValueFactory("stockNum"));

                        tableview_product.setItems(data);
                    }
                }
            });
        }
    }


    /**
     * 导出 库存 -- 销货出库单
     */
    @FXML
    public void importOut(int index){
        SaleGoods saleGoods = (SaleGoods)tableview.getItems().get(index);
        if(saleGoods != null && !"".equals(saleGoods.getId().toString())){

            if(saleGoods != null && saleGoods.getOrderAudit()){

                StageManager.saleGoods = saleGoods;
                Pane pane1 = StageManager.ORDERCONTENT.get("publicPane");

                pane1.getChildren().setAll(loader.load("/fxml/stock/sale_outbound_order.fxml"));
            }else{
                alert_informationDialog("该单据未审核无法进行导出!");
            }
        }
    }
}
