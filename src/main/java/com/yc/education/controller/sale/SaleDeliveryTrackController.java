package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.customer.CustomerDetailInfo;
import com.yc.education.model.customer.Remark;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.stock.StockOutSale;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.customer.ICustomerDetailInfoService;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.customer.IRemarkService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.service.stock.IStockOutSaleProductService;
import com.yc.education.service.stock.IStockOutSaleService;
import com.yc.education.util.AppConst;
import com.yc.education.util.EditCell;
import com.yc.education.util.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description 销货发货跟踪
 * @Author BlueSky
 * @Date 2018-12-24 18:03
 */
@Controller
public class SaleDeliveryTrackController extends BaseController implements Initializable {

    @Autowired IRemarkService iRemarkService; // 备注
    @Autowired ISaleGoodsService iSaleGoodsService; // 销货单
    @Autowired DataSettingService dataSettingService; // 基本数据
    @Autowired ICustomerService iCustomerService; // 客户
    @Autowired ICustomerDetailInfoService iCustomerDetailInfoService; // 客户详细信息
    @Autowired IStockOutSaleService iStockOutSaleService; // 销货出库单
    @Autowired IStockOutSaleProductService iStockOutSaleProductService; // 销货出库单产品详情

    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML public TextField customer_no; // 客户编号
    @FXML public TextField customer_no_end; // 客户编号
    @FXML public TextField sale_no; // 销售编号
    @FXML public TextField sale_no_end; // 销售编号
    @FXML public DatePicker sale_date; // 销货审核日期
    @FXML public DatePicker sale_date_end; // 销货审核日期
    @FXML public TextField out_warehose; // 出库单号
    @FXML public TextField out_warehose_end; // 出库单号
    @FXML public DatePicker verify_date; // 出库核日期
    @FXML public DatePicker verify_date_end; // 出库核日期


