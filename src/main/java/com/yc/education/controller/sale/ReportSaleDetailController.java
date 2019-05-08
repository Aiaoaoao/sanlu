package com.yc.education.controller.sale;

import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.basic.EmployeeBasicService;
import com.yc.education.util.AppConst;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

/**
 * @Description 销售 -- 相关报表 -- 销货明细表
 * @Author BlueSky
 * @Date 2019-03-11 14:57
 */
@Controller
public class ReportSaleDetailController extends BaseController implements Initializable {

    @Autowired private EmployeeBasicService employeeBasicService;       // 员工数据
    @Autowired private DataSettingService dataSettingService;           // 基础数据

    @FXML DatePicker sale_date;             //销货日期
    @FXML DatePicker sale_date_end;
    @FXML TextField sale_no;                //销货单号
    @FXML TextField sale_no_end;
    @FXML TextField customer_no;            //客户编号
    @FXML TextField customer_no_end;
    @FXML ComboBox business_ben;               //负责业务
    @FXML ComboBox business_end;
    @FXML ComboBox warehouse;               //仓库编号
    @FXML ComboBox warehouse_end;
    @FXML TextField product_no;             //产品编号
    @FXML TextField product_no_end;
    @FXML ComboBox product_category;        //产品大类
    @FXML ComboBox product_category_end;
    @FXML ComboBox customer_property;       //客户属性
    @FXML ComboBox customer_property_end;
    @FXML ComboBox category;                //品类
    @FXML ComboBox category_end;

    @FXML GridPane gridpane;                // 单选按钮的区域
    @FXML RadioButton radio_business;       //依业务别
    @FXML RadioButton radio_product;        //依产品别
    @FXML RadioButton radio_date;           //依日期别
    @FXML RadioButton radio_customer;       //依客户别
    @FXML RadioButton radio_warehouse;      //依仓库别

    static String radioValue = "3";

