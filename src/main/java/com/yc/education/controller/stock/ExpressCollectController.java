package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.CompanyBasic;
import com.yc.education.model.stock.ExpressCollect;
import com.yc.education.model.stock.ExpressMail;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.service.stock.ExpressCollectService;
import com.yc.education.service.stock.PurchaseStockService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName ExpressCollectController
 * @Description TODO 快递收件
 * @Author QuZhangJing
 * @Date 2018/11/9 17:37
 * @Version 1.0
 */
@Controller
public class ExpressCollectController extends BaseController implements Initializable {

    @Autowired
    private ExpressCollectService expressCollectService; //快递收件 Service
    @Autowired
    private PurchaseStockService purchaseStockService; //入库单 Service



    @FXML
    private Label fxmlStatus; //状态

    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页


    @FXML private VBox clearvbox; //清除

    @FXML private VBox submitvbox; //提交

    @FXML private VBox insertvbox; //新增

    @FXML private VBox updatevbox; //修改

    @FXML private VBox deletevbox; //删除

    @FXML private VBox printingvbox; //打印



    @FXML private DatePicker collectdate; //收件日期

    @FXML private TextField collectorder; //快递单号

    @FXML private ComboBox seeorder; //入库参考单据

    @FXML private TextField stockorder; //入库单号

    @FXML private ComboBox company; //快递公司

    @FXML private TextField name; //寄件人名称

    @FXML private TextField address; //地址

    @FXML private ComboBox freighttype; //本次运费类型

    @FXML private TextField freightprice; //本次运费

    @FXML private CheckBox ispay; //是否制定时已付 0、未付  1、已付

    @FXML private TextArea remarks; //备注

    @FXML private TextField createpeople; //建档人

    @FXML private TextField createdate; //建档日期

    @FXML private TextField updatepeople; //最后修改人

    @FXML private TextField updatedate; //最后修改日期



    @FXML private TableView expressCollectView;//快递收件
    @FXML private TableColumn findcollectid; //
    @FXML private TableColumn findcollectorder; //快递单号
    @FXML private TableColumn findcollectdate; //收件日期
    @FXML private TableColumn findcollectcompany; //快递公司
    @FXML private TableColumn findcollectname;  //寄件人姓名
    @FXML private TableColumn findcollectseeisnum; //出库参考单
    @FXML private TableColumn findcollectseeorder; //参考单据单
    @FXML private TableColumn findcollectaddress; //寄件人地址
    @FXML private TableColumn findcollectremarks; //备注



    //采购入库单
    @FXML private TableView purchaseStockView; //采购入库单
    @FXML private TableColumn findid; //  编号
    @FXML private TableColumn findstockorder; //采购入库单
    @FXML private TableColumn findcreatedate; //制单日期
    @FXML private TableColumn findstocksuppliernum; //供应商编号
    @FXML private TableColumn findstocksuppliername; //供应商简称
    @FXML private TableColumn findboxnum; //装箱单号
    @FXML private TableColumn findremarks; //备注


    @FXML private VBox collect_find_fast;
    @FXML private VBox collect_find_prev;
    @FXML private VBox collect_find_next;
    @FXML private VBox collect_find_last;

    @FXML private TextField collect_textField_pageSize;

    @FXML private TextField collect_order_textField;


    @FXML private VBox purchaseStock_find_fast;
    @FXML private VBox purchaseStock_find_prev;
    @FXML private VBox purchaseStock_find_next;
    @FXML private VBox purchaseStock_find_last;

    @FXML private TextField purchaseStock_textField_pageSize;

    @FXML private TextField purchaseStock_order_textField;

    private int pageSize = 10;

    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();



