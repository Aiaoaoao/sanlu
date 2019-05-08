package com.yc.education.controller.account;

import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.util.AppConst;
import com.yc.education.util.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 销项成本明细
 * @Author BlueSky
 * @Date 2019-02-13 10:36
 */
@Controller
public class QueryAccountSaleCostController extends BaseController implements Initializable {

    @Autowired DataSettingService iDataSettingService;     //基础数据
    @Autowired ISaleGoodsService iSaleGoodsService;     //销售单
    @Autowired ISaleGoodsProductService iSaleGoodsProductService;     //销售单详情

    @FXML Pane pane;
    @FXML RadioButton radio_goods;
    @FXML RadioButton radio_product;

    public @FXML TextField sale_no;
    public @FXML TextField sale_no_end;
    public @FXML DatePicker sale_date;
    public @FXML DatePicker sale_date_end;
    public @FXML TextField customer_no;
    public @FXML TextField customer_no_end;
    public @FXML TextField product_no;
    public @FXML TextField product_no_end;
    public @FXML ComboBox product_category;
    public @FXML ComboBox product_category_end;

    @FXML TableView tableview_order;
    @FXML TableColumn col_id;
    @FXML TableColumn col_no;
    @FXML TableColumn col_sale_no;
    @FXML TableColumn col_create_date;
    @FXML TableColumn col_customer_name;


    private Stage stage = new Stage();
    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 单选按钮
         ToggleGroup group = new ToggleGroup();
        pane.getChildren().clear();
        radio_goods.setToggleGroup(group);
        radio_product.setToggleGroup(group);
        pane.getChildren().add(radio_goods);
        pane.getChildren().add(radio_product);

