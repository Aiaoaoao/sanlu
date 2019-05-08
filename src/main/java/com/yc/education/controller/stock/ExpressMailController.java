package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.Relation;
import com.yc.education.model.basic.CompanyBasic;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.stock.ExpressMail;
import com.yc.education.model.stock.ExpressMailOutorder;
import com.yc.education.model.stock.MailOrders;
import com.yc.education.service.RelationService;
import com.yc.education.service.UsersService;
import com.yc.education.service.basic.CompanyBasicService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.service.stock.ExpressMailOutorderService;
import com.yc.education.service.stock.ExpressMailService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import com.yc.education.util.StageManager;
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
 * @ClassName ExpressMailCOntroller
 * @Description TODO 快递寄件
 * @Author QuZhangJing
 * @Date 2018/11/12 16:06
 * @Version 1.0
 */
@Controller
public class ExpressMailController extends BaseController implements Initializable {

    @Autowired
    private ExpressMailService expressMailService;

    @Autowired
    private CompanyBasicService companyBasicService;

    @Autowired  ISaleGoodsService iSaleGoodsService;  //销货单
    @Autowired  ISaleGoodsProductService iSaleGoodsProductService;  //销货产品详情
    @Autowired RelationService relationService;

    @Autowired
    private ExpressMailOutorderService expressMailOutorderService;

    /**
     * @Description 关联单据容器
     * @Author BlueSky
     * @Date 12:00 2019/4/28
     **/
    Relation relation = new Relation();
    //每个单据用来锁定关联数据的
    boolean relationLock = false;

    @FXML private Label fxmlStatus; //状态

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

    @FXML private VBox shyes; //审核

    @FXML private VBox shno; //取消审核




    @FXML private DatePicker maildate;//寄件日期
    @FXML private TextField mailorder;//快递单号
    @FXML private ComboBox company;//快递公司

    /**
     *          快递信息
     */
    @FXML private TextField mailnum;//数量(件数
    @FXML private TextField mailweight;//重量(KG)
    @FXML private ComboBox contenttype;//托寄内容类型
    @FXML private TextField content;//托寄内容
    @FXML private ComboBox paymethodtype;//支付方式类型
    @FXML private TextField paymethod;//支付方式
    @FXML private ComboBox freighttype;//本次运费类型
    @FXML private TextField freightprice;//本次运费
    @FXML private CheckBox ispay;//是否制单时已付
    @FXML private CheckBox ismonth;//是否月付
    @FXML private TextField account;//账号
    @FXML private TextField ensure;//保介费
    @FXML private TextField ensurepoint;//账号
    @FXML private ComboBox expressType; //类别
    @FXML private TextArea remarks;//备注
    @FXML private TextField createpeople;//建档人
    @FXML private TextField createdate;//建档日期
    @FXML private TextField updatepeople;//最后更新人
    @FXML private TextField updatedate;//最后更新日期
    @FXML private TextField shpeople;//审核人
    @FXML private TextField shdate;//审核日期


    /**
     *  寄件公司
     */

    @FXML public TextField mailid;//寄件公司编号
    @FXML public TextField mailcompany;//寄件公司
//    @FXML public ComboBox mailprovince;//省
//    @FXML public ComboBox mailcity;//市
//    @FXML public ComboBox mailarea;//区
//    @FXML public TextField mailaddress;//详细地址
    @FXML public ComboBox mailpeople;//寄件人
    @FXML public ComboBox mailphone;//联络方式
    @FXML public ComboBox mailaddr; //送货地址



    /**
     *  收件公司
     */

    @FXML public TextField collectid;//收件公司编号
    @FXML public TextField collectdes;//收件公司名称
    @FXML public TextField collectcompany;//收件公司
//    @FXML public ComboBox collectprovince;//省
//    @FXML public ComboBox collectcity;//市
//    @FXML public ComboBox collectarea;//区
//    @FXML public TextField collectaddress;//详细地址
    @FXML public ComboBox collectpeople;//收件人
    @FXML public ComboBox collectphone;//联络方式
    @FXML public ComboBox collectaddr;//送货地址

    @FXML private TableView orderTableView;