    @FXML TableView order_table; // 订单表格
    @FXML TableColumn col_id; // id
    @FXML TableColumn col_no; // 序号
    @FXML TableColumn col_customer_no; // 客户编号
    @FXML TableColumn col_customer_short; // 客户简称
    @FXML TableColumn col_return; // 需要回传
    @FXML TableColumn col_payment; // 结算方式
    @FXML TableColumn col_sale_no; // 销货单号
    @FXML TableColumn col_sale_audit; // 销货单审核
    @FXML TableColumn col_state; // 发货状态
    @FXML TableColumn col_outwarehouse_no; // 出库单号
    @FXML TableColumn col_outwarehouse_audit; // 出库审核
    @FXML TableColumn col_remark; // 发货备注
    @FXML TableColumn col_cheap; // 经快递校
    @FXML TableColumn col_btn; // 生产快递单
    private static SpringFxmlLoader loader = new SpringFxmlLoader();
    Stage stage = new Stage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        String customerNo = customer_no.getText();
        String customeNoEnd = customer_no_end.getText();
        String saleNo = sale_no.getText();
        String saleNoEnd = sale_no_end.getText();
        String saleDate = sale_date.getValue()==null?"":sale_date.getValue().toString();
        String saleDateEnd = sale_date_end.getValue() == null ? "" : sale_date_end.getValue().toString();
        String outWarehose = out_warehose.getText();
        String outWarehoseEnd = out_warehose_end.getText();
        String verifyDate = verify_date.getValue()==null?"":verify_date.getValue().toString();
        String verifyDateEnd = verify_date_end.getValue() == null ? "" : verify_date_end.getValue().toString();
        List<SaleGoods> goodsList = iSaleGoodsService.listSaleGoodsTrack(page, AppConst.ROWS,customerNo, customeNoEnd, saleNo, saleNoEnd,saleDate,saleDateEnd,outWarehose,outWarehoseEnd, verifyDate, verifyDateEnd);
        if(goodsList != null && goodsList.size() >0){
            PageInfo<SaleGoods> pageInfo = new PageInfo<>(goodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            generalTable(goodsList);
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
     * 查询按钮
     */
    @FXML
    private void queryByCondition(){
        setMenuValue(1);
    }

    /**
     * @Description 加载数据
     * @Author BlueSky
     * @Date 11:49 2019/4/28
     **/
    private void generalTable(List<SaleGoods> goodsList){
        if(goodsList != null){
            int rows = 1;
            for (SaleGoods p : goodsList) {
                p.setNo(rows++);
                Customer customer = iCustomerService.getCustomer(p.getCustomerNo());
                if(customer != null){
                    CustomerDetailInfo info = iCustomerDetailInfoService.getCustomerDetailInfoByCustomerId(customer.getId());
                    if(info != null && info.getSentBack()){
                        p.setPassBackStr("是");
                    }else{
                        p.setPassBackStr("否");
                    }
                }
                if(p.getStockOutSale() != null){
                    if(p.getStockOutSale().getOutboundNo() != null){
                        p.setOutboundNoStr(p.getStockOutSale().getOutboundNo());
                        if(p.getStockOutSale().getOrderAudit() != null && p.getStockOutSale().getOrderAudit()){
                            p.setOutboundNoState("已审核");
                        }else{
                            p.setOutboundNoState("未审核");
                        }
                    }else{
                        p.setOutboundNoStr("暂未出库");
                        p.setOutboundNoState("未审核");
                    }
                }else{
                    p.setOutboundNoStr("暂未出库");
                }
                List<Remark> remarkList = iRemarkService.listRemark(p.getId(),"4");
                if(remarkList != null && remarkList.size() > 0){
                    String remark = "";
                    for (Remark k : remarkList) {
                        remark += k.getRemark()+",";
                    }
                    p.setPaymentStr(remark);
                }
            }
        }

        order_table.setEditable(true);
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
                                importOutExpress(selectdIndex);
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
        col_btn.setCellFactory(buttonFactory);

        final ObservableList<SaleGoods> dataProperty = FXCollections.observableArrayList(goodsList);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_customer_short.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_return.setCellValueFactory(new PropertyValueFactory("passBackStr"));
        col_payment.setCellValueFactory(new PropertyValueFactory("payment"));
        col_sale_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
        col_sale_audit.setCellValueFactory(new PropertyValueFactory("outboundNoState"));
        col_state.setCellValueFactory(new PropertyValueFactory("deliveryStatus"));
        col_outwarehouse_no.setCellValueFactory(new PropertyValueFactory("outboundNoStr"));
        col_outwarehouse_audit.setCellValueFactory(new PropertyValueFactory("outboundNoState"));
//        col_remark.setCellValueFactory(new PropertyValueFactory("paymentStr"));
        col_cheap.setCellValueFactory(new PropertyValueFactory("carryMethod"));

        order_table.setItems(dataProperty);
    }

    /**
     * 清除控件值
     */
    @FXML
    private void clearValue(){
        customer_no.clear();
        customer_no_end.clear();
        sale_no.clear();
        sale_no_end.clear();
        out_warehose.clear();
        out_warehose_end.clear();
        verify_date.setValue(null);
        verify_date_end.setValue(null);
        sale_date.setValue(null);
        sale_date_end.setValue(null);
        order_table.setItems(null);
    }

    /**
     * 导出  -- 快递寄件
     */
    @FXML
    public void importOutExpress(int index){
        SaleGoods saleGoods = (SaleGoods)order_table.getItems().get(index);
        if(saleGoods != null && !"".equals(saleGoods.getId().toString())){
            if(saleGoods.getOutboundNoStr() != null && !"暂未出库".equals(saleGoods.getOutboundNoStr())){
                if(saleGoods != null && saleGoods.getStockOutSale() != null && saleGoods.getStockOutSale().getOrderAudit() !=null && saleGoods.getStockOutSale().getOrderAudit()){
                    StageManager.saleGoods = saleGoods;
                    Pane pane1 = StageManager.ORDERCONTENT.get("publicPane");

                    pane1.getChildren().setAll(loader.load("/fxml/stock/express_mail.fxml"));
                }else{
                    alert_informationDialog("该销货出库单暂未审核无法进行导出!");
                }
            }else {
                alert_informationDialog("该销货单暂未出库，导出快递寄件单失败！");
            }

        }
    }

    /**
     * 打开现有客户
     */
    @FXML
    public void OpenCurrentClientQuery() {

        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleDeliveryTrackControllerBen", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开现有客户
     */
    @FXML
    public void OpenCurrentClientQueryEnd() {

        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleDeliveryTrackControllerEnd", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开销售单据查询窗口
     */
    @FXML
    public void OpenMiniQueryBen() {
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleDeliveryTrackControllerMiniBen", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/sale_goods_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开销售单据查询窗口
     */
    @FXML
    public void OpenMiniQueryEnd() {
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleDeliveryTrackControllerMiniEnd", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/sale_goods_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开销货出库单小窗口
     */
    @FXML
    public void OpenMiniOutboundBen() {
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleDeliveryTrackControllerMiniOutboundBen", this);
        pane.getChildren().setAll(loader.load("/fxml/stock/sale_outbound_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开销货出库单小窗口
     */
    @FXML
    public void OpenMiniOutboundEnd() {
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleDeliveryTrackControllerMiniOutboundEnd", this);
        pane.getChildren().setAll(loader.load("/fxml/stock/sale_outbound_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

}
