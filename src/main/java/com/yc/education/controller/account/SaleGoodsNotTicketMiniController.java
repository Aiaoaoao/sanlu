package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.AccountSaleInvoiceInfoProperty;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.service.account.IAccountSaleInvoiceService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.util.AppConst;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author BlueSky
 * @Description //TODO 账款 - 销项发票 - 查询更多未开票的销货单
 *  //TODO  未开票！！！ 未开票！！！ 未开票！！！
 * @Date 17:56 2019/4/1
 * @Param 
 **/
@Controller
public class SaleGoodsNotTicketMiniController extends BaseController implements Initializable {

    @Autowired
    ISaleGoodsService iSaleGoodsService;

    @Autowired
    ISaleGoodsProductService iSaleGoodsProductService;
    @Autowired
    IAccountSaleInvoiceService iAccountSaleInvoiceService;


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML Button client_sure;
    @FXML
    CheckBox che_recently;
    @FXML TextField num;
    @FXML TextField findOrder; // 订单号

    @FXML TableView tableView;

    @FXML TableColumn id;
    @FXML TableColumn no;
    @FXML TableColumn order_no;
    @FXML TableColumn order_date;
    @FXML TableColumn customer_no;
    @FXML TableColumn customer_name;
    @FXML TableColumn contact;
    @FXML TableColumn business_leader;
    @FXML TableColumn status;

    // 订单id
    private static String  orderid = "";
    // 订单编号
    private static String  orderNo = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
        // 文本框改变监听事件
        findOrder.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        int rows = pageRows(che_recently,num);
        List<SaleGoods> SaleGoodsList = iSaleGoodsService.listSaleGoodsByPage("","",page, AppConst.ROWS);
        if(SaleGoodsList != null && SaleGoodsList.size() >0){
            PageInfo<SaleGoods> pageInfo = new PageInfo<>(SaleGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(SaleGoodsList);
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
    private void loadData(List<SaleGoods> list){
        generalOrder(list);
    }


    /**
     * 根据订单号查询
     */
    @FXML
    public void likeOrder(){
//        List<SaleGoods> goodsList = iSaleGoodsService.listOrderNoLike(findOrder.getText());
//        if(goodsList != null && goodsList.size() > 0){
//            generalOrder(goodsList);
//        }
    }

    /**
     * 渲染数据
     * @param list
     */
    private void generalOrder(List<SaleGoods> list){
        if(list != null){
            int rows = 1;
            for (SaleGoods p : list) {
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                p.setNo(rows++);
            }
        }

        // 查询客户集合
        final ObservableList<SaleGoods> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        no.setCellValueFactory(new PropertyValueFactory("no"));
        order_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
        order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_name.setCellValueFactory(new PropertyValueFactory("customerName"));
        contact.setCellValueFactory(new PropertyValueFactory("contact"));
        business_leader.setCellValueFactory(new PropertyValueFactory("businessLeader"));
        status.setCellValueFactory(new PropertyValueFactory("orderAudit"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleGoods>() {
            @Override
            public void changed(ObservableValue<? extends SaleGoods> observableValue, SaleGoods oldItem, SaleGoods newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    SaleGoodsNotTicketMiniController.orderid = newItem.getId()+"";
                }
                if(newItem.getSaleNo() != null && !("".equals(newItem.getSaleNo()))){
                    SaleGoodsNotTicketMiniController.orderNo = newItem.getSaleNo()+"";
                }
            }
        });

        tableView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });
    }


    //关闭当前窗体
    @FXML
    public void closeWin(){
        Stage stage=(Stage)client_sure.getScene().getWindow();
        StageManager.CONTROLLER.remove("saleInvoiceControllerMore");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 报价单
        AccountSaleInvoiceController controller = (AccountSaleInvoiceController) StageManager.CONTROLLER.get("saleInvoiceControllerMore");
        // 导入选中的产品--销货单
        ObservableList<AccountSaleInvoiceInfoProperty> importPurchaseData = FXCollections.observableArrayList();
        if(controller != null && orderid != null && !"".equals(orderid)){
            SaleGoods order = iSaleGoodsService.selectByKey(Long.valueOf(orderid));
            if(order != null){
                controller.clearValue();
                controller.setSaleGoodsImportVal(order);
                // 把销售订单中的选中产品加载到销项发票的详情中
                List<SaleGoodsProduct> productList = iSaleGoodsProductService.listSaleGoodsProduct(order.getId().toString());
                int rows = 1;
                for (SaleGoodsProduct k : productList) {
                    importPurchaseData.add(new AccountSaleInvoiceInfoProperty("销货单", order.getSaleNo(), (rows++)+"", order.getCustomerNo(), order.getCustomerName(), k.getProductNo(), k.getProductName(), "", k.getUnit(), k.getNum()==null?"0":k.getNum().toString(),k.getPrice()==null?"0.00":k.getPrice().toString(),k.getMoney()==null?"0.00":k.getMoney().toString(),"","","","",k.getRemark()));
                }
                if(importPurchaseData != null){
                    controller.generalProductTab(importPurchaseData);
                }
                controller.setControllerUse();
            }
        }
        closeWin();
    }
}