    @FXML private TableColumn  orderSort; //编号
    @FXML private TableColumn  seeorder; //编号
    @FXML private TableColumn  outorder; //编号
    @FXML private TableColumn  outremarks; //编号
    //快递寄件订单
    @FXML private TableView expressMailView;

    @FXML private TableColumn  findmailid; //编号

    @FXML private TableColumn  findmailorder;//快递单号

    @FXML private TableColumn  findmaildate;//寄件日期

    @FXML private TableColumn  findmailcompany;//快递公司

    @FXML private TableColumn  findmailaddress;//寄件地址

    @FXML private TableColumn  findmailremarks;//备注



    private int pageSize = 10;

    @FXML private VBox mail_find_fast;
    @FXML private VBox mail_find_prev;
    @FXML private VBox mail_find_next;
    @FXML private VBox mail_find_last;

    @FXML private TextField mail_textField_pageSize;

    @FXML private TextField mail_order_textField;


    @FXML TableView tableView3;

    @FXML TableColumn companyid;
    @FXML TableColumn companyisnum;
    @FXML TableColumn companydes;

    /*************************************************表单控件 End *************************************************************/

    @FXML private VBox find_fast;
    @FXML private VBox find_prev;
    @FXML private VBox find_next;
    @FXML private VBox find_last;

    @FXML private TextField textField_pageSize;

    @FXML private TextField company_order_textField;


    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    ObservableList<MailOrders> mailOrders = FXCollections.observableArrayList();



    /**
     * 生成询价订单流水号 (A1809280001)
     * 格式:英文字母(A)+日期(180928)+4位流水号(0001)
     * @return
     */
    public String createIsnum(String currentDate){
        String newDateStr=currentDate.substring(2,4)+currentDate.substring(currentDate.indexOf("-")+1,currentDate.indexOf("-")+3)+currentDate.substring(currentDate.lastIndexOf("-")+1,currentDate.lastIndexOf("-")+3);
        String maxIsnum = expressMailService.selectMaxIdnum(currentDate);
        if(maxIsnum != null){
            String maxAlphabet = maxIsnum.substring(0,1);
            //180928 0001
            int currenNumber = Integer.parseInt(maxIsnum.substring(maxIsnum.length()-4,maxIsnum.length()));
            for (int i = 0; i< NumberUtil.ALPHABET.length; i++){
                if(currenNumber == 9999 ){
                    if( maxAlphabet.equals(NumberUtil.ALPHABET[i])  ){
                        return NumberUtil.ALPHABET[i+1]+"0001";
                    }
                }
            }
            if(currenNumber>0 && currenNumber < 9){
                return maxAlphabet +newDateStr+"000"+(currenNumber+1);
            }else if(currenNumber >= 9 && currenNumber< 99){
                return maxAlphabet+newDateStr +"00"+(currenNumber+1);
            }else if(currenNumber >= 99 && currenNumber< 999){
                return maxAlphabet+newDateStr +"0"+(currenNumber+1);
            }else{
                return maxAlphabet+newDateStr+(currenNumber+1);
            }
        }
        return "A"+newDateStr+"0001";
    }



