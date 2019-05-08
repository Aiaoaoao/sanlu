package com.yc.education.controller.sale;


import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.customer.CustomerDetailInfo;
import com.yc.education.model.customer.InvoiceProperty;
import com.yc.education.model.customer.ShippingAddressProperty;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.account.IAccountSaleInvoiceInfoService;
import com.yc.education.service.account.IAccountSaleInvoiceService;
import com.yc.education.service.customer.ICustomerDetailInfoService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.util.EditCell;
import com.yc.education.util.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * 销货单状态更新
 */
@Controller
public class SaleReturnStatusController extends BaseController implements Initializable {

    @Autowired ISaleGoodsService iSaleGoodsService;
    @Autowired IAccountSaleInvoiceService iAccountSaleInvoiceService;
    @Autowired IAccountSaleInvoiceInfoService iAccountSaleInvoiceInfoService;
    @Autowired ICustomerDetailInfoService iCustomerDetailInfoService;
    @Autowired DataSettingService iDataSettingService;

    @FXML public TextField customer_no; // 客户编号
    @FXML public TextField customer_short; // 客户简称称
    @FXML ComboBox audit_status;
    @FXML ComboBox delivery_status;
    @FXML ComboBox financial_status;
    @FXML ComboBox rotary_status;
    @FXML DatePicker made_date_ben;
    @FXML DatePicker made_date_end;

    @FXML TableView tab_product;

    @FXML TableColumn col_id;
    @FXML TableColumn col_no;
    @FXML TableColumn col_date;
    @FXML TableColumn col_customer_no;
    @FXML TableColumn col_customer_short;
    @FXML TableColumn col_need_back;
    @FXML TableColumn col_payment;
    @FXML TableColumn col_back;
    @FXML TableColumn col_back_date;
    @FXML TableColumn col_financial;
    @FXML TableColumn col_order_status;
    @FXML TableColumn col_delivery;

    @FXML VBox menu_clearAll;
    @FXML VBox menu_commit;
    @FXML VBox menu_printing;

    Stage stage = new Stage();

    /**
     * 清除控件值
     */
    @FXML
    public void clearValue(){
        tab_product.setItems(null);

        made_date_ben.setValue(null);
        made_date_ben.setValue(null);
        customer_no.clear();
        customer_short.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        audit_status.getItems().addAll("全部","已审核","未审核"); // 审核状态
        audit_status.getSelectionModel().select(0);
        financial_status.getItems().addAll("全部","已收款","未收款"); //财务复核状态
        financial_status.getSelectionModel().select(0);
        rotary_status.getItems().addAll("全部","需回传","不回传"); //回传状态
        rotary_status.getSelectionModel().select(0);

        List<DataSetting> stringList =  iDataSettingService.findDataSetting(1L); // 发货状态'
        ObservableList<String> comboBoxList = FXCollections.observableArrayList();
        comboBoxList.add("全部");
        stringList.forEach(p->comboBoxList.add(p.getTitle()));
        delivery_status.getItems().setAll(comboBoxList);
        delivery_status.getSelectionModel().select(0);


        //权限管理   销货单状态更新  修改权限
        if(!getPermissions("41_273_3")){
                menu_commit.setDisable(true);
                menu_clearAll.setDisable(true);
        }
        //权限管理   销货单状态更新  打印权限
        if(!getPermissions("41_275_7")){
            menu_printing.setDisable(true);
        }

    }

    /**
     * 提交订单、保存数据
     */
    @FXML
    public synchronized  void save(){
        List<SaleGoods> goodsList = tab_product.getItems();
        if(goodsList != null){
            goodsList.forEach(p->{
                if(p.getId() != null){
                    iSaleGoodsService.updateAll(p);
                }
            });
        }
    }

