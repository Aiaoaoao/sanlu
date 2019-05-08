package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.sale.OnlineOrderController;
import com.yc.education.controller.sale.SaleDeliveryTrackController;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleOnlineOrder;
import com.yc.education.model.stock.StockOutSale;
import com.yc.education.service.sale.ISaleOnlineOrderService;
import com.yc.education.service.stock.IStockOutSaleService;
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
 * 销货出库单小窗口
 */
@Controller
public class SaleOutboundMiniController extends BaseController implements Initializable {

    @Autowired
    IStockOutSaleService iStockOutSaleService;

    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML CheckBox che_recently;
    @FXML TextField num;
    @FXML TextField queryText;  // 查询内容

    @FXML Button client_sure;

    @FXML TableView tableView;

    @FXML TableColumn id;
    @FXML TableColumn order_no;
    @FXML TableColumn order_date;
    @FXML TableColumn customer_no;
    @FXML TableColumn customer_shortname;
    @FXML TableColumn made_people;
    @FXML TableColumn order_audit;



    // 订单id
    private static String  orderid = "";

    // 订单编号
    private static String  orderno = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }

    /**
     * @Description 模糊查询
     * @Author BlueSky
     * @Date 12:00 2019/4/11
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
        String text = queryText.getText();
        List<StockOutSale> outSaleList = iStockOutSaleService.listStockOutSaleAll(text==null?"":text,page, rows);
        if(outSaleList != null && outSaleList.size() >0){
            PageInfo<StockOutSale> pageInfo = new PageInfo<>(outSaleList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(outSaleList);
        }else {
            tableView.setItems(null);
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
    private void loadData(List<StockOutSale> list){
        if(list != null){
            list.forEach(p->{
                p.setOrderDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                if(p.getOrderAudit() == null){
                    p.setOrderAudit(false);
                }
            });
        }

        // 查询客户集合
        final ObservableList<StockOutSale> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        order_no.setCellValueFactory(new PropertyValueFactory("outboundNo"));
        order_date.setCellValueFactory(new PropertyValueFactory("orderDateStr"));//映射
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_shortname.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        made_people.setCellValueFactory(new PropertyValueFactory("madePeople"));
        order_audit.setCellValueFactory(new PropertyValueFactory("orderAudit"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StockOutSale>() {
            @Override
            public void changed(ObservableValue<? extends StockOutSale> observableValue, StockOutSale oldItem, StockOutSale newItem) {
                if(newItem.getId() != null && !"".equals(newItem.getId())){
                    SaleOutboundMiniController.orderid = newItem.getId()+"";
                }
                if(newItem.getOutboundNo() != null && !"".equals(newItem.getOutboundNo())){
                    SaleOutboundMiniController.orderno = newItem.getOutboundNo();
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
        StageManager.CONTROLLER.remove("SaleOutboundOrderControllerMini");
        StageManager.CONTROLLER.remove("SaleDeliveryTrackControllerMiniOutboundBen");
        StageManager.CONTROLLER.remove("SaleDeliveryTrackControllerMiniOutboundEnd");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        if(orderid != null && !"".equals(orderid)){

            // 库存 - 销货出库单
            SaleOutboundOrderController order = (SaleOutboundOrderController) StageManager.CONTROLLER.get("SaleOutboundOrderControllerMini");
            if(order != null ){
                StockOutSale stockOutSale = iStockOutSaleService.selectByKey(Long.valueOf(orderid));
                if(stockOutSale != null){
                    order.setBasicVal(stockOutSale);
                }
            }

            // 销售 - 销货发货跟踪
            SaleDeliveryTrackController saleDeliveryTrackControllerMiniOutboundBen = (SaleDeliveryTrackController) StageManager.CONTROLLER.get("SaleDeliveryTrackControllerMiniOutboundBen");
            if(saleDeliveryTrackControllerMiniOutboundBen != null){
                saleDeliveryTrackControllerMiniOutboundBen.out_warehose.setText(orderno);
            }

            // 销售 - 销货发货跟踪
            SaleDeliveryTrackController saleDeliveryTrackControllerMiniOutboundEnd = (SaleDeliveryTrackController) StageManager.CONTROLLER.get("SaleDeliveryTrackControllerMiniOutboundEnd");
            if(saleDeliveryTrackControllerMiniOutboundEnd != null){
                saleDeliveryTrackControllerMiniOutboundEnd.out_warehose_end.setText(orderno);
            }
        }

        closeWin();
    }
}