    static List<String> reportBusinessList = new ArrayList<>();         // 销货明细报表 业务别
    static List<String> reportProductList = new ArrayList<>();          // 销货明细报表 产品别
    static List<String> reportDateList = new ArrayList<>();             // 销货明细报表 日期别
    static List<String> reportCustomerList = new ArrayList<>();         // 销货明细报表 客户别
    static List<String> reportWarehouseList = new ArrayList<>();        // 销货明细报表 报表别
    static boolean closewin = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 单选按钮
        ToggleGroup group = new ToggleGroup();
        gridpane.getChildren().clear();
        radio_business.setToggleGroup(group);
        radio_business.setUserData(1);
        radio_product.setToggleGroup(group);
        radio_product.setUserData(2);
        radio_date.setToggleGroup(group);
        radio_date.setUserData(3);
        radio_date.setSelected(true);
        radio_customer.setToggleGroup(group);
        radio_customer.setUserData(4);
        radio_warehouse.setToggleGroup(group);
        radio_warehouse.setUserData(5);
        gridpane.getChildren().add(radio_business);
        gridpane.getChildren().add(radio_product);
        gridpane.getChildren().add(radio_date);
        gridpane.getChildren().add(radio_customer);
        gridpane.getChildren().add(radio_warehouse);
        // 选中某个单选框时输出选中的值
        group.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
                    public void changed(
                            ObservableValue<? extends Toggle> ov,
                            Toggle old_toggle, Toggle new_toggle) {
                        if (group.getSelectedToggle() != null) {
                            radioValue = group.getSelectedToggle().getUserData()==null?"1":group.getSelectedToggle().getUserData().toString();
                        }
                    }
                });

        List<EmployeeBasic> employeeBasicList = employeeBasicService.selectEmployeeBasic();
        if(employeeBasicList!=null){
            business_ben.getItems().add("");
            business_end.getItems().add("");
            employeeBasicList.forEach(p->{
                business_ben.getItems().add(p.getEmpname());
                business_end.getItems().add(p.getEmpname());
            });
        }
        List<DataSetting> dataSettingList = dataSettingService.findDataSetting(29L);
        if(dataSettingList!=null){
            warehouse.getItems().add("");
            warehouse_end.getItems().add("");
            dataSettingList.forEach(p->{
                warehouse.getItems().add(p.getTitle());
                warehouse_end.getItems().add(p.getTitle());
            });
        }
        dataSettingList = dataSettingService.findDataSetting(6L);
        if(dataSettingList!=null){
            product_category.getItems().add("");
            product_category_end.getItems().add("");
            dataSettingList.forEach(p->{
                product_category.getItems().add(p.getTitle());
                product_category_end.getItems().add(p.getTitle());
            });
        }
    }

    /**
     * 获取报表参数
     * @return
     */
    private Map<String ,Object> getParams(){
        Map<String,Object> maps = new HashMap<>();
        // 查询展示条件字段
        String saleDate="",productNo="",warehouseNo="",customerNo="",productCategory="",company="",business="",saleNo="";
        // 销货日期 1
        maps.put("createDateBen", sale_date.getValue()==null?"":sale_date.getValue());
        saleDate = sale_date.getValue()==null?"":sale_date.getValue().toString();
        maps.put("createDateEnd", sale_date_end.getValue()==null?"":sale_date_end.getValue());
        saleDate += sale_date_end.getValue()==null?"":" ~ " +sale_date_end.getValue().toString();
        if(saleDate==null||"".equals(saleDate)){
            saleDate = "全部";
        }
        maps.put("saleDate", saleDate);

        // 产品编号 2
        maps.put("productNoBen", product_no.getText()==null||"".equals(product_no.getText())?"":product_no.getText());
        productNo = product_no.getText();
        maps.put("productNoEnd", product_no_end.getText()==null||"".equals(product_no_end.getText())?"":product_no_end.getText());
        productNo +=  product_no_end.getText()==null||"".equals(product_no_end.getText())?"":" ~ " +product_no_end.getText();
        if(productNo==null||"".equals(productNo)){
            productNo = "全部";
        }
        maps.put("productNo", productNo);

        // 销货单号 3
        maps.put("saleNoBen", sale_no.getText()==null||"".equals(sale_no.getText())?"":sale_no.getText());
        saleNo = sale_no.getText();
        maps.put("saleNoEnd", sale_no_end.getText()==null||"".equals(sale_no_end.getText())?"":sale_no_end.getText());
        saleNo += sale_no_end.getText()==null||"".equals(sale_no_end.getText())?"":" ~ "+sale_no_end.getText();
        if(saleNo==null||"".equals(saleNo)){
            saleNo = "全部";
        }
        maps.put("saleNo", saleNo);

        // 客户编号 4
        maps.put("customerNoBen", customer_no.getText()==null||"".equals(customer_no.getText())?"":customer_no.getText());
        customerNo = customer_no.getText();
        maps.put("customerNoEnd", customer_no_end.getText()==null||"".equals(customer_no_end.getText())?"":customer_no_end.getText());
        customerNo += customer_no_end.getText()==null||"".equals(customer_no_end.getText())?"":" ~ " + customer_no_end.getText();
        if(customerNo==null||"".equals(customerNo)){
            customerNo = "全部";
        }
        maps.put("customerNo", customerNo);

        // 业务负责 5
        maps.put("businessLeaderBen", business_ben.getSelectionModel().getSelectedItem()==null?"":business_ben.getSelectionModel().getSelectedItem());
        business = business_ben.getSelectionModel().getSelectedItem()==null?"":business_ben.getSelectionModel().getSelectedItem().toString();
        maps.put("businessLeaderEnd", business_end.getSelectionModel().getSelectedItem()==null?"":business_end.getSelectionModel().getSelectedItem());
        business += business_end.getSelectionModel().getSelectedItem()==null?"":" ~ " +business_end.getSelectionModel().getSelectedItem().toString();
        if(business==null||"".equals(business)){
            business = "全部";
        }
        maps.put("business", business);

        // 仓库编号 6
        maps.put("warehouseBen", warehouse.getSelectionModel().getSelectedItem()==null?"":warehouse.getSelectionModel().getSelectedItem());
        warehouseNo = warehouse.getSelectionModel().getSelectedItem()==null?"":warehouse.getSelectionModel().getSelectedItem().toString();
        maps.put("warehouseEnd", warehouse_end.getSelectionModel().getSelectedItem()==null?"":warehouse_end.getSelectionModel().getSelectedItem());
        warehouseNo += warehouse_end.getSelectionModel().getSelectedItem()==null?"":" ~ " +warehouse_end.getSelectionModel().getSelectedItem().toString();
        if(warehouseNo==null||"".equals(warehouseNo)){
            warehouseNo = "全部";
        }
        maps.put("warehouseNo", warehouseNo);


        // 产品大类 7
        maps.put("productCategoryBen", product_category.getSelectionModel().getSelectedItem()==null?"":product_category.getSelectionModel().getSelectedItem());
        productCategory = product_category.getSelectionModel().getSelectedItem()==null?"":product_category.getSelectionModel().getSelectedItem().toString();

        maps.put("productCategoryEnd", product_category_end.getSelectionModel().getSelectedItem()==null?"":product_category_end.getSelectionModel().getSelectedItem());
        productCategory += product_category_end.getSelectionModel().getSelectedItem()==null?"":" ~ " +product_category_end.getSelectionModel().getSelectedItem().toString();

        if(productCategory==null||"".equals(productCategory)){
            productCategory = "全部";
        }
        maps.put("productCategory", productCategory);

        // 制表人
        maps.put("createPeople", getAdminName());
        // 公司别
        maps.put("company", "三禄");


        // 依产品别 表头显示隐藏
        maps.put("bool_product_no", "False");
        maps.put("bool_product_name", "False");
        maps.put("bool_create_date", "False");
        maps.put("bool_sale_no", "False");
        maps.put("bool_customer_no_str", "False");
        maps.put("bool_business_leader_str", "False");
        maps.put("bool_warehouse_out_str", "False");
        maps.put("bool_currency", "False");
        maps.put("bool_price", "False");
        maps.put("bool_sale_money", "False");
        maps.put("bool_calc_money", "False");
        maps.put("bool_tax", "False");
        reportProductList.forEach(p->{
            if("产品编号".equals(p)){
                maps.put("bool_product_no", "True");
            }
            if("产品名称".equals(p)){
                maps.put("bool_product_name", "True");
            }
            if("单据日期".equals(p)){
                maps.put("bool_create_date", "True");
            }
            if("单据单号".equals(p)){
                maps.put("bool_sale_no", "True");
            }
            if("客户简称".equals(p)){
                maps.put("bool_customer_no_str", "True");
            }
            if("业务负责".equals(p)){
                maps.put("bool_business_leader_str", "True");
            }
            if("仓库名称".equals(p)){
                maps.put("bool_warehouse_out_str", "True");
            }
            if("币别".equals(p)){
                maps.put("bool_currency", "True");
            }
            if("单价".equals(p)){
                maps.put("bool_price", "True");
            }
            if("销售金额".equals(p)){
                maps.put("bool_sale_money", "True");
            }
            if("小计".equals(p)){
                maps.put("bool_calc_money", "True");
            }
            if("税别".equals(p)){
                maps.put("bool_tax", "True");
            }
        });


        // 依日期别 表头显示隐藏
        maps.put("bool_sale_date", "False");
        maps.put("bool_customer", "False");
        maps.put("bool_business", "False");
        maps.put("bool_warehouse", "False");
        maps.put("bool_currency", "False");
        maps.put("bool_sale_money", "False");
        maps.put("bool_tax_money", "False");
        maps.put("bool_calc_money", "False");
        maps.put("bool_tax", "False");

        reportDateList.forEach(p->{
            if("税别".equals(p)){
                maps.put("bool_tax", "True");
            }
            if("销货总金额".equals(p)){
                maps.put("bool_calc_money", "True");
            }
            if("税额".equals(p)){
                maps.put("bool_tax_money", "True");
            }
            if("销售净额".equals(p)){
                maps.put("bool_sale_money", "True");
            }
            if("币别".equals(p)){
                maps.put("bool_currency", "True");
            }
            if("仓库名称".equals(p)){
                maps.put("bool_warehouse", "True");
            }
            if("业务负责".equals(p)){
                maps.put("bool_business", "True");
            }
            if("客户简称".equals(p)){
                maps.put("bool_customer", "True");
            }
            if("销货日期".equals(p)){
                maps.put("bool_sale_date", "True");
            }
        });


        return maps;
    }

    /**
     * 获取模板路径
     * @return
     */
    private synchronized String getUrl(){
        String url;
        // 1：业务别、2：产品别、3：日期别、4：客户别、5：仓库别
        switch (radioValue){
            case "1":
                url = "/jasper/sale_detail_business.jasper";
                break;
            case "2":
                url = "/jasper/sale_detail_product.jasper";
                break;
            case "3":
                url = "/jasper/sale_detail_date.jasper";
                break;
            case "4":
                url = "/jasper/sale_detail_customer.jasper";
                break;
            case "5":
                url = "/jasper/sale_detail_warehouse.jasper";
                break;
            default:
                url = "/jasper/sale_detail_date.jasper";
                break;
        }
        return url;
    }

    /**
     * 报表列选项
     * @return
     */
    @FXML
    public synchronized boolean getSelectChe(){
        Stage stage = new Stage();

        reportBusinessList = new ArrayList<>();
        reportProductList = new ArrayList<>();
        reportDateList = new ArrayList<>();
        reportCustomerList = new ArrayList<>();
        reportWarehouseList = new ArrayList<>();

        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        pane.getChildren().clear();

        switch (radioValue){
            case "1":
                Stream.iterate(0, i -> i + 1).limit(Arrays.asList(AppConst.SALE_DETAIL_DATE).size()).forEach(i -> {

                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(AppConst.SALE_DETAIL_DATE[i]);
                    checkBox.setUserData(AppConst.SALE_DETAIL_DATE_P[i]);
                    checkBox.setPrefWidth(120L);
                    checkBox.setSelected(true);
                    reportDateList.add(AppConst.SALE_DETAIL_DATE_P[i]);
                    checkBox.setPadding(new Insets(5, 5, 5, 5));
                    if (i > 0) {
                        int r = i / 5;
                        checkBox.setLayoutX(120L * (i % 5));
                        checkBox.setLayoutY(r * 25L);
                    }
                    pane.getChildren().add(checkBox);

                    checkBox.selectedProperty().addListener
                            (new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                                    if(new_val){
                                        if(checkBox.getUserData() != null){
                                            reportDateList.add(checkBox.getUserData().toString());
                                        }
                                    }else {
                                        reportDateList.remove(checkBox.getUserData().toString());
                                    }
                                }
                            });
                });
                stage.setTitle("报表列选择 - 依业务别");
                break;
            case "2":
                Stream.iterate(0, i -> i + 1).limit(Arrays.asList(AppConst.SALE_DETAIL_PRODUCT).size()).forEach(i -> {

                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(AppConst.SALE_DETAIL_PRODUCT[i]);
                    checkBox.setUserData(AppConst.SALE_DETAIL_PRODUCT[i]);
                    checkBox.setPrefWidth(120L);
                    checkBox.setSelected(true);
                    reportProductList.add(AppConst.SALE_DETAIL_PRODUCT[i]);
                    checkBox.setPadding(new Insets(5, 5, 5, 5));
                    if (i > 0) {
                        int r = i / 5;
                        checkBox.setLayoutX(120L * (i % 5));
                        checkBox.setLayoutY(r * 25L);
                    }
                    pane.getChildren().add(checkBox);

                    checkBox.selectedProperty().addListener
                            (new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                                    if(new_val){
                                        if(checkBox.getUserData() != null){
                                            reportProductList.add(checkBox.getUserData().toString());
                                        }
                                    }else {
                                        reportProductList.remove(checkBox.getUserData().toString());
                                    }
                                }
                            });
                });
                stage.setTitle("报表列选择 - 依产品别");
                break;
            case "3":
                Stream.iterate(0, i -> i + 1).limit(Arrays.asList(AppConst.SALE_DETAIL_DATE).size()).forEach(i -> {

                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(AppConst.SALE_DETAIL_DATE[i]);
                    checkBox.setUserData(AppConst.SALE_DETAIL_DATE[i]);
                    checkBox.setPrefWidth(120L);
                    checkBox.setSelected(true);
                    reportDateList.add(AppConst.SALE_DETAIL_DATE[i]);
                    checkBox.setPadding(new Insets(5, 5, 5, 5));
                    if (i > 0) {
                        int r = i / 5;
                        checkBox.setLayoutX(120L * (i % 5));
                        checkBox.setLayoutY(r * 25L);
                    }
                    pane.getChildren().add(checkBox);

                    checkBox.selectedProperty().addListener
                            (new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                                    if(new_val){
                                        if(checkBox.getUserData() != null){
                                            reportDateList.add(checkBox.getUserData().toString());
                                        }
                                    }else {
                                        reportDateList.remove(checkBox.getUserData().toString());
                                    }
                                }
                            });
                });
                stage.setTitle("报表列选择 - 依日期别");
                break;
            case "4":
                Stream.iterate(0, i -> i + 1).limit(Arrays.asList(AppConst.SALE_DETAIL_DATE).size()).forEach(i -> {

                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(AppConst.SALE_DETAIL_DATE[i]);
                    checkBox.setUserData(AppConst.SALE_DETAIL_DATE_P[i]);
                    checkBox.setPrefWidth(120L);
                    checkBox.setSelected(true);
                    reportDateList.add(AppConst.SALE_DETAIL_DATE_P[i]);
                    checkBox.setPadding(new Insets(5, 5, 5, 5));
                    if (i > 0) {
                        int r = i / 5;
                        checkBox.setLayoutX(120L * (i % 5));
                        checkBox.setLayoutY(r * 25L);
                    }
                    pane.getChildren().add(checkBox);

                    checkBox.selectedProperty().addListener
                            (new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                                    if(new_val){
                                        if(checkBox.getUserData() != null){
                                            reportDateList.add(checkBox.getUserData().toString());
                                        }
                                    }else {
                                        reportDateList.remove(checkBox.getUserData().toString());
                                    }
                                }
                            });
                });
                stage.setTitle("报表列选择 - 依客户别");
                break;
            case "5":
                Stream.iterate(0, i -> i + 1).limit(Arrays.asList(AppConst.SALE_DETAIL_DATE).size()).forEach(i -> {

                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(AppConst.SALE_DETAIL_DATE[i]);
                    checkBox.setUserData(AppConst.SALE_DETAIL_DATE_P[i]);
                    checkBox.setPrefWidth(120L);
                    checkBox.setSelected(true);
                    reportDateList.add(AppConst.SALE_DETAIL_DATE_P[i]);
                    checkBox.setPadding(new Insets(5, 5, 5, 5));
                    if (i > 0) {
                        int r = i / 5;
                        checkBox.setLayoutX(120L * (i % 5));
                        checkBox.setLayoutY(r * 25L);
                    }
                    pane.getChildren().add(checkBox);

                    checkBox.selectedProperty().addListener
                            (new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                                    if(new_val){
                                        if(checkBox.getUserData() != null){
                                            reportDateList.add(checkBox.getUserData().toString());
                                        }
                                    }else {
                                        reportDateList.remove(checkBox.getUserData().toString());
                                    }
                                }
                            });
                });
                stage.setTitle("报表列选择 - 依仓库别");
                break;
            default:
                Stream.iterate(0, i -> i + 1).limit(Arrays.asList(AppConst.SALE_DETAIL_DATE).size()).forEach(i -> {

                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(AppConst.SALE_DETAIL_DATE[i]);
                    checkBox.setUserData(AppConst.SALE_DETAIL_DATE_P[i]);
                    checkBox.setPrefWidth(120L);
                    checkBox.setSelected(true);
                    reportDateList.add(AppConst.SALE_DETAIL_DATE_P[i]);
                    checkBox.setPadding(new Insets(5, 5, 5, 5));
                    if (i > 0) {
                        int r = i / 5;
                        checkBox.setLayoutX(120L * (i % 5));
                        checkBox.setLayoutY(r * 25L);
                    }
                    pane.getChildren().add(checkBox);

                    checkBox.selectedProperty().addListener
                            (new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                                    if(new_val){
                                        if(checkBox.getUserData() != null){
                                            reportDateList.add(checkBox.getUserData().toString());
                                        }
                                    }else {
                                        reportDateList.remove(checkBox.getUserData().toString());
                                    }
                                }
                            });
                });
                stage.setTitle("报表列选择 - 依日期别");
                break;
        }


        Button button = new Button();
        button.setText(" 确认 ");
        button.setLayoutX(500);
        button.setLayoutY(220);
        pane.getChildren().add(button);

        button.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            closewin = true;
            stage.close();
            // 打开预览
            openWebviewWindow();
        });

        borderPane.setCenter(pane);
        stage.setScene(new Scene(borderPane));


        stage.setHeight(300);
        stage.setWidth(600);
        stage.show();
        return closewin;
    }

    /**
     * 预览
     */
    @FXML
    @SneakyThrows
    public synchronized void  openWebviewWindow() {

//        Stage stage = new Stage();
//
//        // 获取源文件
//        URL url = this.getClass().getResource(getUrl());
//        String str = url==null?null:url.toString().substring(url.toString().indexOf(':')+1, url.toString().length());
//        String filePath = str.substring(0, str.lastIndexOf("/"));
//        String fileName = str.substring(str.lastIndexOf("/"), str.length()).split("\\.")[0];
//
//        //传入报表源文件绝对路径，外部参数对象，DB连接，得到JasperPring对象
//        JasperPrint jasperPrint = JasperFillManager.fillReport(str, getParams(), AppConst.getConnection());
//
//        //导出HTML文件
//        JasperExportManager.exportReportToHtmlFile(jasperPrint, filePath+fileName+".html");
//
//        stage.setTitle("销货明细表（日期别）");
//        WebView webView = new WebView();
//        WebEngine webEngine = webView.getEngine();
//
//        webEngine.load(this.getClass().getResource("/jasper"+fileName+".html").toExternalForm());
//
//        Scene scene = new Scene(webView, 10, 10, Color.web("lightgray"));
//
//        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//        stage.setX(primaryScreenBounds.getMinX());
//        stage.setY(primaryScreenBounds.getMinY());
//        stage.setWidth(primaryScreenBounds.getWidth());
//        stage.setHeight(primaryScreenBounds.getHeight());
//        stage.setScene(scene);
//        stage.show();

        openHtml(getUrl());

    }

    /**
     * 打开html
     * @param src
     */
    @SneakyThrows
    private void openHtml(String src){
        Stage stage = new Stage();

        // 获取源文件
        URL url = this.getClass().getResource(src);
        String str = url==null?null:url.toString().substring(url.toString().indexOf(':')+1, url.toString().length());
        String filePath = str.substring(0, str.lastIndexOf("/"));
        String fileName = str.substring(str.lastIndexOf("/"), str.length()).split("\\.")[0];

        //传入报表源文件绝对路径，外部参数对象，DB连接，得到JasperPring对象
        JasperPrint jasperPrint = JasperFillManager.fillReport(str, getParams(), AppConst.getConnection());

        //导出HTML文件
        JasperExportManager.exportReportToHtmlFile(jasperPrint, filePath+fileName+".html");

        stage.setTitle("销货明细表（日期别）");
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
     * 组合模板一
     */
    @FXML
    @SneakyThrows
    public void openReportModelOne(){
        openHtml("/jasper/sale_detail_warehouse_one.jasper");
    }

    /**
     * 组合模板二
     */
    @FXML
    @SneakyThrows
    public void openReportModelTwo(){
        openHtml("/jasper/sale_detail_warehouse_two.jasper");
    }



    /**
     * 导出 excel
     */
    @FXML
    @SneakyThrows
    public synchronized void exportExcel(){
        String exportURL = choseURL();
        if(exportURL != null){
            //开始导出文件

            String reportName = null;
            JasperPrint jasperPrint = null;
            Map<String ,Object> map = exportCommon(getUrl(),getParams());
            if(map != null){
                reportName = map.get("reportName")==null?null:map.get("reportName").toString();
                jasperPrint = map.get("jasperPrint")==null?null:(JasperPrint) map.get("jasperPrint");
            }

            // 通过Jasper对象创建指定的Excel文件导出对象
            JExcelApiExporter exporter = new JExcelApiExporter();
            // 设置导出文件的相关信息，并且导出文件在指定目录下（在这里指reportName所指的的目录）

            reportName = exportURL+"\\"+reportName+".xls";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,reportName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.exportReport();
            alert_informationDialog("导出完成");
        }else {
            System.err.println("用户取消了路径选择");
        }

    }

    /**
     * 导出 PDF
     */
    @FXML
    @SneakyThrows
    public synchronized  void exportPDF(){
        String exportURL = choseURL();
        if(exportURL != null){
            //开始导出文件

            String reportName = null;
            JasperPrint jasperPrint = null;
            Map<String ,Object> map = exportCommon(getUrl(),getParams());
            if(map != null){
                reportName = map.get("reportName")==null?null:map.get("reportName").toString();
                jasperPrint = map.get("jasperPrint")==null?null:(JasperPrint) map.get("jasperPrint");
            }

            reportName = exportURL+"\\"+reportName+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportName);
            alert_informationDialog("导出完成");
        }else {
            System.err.println("用户取消了路径选择");
        }
    }

    /**
     * 导出 Word
     */
    @FXML
    @SneakyThrows
    public synchronized  void exportWord(){
        String exportURL = choseURL();
        if(exportURL != null){
            //开始导出文件

            try {
                String reportName="";
                Map<String ,Object> map = exportCommon(getUrl(),getParams());
                if(map != null){
                    reportName = map.get("reportName")==null?null:map.get("reportName").toString();
                }
                // 通过模板、Map参数、数据源 创建Jasper对象，使用这个对象可以创建导出各种格式文件的实例对象
                URL url = this.getClass().getResource(getUrl());
                String str = url==null?null:url.toString().substring(url.toString().indexOf(':')+1, url.toString().length());
                JasperPrint jasperPrint = JasperFillManager.fillReport(str,getParams(), AppConst.getConnection());

                reportName = exportURL+"\\"+reportName+".doc";
                JRRtfExporter docReport = new JRRtfExporter();
                docReport.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,reportName);
                docReport.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                docReport.exportReport();
                alert_informationDialog("导出完成");
                System.err.println("导出完成");
            } catch (JRException e) {
                e.printStackTrace();
            }

        }else {
            System.err.println("用户取消了路径选择");
        }
    }

    /**
     * 选择文件打印
     */
    @FXML
    public synchronized  void printView(){
        JFileChooser fileChooser = new JFileChooser(); // 创建打印作业
        int state = fileChooser.showOpenDialog(null);
        if (state == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // 获取选择的文件
            // 构建打印请求属性集
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            // 设置打印格式，因为未确定类型，所以选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            // 查找所有的可用的打印服务
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            // 定位默认的打印服务
            PrintService defaultService = PrintServiceLookup
                    .lookupDefaultPrintService();
            // 显示打印对话框
            PrintService service = ServiceUI.printDialog(null, 200, 200,
                    printService, defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob(); // 创建打印作业
                    FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
//                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