    /**
     * 销货状态查询
     */
    @FXML
    private void selectByWhere(){
        String customerno = customer_no.getText();
        LocalDate bentime = made_date_ben.getValue();
        LocalDate endtime = made_date_end.getValue();
        String bentimeStr = "";
        String endtimeStr = "";
        if(customerno == null){
            customerno = "";
        }
        if(bentime != null){
            bentimeStr = bentime.toString();
        }
        if(endtime != null){
            endtimeStr = endtime.toString();
        }
        String verifystate = audit_status.getSelectionModel().getSelectedItem().toString();
        if("全部".equals(verifystate)){
            verifystate = "";
        }else if("已审核".equals(verifystate)){
            verifystate = "1";
        }else{
            verifystate = "0";
        }
        String deliveryState = delivery_status.getItems() == null ? "":delivery_status.getSelectionModel().getSelectedItem().toString();
        String financialState = financial_status.getItems() == null ? "":financial_status.getSelectionModel().getSelectedItem().toString();
        String sendBackState = rotary_status.getItems() == null ? "":rotary_status.getSelectionModel().getSelectedItem().toString();
        if("全部".equals(deliveryState)){
            deliveryState = "";
        }
        if("全部".equals(financialState)){
            financialState = "";
        }
        if("全部".equals(sendBackState)){
            sendBackState = "";
        }
        List<SaleGoods> list = iSaleGoodsService.listSaleGoodsByStates(customerno, bentimeStr, endtimeStr, verifystate, deliveryState, financialState, sendBackState);
        tab_product.getItems().clear();
        if(list != null){
            generalProductTab(FXCollections.observableArrayList(list));
        }
    }


    /**
     * 给产品tableview加载数据
     * @param productPropertyList
     */
    private void generalProductTab(ObservableList<SaleGoods> productPropertyList){
        tab_product.setEditable(true);

        col_back_date.setCellFactory(column -> EditCell.createStringEditCell());
        // checkbox
        Callback<TableColumn<InvoiceProperty, Boolean>, TableCell<InvoiceProperty, Boolean>> generalCheckboxFactory
                = new Callback<TableColumn<InvoiceProperty, Boolean>, TableCell<InvoiceProperty, Boolean>>() {
            @Override
            public TableCell call(final TableColumn<InvoiceProperty, Boolean> param) {
                final TableCell<Customer, Boolean> cell = new TableCell<Customer, Boolean>() {

                    final CheckBox checkBox = new CheckBox("已回");

                    @Override
                    public void updateItem(Boolean ite, boolean empty) {
                        if (ite != null) {
                            checkBox.setSelected(ite);
                        }
                        super.updateItem(ite, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            checkBox.setOnAction((ActionEvent t)
                                    -> {
                                int selectdIndex = getTableRow().getIndex();
                                List<SaleGoods> propertyList = tab_product.getItems();
                                SaleGoods saleGoods = (SaleGoods) tab_product.getItems().get(selectdIndex);
                                if (saleGoods != null && propertyList != null) {
                                    // 如果回传 是没有任何值的时候 默认不勾选
                                    if(saleGoods.getPassBack() == null){
                                        saleGoods.setPassBack(false);
                                    }
                                    propertyList.get(selectdIndex).setPassBack(!saleGoods.getPassBack());
                                    if(!saleGoods.getPassBack()){
                                        propertyList.get(selectdIndex).setPassBackDate(null);
                                    }else{
                                        propertyList.get(selectdIndex).setPassBackDate(new Date());
                                    }
                                    generalProductTab(FXCollections.observableArrayList(propertyList));
                                }
                            });
                            checkBox.setMaxWidth(Double.MAX_VALUE);
                            setGraphic(checkBox);
                            setText(null);
                        }
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        };
        col_back.setCellFactory(generalCheckboxFactory);
        final ObservableList<SaleGoods> dataProperty = FXCollections.observableArrayList(productPropertyList);


        productPropertyList.forEach(p->{
            if(p.getSentBack() != null && p.getSentBack()){
                p.setPassBackStr("是");
            }else {
                p.setPassBackStr("否");
            }
            p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
            if(p.getPassBackDate() != null){
                p.setPassBackDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getPassBackDate()));
            }else{
                p.setPassBackDateStr(null);
            }
            if(p.getOrderAudit() != null && p.getOrderAudit()){
                p.setAuditStatus("已审核");
            }else{
                p.setAuditStatus("未审核");
            }
        });

        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
        col_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_customer_short.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_need_back.setCellValueFactory(new PropertyValueFactory("passBackStr"));
        col_payment.setCellValueFactory(new PropertyValueFactory("payment"));
        col_back.setCellValueFactory(new PropertyValueFactory("passBack"));
        col_back_date.setCellValueFactory(new PropertyValueFactory("passBackDateStr"));
        col_financial.setCellValueFactory(new PropertyValueFactory("financial"));
        col_order_status.setCellValueFactory(new PropertyValueFactory("auditStatus"));
        col_delivery.setCellValueFactory(new PropertyValueFactory("deliveryStatus"));

        tab_product.setItems(dataProperty);
    }


    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO  打开窗口--现有客户查询
     * @Date 20:22 2018/8/15
     * @Param []
     **/
    @FXML
    public void OpenCurrentClientQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();

        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleReturnStatusController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

}
