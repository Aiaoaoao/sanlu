package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.account.AccountReceivable;
import com.yc.education.model.customer.*;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.customer.*;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 客户需求商品查询--小窗口
 * @Author: BlueSky
 * @Date: 2018/8/15 15:14
 */
@Controller
public class CustomerDemandGoodsQueryMiniController extends BaseController implements Initializable {

    @Autowired
    ICustomerDemandGoodsService iCustomerDemandGoodsService;
    @Autowired
    ICustomerDataMaintainService iCustomerDataMaintainService;
    @Autowired
    ICustomerDetailInfoService iCustomerDetailInfoService;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    DataSettingService iDataSettingService;


    @FXML
    VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML CheckBox che_recently;
    @FXML TextField num;
    @FXML TextField text_query;

    @FXML TableView customer_demand_goods_table;
    @FXML TableColumn id;
    @FXML TableColumn create_no;
    @FXML TableColumn create_date;
    @FXML TableColumn customer_no;
    @FXML TableColumn customer_type;
    @FXML TableColumn customer_name;
    @FXML TableColumn remark;
    // 客户需求商品id
    public static String  customerDemandGoodsId = "";
    // 客户资料维护
    public static String  maintainId = "";
    public static LocalDate maintainCreateDate ;
    public static String  maintainCustomerNo = "";
    public static String  maintainCustomerName = "";
    public static String  maintainCustomerNoRemark = "";
    public static String  maintainBuildNo = "";
    // 客户需求商品查询
    public static String createNo="" ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);

        customer_demand_goods_table.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });
    }

    /**
     * @Description 模糊查询
     * @Author BlueSky
     * @Date 15:12 2019/4/11
     **/
    @FXML
    public void textQuery(){
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){

        int rows = pageRows(che_recently,num);
        String text = text_query.getText();

        // 客户需求商品 + 客户需求商品查询
        List<CustomerDemandGoods> demandGoodsList = iCustomerDemandGoodsService.listCustomerDemandGoods(text,page, rows);
        if(demandGoodsList != null && demandGoodsList.size() >0){
            PageInfo<CustomerDemandGoods> pageInfo = new PageInfo<>(demandGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            customerDemandMethod(demandGoodsList); // 客户需求商品
        }else {
            customer_demand_goods_table.setItems(null);
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
     * 客户需求商品
     */
    private void customerDemandMethod(List<CustomerDemandGoods> list){

        // 查询地址集合
        final ObservableList<CustomerDemandGoods> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory<Customer,Long>("id"));
        create_no.setCellValueFactory(new PropertyValueFactory("createNo"));
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_type.setCellValueFactory(new PropertyValueFactory("customerType"));
        customer_name.setCellValueFactory(new PropertyValueFactory("customerName"));
        remark.setCellValueFactory(new PropertyValueFactory("remark"));
        create_date.setCellValueFactory(new PropertyValueFactory<Customer,Date>("createDate"));
        create_date.setCellFactory(column -> {
            TableCell<Customer, Date> cell = new TableCell<Customer, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        if(item != null) {
                            this.setText(format.format(item));
                        }
                    }
                }
            };
            return cell;
        });

        customer_demand_goods_table.setItems(data);
        // 选择行 保存数据
        customer_demand_goods_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerDemandGoods>() {
            @Override
            public void changed(ObservableValue<? extends CustomerDemandGoods> observable, CustomerDemandGoods oldValue, CustomerDemandGoods newValue) {
                if(!"".equals(newValue.getId().toString())){
                    CustomerDemandGoodsQueryMiniController.customerDemandGoodsId = newValue.getId().toString();
                }
                // 客户需求商品查询
                if(!"".equals(newValue.getCreateNo().toString())){
                    CustomerDemandGoodsQueryMiniController.createNo = newValue.getCreateNo().toString();
                }
            }
        });
    }

    //关闭当前窗体
    @FXML
    public void closeWin(){
        Stage stage=(Stage)customer_demand_goods_table.getScene().getWindow();
        StageManager.CONTROLLER.remove("CustomerDemandGoodsController");
        StageManager.CONTROLLER.remove("CustomerDemandGoodsQueryController");
        StageManager.CONTROLLER.remove("CustomerDemandGoodsQueryControllerState");
        stage.close();
    }
    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 客户需求商品
        CustomerDemandGoodsController demand = (CustomerDemandGoodsController) StageManager.CONTROLLER.get("CustomerDemandGoodsController");
        // 客户需求商品
        CustomerDemandGoodsQueryController demandQuery = (CustomerDemandGoodsQueryController) StageManager.CONTROLLER.get("CustomerDemandGoodsQueryController");
        String state = (String) StageManager.CONTROLLER.get("CustomerDemandGoodsQueryControllerState");
        if(demand != null){
            demand.id.setText(customerDemandGoodsId);
            demand.getCustomerDemandGoodsById();
        }
        if(demandQuery != null){
            if(state != null){
                if("ben".equals(state)){
                    demandQuery.create_no.setText(createNo);
                    demandQuery.create_no_end.setText(createNo);
                }else if("end".equals(state)){
                    demandQuery.create_no_end.setText(createNo);
                }
            }
        }
        // 关闭窗口并清除 CONTROLLER 缓存
        closeWin();
    }
}
