package com.yc.education.controller.purchase;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.AccountInputInvoice;
import com.yc.education.service.account.IAccountInputInvoiceService;
import com.yc.education.service.account.IAccountPayableService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import com.yc.education.util.StageManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName PurchaseInvoiceController
 * @Description TODO 应付账款查询
 * @Author QuZhangJing
 * @Date 2018/12/10 15:38
 * @Version 1.0
 */
@Controller
public class PurchaseInvoiceController extends BaseController implements Initializable {

    @Autowired IAccountPayableService iAccountPayableService; //应付账款冲账
    @Autowired IAccountInputInvoiceService iAccountInputInvoiceService; //进项发票


    @FXML
    private TableView tableView1;
    @FXML TableColumn no;
    @FXML
    private TableColumn  supplierOrder; //供应商编号
    @FXML
    private TableColumn  supplierDes; //供应商简称
    @FXML
    private TableColumn  invoiceNumber; //发票号码
    @FXML
    private TableColumn  invoiceDate; //发票日期
    @FXML
    private TableColumn  invoicePrice; //开票金额
    @FXML
    private TableColumn  offsetPrice; //已冲金额
    @FXML
    private TableColumn  meetPrice; //应付金额

    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML
    public  TextField supplierOrderStart ;

    @FXML
    public  TextField supplierOrderEnd ;

    @FXML
    public   TextField invoiceNumberStart ;

    @FXML
    public   TextField invoiceNumberEnd ;

    @FXML
    private DatePicker  invoiceDateStart;

    @FXML
    private DatePicker  invoiceDateEnd;


    private Stage stage = new Stage();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String newStr = location.toString();

        int index = newStr.indexOf("pay.fxml");

        if(index != -1) {

            setMenuValue(1);

        }
    }


    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        String supplierOrders = supplierOrderStart.getText();

        String supplierOrdere = supplierOrderEnd.getText();

        String invoiceNumbers = invoiceNumberStart.getText();

        String invoiceNumbere = invoiceNumberEnd.getText();

        String invoiceDates = invoiceDateStart.getValue() != null?invoiceDateStart.getValue().toString():"";

        String invoiceDatee = invoiceDateEnd.getValue() != null?invoiceDateEnd.getValue().toString():"";
        List<AccountInputInvoice> invoiceList = iAccountInputInvoiceService.listAccountInputInvoiceByDate(invoiceDates, invoiceDatee,supplierOrders, supplierOrdere, invoiceNumbers, invoiceNumbere,page,AppConst.ROWS);
        if(invoiceList != null && invoiceList.size() >0){
            PageInfo<AccountInputInvoice> pageInfo = new PageInfo<>(invoiceList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadPurchaseInvoice(invoiceList);
        }else {
            tableView1.setItems(null);
        }
    }


    /**
     * 分页
     * @param event
     */
    @FXML
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        if(node.getUserData() != null){
            int page =Integer.parseInt(String.valueOf(node.getUserData()));
            setMenuValue(page);
        }
    }


    /**
     * @Description 加载进项发票
     * @Author BlueSky
     * @Date 10:38 2019/4/26
     **/
    public void loadPurchaseInvoice(List<AccountInputInvoice> invoiceList){

        invoiceList.forEach(p->{
            p.setRushMoney("0.00");
            p.setInvoiceDateStr(df.format(p.getCreateDate()));
        });

        supplierOrder.setCellValueFactory(new PropertyValueFactory<>("supplierNo"));
        supplierDes.setCellValueFactory(new PropertyValueFactory<>("supplierNoStr"));
        invoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoceNo"));
        invoiceDate.setCellValueFactory(new PropertyValueFactory<>("invoiceDateStr"));
        invoicePrice.setCellValueFactory(new PropertyValueFactory<>("money"));
        offsetPrice.setCellValueFactory(new PropertyValueFactory<>("rushMoney"));
        meetPrice.setCellValueFactory(new PropertyValueFactory<>("money"));

        tableView1.setItems(FXCollections.observableArrayList(invoiceList));

    }

    /**
     * @Description 搜索按钮
     * @Author BlueSky
     * @Date 10:38 2019/4/26
     **/
    public void seachBtn(){

        setMenuValue(1);
    }

    /**
     * @Description 清除控件值
     * @Author BlueSky
     * @Date 10:37 2019/4/26
     **/
    public  void clearBtn(){
    supplierOrderStart.setText(NumberUtil.NULLSTRING);

    supplierOrderEnd.setText(NumberUtil.NULLSTRING);

    invoiceNumberStart.setText(NumberUtil.NULLSTRING);

    invoiceNumberEnd.setText(NumberUtil.NULLSTRING);

    invoiceDateStart.setValue(null);

    invoiceDateEnd.setValue(null);
    }


    /**
     * @Description 查询更多已开票的 销项发票
     * @Author BlueSky
     * @Date 9:51 2019/4/26
     **/
    @FXML
    public void moreInvoiceOrderBen(){
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("PurchaseInvoiceControllerMoreBen", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_input_invoice_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @Description 查询更多已开票的 销项发票
     * @Author BlueSky
     * @Date 9:51 2019/4/26
     **/
    @FXML
    public void moreInvoiceOrderEnd(){
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("PurchaseInvoiceControllerMoreEnd", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_input_invoice_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * @Description 打开窗口--供应商
     * @Author BlueSky
     * @Date 10:40 2019/4/26
     **/
    @FXML
    public void OpenSupplierQueryBen() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("PurchaseInvoiceControllerSupplierBen", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/supplier_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * @Description 打开窗口--供应商
     * @Author BlueSky
     * @Date 10:40 2019/4/26
     **/
    @FXML
    public void OpenSupplierQueryEnd() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("PurchaseInvoiceControllerSupplierEnd", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/supplier_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
