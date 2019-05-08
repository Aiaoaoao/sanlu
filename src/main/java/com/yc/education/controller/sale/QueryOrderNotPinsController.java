package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.ProductStock;
import com.yc.education.model.sale.SalePurchaseOrder;
import com.yc.education.model.sale.SalePurchaseOrderProduct;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.sale.*;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
 * 销售 - 业务查询 - 订货未销
 */
@Controller
public class QueryOrderNotPinsController extends BaseController implements Initializable {

    @Autowired ISalePurchaseOrderService iSalePurchaseOrderService;
    @Autowired ISalePurchaseOrderProductService iSalePurchaseOrderProductService;
    @Autowired private ProductStockService iProductStockService; //产品库存 Service


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页


    @FXML TableView tab_product;

    @FXML TableColumn col_id;
    @FXML TableColumn col_no;
    @FXML TableColumn col_order_no;
    @FXML TableColumn col_date;
    @FXML TableColumn col_customer_no;
    @FXML TableColumn col_customer_short;
    @FXML TableColumn col_operate;

    @FXML TableView tab_product_info;

    @FXML TableColumn col_info_id;
    @FXML TableColumn col_info_no;
    @FXML TableColumn col_info_product_no;
    @FXML TableColumn col_info_product_name;
    @FXML TableColumn col_info_order_num;
    @FXML TableColumn col_info_sale_num;
    @FXML TableColumn col_info_available;
    @FXML TableColumn col_info_storage;
    @FXML TableColumn col_info_price;
    @FXML TableColumn col_info_remark;


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
        List<SalePurchaseOrder> SalePurchaseOrderList = iSalePurchaseOrderService.listSalePurchaseOrderByOrderNotPins(page, AppConst.ROWS);
        if(SalePurchaseOrderList != null && SalePurchaseOrderList.size() >0){
            PageInfo<SalePurchaseOrder> pageInfo = new PageInfo<>(SalePurchaseOrderList);
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
    private void loadData(List<SalePurchaseOrder> list){
        if(list != null){
            int rows = 1;
            for (SalePurchaseOrder p : list) {
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                p.setNo(rows++);
            }
        }

        // Button
        Callback<TableColumn<SalePurchaseOrder, String>, TableCell<SalePurchaseOrder, String>> buttonFactory
                = new Callback<TableColumn<SalePurchaseOrder, String>, TableCell<SalePurchaseOrder, String>>() {
            @Override
            public TableCell call(final TableColumn<SalePurchaseOrder, String> param) {
                final TableCell<SalePurchaseOrder, String> cell = new TableCell<SalePurchaseOrder, String>() {

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
                                // 导出
                                importOut(selectdIndex);

                            });
                            btn1.setMaxWidth(50);
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

        // 查询客户集合
        final ObservableList<SalePurchaseOrder> data = FXCollections.observableArrayList(list);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_customer_short.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_operate.setCellFactory(buttonFactory);

        tab_product.setItems(data);

        // 选择行 保存数据
        tab_product.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalePurchaseOrder>() {
            @Override
            public void changed(ObservableValue<? extends SalePurchaseOrder> observableValue, SalePurchaseOrder oldItem, SalePurchaseOrder newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId())) && newItem.getCreateDate() != null && !("".equals(newItem.getCreateDate()))){

                    List<SalePurchaseOrderProduct> listProduct = iSalePurchaseOrderProductService.listPurchaseOrderProduct(newItem.getId().toString());
                    if(listProduct != null){
                        for (int i = 0; i < listProduct.size(); i++) {
                            listProduct.get(i).setNo(i+1);
                            ProductStock productStock = iProductStockService.findProductStockReturnNum(listProduct.get(i).getProductNo());
                            if(productStock != null){
                                listProduct.get(i).setUsableNum(productStock.getUsablenum()==null?"0":productStock.getUsablenum().toString());
                                listProduct.get(i).setIfstock(productStock.getStocknum()==null?"0":productStock.getUsablenum().toString());
                            }
                        }
                    }
                    // 查询客户集合
                    final ObservableList<SalePurchaseOrderProduct> data = FXCollections.observableArrayList(listProduct);
                    col_info_id.setCellValueFactory(new PropertyValueFactory("id"));
                    col_info_no.setCellValueFactory(new PropertyValueFactory("no"));
                    col_info_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
                    col_info_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
                    col_info_order_num.setCellValueFactory(new PropertyValueFactory("num"));
                    col_info_sale_num.setCellValueFactory(new PropertyValueFactory("saleNum"));
                    col_info_available.setCellValueFactory(new PropertyValueFactory("usableNum"));
                    col_info_storage.setCellValueFactory(new PropertyValueFactory("ifstock"));
                    col_info_price.setCellValueFactory(new PropertyValueFactory("price"));
                    col_info_remark.setCellValueFactory(new PropertyValueFactory("remark"));

                    tab_product_info.setItems(data);
                }
            }
        });
    }

    /**
     * 导出 订货未销 导出到 销货单
     */
    @FXML
    public void importOut(int index){
        SalePurchaseOrder purchaseOrder = (SalePurchaseOrder)tab_product.getItems().get(index);
        if(purchaseOrder != null && !"".equals(purchaseOrder.getId().toString())){
            StageManager.salePurchaseOrder = purchaseOrder;
            if(purchaseOrder != null && purchaseOrder.getOrderAudit()){

                Pane pane1 = StageManager.ORDERCONTENT.get("SalePane");

                pane1.getChildren().setAll(loader.load("/fxml/sale/sale_slip.fxml"));
            }else{
                alert_informationDialog("该单据未审核无法进行导出!");
            }
        }
    }
}
