package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.service.sale.ISaleReturnGoodsProductService;
import com.yc.education.service.sale.ISaleReturnGoodsService;
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
 * 业务查询 - 销退未入库
 */
@Controller
public class QuerySaleReturnNotInController extends BaseController implements Initializable {


    @Autowired
    ISaleReturnGoodsService iSaleReturnGoodsService; // 销退
    @Autowired
    ISaleReturnGoodsProductService iSaleReturnGoodsProductService; //销退产品
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
    @FXML TableColumn col_order_no; // 订单编号
    @FXML TableColumn col_date; // 退货日期
    @FXML TableColumn col_customer_no; // 客户编号
    @FXML TableColumn col_customer_name; // 客户姓名
    @FXML TableColumn col_reason; // 退货原因
    @FXML TableColumn col_operation; // 操作

    @FXML TableView tableview_product;

    @FXML TableColumn colp_id;
    @FXML TableColumn colp_no;
    @FXML TableColumn colp_product_no; // 产品编号
    @FXML TableColumn colp_product_name; // 产品名称
    @FXML TableColumn colp_return_num; // 退货数量
    @FXML TableColumn colp_stock_num;  //入库数量

    SpringFxmlLoader loader = new SpringFxmlLoader();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<SaleReturnGoods> saleReturnGoodsList = iSaleReturnGoodsService.listSaleGoodsByOrderNotInbound(page, AppConst.ROWS);
        if(saleReturnGoodsList != null && saleReturnGoodsList.size() >0){
            PageInfo<SaleReturnGoods> pageInfo = new PageInfo<>(saleReturnGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(saleReturnGoodsList);
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
    private void loadData(List<SaleReturnGoods> list){
        if(list != null){
            int rows = 1;
            for (SaleReturnGoods p : list) {
                p.setNo(rows++);
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
            }
        }

        // Button
        Callback<TableColumn<SaleReturnGoods, String>, TableCell<SaleReturnGoods, String>> buttonFactory
                = new Callback<TableColumn<SaleReturnGoods, String>, TableCell<SaleReturnGoods, String>>() {
            @Override
            public TableCell call(final TableColumn<SaleReturnGoods, String> param) {
                final TableCell<SaleReturnGoods, String> cell = new TableCell<SaleReturnGoods, String>() {

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
                                exportStockChangeOrder(((SaleReturnGoods)tableview.getItems().get(selectdIndex)));
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
        final ObservableList<SaleReturnGoods> data = FXCollections.observableArrayList(list);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("pinbackNo"));
        col_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_reason.setCellValueFactory(new PropertyValueFactory("returnReason"));

        tableview.setItems(data);

        // 选择行
        tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleReturnGoods>() {
            @Override
            public void changed(ObservableValue<? extends SaleReturnGoods> observableValue, SaleReturnGoods oldItem, SaleReturnGoods newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId())) && newItem.getCreateDate() != null && !("".equals(newItem.getCreateDate()))){

                    List<SaleReturnGoodsProduct> listProduct = iSaleReturnGoodsProductService.listReturnGoodsProduct(newItem.getId().toString());
                    int rows = 1;
                    for (SaleReturnGoodsProduct p : listProduct) {
                        p.setNo(rows++);
                    }
                    // 查询客户集合
                    final ObservableList<SaleReturnGoodsProduct> data = FXCollections.observableArrayList(listProduct);
                    colp_id.setCellValueFactory(new PropertyValueFactory("id"));
                    colp_no.setCellValueFactory(new PropertyValueFactory("no"));
                    colp_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
                    colp_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
                    colp_return_num.setCellValueFactory(new PropertyValueFactory("num"));
                    colp_stock_num.setCellValueFactory(new PropertyValueFactory("inboundNum"));

                    tableview_product.setItems(data);
                }
            }
        });
    }

    /**
     * 销货退货单  导出  --库存异动作业
     */
    public void exportStockChangeOrder(SaleReturnGoods saleReturnGoods){

        if(saleReturnGoods != null){


            if(saleReturnGoods.getOrderAudit()){

                StageManager.saleReturnGoods = saleReturnGoods;

                Pane pane1 = StageManager.ORDERCONTENT.get("publicPane");

                pane1.getChildren().setAll(loader.load("/fxml/stock/stock_change.fxml"));

            }else{
                alert_informationDialog(AppConst.ALERT_EXAMINE);
            }

        }else{
            alert_informationDialog("暂未单据导出!");
        }


    }
}