    public void findExpressMailSearch(){
        String pageSizes =  mail_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadExpressMail(1);
        }
    }


    public void findExpressMailPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadExpressMail(pageNum);
    }


    public void moreExpressMailClick(){

        stage.setTitle("快递寄件查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/express_mail_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
                /*stage.initStyle(StageStyle.UNDECORATED);
               DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadExpressMail(1);
    }

    /**
     * 现有库位查询
     */
    public void loadExpressMail(int pageNum){

        List<ExpressMail> expressMails = expressMailService.findExpressMail("".equals(mail_order_textField.getText()) || mail_order_textField.getText() == null ? "" : mail_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<ExpressMail> pageInfo = new PageInfo<>(expressMails);

        mail_find_fast.setUserData(1); //首页

        mail_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        mail_find_next.setUserData(pageInfo.getNextPage());//下一页

        mail_find_last.setUserData(pageInfo.getPages());//尾页
        ObservableList<ExpressMail> list = FXCollections.observableArrayList();


        findmailorder.setCellValueFactory(new PropertyValueFactory("mailorder"));
        findmaildate.setCellValueFactory(new PropertyValueFactory("maildatetime"));
        findmailcompany.setCellValueFactory(new PropertyValueFactory("company"));
        findmailaddress.setCellValueFactory(new PropertyValueFactory("mailaddress"));
        findmailremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        for (ExpressMail expressMail:expressMails) {

            expressMail.setMaildatetime(new SimpleDateFormat("yyyy-MM-dd").format(expressMail.getMaildate()));

            list.add(expressMail);

        }

        expressMailView.setItems(list); //tableview添加list

        expressMailView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ExpressMail>() {
            @Override
            public void changed(ObservableValue<? extends ExpressMail> observableValue, ExpressMail oldItem, ExpressMail newItem) {
                if(newItem.getMailorder()!= null && !("".equals(newItem.getMailorder()))){
                    expressMailView.setUserData(newItem.getId());
                }
            }
        });


        expressMailView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeExpressMailWin();
            }
        });


    }

    public void closeExpressMailWin(){
        long id =(long)expressMailView.getUserData();
        ExpressMail expressMail =  expressMailService.selectByKey(id);
        loadDate(expressMail);
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }





    //现有客户查询
    public void existingCustomers() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("ExpressMailController", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }




    public void findSearch(){
        String pageSizes =  textField_pageSize.getText();

        if(!"".equals(pageSizes) || pageSizes != null  ){
            pageSize = Integer.parseInt(pageSizes);
            loadMoreSupplier(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }




    }


    public void findPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreSupplier(pageNum);
    }


    public void moreCompanyBasicClick(){

        stage.setTitle("寄件公司查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/company_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreSupplier(1);
    }


    public void loadMoreSupplier(int pageNum){

        List<CompanyBasic> companyBasics =companyBasicService.selectCompanyBasic("".equals(company_order_textField.getText()) || company_order_textField.getText() == null ? "" : company_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<CompanyBasic> pageInfo = new PageInfo<>(companyBasics);

        find_fast.setUserData(1); //首页

        find_prev.setUserData(pageInfo.getPrePage()); //上一页

        find_next.setUserData(pageInfo.getNextPage());//下一页

        find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<CompanyBasic> list =FXCollections.observableArrayList();


        tableView3.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

//        companyid.setCellValueFactory(new PropertyValueFactory("id"));
        companyisnum.setCellValueFactory(new PropertyValueFactory("idnum"));
        companydes.setCellValueFactory(new PropertyValueFactory("comdes"));

        for (CompanyBasic supplierBasics1:companyBasics) {

            if(supplierBasics1.getIsstop() == 1){
                supplierBasics1.setComdes(supplierBasics1.getComdes()+"(停用)");
            }

            list.add(supplierBasics1);

        }

        tableView3.setItems(list); //tableview添加list

        tableView3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CompanyBasic>() {
            @Override
            public void changed(ObservableValue<? extends CompanyBasic> observableValue, CompanyBasic oldItem, CompanyBasic newItem) {
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    tableView3.setUserData(newItem.getId());
                }
            }
        });


        tableView3.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });


    }

    public void closeSureWin(){
        long id =(long)tableView3.getUserData();
        CompanyBasic companyBasic =  companyBasicService.selectByKey(id);
        mailid.setText(companyBasic.getIdnum());
        mailcompany.setText(companyBasic.getComname());
        mailphone.getItems().setAll(companyBasic.getPhone());
        mailphone.getSelectionModel().select(0);
        mailaddr.getItems().setAll(companyBasic.getAddress());
        mailaddr.getSelectionModel().select(0);
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


        List<ExpressMail> expressMails = expressMailService.findExpressMail(pageNum,1);

        PageInfo<ExpressMail> pageInfo = new PageInfo<>(expressMails);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);


        if(expressMails != null){
            expressMails.forEach(inquiryOrder ->loadDate(inquiryOrder));
        }else{
            loadOrders(0);
        }





    }


    /**
     * 向控件加载数据
     * @param expressMail
     */
    public void loadDate(ExpressMail expressMail){


        maildate.setValue(LocalDateTime.ofInstant(expressMail.getMaildate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        mailorder.setUserData(expressMail.getId());

        mailorder.setText(expressMail.getMailorder());

        company.getItems().setAll("顺丰快递");


        for(int i=0,len=company.getItems().size();i<len;i++){
            if(company.getItems().get(i).equals(expressMail.getCompany())){
                company.getSelectionModel().select(i);
            }else{
                company.getSelectionModel().select(0);
            }
        }

        mailnum.setText(expressMail.getMailnum().toString());

        mailweight.setText(expressMail.getMailweight().toString());

        content.setText(expressMail.getContent());

        paymethod.setText(expressMail.getPaymethod());

        freightprice.setText(expressMail.getFreightprice().toString());


        if(expressMail.getIspay() == 1)ispay.setSelected(true);

        if(expressMail.getIsmonth() == 1)ismonth.setSelected(true);

        account.setText(expressMail.getAccount());

        ensure.setText(expressMail.getEnsure().toString());

        ensurepoint.setText(expressMail.getEnsurepoint().toString());

        remarks.setText(expressMail.getRemarks());

        createpeople.setText(expressMail.getCreatepeople());

        createdate.setText(expressMail.getCreatedate());

        updatepeople.setText(expressMail.getUpdatepeople());

        updatedate.setText(expressMail.getUpdatedate());

        shpeople.setText(expressMail.getShpeople());

        shdate.setText(expressMail.getShdate());

//        @FXML private ComboBox contenttype;//托寄内容类型
//        @FXML private ComboBox paymethodtype;//支付方式类型
//        @FXML private ComboBox freighttype;//本次运费类型


        mailid.setText(expressMail.getMailid());
        mailcompany.setText(expressMail.getMailcompany());

//        selectedComboBox(mailprovince,expressMail.getMailprovince());
//        selectedComboBox(mailcity,expressMail.getMailcity());
//        selectedComboBox(mailarea,expressMail.getMailarea());

            mailaddr.getItems().setAll(expressMail.getMailaddress());
            mailaddr.getSelectionModel().select(0);
            mailpeople.getItems().setAll(expressMail.getMailpeople());
            mailpeople.getSelectionModel().select(0);
            mailphone.getItems().setAll(expressMail.getMailphone());
            mailphone.getSelectionModel().select(0);
//        mailaddress.setText(expressMail.getMailaddress());
//        mailpeople.setDisable(!status);
//        mailphone.setDisable(!status);

        collectid.setText(expressMail.getCollectid());
        collectdes.setText(expressMail.getCollectdes());
        collectcompany.setText(expressMail.getCollectcompany());

//        selectedComboBox(collectprovince,expressMail.getCollectprovince());
//        selectedComboBox(collectcity,expressMail.getCollectcity());
//        selectedComboBox(collectarea,expressMail.getCollectarea());

        collectaddr.getItems().setAll(expressMail.getCollectaddress());
        collectaddr.getSelectionModel().select(0);
        collectpeople.getItems().setAll(expressMail.getCollectpeople());
        collectpeople.getSelectionModel().select(0);
        collectphone.getItems().setAll(expressMail.getCollectphone());
        collectphone.getSelectionModel().select(0);
//        collectaddress.setText(expressMail.getCollectaddress());
//        collectpeople.setDisable(!status);
//        collectphone.setDisable(!status);

        loadOrders(expressMail.getId());

        //权限管理
        matchingPermissions("快递寄件",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox);

    }






    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){

        maildate.setDisable(!status);

        mailorder.setDisable(!status);

        mailorder.setDisable(!status);

        company.setDisable(!status);

        mailnum.setDisable(!status);

        mailweight.setDisable(!status);

        content.setDisable(!status);

        paymethod.setDisable(!status);

        freightprice.setDisable(!status);

        ispay.setDisable(!status);

        ismonth.setDisable(!status);

        account.setDisable(!status);

        expressType.setDisable(!status);

        ensure.setDisable(!status);

        ensurepoint.setDisable(!status);

        remarks.setDisable(!status);

        createpeople.setDisable(!status);

        createdate.setDisable(!status);

        updatepeople.setDisable(!status);

        updatedate.setDisable(!status);

        shpeople.setDisable(!status);

        shdate.setDisable(!status);

        contenttype.setDisable(!status);

        paymethodtype.setDisable(!status);

        freighttype.setDisable(!status);

        mailid.setDisable(!status);
        mailcompany.setDisable(!status);
//        mailprovince.setDisable(!status);
//        mailcity.setDisable(!status);
//        mailarea.setDisable(!status);
//        mailaddress.setDisable(!status);
        mailaddr.setDisable(!status);
//        mailaddr.setEditable(status);
        mailpeople.setDisable(!status);
//        mailpeople.setEditable(status);
        mailphone.setDisable(!status);
//        mailphone.setEditable(status);

        collectid.setDisable(!status);
        collectdes.setDisable(!status);
        collectcompany.setDisable(!status);
//        collectprovince.setDisable(!status);
//        collectcity.setDisable(!status);
//        collectarea.setDisable(!status);
//        collectaddress.setDisable(!status);
         collectaddr.setDisable(!status);
//        collectaddr.setEditable(status);
        collectpeople.setDisable(!status);
        collectphone.setDisable(!status);

    }



    /**
     * 清空
     */
    public void clearValue(){



        maildate.setValue(null);

        mailorder.setText(NumberUtil.NULLSTRING);

        mailorder.setText(NumberUtil.NULLSTRING);

        company.getSelectionModel().select(0);

        mailnum.setText(NumberUtil.NULLSTRING);

        mailweight.setText(NumberUtil.NULLSTRING);

        content.setText(NumberUtil.NULLSTRING);

        paymethod.setText(NumberUtil.NULLSTRING);

        freightprice.setText(NumberUtil.NULLSTRING);

        ispay.setText(NumberUtil.NULLSTRING);

        ismonth.setText(NumberUtil.NULLSTRING);

        account.setText(NumberUtil.NULLSTRING);

        ensure.setText(NumberUtil.NULLSTRING);

        ensurepoint.setText(NumberUtil.NULLSTRING);

        remarks.setText(NumberUtil.NULLSTRING);

        createpeople.setText(NumberUtil.NULLSTRING);

        createdate.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

        updatedate.setText(NumberUtil.NULLSTRING);

        shpeople.setText(NumberUtil.NULLSTRING);

        shdate.setText(NumberUtil.NULLSTRING);

        contenttype.getSelectionModel().select(0);

        paymethodtype.getSelectionModel().select(0);

        freighttype.getSelectionModel().select(0);


        mailid.setText(NumberUtil.NULLSTRING);
        mailcompany.setText(NumberUtil.NULLSTRING);
//        mailprovince.getSelectionModel().select(0);
//        mailcity.getSelectionModel().select(0);
//        mailarea.getSelectionModel().select(0);
//        mailaddress.setText(NumberUtil.NULLSTRING);
        mailaddr.getSelectionModel().select(0);
        mailpeople.getSelectionModel().select(0);
        mailphone.getSelectionModel().select(0);

        collectid.setText(NumberUtil.NULLSTRING);
        collectdes.setText(NumberUtil.NULLSTRING);
        collectcompany.setText(NumberUtil.NULLSTRING);
//        collectprovince.getSelectionModel().select(0);
//        collectcity.getSelectionModel().select(0);
//        collectarea.getSelectionModel().select(0);
//        collectaddress.setText(NumberUtil.NULLSTRING);
        collectaddr.getSelectionModel().select(0);
        collectpeople.getSelectionModel().select(0);
        collectphone.getSelectionModel().select(0);

    }


    public void loadOrders(long orderid){


        List<ExpressMailOutorder> expressMailOutorders = expressMailOutorderService.findExpressMailOutorders(orderid);


//        @FXML private TableView orderTableView;


        orderSort.setCellValueFactory(new PropertyValueFactory("sort"));
        seeorder.setCellValueFactory(new PropertyValueFactory("outOrders"));
        outorder.setCellValueFactory(new PropertyValueFactory("mailOrder"));
        outremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        if(expressMailOutorders.size() >0 && expressMailOutorders != null){

            for(int i=0,len=expressMailOutorders.size();i<len;i++){

                expressMailOutorders.get(i).setSort(i+1);

                MailOrders mailOrderss= new MailOrders();

                mailOrderss.setId(expressMailOutorders.get(i).getId());
                mailOrderss.setSort(i+1+"");
                mailOrderss.setMailOrder(expressMailOutorders.get(i).getOutorder());
                mailOrderss.setOutOrders(expressMailOutorders.get(i).getOrders());
                mailOrderss.setRemarks(expressMailOutorders.get(i).getRemarks());
                mailOrders.add(mailOrderss);
            }

        }
        orderTableView.setItems(mailOrders);

    }




    private void saveMailOrders(long mailid){


        if(mailOrders.size() > 0  && mailOrders != null  ){

            for (MailOrders mailOrders:mailOrders) {

                ExpressMailOutorder expressMailOutorder = new ExpressMailOutorder();
                expressMailOutorder.setOrders(mailOrders.getMailOrder());
                expressMailOutorder.setOutorder(mailOrders.getOutOrders());
                expressMailOutorder.setRemarks(mailOrders.getRemarks());

                expressMailOutorderService.save(expressMailOutorder);
            }

        }

    }


    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) mailorder.getUserData();

            int rows = expressMailService.delete(id);
            if (rows > 0) {
                findInquiry(1); //初始化第一条数据
            }
            relationService.deleteRelation("快递寄件",id);
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
        setDatePicker(maildate);
        createPeople(createpeople,createdate);//制单人
    }





    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改

        LocalDate orderDate =maildate.getValue();

        if(orderDate == null){
            alert_informationDialog("请填写制单日期!");
            return;
        }


        ExpressMail expressMail = new ExpressMail(
                new Date(java.sql.Date.valueOf(orderDate).getTime()),
                mailorder.getText(),
                company.getItems().size()==0?"":company.getSelectionModel().getSelectedItem().toString(),
                "".equals(mailnum.getText())?0:Integer.parseInt(mailnum.getText()),
                "".equals(mailweight.getText())?0.00:Integer.parseInt(mailnum.getText()),
                contenttype.getSelectionModel().getSelectedIndex()+1,
                content.getText(),
                paymethodtype.getSelectionModel().getSelectedIndex()+1,
                paymethod.getText(),
                freighttype.getSelectionModel().getSelectedIndex()+1,
                "".equals(freightprice.getText())?0:Double.parseDouble(freightprice.getText()),
                ispay.isSelected()==true?1:0,
                ismonth.isSelected()==true?1:0,
                account.getText(),
                "".equals(ensure.getText())?0:Double.parseDouble(ensure.getText()),
                "".equals(ensurepoint.getText())?0:Double.parseDouble(ensurepoint.getText()),
                remarks.getText(),
                createpeople.getText(),
                createdate.getText(),
                updatepeople.getText(),
                updatedate.getText(),
                shpeople.getText(),
                shdate.getText(),
                0,
                mailid.getText(),
                mailcompany.getText(),
               "",
                "",
                "",
                mailaddr.getItems().size()==0 || "".equals(mailaddr.getSelectionModel().getSelectedItem())  ? "" :mailaddr.getSelectionModel().getSelectedItem().toString(),
                mailpeople.getItems().size()==0 || "".equals(mailpeople.getSelectionModel().getSelectedItem())  ? "" :mailpeople.getSelectionModel().getSelectedItem().toString(),
                mailphone.getItems().size()==0 || "".equals(mailphone.getSelectionModel().getSelectedItem())   ? "" :mailphone.getSelectionModel().getSelectedItem().toString(),
                collectid.getText(),
                collectdes.getText(),
                collectcompany.getText(),
                "",
                "",
                "",
                collectaddr.getItems().size()==0 || "".equals(collectaddr.getSelectionModel().getSelectedItem()) ?"":collectaddr.getSelectionModel().getSelectedItem().toString(),
                collectpeople.getItems().size()==0 || "".equals(collectpeople.getSelectionModel().getSelectedItem()) ?"":collectpeople.getSelectionModel().getSelectedItem().toString(),
                collectphone.getItems().size()==0 || "".equals(collectphone.getSelectionModel().getSelectedItem()) ?"":collectphone.getSelectionModel().getSelectedItem().toString()
        );




        int rows =0;
        if(submitStuts==1){
            //产生订单编号
            String orderNum = createIsnum(orderDate.toString());
            mailorder.setText(orderNum);
            expressMail.setMailorder(orderNum);
            rows = expressMailService.save(expressMail);

            if(relationLock){
                // 添加关联单据
                relation.setRelationId(expressMail.getId());
                relation.setRelationName("快递寄件");
                relationService.save(relation);
            }


            if("销货出库单".equals(relation.getBeRelationName()) && !"".equals(relation.getTempStr())){
                SaleGoods saleGoods = new SaleGoods();
                saleGoods.setId(Long.valueOf(relation.getTempStr()));
                saleGoods.setImportExpressAudit(true);
                iSaleGoodsService.updateNotNull(saleGoods);
            }
            saveMailOrders(expressMail.getId());
        }else if(submitStuts ==2){
            expressMail.setId((long)mailorder.getUserData());
            rows = expressMailService.updateNotNull(expressMail);
        }

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);



        this.loadDate(expressMail); //重新加载数据


        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

    }



    /**
     * 审核通过
     *
     * 修改审核人、审核日期
     *
     */
    public void shButtonSuccess(){

        shno.setDisable(false);

        shyes.setDisable(true);

        lastUpdatePeopel(shpeople,shdate);

        updateOrderStatus(1);

    }

    /**
     * 取消审核
     *
     * 需查看 单据是否被其他地方调用
     *
     */
    public void shButtonCancel(){
        shno.setDisable(true);
        shyes.setDisable(false);
        cancelSh(shpeople,shdate);
        updateOrderStatus(0);
    }

    /**
     * 修改审核状态
     * @param status  0、未审核    1、审核通过
     */
    public void updateOrderStatus(int status){
        long id =  (long)mailorder.getUserData();
        ExpressMail expressMail = expressMailService.selectByKey(id);
        expressMail.setShstatus(status);

        if(status == 1){
            expressMail.setShpeople(getAdminName());
            expressMail.setShdate(getCurrentDate());

            //已审核不得修改和删除
            updatevbox.setDisable(true);
            deletevbox.setDisable(true);

        }else{
            expressMail.setShpeople(NumberUtil.NULLSTRING);
            expressMail.setShdate(NumberUtil.NULLSTRING);

            //未审核可以修改和删除
            updatevbox.setDisable(false);
            deletevbox.setDisable(false);
        }

        expressMailService.updateNotNull(expressMail);
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String newStr = location.toString();

        int index = newStr.indexOf("express_mail.fxml");

        if(index != -1) {

            relation = new Relation();
            relationLock = false;
            company.getItems().setAll("顺丰快递");
            // 正常单据
            expressType.getItems().setAll("快件","慢件");
            expressType.getSelectionModel().select(0);

//            setComboBox(20,paymethodtype,0);

            paymethodtype.getItems().setAll("寄方付","收方付","第三方付");
            paymethodtype.getSelectionModel().select(0);

            setComboBox(7,freighttype,0);

            //  contenttype  托寄内容

            contenttype.getItems().setAll("资料","包裹","其他");
            contenttype.getSelectionModel().select(0);


            //销货出库单
            SaleGoods saleGoods = StageManager.saleGoods;

            if(saleGoods != null){
                loadOrders(0);

                // 添加关联单据
                relation.setBeRelationId(saleGoods.getStockOutSale().getId());
                relation.setBeRelationName("销货出库单");
                relation.setTempStr(saleGoods.getId().toString());
                relationLock = true;
                insert();
                collectid.setText(saleGoods.getCustomerNo());
                collectcompany.setText(saleGoods.getCustomerNoStr());
                collectdes.setText(saleGoods.getCustomerNoStr());
                collectaddr.getSelectionModel().select(saleGoods.getShippingAddress());
                collectphone.getSelectionModel().select(saleGoods.getPhone());
                collectpeople.getSelectionModel().select(saleGoods.getContact());

                MailOrders mailOrdersModel = new MailOrders();
                mailOrdersModel.setId(0L);
                mailOrdersModel.setMailOrder(saleGoods.getStockOutSale().getOutboundNo());
                mailOrdersModel.setOutOrders(saleGoods.getStockOutSale().getOutboundNo());
                mailOrdersModel.setRemarks("");
                mailOrders.add(mailOrdersModel);
            }else{


//            bindCityListen(mailprovince,mailcity,mailarea);
//            bindCityListen(collectprovince,collectcity,collectarea);

                findInquiry(1);
            }
        }


    }
}