    /**
     * 生成询价订单流水号 (A1809280001)
     * 格式:英文字母(A)+日期(180928)+4位流水号(0001)
     * @return
     */
//    public String createIsnum(String currentDate){
//        String newDateStr=currentDate.substring(2,4)+currentDate.substring(currentDate.indexOf("-")+1,currentDate.indexOf("-")+3)+currentDate.substring(currentDate.lastIndexOf("-")+1,currentDate.lastIndexOf("-")+3);
//        String maxIsnum = expressCollectService.selectMaxIdnum(currentDate);
//        if(maxIsnum != null){
//            String maxAlphabet = maxIsnum.substring(0,1);
//            //180928 0001
//            int currenNumber = Integer.parseInt(maxIsnum.substring(maxIsnum.length()-4,maxIsnum.length()));
//            for (int i = 0; i< NumberUtil.ALPHABET.length; i++){
//                if(currenNumber == 9999 ){
//                    if( maxAlphabet.equals(NumberUtil.ALPHABET[i])  ){
//                        return NumberUtil.ALPHABET[i+1]+"0001";
//                    }
//                }
//            }
//            if(currenNumber>0 && currenNumber < 9){
//                return maxAlphabet +newDateStr+"000"+(currenNumber+1);
//            }else if(currenNumber >= 9 && currenNumber< 99){
//                return maxAlphabet+newDateStr +"00"+(currenNumber+1);
//            }else if(currenNumber >= 99 && currenNumber< 999){
//                return maxAlphabet+newDateStr +"0"+(currenNumber+1);
//            }else{
//                return maxAlphabet+newDateStr+(currenNumber+1);
//            }
//        }
//        return "A"+newDateStr+"0001";
//    }