        // 加载下拉框数据
        setComboBox(6L, product_category, 0);
        setComboBox(6L, product_category_end, 0);
    }

    /**
     * 查询按钮
     */
    @FXML
    public void queryButton(){
        tableview_order.setItems(null);
        String saleno = sale_no.getText();
        String salenoEnd = sale_no_end.getText();
        String saledate = sale_date.getValue()==null?null:sale_date.getValue().toString();
        String saledateEnd = sale_date_end.getValue()==null?null:sale_date_end.getValue().toString();
        String customerno = customer_no.getText();
        String customernoEnd = customer_no_end.getText();
        String productno = product_no.getText();
        String productnoEnd = product_no_end.getText();
        String productcategory = product_category.getSelectionModel().getSelectedItem()==null?null:product_category.getSelectionModel().getSelectedItem().toString();
        String productcategoryEnd = product_category_end.getSelectionModel().getSelectedItem()==null?null:product_category_end.getSelectionModel().getSelectedItem().toString();
        List<SaleGoods> saleGoodsList = iSaleGoodsService.listSaleGoodsBySaleCost(saledate, saledateEnd, saleno, salenoEnd, customerno, customernoEnd, productno, productnoEnd, productcategory, productcategoryEnd);

        generalTable(saleGoodsList);
    }


    /**
     * 加载表格数据
     * @param list
     */
    public void generalTable(List<SaleGoods> list){
        if(list !=null){
            int rows = 1;
            for (SaleGoods p : list) {
                if(p.getCreateDate()!=null){
                    p.setNo(rows++);
                    p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                }
            }
            ObservableList<SaleGoods> data = FXCollections.observableArrayList(list);
            col_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_no.setCellValueFactory(new PropertyValueFactory("no"));
            col_sale_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
            col_create_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
            col_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
            tableview_order.setItems(data);
        }
    }

    /**
     * 清除控件上的值
     */
    public void clearValue(){
        sale_no.clear();
        sale_no_end.clear();
        sale_date.setValue(null);
        sale_date_end.setValue(null);
        customer_no.clear();
        customer_no_end.clear();
        product_no.clear();
        product_no_end.clear();
        product_category.getSelectionModel().selectFirst();
        product_category_end.getSelectionModel().selectFirst();
        tableview_order.setItems(null);
    }

    /**
     * 现有产品查询
     */
    @FXML
    public  void moreProductButton(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountSaleCostControllerProduct", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/product_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 现有产品查询
     */
    @FXML
    public  void moreProductButtonEnd(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountSaleCostControllerProductEnd", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/product_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 现有客户查询
     */
    @FXML
    public void CurrentClientButton() {
        Stage stage = new Stage();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountSaleCostControllerCustomer", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 现有客户查询
     */
    @FXML
    public void CurrentClientButtonEnd() {
        Stage stage = new Stage();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountSaleCostControllerCustomerEnd", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开销货单查询窗口
     */
    @FXML
    public void moreSaleGoods() {
        SpringFxmlLoader loader = new SpringFxmlLoader();

        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountSaleCostControllerSaleGoods", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/sale_goods_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开销货单查询窗口
     */
    @FXML
    public void moreSaleGoodsEnd() {
        SpringFxmlLoader loader = new SpringFxmlLoader();

        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountSaleCostControllerSaleGoodsEnd", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/sale_goods_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开预览窗口
     */
    @FXML
    @SneakyThrows
    public void openWebviewWindow() {
        Map<String, Object> rptParameters = new HashMap<String, Object>();
        rptParameters.put("myParam_title", "上海三禄贸易有限公司");
        rptParameters.put("myParam_category", "销售合同");

        URL url = this.getClass().getResource("/jasper/sale_goods.jasper");

        String str = url==null?null:url.toString().substring(url.toString().indexOf(':')+1, url.toString().length());
        String filePath = str.substring(0, str.lastIndexOf("/"));
        String fileName = str.substring(str.lastIndexOf("/"), str.length()).split("\\.")[0];
        //传入报表源文件绝对路径，外部参数对象，DB连接，得到JasperPring对象
        JasperPrint jasperPrint = JasperFillManager.fillReport(str, rptParameters, AppConst.getConnection());

        //导出HTML文件
        JasperExportManager.exportReportToHtmlFile(jasperPrint, filePath+fileName+".html");

        stage.setTitle("销售合同");
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load(this.getClass().getResource("/jasper"+fileName+".html").toExternalForm());

        Scene scene = new Scene(webView, 10, 10, Color.web("lightgray"));

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开webview窗口
     */
    @FXML
    @SneakyThrows
    private void openHtmlWindow(){

        // 打开 Webview
        stage.setTitle("Web View Load HTML");
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

//      webEngine.load(this.getClass().getResource("../../../../../html/lineChart.html").toExternalForm());
        webEngine.load(this.getClass().getResource("/html/test.html").toExternalForm());

        Scene scene = new Scene(webView, 10, 10, Color.web("lightgray"));

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * jasport导出测试
     */
    @SneakyThrows
    private void exportTest(){
        //设定报表所需要的外部参数内容
        Map<String, Object> rptParameters = new HashMap<String, Object>();
        rptParameters.put("myParam_title", "上海三禄贸易有限公司");
        rptParameters.put("myParam_category", "销售合同");

        //传入报表源文件绝对路径，外部参数对象，DB连接，得到JasperPring对象
//            JasperPrint jasperPrint = JasperFillManager.fillReport(this.getClass().getResource("../../../../../jasper/test_salegoods.jasper").toString(), rptParameters, AppConst.getConnection());

        JasperPrint jasperPrint = JasperFillManager.fillReport("/F:/BlueSky/Work/Workspace/sanlu/target/classes/jasper/test_salegoods.jasper", rptParameters, AppConst.getConnection());

        //导出PDF文件
        JasperExportManager.exportReportToPdfFile(jasperPrint,"F:/test.pdf");

        //导入HTML文件
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "F:/test.html");

        //执行结束
        System.out.println("Export success!!");
    }

    /**
     * 导出
     */
    @FXML
    @SneakyThrows
    private void exportExcel(){
        // 通过URL获取模板
        File reportTemplate = new File("/F:/BlueSky/Work/Workspace/sanlu/target/classes/jasper/sale_goods.jasper");
        // 设置文件路径与名称
        String filePath = reportTemplate.getParent();
        String fileName = reportTemplate.getName().split("\\.")[0];
        // 初始化传入iReport模板的Map参数
        Map<String, Object> rptParameters = new HashMap<String, Object>();
        rptParameters.put("myParam_title", "上海三禄贸易有限公司");
        rptParameters.put("myParam_category", "销售合同");
        // 创建空的数据源
        JRDataSource dataSource = new JREmptyDataSource();
        try {
            filePath = "/F:/Jasper";
            String reportName = "";
            // 通过模板、Map参数、数据源 创建Jasper对象，使用这个对象可以创建导出各种格式文件的实例对象
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportTemplate.getPath(),rptParameters, AppConst.getConnection());
            // 通过Jasper对象创建指定的Excel文件导出对象
            JExcelApiExporter exporter = new JExcelApiExporter();
            // 设置导出文件的相关信息，并且导出文件在指定目录下（在这里指reportName所指的的目录）

            reportName = filePath+"\\"+fileName+".xls";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,reportName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.exportReport();

            reportName = filePath+"\\"+fileName+".html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportName);

            reportName = filePath+"\\"+fileName+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportName);

            reportName = filePath+"\\"+fileName+".doc";
            JRRtfExporter docReport = new JRRtfExporter();
            docReport.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,reportName);
            docReport.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            docReport.exportReport();

            System.err.println("导出完成");
        } catch (JRException e) {
            e.printStackTrace();
        }

    }




}