    public void findExpressCollectSearch(){
        String pageSizes =  collect_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadExpressCollect(1);
        }
    }


    public void findExpressCollectPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadExpressCollect(pageNum);
    }

    /**
     * 快递收件查询
     */
    public void moreExpressCollectClick(){
        stage.setTitle("快递收件查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/express_collect_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
                /*stage.initStyle(StageStyle.UNDECORATED);
               DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadExpressCollect(1);
    }


    public void loadExpressCollect(int pageNum){


        List<ExpressCollect> expressCollects  =  expressCollectService.findExpressCollect("".equals(collect_order_textField.getText()) || collect_order_textField.getText() == null ? "" : collect_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<ExpressCollect> pageInfo = new PageInfo<>(expressCollects);

        collect_find_fast.setUserData(1); //首页

        collect_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        collect_find_next.setUserData(pageInfo.getNextPage());//下一页

        collect_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<ExpressCollect> list =FXCollections.observableArrayList();

        findcollectid.setCellValueFactory(new PropertyValueFactory("id"));
        findcollectorder.setCellValueFactory(new PropertyValueFactory("collectorder"));
        findcollectdate.setCellValueFactory(new PropertyValueFactory("collectdatatime"));
        findcollectcompany.setCellValueFactory(new PropertyValueFactory("company"));
        findcollectname.setCellValueFactory(new PropertyValueFactory("name"));
        findcollectseeisnum.setCellValueFactory(new PropertyValueFactory("seeorder"));
        findcollectseeorder.setCellValueFactory(new PropertyValueFactory("stockorder"));
        findcollectaddress.setCellValueFactory(new PropertyValueFactory("address"));
        findcollectremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        if(expressCollects.size()>0){

            for (ExpressCollect expressCollect:expressCollects) {
                expressCollect.setCollectdatatime(DateToString(expressCollect.getCollectdate()));
                list.add(expressCollect);
            }

        }

        expressCollectView.setItems(list);


        expressCollectView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ExpressCollect>() {
            @Override
            public void changed(ObservableValue<? extends ExpressCollect> observableValue, ExpressCollect oldItem, ExpressCollect newItem) {
                if(newItem.getCollectorder() != null && !("".equals(newItem.getCollectorder()))){
                    expressCollectView.setUserData(newItem.getId());
                }
            }
        });


        expressCollectView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeExpressCollectWin();
            }
        });





    }




    public void closeExpressCollectWin(){
        long id =(long)expressCollectView.getUserData();
        ExpressCollect expressCollect = expressCollectService.selectByKey(id);
        loadDate(expressCollect);
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }



    public void findPurchaseStockSearch(){
        String pageSizes =  purchaseStock_textField_pageSize.getText();

        if(!"".equals(pageSizes) || pageSizes != null  ){
            pageSize = Integer.parseInt(pageSizes);
            loadMorePurchaseStock(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }




    }


    public void findPurchaseStockPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMorePurchaseStock(pageNum);
    }

    public void morePurchaseStockClick(){

        stage.setTitle("现有入库单查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/expresscollect_stock_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
                /*stage.initStyle(StageStyle.UNDECORATED);
               DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMorePurchaseStock(1);
    }



    public void loadMorePurchaseStock(int pageNum){

        List<PurchaseStock> purchaseStocks  =  purchaseStockService.findPurchaseStock("".equals(purchaseStock_order_textField.getText()) || purchaseStock_order_textField.getText() == null ? "" : purchaseStock_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<PurchaseStock> pageInfo = new PageInfo<>(purchaseStocks);

        purchaseStock_find_fast.setUserData(1); //首页

        purchaseStock_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        purchaseStock_find_next.setUserData(pageInfo.getNextPage());//下一页

        purchaseStock_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<PurchaseStock> list =FXCollections.observableArrayList();

        findid.setCellValueFactory(new PropertyValueFactory("id"));
        findstockorder.setCellValueFactory(new PropertyValueFactory("stockorder"));
        findcreatedate.setCellValueFactory(new PropertyValueFactory("createdate"));
        findstocksuppliernum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        findstocksuppliername.setCellValueFactory(new PropertyValueFactory("suppliername"));
        findboxnum.setCellValueFactory(new PropertyValueFactory("boxnum"));
        findremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        if(purchaseStocks.size()>0){

            for (PurchaseStock purchaseStock:purchaseStocks) {
                list.add(purchaseStock);
            }

        }

        purchaseStockView.setItems(list);


        purchaseStockView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PurchaseStock>() {
            @Override
            public void changed(ObservableValue<? extends PurchaseStock> observableValue, PurchaseStock oldItem, PurchaseStock newItem) {
                if(newItem.getStockorder() != null && !("".equals(newItem.getStockorder()))){
                    purchaseStockView.setUserData(newItem.getId());
                }
            }
        });


        purchaseStockView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closePurchaseStockWin();
            }
        });


    }


    public void closePurchaseStockWin(){
        long id =(long)purchaseStockView.getUserData();
        PurchaseStock purchaseStock =  purchaseStockService.selectByKey(id);
        //入库单号
        stockorder.setText(purchaseStock.getStockorder());
        coseWin();
    }








    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));
        findInquiry(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);//修改为修改状态
    }




    /**
     * 分页查询盘点订单
     */
    public void findInquiry(int pageNum){


        List<ExpressCollect> expressCollects = expressCollectService.findExpressCollect(pageNum,1);

        PageInfo<ExpressCollect> pageInfo = new PageInfo<>(expressCollects);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页





        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);


        if(expressCollects != null){
            expressCollects.forEach(inquiryOrder ->loadDate(inquiryOrder));
        }

        if(expressCollects.size() == 0  || expressCollects == null ){
            /*loadCheckStockProduct(0);*/
            //权限管理
            seeorder.getItems().setAll("库存异动作业");
            company.getItems().setAll("顺丰快递");

            matchingPermissions("快递收件",insertvbox,deletevbox,updatevbox,printingvbox,clearvbox);
        }



    }


    /**
     * 向控件加载数据
     * @param expressCollect
     */
    public void loadDate(ExpressCollect expressCollect){



        collectdate.setValue(LocalDateTime.ofInstant(expressCollect.getCollectdate().toInstant(), ZoneId.systemDefault()).toLocalDate());


        collectorder.setUserData(expressCollect.getId());

        collectorder.setText(expressCollect.getCollectorder());

        seeorder.getItems().setAll("库存异动作业");

        for(int i=0,len=seeorder.getItems().size();i<len;i++){
            if(seeorder.getItems().get(i).equals(expressCollect.getSeeorder())){
                seeorder.getSelectionModel().select(i);
            }
        }


        company.getItems().setAll("顺丰快递");


        for(int i=0,len=company.getItems().size();i<len;i++){
            if(company.getItems().get(i).equals(expressCollect.getCompany())){
                company.getSelectionModel().select(i);
            }
        }

        stockorder.setText(expressCollect.getStockorder());

        name.setText(expressCollect.getName());

        address.setText(expressCollect.getAddress());

        freightprice.setText(Double.toString(expressCollect.getFreightprice()));

        int payNum = expressCollect.getIspay();

        if(payNum == 1)ispay.setSelected(true);

        remarks.setText(expressCollect.getRemarks());

        createpeople.setText(expressCollect.getCreatepeople());

        createdate.setText(expressCollect.getCreatedate());

        updatepeople.setText(expressCollect.getUpdatepeople());

        updatedate.setText(expressCollect.getUpdatedate());
        //权限管理
        matchingPermissions("快递收件",insertvbox,deletevbox,updatevbox,printingvbox,clearvbox);

    }




    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){

        collectdate.setDisable(!status);

        collectorder.setDisable(!status);

        collectorder.setDisable(!status);

        seeorder.setDisable(!status);

        company.setDisable(!status);

        stockorder.setDisable(!status);

        name.setDisable(!status);

        address.setDisable(!status);

        freighttype.setDisable(!status);

        freightprice.setDisable(!status);

        ispay.setDisable(!status);

        remarks.setDisable(!status);

        createpeople.setDisable(!status);

        createdate.setDisable(!status);

        updatepeople.setDisable(!status);

        updatedate.setDisable(!status);

    }



    /**
     * 清空
     */
    public void clearValue(){

        collectdate.setValue(null);

        collectorder.setText(NumberUtil.NULLSTRING);

        seeorder.getSelectionModel().select(0);

        stockorder.setText(NumberUtil.NULLSTRING);

        name.setText(NumberUtil.NULLSTRING);

        address.setText(NumberUtil.NULLSTRING);

        freighttype.getSelectionModel().select(0);

        freightprice.setText(NumberUtil.NULLSTRING);

        ispay.setText(NumberUtil.NULLSTRING);

        remarks.setText(NumberUtil.NULLSTRING);

        createpeople.setText(NumberUtil.NULLSTRING);

        createdate.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

        updatedate.setText(NumberUtil.NULLSTRING);



    }

    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) collectorder.getUserData();

            int rows = expressCollectService.delete(id);
            if (rows > 0) {
                findInquiry(1); //初始化第一条数据
            }
            NumberUtil.changeStatus(fxmlStatus, 0); //修改为修改状态
            this.changeEditable(false);
        }
    }



    /**
     * 修改
     */
    public void update(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.UPDATE);//修改为修改状态
        this.changeEditable(true);
        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);
        lastUpdatePeopel(updatepeople,updatedate);
        //联系人空白行
    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件
        submitvbox.setDisable(false);
        updatevbox.setDisable(true);
        deletevbox.setDisable(true);
        insertvbox.setDisable(true);
        setDatePicker(collectdate);
        createPeople(createpeople,createdate);//制单人
    }


    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改

        LocalDate orderDate =collectdate.getValue();

        if(orderDate == null){
            alert_informationDialog("请填写收件日期!");
            return;
        }

        if("".equals(collectorder.getText()) || collectorder.getText() == null ){
            alert_informationDialog("请填写快递单号!");
            return;
        }


        ExpressCollect expressCollect = new ExpressCollect(
                new Date(java.sql.Date.valueOf(orderDate).getTime()),
                collectorder.getText(),
                seeorder.getItems().size()==0?"":seeorder.getSelectionModel().getSelectedItem().toString(),
                stockorder.getText(),
                company.getItems().size()==0?"":company.getSelectionModel().getSelectedItem().toString(),
                name.getText(),
                address.getText(),
                freighttype.getSelectionModel().getSelectedIndex(),
                "".equals(freightprice.getText())?0.00:Double.parseDouble(freightprice.getText()),
                ispay.isSelected()==true?1:0,
                remarks.getText(),
                createpeople.getText(),
                createdate.getText(),
                updatepeople.getText(),
                updatedate.getText()
        );


        int rows =0;
        if(submitStuts==1){
            //产生订单编号
//            String orderNum = createIsnum(orderDate.toString());
//            collectorder.setText(orderNum);
//            expressCollect.setCollectorder (orderNum);
            rows = expressCollectService.save(expressCollect);
        }else if(submitStuts ==2){
            expressCollect.setId((long)collectorder.getUserData());
            rows = expressCollectService.updateNotNull(expressCollect);
        }

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);



        this.loadDate(expressCollect); //重新加载数据


        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {



        String newStr = location.toString();

        int index = newStr.indexOf("express_collect.fxml");

        if(index != -1) {

            setComboBox(7,freighttype,0);

            findInquiry(1);
        }


    }
}
