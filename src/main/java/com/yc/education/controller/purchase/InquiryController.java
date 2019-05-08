
package com.yc.education.controller.purchase;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.Relation;
import com.yc.education.model.basic.*;
import com.yc.education.model.purchase.*;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.sale.SaleOfferProduct;
import com.yc.education.model.sale.SaleQuotation;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.*;
import com.yc.education.service.purchase.InquiryDescriptionService;
import com.yc.education.service.purchase.InquiryOrderService;
import com.yc.education.service.purchase.InquiryProductService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.service.sale.ISaleOfferProductService;
import com.yc.education.service.sale.ISaleQuotationService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import com.yc.education.util.StageManager;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName InquiryController
 * @Description TODO  询价单
 * @Author QuZhangJing
 * @Date 2018/9/21 16:37
 * @Version 1.0
 */
@Controller
public class InquiryController extends BaseController implements Initializable {

    @Autowired
    private InquiryOrderService inquiryOrderService; //询价单 Service
    @Autowired
    private InquiryProductService inquiryProductService; //询价产品 Service
    @Autowired
    private SupplierBasicService supplierBasicService; //供应商 Service
    @Autowired
    private InquiryDescriptionService inquiryDescriptionService; //询价单备注及说明 Service
    @Autowired
    private SupplierContactService supplierContactService;//供应商联系人 Service
    @Autowired
    private ProductBasicService productBasicService; //产品基本资料 Service
    @Autowired
    private DataSettingService dataSettingService; //资料设定 Service
    @Autowired
    private EmployeeBasicService employeeBasicService;//员工Service
    @Autowired
    private ISaleQuotationService iSaleQuotationService;
    @Autowired
    private ISaleOfferProductService iSaleOfferProductService;
    @Autowired
    private ISaleGoodsService iSaleGoodsService;
    @Autowired
    private ISaleGoodsProductService iSaleGoodsProductService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private SupplierPurchaserService supplierPurchaserService;




    long changeId =0;

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

    @FXML private VBox shyes; //审核

    @FXML private VBox shno; //取消审核

    @FXML private VBox importData;//导入

    @FXML private VBox importOut; //导出

    @FXML private VBox printingvbox; //打印




    @FXML private DatePicker createdate; //制单日期

    @FXML private TextField orders; //询价订单

    @FXML private TextField suppliernum; //供应商编号

    @FXML private TextField suppliername; //供应商简称

    @FXML private ComboBox tax; //税别

    @FXML private ComboBox currency; //币别

    @FXML private DatePicker replydate; //回复日期

    @FXML private DatePicker validdate; //有效期至

    @FXML private ComboBox purpeopletype; //采购负责类型

    @FXML private TextField purpeople; //采购负责人

    @FXML private TextField createpeople; //制单人

    @FXML private TextField shpeople; //审核人

    @FXML private TextField shdate; //审核日期

    @FXML private TextField updatepeople; //最后更新人

    @FXML private TextField updatedate; //最后更新日期



    //供应商基本资料


    @FXML private TextField supname;//供应商名称

    @FXML private ComboBox supcontacts; //供应商联系人

    @FXML private ComboBox supohone; //供应商 联系电话

    @FXML private ComboBox suptax; //供应商 传真

    @FXML private ComboBox supaddress; //供应商 地址





    @FXML private TableView tableView1;

    @FXML private TableColumn editId;

    @FXML private TableColumn proisnum;

    @FXML private TableColumn proname;

    @FXML private TableColumn protype;

    @FXML private TableColumn pronum;

    @FXML private TableColumn prounit;

    @FXML private TableColumn proprice;

    @FXML private TableColumn totalprice;

    /**
     * 期望交期
     */
    @FXML
    private TableColumn estimateDeliver;

    @FXML private TableColumn proremark;



    @FXML private  TextField totalnum;//数量合计

    @FXML private  TextField totalmoney;//金额合计

    @FXML  private TextField totalloan; //货款合计
    @FXML  private TextField totaltax; //税款合计


    //查询更多供应商
    @FXML private TableView tableView3; //供应商查询
    @FXML private TableColumn supid; //id
    @FXML private TableColumn findsupplierid; //供应商编号
    @FXML private TableColumn findsuppliername; //供应商简称

    //查询更多询价订单
    @FXML private TableView tableView4; //询价订单
    @FXML private TableColumn inquiryid; //id
    @FXML private TableColumn findinquiryorder; //询价订单号
    @FXML private TableColumn findinquirydata; //制单日期
    @FXML private TableColumn findsuppliernum;//供应商编号
    @FXML private TableColumn findsupplierdes;//供应商简称
    @FXML private TableColumn findsupplierpeo;//业务负责
    @FXML private TableColumn findsuppliercontact;//联系人
    @FXML private TableColumn findphone; //电话
    @FXML private TableColumn findfax;//传真


    //备注及说明
    @FXML private TableView tableViewDes; //备注及说明
    @FXML private TableColumn desid; //id
    @FXML private TableColumn descontent; //
    //报表备注
    @FXML private TableView tableViewRem; //报表备注
    @FXML private TableColumn remid; //id
    @FXML private TableColumn remcontent; //


    //询价产品查询
    @FXML private TableView tableViewProduct; //产品基本查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn findproductid; //产品基本编号
    @FXML private TableColumn findproductname; //产品基本名称
    @FXML private TableColumn findprotype; //产品类型
    @FXML private TableColumn findnormprice; //产品标准售价
    @FXML private TableColumn findlowprice; //产品最低售价
    @FXML private TableColumn findsafetystock; //安全存量
    @FXML private TableColumn findremarks; //备注


    @FXML private Button supplierBtn;

    @FXML
    private TableView quotationView;

    @FXML
    private TableColumn quotationid; //编号

    @FXML
    private TableColumn findquotationorder; //单据编号

    @FXML
    private TableColumn findquotationdata; //制单日期

    @FXML
    private TableColumn findstatus; //单据状态

    //报价单
    @FXML
    private TableView quotationProductView;
    @FXML
    private TableColumn quotationproid; //编号
    @FXML
    private TableColumn findproid; //产品编号
    @FXML
    private TableColumn finprosort;//产品序号
    @FXML
    private TableColumn findproname;//产品名称
    @FXML
    private TableColumn finpronum;//数量
    @FXML
    private TableColumn findproprice;//单价


    private long quotationOrderId=0;

    //销货单
    @FXML
    private TableView saleGoodView;
    @FXML
    private TableColumn salegoodid; //编号
    @FXML
    private TableColumn findsalegoodidorder; //单据编号
    @FXML
    private TableColumn findsalegoodiddata; //制单日期
    @FXML
    private TableColumn findsalegoodidstatus; //单据状态

    @FXML
    private TableView saleGoodProductView;
    @FXML
    private TableColumn salegoodproid; //编号
    @FXML
    private TableColumn findsalegoodproid; //产品编号
    @FXML
    private TableColumn finpsalegoodrosort; //产品序号
    @FXML
    private TableColumn findsalegoodproname; //产品名称
    @FXML
    private TableColumn findsalegoodpronum; //数量
    @FXML
    private TableColumn findsalegoodproprice; //单价

    @FXML private VBox inquiry_find_fast;
    @FXML private VBox inquiry_find_prev;
    @FXML private VBox inquiry_find_next;
    @FXML private VBox inquiry_find_last;

    @FXML private TextField inquiry_textField_pageSize;

    @FXML private TextField inquiry_order_textField;

    private int pageSize = 10;


    @FXML private VBox supplier_find_fast;
    @FXML private VBox supplier_find_prev;
    @FXML private VBox supplier_find_next;
    @FXML private VBox supplier_find_last;

    @FXML private TextField supplier_textField_pageSize;

    @FXML private TextField supplier_order_textField;


    @FXML private VBox product_find_fast;
    @FXML private VBox product_find_prev;
    @FXML private VBox product_find_next;
    @FXML private VBox product_find_last;

    @FXML private TextField product_textField_pageSize;

    @FXML private TextField product_order_textField;



    @FXML private VBox salegood_find_fast;
    @FXML private VBox salegood_find_prev;
    @FXML private VBox salegood_find_next;
    @FXML private VBox salegood_find_last;

    @FXML private TextField salegood_textField_pageSize;

    @FXML private VBox quotation_find_fast;
    @FXML private VBox quotation_find_prev;
    @FXML private VBox quotation_find_next;
    @FXML private VBox quotation_find_last;

    @FXML private TextField quotation_textField_pageSize;

    private int tableViewIndex = 0;



    //询价订单-询价产品-TabelView参数绑定
    private ObservableList<InquiryProductProperty> inquiryProductProperties = FXCollections.observableArrayList();

    //询价订单--TabelView参数绑定
    private ObservableList<InquiryProperty> inquiryProperties = FXCollections.observableArrayList();

    //询价单--备注及说明--之TabelView参数绑定
    private ObservableList<InquiryDescriptionProperty> inquiryDescriptionProperties = FXCollections.observableArrayList();

    //询价单--报表备注--之TabelView参数绑定
    private ObservableList<InquiryRemarkProperty> inquiryRemarkProperties = FXCollections.observableArrayList();
    //报价单 转单产品
    private ObservableList<QuotationImportProperty> quotationImportProperties = FXCollections.observableArrayList();
    //销货单 转单产品
    private ObservableList<SaleGoodImportProperty> saleGoodImportProperties = FXCollections.observableArrayList();

    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @FXML
    private CheckBox shCheckBox;

    @FXML
    private CheckBox bjShCheckBox;


    /**
     * 单据关联容器
     */
    Relation relation = new Relation();
    //每个单据用来锁定关联数据的
    boolean relationLock = false;


    /**
     * 生成询价订单流水号 (A1809280001)
     * 格式:英文字母(A)+日期(180928)+4位流水号(0001)
     * @return
     */
    public String createIsnum(String currentDate){
                String newDateStr=currentDate.substring(2,4)+currentDate.substring(currentDate.indexOf("-")+1,currentDate.indexOf("-")+3)+currentDate.substring(currentDate.lastIndexOf("-")+1,currentDate.lastIndexOf("-")+3);
                String maxIsnum = inquiryOrderService.selectMaxIdnum(currentDate);
        if(maxIsnum != null){
            String maxAlphabet = maxIsnum.substring(0,1);
            //180928 0001
            int currenNumber = Integer.parseInt(maxIsnum.substring(maxIsnum.length()-4,maxIsnum.length()));
            for (int i=0;i< NumberUtil.ALPHABET.length;i++){
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



    public void findQuotationSearch(){

        String pageSizes =  quotation_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreQuotationImport(1);
        }

    }


    public void findQuotationPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreQuotationImport(pageNum);
    }



    //导入---报价单
    public void importButtonQuotation(){

        stage.setTitle("导入-报价单");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/quotation_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize =  10;
        loadMoreQuotationImport(1);

    }




    public void loadMoreQuotationImport(int pageNum){

        List<SaleQuotation> saleQuotations = iSaleQuotationService.listSaleQuotationAllByStatus(bjShCheckBox.isSelected() ? 1:0,pageNum,pageSize);

        PageInfo<SaleQuotation> pageInfo = new PageInfo<>(saleQuotations);

        quotation_find_fast.setUserData(1); //首页

        quotation_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        quotation_find_next.setUserData(pageInfo.getNextPage());//下一页

        quotation_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<SaleQuotation> list = FXCollections.observableArrayList();


//        quotationid.setCellValueFactory(new PropertyValueFactory("id"));
        findquotationorder.setCellValueFactory(new PropertyValueFactory("offerNo"));
        findquotationdata.setCellValueFactory(new PropertyValueFactory("createDateStr"));
//        findsuppliernum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        findstatus.setCellValueFactory(new PropertyValueFactory("status"));
        /*findcomestock.setCellValueFactory(new PropertyValueFactory("purpeople"));*/


        for (SaleQuotation saleQuotation:saleQuotations) {

            saleQuotation.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(saleQuotation.getCreateDate()));

            if(!saleQuotation.getOrderAudit()){
                saleQuotation.setStatus("未审核");
            }else{
                saleQuotation.setStatus("已审核");
            }

            list.add(saleQuotation);

        }

        quotationView.setItems(list); //tableview添加list

        quotationView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleQuotation>() {
            @Override
            public void changed(ObservableValue<? extends SaleQuotation> observableValue, SaleQuotation oldItem, SaleQuotation newItem) {
                if(newItem != null && newItem.getId()>0){
                    loadMoreQuotationProductImport(newItem.getId());
                }
            }
        });
    }


    public void loadMoreQuotationProductImport(long id){
        quotationOrderId=id;
        List<SaleOfferProduct> saleOfferProducts =  iSaleOfferProductService.listSaleOfferProduct(id);

        quotationProductView.setEditable(true);
        quotationproid.setCellFactory(CheckBoxTableCell.forTableColumn(quotationproid));

        quotationproid.setCellValueFactory(new PropertyValueFactory("checked"));
        findproid.setCellValueFactory(new PropertyValueFactory("proisnum"));
        findproname.setCellValueFactory(new PropertyValueFactory("sort"));
        findproname.setCellValueFactory(new PropertyValueFactory("proname"));
        finpronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        findproprice.setCellValueFactory(new PropertyValueFactory("proprice"));

        quotationImportProperties.clear();

        for (SaleOfferProduct saleOfferProduct:saleOfferProducts) {

            QuotationImportProperty quotationImportProperty = new QuotationImportProperty(false,saleOfferProduct.getId(),saleOfferProduct.getProductNo(),0,saleOfferProduct.getProductName(),saleOfferProduct.getNum(),saleOfferProduct.getPrice().doubleValue());

            quotationImportProperties.add(quotationImportProperty);

        }

        quotationProductView.setItems(quotationImportProperties); //tableview添加list

    }




    //导入----根据询价单据编号查询询价产品

    //确定导入产品
    public  void importQuotationProductData(){

       SaleQuotation saleQuotation = iSaleQuotationService.selectByKey(quotationOrderId);

        //存储被关联单据
        relation.setBeRelationName("报价单");
        relation.setBeRelationId(saleQuotation.getId());
        relationLock = true;

        tax.getSelectionModel().select(saleQuotation.getTax());
        currency.getSelectionModel().select(saleQuotation.getCurrency());

        for(QuotationImportProperty quotationImportProperty : quotationImportProperties){
//                boolean aoutationLock = true;
            if(!saleQuotation.getOrderAudit()){
                alert_informationDialog(AppConst.ALERT_EXAMINE);
            }else{
                //选中导入的产品
                if(quotationImportProperty.isChecked()){
                     SaleOfferProduct saleOfferProduct =  iSaleOfferProductService.selectByKey(quotationImportProperty.getId());

//                    for (InquiryProductProperty inquiryProductProperty :inquiryProductProperties) {
//                            if(inquiryProductProperty.getProisnum().equals(saleOfferProduct.getProductNo())){
//                                alert_informationDialog("产品"+inquiryProductProperty.getProisnum()+"重复!");
//                                aoutationLock  =  false;
//                            }
//                    }

//                    if(aoutationLock){
                        InquiryProductProperty inquiryProductProperty = new InquiryProductProperty(0,saleOfferProduct.getProductNo(),saleOfferProduct.getProductName(),saleOfferProduct.getCategory(),saleOfferProduct.getNum().toString(),saleOfferProduct.getUnit(),saleOfferProduct.getPrice().toString(),saleOfferProduct.getMoney().toString(),saleOfferProduct.getRemark(),setPushBackDate(createdate,10));

                        inquiryProductProperties.add(inquiryProductProperty);

                        totalCost(Integer.parseInt(inquiryProductProperty.getPronum()),Double.parseDouble(inquiryProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
//                    }

                }
            }
        }

        quotationImportProperties.clear();
        coseInquiryWin();
    }



    public void findSaleGoodSearch(){

        String pageSizes =  salegood_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreSaleGoodImport(1);
        }

    }


    public void findSaleGoodPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreSaleGoodImport(pageNum);
    }




    //导入---销货单
    public void importButtonSaleGoods(){

        stage.setTitle("导入-销货单");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/sale_good_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreSaleGoodImport(1);

    }



    public void loadMoreSaleGoodImport(int pageNum){


        List<SaleGoods> saleGoods = iSaleGoodsService.listSaleGoodsAll(shCheckBox.isSelected()?1:0,pageNum,pageSize);

        PageInfo<SaleGoods> pageInfo = new PageInfo<>(saleGoods);

        salegood_find_fast.setUserData(1); //首页

        salegood_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        salegood_find_next.setUserData(pageInfo.getNextPage());//下一页

        salegood_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<SaleGoods> list = FXCollections.observableArrayList();


//        salegoodid.setCellValueFactory(new PropertyValueFactory("id"));
        findsalegoodidorder.setCellValueFactory(new PropertyValueFactory("saleNo"));
        findsalegoodiddata.setCellValueFactory(new PropertyValueFactory("createDateStr"));
//        findsuppliernum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        findsalegoodidstatus.setCellValueFactory(new PropertyValueFactory("status"));
        /*findcomestock.setCellValueFactory(new PropertyValueFactory("purpeople"));*/


        for (SaleGoods saleGoods1:saleGoods) {

            saleGoods1.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(saleGoods1.getCreateDate()));

            if(!saleGoods1.getOrderAudit()){
                saleGoods1.setStatus("未审核");
            }else{
                saleGoods1.setStatus("已审核");
            }

            list.add(saleGoods1);

        }

        saleGoodView.setItems(list); //tableview添加list

        saleGoodView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleGoods>() {
            @Override
            public void changed(ObservableValue<? extends SaleGoods> observableValue, SaleGoods oldItem, SaleGoods newItem) {
                if(newItem != null && newItem.getId()>0){
                    loadMoreSaleGoodProductImport(newItem.getId());
                }
            }
        });
    }


    public void loadMoreSaleGoodProductImport(long id){
        quotationOrderId=id;

        List<SaleGoodsProduct> saleGoodsProducts = iSaleGoodsProductService.listSaleGoodsProduct(id+"");

        saleGoodProductView.setEditable(true);
        salegoodproid.setCellFactory(CheckBoxTableCell.forTableColumn(quotationproid));

        salegoodproid.setCellValueFactory(new PropertyValueFactory("checked"));
        findsalegoodproid.setCellValueFactory(new PropertyValueFactory("proisnum"));
        finpsalegoodrosort.setCellValueFactory(new PropertyValueFactory("sort"));
        findsalegoodproname.setCellValueFactory(new PropertyValueFactory("proname"));
        findsalegoodpronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        findsalegoodproprice.setCellValueFactory(new PropertyValueFactory("proprice"));

        saleGoodImportProperties.clear();

        for (SaleGoodsProduct saleGoodsProduct:saleGoodsProducts) {

            SaleGoodImportProperty saleGoodsProductProperty = new SaleGoodImportProperty(false,saleGoodsProduct.getId(),saleGoodsProduct.getProductNo(),0,saleGoodsProduct.getProductName(),saleGoodsProduct.getNum(),saleGoodsProduct.getPrice().doubleValue());

            saleGoodImportProperties.add(saleGoodsProductProperty);

        }

        saleGoodProductView.setItems(saleGoodImportProperties); //tableview添加list

    }




    //导入----根据询价单据编号查询询价产品

    //确定导入产品
    public  void importSaleGoodProductData(){


        SaleGoods saleGoods = iSaleGoodsService.selectByKey(quotationOrderId);

        //存储被关联单据
        relation.setBeRelationName("销货单");
        relation.setBeRelationId(saleGoods.getId());
        relationLock = true;

        tax.getSelectionModel().select(saleGoods.getTax());
        currency.getSelectionModel().select(saleGoods.getCurrency());

        for(SaleGoodImportProperty saleGoodImportProperty : saleGoodImportProperties){
//            boolean aoutationLock = true;
            if(!saleGoods.getOrderAudit()){
                alert_informationDialog(AppConst.ALERT_EXAMINE);
            }else{
                //选中导入的产品
                if(saleGoodImportProperty.isChecked()){




                    SaleGoodsProduct saleGoodsProduct = iSaleGoodsProductService.selectByKey(saleGoodImportProperty.getId());


//                    for (InquiryProductProperty inquiryProductProperty :inquiryProductProperties) {
//                        if(inquiryProductProperty.getProisnum().equals(saleGoodsProduct.getProductNo())){
//                            alert_informationDialog("产品"+inquiryProductProperty.getProisnum()+"重复!");
//                            aoutationLock  =  false;
//                        }
//                    }

//                    if(aoutationLock) {

                        InquiryProductProperty inquiryProductProperty = new InquiryProductProperty(0, saleGoodsProduct.getProductNo(), saleGoodsProduct.getProductName(), saleGoodsProduct.getCategory(), saleGoodsProduct.getNum().toString(), saleGoodsProduct.getUnit(), saleGoodsProduct.getPrice().toString(), saleGoodsProduct.getMoney().toString(), saleGoodsProduct.getRemark(), setPushBackDate(createdate, 10));

                        inquiryProductProperties.add(inquiryProductProperty);

                        totalCost(Integer.parseInt(inquiryProductProperty.getPronum()), Double.parseDouble(inquiryProductProperty.getTotalprice()), tax.getSelectionModel().getSelectedIndex() + 1, getTaxReruenDouble(createdate.getValue(), 1));
//                    }
                }
            }
        }

        saleGoodImportProperties.clear();
        coseInquiryWin();
    }



    public void findInquirySearch(){
        String pageSizes =  inquiry_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreInquiry(1);
        }




    }


    public void findInquiryPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreInquiry(pageNum);
    }



    //点击弹出 现有询价单查询
    public void moreInquiryClick(){

        stage.setTitle("现有询价单查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/inquiry_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize =  10;
        loadMoreInquiry(1);
    }


    public void loadMoreInquiry(int pageNum){


        List<InquiryOrder> inquiryOrder = inquiryOrderService.findInquiryOrder("".equals(inquiry_order_textField.getText()) || inquiry_order_textField.getText() == null ? "" : inquiry_order_textField.getText(),pageNum,pageSize);

        PageInfo<InquiryOrder> pageInfo = new PageInfo<>(inquiryOrder);

        inquiry_find_fast.setUserData(1); //首页

        inquiry_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        inquiry_find_next.setUserData(pageInfo.getNextPage());//下一页

        inquiry_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<InquiryOrder> list =FXCollections.observableArrayList();


        /*tableView4.setEditable(true);*/


//        inquiryid.setCellValueFactory(new PropertyValueFactory("id"));
        findinquiryorder.setCellValueFactory(new PropertyValueFactory("orders"));
        findinquirydata.setCellValueFactory(new PropertyValueFactory("paremdate"));
        findsuppliernum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        findsupplierdes.setCellValueFactory(new PropertyValueFactory("suppliername"));
        findsupplierpeo.setCellValueFactory(new PropertyValueFactory("purpeople"));
        findsuppliercontact.setCellValueFactory(new PropertyValueFactory("supplierconcat"));
        findphone.setCellValueFactory(new PropertyValueFactory("supplierphone"));
        findfax.setCellValueFactory(new PropertyValueFactory("supplierfax"));


        for (InquiryOrder supplierBasics1:inquiryOrder) {

            supplierBasics1.setParemdate(new SimpleDateFormat("yyyy-MM-dd").format(supplierBasics1.getCreatedate()));

            list.add(supplierBasics1);

        }

        tableView4.setItems(list); //tableview添加list

        tableView4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InquiryOrder>() {
            @Override
            public void changed(ObservableValue<? extends InquiryOrder> observableValue, InquiryOrder oldItem, InquiryOrder newItem) {
                if(newItem.getOrders() != null && !("".equals(newItem.getOrders()))){
                    findinquiryorder.setUserData(newItem.getId());
                }
            }
        });


        tableView4.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeInquiryWin();
            }
        });


    }

    public void closeInquiryWin(){
        long id =(long)findinquiryorder.getUserData();
        InquiryOrder inquiryOrder =  inquiryOrderService.selectByKey(id);
        orders.setText(inquiryOrder.getOrders());
        loadDate(inquiryOrder);
        loadSupplier(inquiryOrder.getSuppliernum(),inquiryOrder);
        /*loadDate(supplierBasic);*/
        coseInquiryWin();
    }

    public void coseInquiryWin(){
        stage.close();
    }


    public void findSupplierSearch(){

        String pageSizes =  supplier_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreSupplier(1);
        }

    }


    public void findSupplierPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreSupplier(pageNum);
    }


    /**
     * 点击弹出 现有供应商查询
     */
    public void moreSupplierClick(){

        stage.setTitle("现有供应商查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/inquiry_supplier_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreSupplier(1);
    }


    /**
     * 现有供应商查询
     */
    public void loadMoreSupplier(int pageNum){

        List<SupplierBasic> supplierBasics = supplierBasicService.selectSupplierBasic("".equals(supplier_order_textField.getText()) || supplier_order_textField.getText() ==  null ? "" : supplier_order_textField.getText(),pageNum,pageSize);

        PageInfo<SupplierBasic> pageInfo = new PageInfo<>(supplierBasics);

        supplier_find_fast.setUserData(1); //首页

        supplier_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        supplier_find_next.setUserData(pageInfo.getNextPage());//下一页

        supplier_find_last.setUserData(pageInfo.getPages());//尾页
        ObservableList<SupplierBasic> list =FXCollections.observableArrayList();


        tableView3.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

        supid.setCellValueFactory(new PropertyValueFactory("id"));
        findsupplierid.setCellValueFactory(new PropertyValueFactory("idnum"));
        findsuppliername.setCellValueFactory(new PropertyValueFactory("supdes"));

        for (SupplierBasic supplierBasics1:supplierBasics) {

            list.add(supplierBasics1);

        }

        tableView3.setItems(list); //tableview添加list

        tableView3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierBasic>() {
            @Override
            public void changed(ObservableValue<? extends SupplierBasic> observableValue, SupplierBasic oldItem, SupplierBasic newItem) {
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    suppliernum.setUserData(newItem.getId());
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
        long id =(long)suppliernum.getUserData();
//        long orderId =(long)orders.getUserData();
//         InquiryOrder inquiryOrder =  inquiryOrderService.selectByKey(orderId);
        SupplierBasic supplierBasic =  supplierBasicService.selectByKey(id);
        suppliernum.setText(supplierBasic.getIdnum());
        suppliername.setText(supplierBasic.getSupdes());

        List<SupplierPurchaser> supplierPurchasers = supplierPurchaserService.selectSupplierPurchaseBySupplierid(supplierBasic.getId());

        if(supplierPurchasers.size() > 0 && supplierPurchasers != null){

            for (SupplierPurchaser supplierPurchaser:supplierPurchasers) {
                if(supplierPurchaser.getKeycontent() == 1){

                    ObservableList<String > purchanseEmp =  purpeopletype.getItems();
                    for (int i=0,len = purchanseEmp.size();i<len;i++) {
                            if(purchanseEmp.get(i).equals(supplierPurchaser.getStaffcode())){
                                purpeopletype.getSelectionModel().select(i);
                            }
                    }
                    purpeople.setText(supplierPurchaser.getStaffname());
                }
            }
        }else{
            purpeopletype.getSelectionModel().select(-1);
            purpeople.setText("");
        }


        List<SupplierContact> supplierContacts = supplierContactService.selectSupplierContactBySupplierid(supplierBasic.getId());

        if(supplierContacts.size() > 0 && supplierContacts != null){

            supcontacts.getItems().clear();
            supohone.getItems().clear();
            suptax.getItems().clear();
            supaddress.getItems().clear();

            for(int i=0,len=supplierContacts.size();i<len;i++){

                supcontacts.getItems().add(supplierContacts.get(i).getUname());
                supohone.getItems().add(supplierContacts.get(i).getUphone());
                suptax.getItems().add(supplierContacts.get(i).getUfax());
            }


            for(int i=0,len=supplierContacts.size();i<len;i++){

                if(supplierContacts.get(i).getKeycontact() == 1){
                    supcontacts.getSelectionModel().select(i);
                    supohone.getSelectionModel().select(i);
                    suptax.getSelectionModel().select(i);
                    break;
                }else{
                    supcontacts.getSelectionModel().select(0);
                    supohone.getSelectionModel().select(0);
                    suptax.getSelectionModel().select(0);
                }

            }
            supname.setText(supplierBasic.getSupname());
            supaddress.getItems().add(supplierBasic.getRegadd());
            supaddress.getSelectionModel().select(0);

        }



//        loadSupplier(supplierBasic.getIdnum(),new InquiryOrder());
        /*loadDate(supplierBasic);*/
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }


    public void findProductSearch(){

        String pageSizes =  product_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreProduct(1);
        }

    }


    public void findProductPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreProduct(pageNum);
    }




    public void moreInquiryProductClick(){

        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/inquiry_product_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10 ;
        loadMoreProduct(1);
    }


    /**
     * 现有产品查询
     */
    public void loadMoreProduct(int pageNum){

        List<ProductBasic> productBasics = productBasicService.selectProductBasic("".equals(product_order_textField.getText()) || product_order_textField.getText() == null ? "" : product_order_textField.getText() ,pageNum,pageSize);


        PageInfo<ProductBasic> pageInfo = new PageInfo<>(productBasics);

        product_find_fast.setUserData(1); //首页

        product_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        product_find_next.setUserData(pageInfo.getNextPage());//下一页

        product_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<ProductBasic> list = FXCollections.observableArrayList();

        tableViewProduct.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

//        depid.setCellValueFactory(new PropertyValueFactory("id"));
        findproductid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findproductname.setCellValueFactory(new PropertyValueFactory("proname"));
        findprotype.setCellValueFactory(new PropertyValueFactory("protypeStr"));
        findnormprice.setCellValueFactory(new PropertyValueFactory("normprice"));
        findlowprice.setCellValueFactory(new PropertyValueFactory("lowprice"));
        findsafetystock.setCellValueFactory(new PropertyValueFactory("safetystock"));
        findremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        for (ProductBasic productBasic:productBasics) {

            DataSetting dataSetting = dataSettingService.findDataSettingBySortAndParentId(productBasic.getProtype().intValue(),6);

            productBasic.setProtypeStr(dataSetting.getTitle());

            list.add(productBasic);
        }

        tableViewProduct.setItems(list); //tableview添加list

        tableViewProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductBasic>() {
            @Override
            public void changed(ObservableValue<? extends ProductBasic> observableValue, ProductBasic oldItem, ProductBasic newItem) {
                if(newItem.getIsnum()!= null && !("".equals(newItem.getIsnum()))){
                    tableViewProduct.setUserData(newItem.getId());
                }
            }
        });


        tableViewProduct.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeInquiryProductWin();
            }
        });


    }

    public void closeInquiryProductWin(){
        long id =(long)tableViewProduct.getUserData();
        ProductBasic productBasic =  productBasicService.selectByKey(id);
//        boolean aoutationLock = true;
        totalnum.setText("0");
        totalmoney.setText("0.00");
        totaltax.setText("0.00");
        totalloan.setText("0.00");
        for(int i=0,len=inquiryProductProperties.size();i<len;i++){
            InquiryProductProperty inquiryProductProperty = inquiryProductProperties.get(i);
//            if( inquiryProductProperty.getProisnum() != null && inquiryProductProperties.get(i).getProisnum().equals(productBasic.getIsnum())){
//                aoutationLock = false;
//                alert_informationDialog("产品"+inquiryProductProperty.getProisnum()+"重复!");
//
//            }
            if(i == tableViewIndex){

//                if(aoutationLock){
                    inquiryProductProperty.setProisnum(productBasic.getIsnum());//产品编号
                    inquiryProductProperty.setProname(productBasic.getProname());//产品名称

                    //品类
                    {
                        List<DataSetting> dataSettings = dataSettingService.findDataSetting(6L);
                        long protypeIndex = productBasic.getProtype();
                        for(int j=0,jlen=dataSettings.size();j<jlen;j++){
                            if(protypeIndex == j+1){
                                inquiryProductProperty.setProtype(dataSettings.get(j).getTitle());   //品类
                            }
                        }

                    }

                    inquiryProductProperty.setPronum("0");//数量

                    //基本单位
                    {
                        List<DataSetting> dataSettings = dataSettingService.findDataSetting(5L);
                        long prounit = productBasic.getBasicunit();
                        for(int j=0,jlen=dataSettings.size();j<jlen;j++){
                            if(prounit == j+1){
                                inquiryProductProperty.setProunit(dataSettings.get(j).getTitle());   //基本单位
                            }
                        }

                    }

//                    inquiryProductProperty.setProprice(productBasic.getNormprice().toString());//单价

                    inquiryProductProperty.setProprice("0.00");//单价

//                    inquiryProductProperty.setTotalprice(String.valueOf(productBasic.getMinnum()*productBasic.getNormprice()));//金额

                    inquiryProductProperty.setTotalprice("0.00");//金额

                    inquiryProductProperty.setProremark(productBasic.getRemarks()); //备注

                    inquiryProductProperty.setEstimateDeliver(setPushBackDate(createdate,10));

                    totalCost(Integer.parseInt(inquiryProductProperty.getPronum()),Double.parseDouble(inquiryProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
//                }


            }
        }

//        totalCost();


        coseWin();
    }






    /**
     * 上下首尾跳页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));
        findInquiry(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);//修改为修改状态
    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findInquiry(int pageNum){

        List<InquiryOrder> inquiryOrders = inquiryOrderService.findInquiryOrder(pageNum,1);

        PageInfo<InquiryOrder> pageInfo = new PageInfo<>(inquiryOrders);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        inquiryOrders.forEach(inquiryOrder ->loadDate(inquiryOrder));

        if(inquiryOrders.size()<=0){

            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(true);

            deletevbox.setDisable(true);

            shno.setDisable(true);

            shyes.setDisable(true);

            importOut.setDisable(true);

            setComboBox(7L,currency,0); //币别

            orders.setUserData(0L);

            inquiryProduct();

            description(0L);

            remarks(0L);

            //权限管理
            matchingPermissions("询价单",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox,0);
        }




    }


    /**
     * 向控件加载数据
     * @param inquiryOrder
     */
    public void loadDate(InquiryOrder inquiryOrder){

        loadSupplier(inquiryOrder.getSuppliernum(),inquiryOrder); //加载供应商信息

        createdate.setValue(LocalDateTime.ofInstant(inquiryOrder.getCreatedate().toInstant(), ZoneId.systemDefault()).toLocalDate()); //制单日期

        orders.setUserData(inquiryOrder.getId());

        orders.setText(inquiryOrder.getOrders());//询价订单

        suppliernum.setText(inquiryOrder.getSuppliernum()); //供应商编号

        suppliername.setText(inquiryOrder.getSuppliername());//供应商名称


        int taxs = inquiryOrder.getTaxs();

        tax.getSelectionModel().select(--taxs); //税别


        int currentcyInt = inquiryOrder.getCurrencys();

        setComboBox(7L,currency,--currentcyInt); //币别


        replydate.setValue(LocalDateTime.ofInstant(inquiryOrder.getReplydate().toInstant(), ZoneId.systemDefault()).toLocalDate()); //回复日期

        validdate.setValue(LocalDateTime.ofInstant(inquiryOrder.getValiddate().toInstant(), ZoneId.systemDefault()).toLocalDate()); //有效期至

        int purpeopletypes = inquiryOrder.getPurpeopletype();

        purpeopletype.getSelectionModel().select(--purpeopletypes); //采购负责类型


        purpeople.setText(inquiryOrder.getPurpeople()); //采购负责人

        createpeople.setText(inquiryOrder.getCreatepeople());//制单人

        shpeople.setText(inquiryOrder.getShpeople());//审核人


        shdate.setText(inquiryOrder.getShdate()); //审核日期


        updatepeople.setText(inquiryOrder.getUpdatepeople()); //最后更新人

        updatedate.setText(inquiryOrder.getUpdatedate()); //最后更新日期


        int isSh = inquiryOrder.getShstatus();



        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

        inquiryProduct();

        description(inquiryOrder.getId());

        remarks(inquiryOrder.getId());

//        if(isSh == 0){
//            shyes.setDisable(false);
//            shno.setDisable(true);
//        }else{
//
//            //已审核不得修改和删除
//            updatevbox.setDisable(true);
//            deletevbox.setDisable(true);
//
//            shyes.setDisable(true);
//            shno.setDisable(false);
//        }

        //权限管理
        matchingPermissions("询价单",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox,isSh);

    }

    /**
     * 根据供应商编号加载供应商
     * @param isNum
     */
    public void loadSupplier(String isNum,InquiryOrder inquiryOrder){


        //供应商
        SupplierBasic supplierBasic =  supplierBasicService.selectSupplierBasicByIsnum(isNum);

        if(supplierBasic !=  null){

            supname.setText(supplierBasic.getSupname());

            //查询供应商联系人
            List<SupplierContact> supplierContacts = supplierContactService.selectSupplierContactBySupplierid(supplierBasic.getId());

            supcontacts.getItems().clear();
            supohone.getItems().clear();
            suptax.getItems().clear();
            supaddress.getItems().clear();

            for(int i=0,len=supplierContacts.size();i<len;i++){

                supcontacts.getItems().add(supplierContacts.get(i).getUname());

                if(inquiryOrder != null){
                    if(supplierContacts.get(i).getUname().equals(inquiryOrder.getSupplierconcat())){
                        supcontacts.getSelectionModel().select(i);
                    }
                }else{
                    supcontacts.getSelectionModel().select(0);
                }

                supohone.getItems().add(supplierContacts.get(i).getUphone());
                if(inquiryOrder != null) {
                    if (supplierContacts.get(i).getUphone().equals(inquiryOrder.getSupplierphone())) {
                        supohone.getSelectionModel().select(i);
                    }
                }else{
                    supohone.getSelectionModel().select(0);
                }

                suptax.getItems().add(supplierContacts.get(i).getUfax());
                if(inquiryOrder != null) {
                    if (supplierContacts.get(i).getUfax().equals(inquiryOrder.getSupplierfax())) {
                        suptax.getSelectionModel().select(i);
                    }
                }else{
                    suptax.getSelectionModel().select(0);
                }



            }


            supaddress.getItems().add(supplierBasic.getRegadd());
            supaddress.getSelectionModel().select(0);


        }

    }




    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){

        orders.setEditable(false);//询价订单

        suppliernum.setDisable(!status); //供应商编号

        suppliername.setDisable(!status); //供应商名称

        tax.setDisable(!status); //税别

        currency.setDisable(!status); //币别

        purpeopletype.setDisable(!status); //采购负责类型

        purpeople.setDisable(!status); //采购负责人

        createpeople.setDisable(!status); //制单人

        shpeople.setDisable(!status); //审核人

        shdate.setDisable(!status); //审核日期

        updatepeople.setDisable(!status); //最后更新人

        updatedate.setDisable(!status); //最后更新日期

        supname.setDisable(!status);

        supcontacts.setDisable(!status);
        supohone.setDisable(!status);
        suptax.setDisable(!status);
        supaddress.setDisable(!status);

        tableView1.setEditable(status);

        tableView1.setDisable(!status);

        tableViewDes.setEditable(status);
        tableViewRem.setEditable(status);


        createdate.setDisable(!status);
        replydate.setDisable(!status);
        validdate.setDisable(!status);

        importData.setDisable(!status);

        supplierBtn.setDisable(!status); //查询更多供应商窗体

    }



    /**
     * 清空
     */
    public void clearValue(){

        orders.setText(NumberUtil.NULLSTRING);//询价订单

        suppliernum.setText(NumberUtil.NULLSTRING); //供应商编号

        suppliername.setText(NumberUtil.NULLSTRING); //供应商名称

        tax.getSelectionModel().select(0); //税别

        currency.getSelectionModel().select(0); //币别

        purpeopletype.getSelectionModel().select(0); //采购负责类型

        purpeople.setText(NumberUtil.NULLSTRING); //采购负责人

        createpeople.setText(NumberUtil.NULLSTRING); //制单人

        shpeople.setText(NumberUtil.NULLSTRING); //审核人

        shdate.setText(NumberUtil.NULLSTRING); //审核日期

        updatepeople.setText(NumberUtil.NULLSTRING); //最后更新人

        updatedate.setText(NumberUtil.NULLSTRING); //最后更新日期

        totalnum.setText("0");

        totalmoney.setText("0.00");

        totaltax.setText("0.00");

        totalloan.setText("0.00");

        inquiryDescriptionProperties.clear();

        inquiryRemarkProperties.clear();

        inquiryProductProperties.clear();

        supname.setText(NumberUtil.NULLSTRING);
        supcontacts.getItems().clear();
        supohone.getItems().clear();
        suptax.getItems().clear();
        supaddress.getItems().clear();

    }

    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) orders.getUserData();
            int rows = inquiryOrderService.delete(id);

            List<InquiryProduct> inquiryProducts = inquiryProductService.findInquiryProductByInquiryid(id);

            for (InquiryProduct inquiryProduct:inquiryProducts) {
                inquiryProductService.delete(inquiryProduct.getId());
            }

            relationService.deleteRelation("询价单",id);

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

        lastUpdatePeopel(updatepeople,updatedate); //最后修改人 和最后修改日期
        //联系人空白行
       /* blankContact();
        blankPurchaser();*/

//        blankInquiryProduct();
        blankInquiryDescription();
        blankInquiryRemarks();


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
        createPeople(createpeople);//制单人

       /* supplierContactProperties.clear();*/
        //联系人空白行
       /* blankContact();
        blankPurchaser();*/
        setDatePicker(createdate);
        setArrivalDate(createdate,replydate,10);
        setArrivalDate(replydate,validdate,10);
//        blankInquiryProduct();
        blankInquiryDescription();
        blankInquiryRemarks();
    }


    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改

        LocalDate orderDate =createdate.getValue();

        String supperIsnum =suppliernum.getText();

        if(supperIsnum == "" || "".equals(supperIsnum)){
            alert_informationDialog("请填写供应商编号!");
            return;
        }
        if(orderDate == null){
            alert_informationDialog("请填写制单日期!");
            return;
        }


        InquiryOrder inquiryOrder = new InquiryOrder(new Date(java.sql.Date.valueOf(orderDate).getTime()),
                                                        orders.getText(),
                                                        suppliernum.getText(),
                                                        suppliername.getText(),
                                                        tax.getSelectionModel().getSelectedIndex()+1 ,
                                                        currency.getSelectionModel().getSelectedIndex()+1,
                                                        new Date(java.sql.Date.valueOf(replydate.getValue()).getTime()),
                                                        new Date(java.sql.Date.valueOf(validdate.getValue()).getTime()),
                                                        purpeopletype.getSelectionModel().getSelectedIndex()+1,
                                                        purpeople.getText(),
                                                        createpeople.getText(),
                                                        shpeople.getText(),
                                                        shdate.getText(),
                                                        updatepeople.getText(),
                                                        updatedate.getText(),
                                                        supname.getText(),
                supcontacts.getItems().size()==0?"":supcontacts.getSelectionModel().getSelectedItem().toString(),
                supohone.getItems().size()==0?"":supohone.getSelectionModel().getSelectedItem().toString(),
                suptax.getItems().size()==0?"":suptax.getSelectionModel().getSelectedItem().toString(),
                supaddress.getItems().size()==0?"":supaddress.getSelectionModel().getSelectedItem().toString(),
                                                        0,
                                                        0);

                            int rows =0;
                            if(submitStuts==1){
                                //产生订单编号
                                String orderNum = createIsnum(orderDate.toString());
                                orders.setText(orderNum);
                                inquiryOrder.setOrders(orderNum);
                                rows = inquiryOrderService.save(inquiryOrder);


                                if(relationLock){
                                    //添加关联关系
                                    relation.setRelationName("询价单");
                                    relation.setRelationId(inquiryOrder.getId());
                                    relationService.save(relation);
                                    relationLock = !relationLock;
                                }



                            }else if(submitStuts ==2){
                                inquiryOrder.setId((long)orders.getUserData());
                                rows = inquiryOrderService.updateNotNull(inquiryOrder);
                            }

                            /*saveContact(supplierBasic.getId());//联系人
                            savePurchaser(supplierBasic.getId()); //采购负责人*/
                            saveInquiryProduct(inquiryOrder.getId());
                            saveInquiryDescription(inquiryOrder.getId());
                             saveInquiryRemarks(inquiryOrder.getId());
                            NumberUtil.changeStatus(fxmlStatus,0);
                            submitvbox.setDisable(true);

//                            this.loadDate(inquiryOrder); //重新加载数据
                                  findInquiry(1);
    }



    //询价产品
    public void inquiryProduct(){

        List<InquiryProduct > inquiryProducts =  inquiryProductService.findInquiryProductByInquiryid((long)orders.getUserData());

        proisnum.setCellFactory(TextFieldTableCell.forTableColumn());
        proname.setCellFactory(TextFieldTableCell.forTableColumn());
        protype.setCellFactory(TextFieldTableCell.forTableColumn());
        pronum.setCellFactory(TextFieldTableCell.forTableColumn());
        prounit.setCellFactory(TextFieldTableCell.forTableColumn());
        proprice.setCellFactory(TextFieldTableCell.forTableColumn());
        totalprice.setCellFactory(TextFieldTableCell.forTableColumn());
        proremark.setCellFactory(TextFieldTableCell.forTableColumn());
        estimateDeliver.setCellFactory(TextFieldTableCell.forTableColumn()); //期望交期

//        estimateDeliver.setCellValueFactory(buttonFactory);

//        editId.setCellValueFactory(new PropertyValueFactory("editId"));
        proisnum.setCellValueFactory(new PropertyValueFactory("proisnum"));
        proname.setCellValueFactory(new PropertyValueFactory("proname"));
        protype.setCellValueFactory(new PropertyValueFactory("protype"));
        pronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        prounit.setCellValueFactory(new PropertyValueFactory("prounit"));
        proprice.setCellValueFactory(new PropertyValueFactory("proprice"));
        totalprice.setCellValueFactory(new PropertyValueFactory("totalprice"));
        proremark.setCellValueFactory(new PropertyValueFactory("proremark"));
        estimateDeliver.setCellValueFactory(new PropertyValueFactory("estimateDeliver"));

        inquiryProductProperties.clear();

        totalnum.setText("0");
        totalmoney.setText("0.00");
        totaltax.setText("0.00");
        totalloan.setText("0.00");



        if(inquiryProducts.size()>0){
            for (InquiryProduct inquiryProduct:inquiryProducts) {
                InquiryOrder inquiryOrder = inquiryOrderService.selectByKey(inquiryProduct.getInquiryid());



                totalCost(inquiryProduct.getPronum(),inquiryProduct.getTotalprice(),inquiryOrder.getTaxs(),getTaxReruenDouble(createdate.getValue(),1));
                InquiryProductProperty inquiryProductProperty = new InquiryProductProperty(inquiryProduct.getId(),inquiryProduct.getProisnum(),inquiryProduct.getProname(),inquiryProduct.getProtype(),inquiryProduct.getPronum().toString(),inquiryProduct.getProunit(),inquiryProduct.getProprice().toString(),inquiryProduct.getTotalprice().toString(),inquiryProduct.getProremark(),inquiryProduct.getExpecteddate());

                inquiryProductProperties.add(inquiryProductProperty);
            }
        }
        tableView1.setItems(inquiryProductProperties); //tableview添加list



        tableView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InquiryProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends InquiryProductProperty> observableValue, InquiryProductProperty oldItem, InquiryProductProperty newItem) {
                tableViewIndex = tableView1.getSelectionModel().getSelectedIndex();
                    if(newItem.getEditId()>0){
                        changeId = newItem.getEditId();
                    }else{
                        changeId = 0;
                    }
            }
        });




        //提交单价计算金额  询价订单--询价产品 金额总计
        proprice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override

            public void handle(TableColumn.CellEditEvent event) {


                String newValue =event.getNewValue().toString();

                Double  newPrice = newValue != null && !"".equals(newValue)?newPrice=Double.parseDouble(newValue):0.00;

                totalnum.setText("0");
                totalmoney.setText("0.00");
                totaltax.setText("0.00");
                totalloan.setText("0.00");
                for(int i = 0,len = inquiryProductProperties.size() ;i<len;i++ ){

                    if(i == tableViewIndex){

                        inquiryProductProperties.get(i).setProprice(String.valueOf(newPrice));
                        //计算金额
                        inquiryProductProperties.get(i).setTotalprice(String.valueOf(Integer.parseInt(inquiryProductProperties.get(i).getPronum())*newPrice));
                    }

                       if(inquiryProductProperties.get(i).getPronum() != null && inquiryProductProperties.get(i).getTotalprice() != null){

                           totalCost(Integer.parseInt(inquiryProductProperties.get(i).getPronum()),Double.parseDouble(inquiryProductProperties.get(i).getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
                       }

                }
            }

        });


        pronum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override

            public void handle(TableColumn.CellEditEvent event) {


                String newValue =event.getNewValue().toString();

                int  newNum = newValue != null && !"".equals(newValue)?newNum=Integer.parseInt(newValue):0;

                totalnum.setText("0");
                totalmoney.setText("0.00");
                totaltax.setText("0.00");
                totalloan.setText("0.00");


                        for(int i = 0,len = inquiryProductProperties.size() ;i<len;i++ ){

                            if(i == tableViewIndex){

                                inquiryProductProperties.get(i).setPronum(String.valueOf(newNum));
                                //计算金额
                                inquiryProductProperties.get(i).setTotalprice(String.valueOf(newNum * Double.parseDouble(inquiryProductProperties.get(i).getProprice())));
                            }

                            if(inquiryProductProperties.get(i).getPronum() != null && inquiryProductProperties.get(i).getTotalprice() != null){

                                totalCost(Integer.parseInt(inquiryProductProperties.get(i).getPronum()),Double.parseDouble(inquiryProductProperties.get(i).getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
                            }
                        }

            }

        });

    }
                //
                public void saveInquiryProduct(long inquiryid){
                    for (InquiryProductProperty inquiryProductProperty :inquiryProductProperties) {
                        if(inquiryProductProperty.getProisnum()!=null){
                            if(inquiryProductProperty.getEditId()>0){
                                //修改采购负责人
                                InquiryProduct inquiryProduct = new InquiryProduct(inquiryProductProperty.getEditId(),inquiryProductProperty.getProisnum(),inquiryProductProperty.getProname(),inquiryProductProperty.getProtype(),Integer.parseInt(inquiryProductProperty.getPronum()),inquiryProductProperty.getProunit(),Double.parseDouble(inquiryProductProperty.getProprice()),Double.parseDouble(inquiryProductProperty.getTotalprice()),inquiryProductProperty.getProremark(),inquiryid,inquiryProductProperty.getEstimateDeliver());
                                inquiryProductService.updateNotNull(inquiryProduct);
                            }else{
                                //新增采购负责人
                                InquiryProduct inquiryProduct = new InquiryProduct(inquiryProductProperty.getProisnum(),inquiryProductProperty.getProname(),inquiryProductProperty.getProtype(),Integer.parseInt(inquiryProductProperty.getPronum()),inquiryProductProperty.getProunit(),Double.parseDouble(inquiryProductProperty.getProprice()),Double.parseDouble(inquiryProductProperty.getTotalprice()),inquiryProductProperty.getProremark(),inquiryid,inquiryProductProperty.getEstimateDeliver());
                                inquiryProductService.save(inquiryProduct);
                            }
                        }
                    }
                }


            //询价单产品空白
            public void blankInquiryProduct(){
                InquiryProductProperty inquiryProductProperty = new InquiryProductProperty();
                inquiryProductProperties.add(inquiryProductProperty);
            }

    /**
     * 核算
     * @param pronum
     * @param price
     * @param tax 税别类型  "外加","内含","零税","免税"
     */
    public void totalCost(int pronum,Double price,int tax,double taxRate){


        int countNum =   "".equals(totalnum.getText()) || totalnum.getText() == null ? 0 : Integer.parseInt(totalnum.getText());

        Double taxNum =    "".equals(totaltax.getText()) || totaltax.getText() == null ? 0.00 :  Double.parseDouble(totaltax.getText());

        Double totaloanNum =  "".equals(totalloan.getText()) || totalloan.getText() == null ? 0.00 : Double.parseDouble(totalloan.getText());

        totalnum.setText(String.valueOf((countNum+pronum)));

        switch (tax){
            case 1:

                totaltax.setText(String.valueOf(new BigDecimal(price * taxRate + taxNum ).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()));

                totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + price).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()));

                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()));

                break;
            case 2:

                Double capital =  price / (taxRate + 1); //税前价格

                totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + capital).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()));

                totaltax.setText(String.valueOf(new BigDecimal(price - capital + taxNum).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()));


                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()));

                break;
            default:
                totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + price ).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()));

                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()));

                break;
        }


    }


            //备注及说明
            public void description(long inquiryid){

                List<InquiryDescription> inquiryDescriptions = inquiryDescriptionService.findInquiryDescription(inquiryid,1);


                descontent.setCellFactory(TextFieldTableCell.forTableColumn());


//                desid.setCellValueFactory(new PropertyValueFactory("id"));
                descontent.setCellValueFactory(new PropertyValueFactory("content"));

                inquiryDescriptionProperties.clear();

                if(inquiryDescriptions.size() > 0){

                    for (InquiryDescription inquiryDescription:inquiryDescriptions) {
                        InquiryDescriptionProperty inquiryDescriptionProperty = new InquiryDescriptionProperty(inquiryDescription.getId(),inquiryDescription.getDes());
                        inquiryDescriptionProperties.add(inquiryDescriptionProperty);
                    }

                }

                tableViewDes.setItems(inquiryDescriptionProperties);

            }

            //备注及说明
    public void saveInquiryDescription(long inquiryid){
        for (InquiryDescriptionProperty inquiryDescriptionProperty :inquiryDescriptionProperties) {
            if(inquiryDescriptionProperty.getContent()!=null){
                if(inquiryDescriptionProperty.getId() > 0){
                    //修改
                    InquiryDescription inquiryDescription = new InquiryDescription(inquiryDescriptionProperty.getId(),inquiryDescriptionProperty.getContent(),inquiryid,1);
                    inquiryDescriptionService.updateNotNull(inquiryDescription);
                }else{
                    //新增
                    InquiryDescription inquiryDescription = new InquiryDescription(inquiryDescriptionProperty.getContent(),inquiryid,1);
                    inquiryDescriptionService.save(inquiryDescription);
                }
            }
        }
    }


    // 备注及说明---空白
    public void blankInquiryDescription(){
        InquiryDescriptionProperty inquiryDescriptionProperty = new InquiryDescriptionProperty();
        inquiryDescriptionProperties.add(inquiryDescriptionProperty);
    }





    //报表备注
    public void remarks(long inquiryid){

        List<InquiryDescription> inquiryDescriptions = inquiryDescriptionService.findInquiryDescription(inquiryid,2);


        remcontent.setCellFactory(TextFieldTableCell.forTableColumn());


//        remid.setCellValueFactory(new PropertyValueFactory("id"));
        remcontent.setCellValueFactory(new PropertyValueFactory("content"));

        inquiryRemarkProperties.clear();

        if(inquiryDescriptions.size() > 0){

            for (InquiryDescription inquiryDescription:inquiryDescriptions) {
                InquiryRemarkProperty inquiryRemarkProperty = new InquiryRemarkProperty(inquiryDescription.getId(),inquiryDescription.getDes());
                inquiryRemarkProperties.add(inquiryRemarkProperty);
            }

        }

        tableViewRem.setItems(inquiryRemarkProperties);

    }


    //保存询价单报表备注
    public void saveInquiryRemarks(long inquiryid){
        for (InquiryRemarkProperty inquiryRemarkProperty :inquiryRemarkProperties) {
            if(inquiryRemarkProperty.getContent()!=null){
                if(inquiryRemarkProperty.getId() > 0){
                    //修改
                    InquiryDescription inquiryDescription = new InquiryDescription(inquiryRemarkProperty.getId(),inquiryRemarkProperty.getContent(),inquiryid,2);
                    inquiryDescriptionService.updateNotNull(inquiryDescription);
                }else{
                    //新增
                    InquiryDescription inquiryDescription = new InquiryDescription(inquiryRemarkProperty.getContent(),inquiryid,2);
                    inquiryDescriptionService.save(inquiryDescription);
                }
            }
        }
    }


    //空白
    public void blankInquiryRemarks(){
        InquiryRemarkProperty inquiryRemarkProperty = new InquiryRemarkProperty();
        inquiryRemarkProperties.add(inquiryRemarkProperty);
    }







    /**
     * tableView1 键盘按下触发
     * @param event
     */
    public void tableView1Key(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {

            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){

                if(changeId != 0){
                    inquiryProductService.delete(changeId);
                }

                ObservableList<InquiryProductProperty> inquiryProductPropertiesNew = FXCollections.observableArrayList();

                totalnum.setText("0");
                totalmoney.setText("0.00");
                totaltax.setText("0.00");
                totalloan.setText("0.00");

                for(int i=0,len = inquiryProductProperties.size();i<len;i++  ){
                    if(i != tableViewIndex){
                        inquiryProductPropertiesNew .add(inquiryProductProperties.get(i));
                        totalCost(Integer.parseInt(inquiryProductProperties.get(i).getPronum()),Double.parseDouble(inquiryProductProperties.get(i).getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
                    }
                }

//                inquiryProductProperties.clear();
                inquiryProductProperties.setAll(inquiryProductPropertiesNew);
            }

        }


        if (event.getCode() == KeyCode.INSERT) {
            //联系人空白行
           /* blankContact();*/
           blankInquiryProduct();
        }

        if(event.getCode() == KeyCode.F4){
            moreInquiryProductClick();
        }
    }


    /**
     * 回车查询
     * @param event
     */
    public void isNumKey(KeyEvent event){


        if(event.getCode()==KeyCode.ENTER){

          /*  String value = isNum.getText();
            if(!"".equals(value)){
                SupplierBasic supplierBasic = supplierBasicService.selectSupplierBasicByIsnum(value);

                if(supplierBasic != null){
                    this.loadDate(supplierBasic);
                }
            }*/

        }

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

        long id =  (long)orders.getUserData();

        if(!relationService.isRelation("询价单",id)){
            shno.setDisable(true);
            shyes.setDisable(false);
            cancelSh(shpeople,shdate);
            updateOrderStatus(0);
        }else {
            alert_informationDialog(AppConst.ALERT_OCCUPY);
        }


    }

    /**
     * 修改审核状态
     * @param status  0、未审核    1、审核通过
     */
    public void updateOrderStatus(int status){
        long id =  (long)orders.getUserData();

        InquiryOrder inquiryOrder = inquiryOrderService.selectByKey(id);

        inquiryOrder.setShstatus(status);

        if(status == 1){
            inquiryOrder.setShpeople(getAdminName());
            inquiryOrder.setShdate(getCurrentDate());
            //已审核不得修改和删除
            updatevbox.setDisable(true);
            deletevbox.setDisable(true);

        }else{
            inquiryOrder.setShpeople(NumberUtil.NULLSTRING);
            inquiryOrder.setShdate(NumberUtil.NULLSTRING);
            //未审核可以修改和删除
            updatevbox.setDisable(false);
            deletevbox.setDisable(false);
        }


        inquiryOrderService.updateNotNull(inquiryOrder);
    }



    //导出---询价单导出采购订单
    public void importOutPurchase(){


        InquiryOrder inquiryOrder =inquiryOrderService.selectByKey((long)orders.getUserData());

       if(inquiryOrder.getShstatus() == 1){
           StageManager.inquiryOrderInfo =inquiryOrder;

           StageManager.inquiryProductsInfo = inquiryProductService.findInquiryProductByInquiryid((long)orders.getUserData());

           Pane pane1 = StageManager.ORDERCONTENT.get("PurchasePane");

           pane1.getChildren().setAll(loader.load("/fxml/purchase/purchase_order.fxml"));
       }else{
           alert_informationDialog(AppConst.ALERT_EXAMINE);
       }


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {




        String newStr = location.toString();

        int index = newStr.indexOf("inquiry.fxml");

        if(index != -1){
            relation = new Relation();
            relationLock = false;

            tax.getItems().addAll("外加","内含","零税","免税");

            // 切换年份
            tax.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                    totalnum.setText("0");
                    totalmoney.setText("0.00");
                    totaltax.setText("0.00");
                    totalloan.setText("0.00");

                    for(int i=0,len = inquiryProductProperties.size();i<len;i++  ){

                            totalCost(Integer.parseInt(inquiryProductProperties.get(i).getPronum()),Double.parseDouble(inquiryProductProperties.get(i).getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));

                    }

                }
            });


            purpeopletype.getItems().clear();

            List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();

            for(int i=0,len=employeeBasics.size();i<len;i++){
                purpeopletype.getItems().add(employeeBasics.get(i).getIdnum());
            }
            purpeopletype.getSelectionModel().select(0);



           SaleQuotation saleQuotation  = StageManager.saleQuotation;

           SaleGoods saleGoods = StageManager.saleGoods;

            if(saleQuotation != null){

                //存储被关联单据
                relation.setBeRelationName("报价单");
                relation.setBeRelationId(saleQuotation.getId());
                relationLock = true;

                insert();

                orders.setUserData(0L);

                inquiryProduct();

                tax.getSelectionModel().select(saleQuotation.getTax());

                currency.getSelectionModel().select(saleQuotation.getCurrency());


                List<SaleOfferProduct> saleOfferProducts = iSaleOfferProductService.listSaleOfferProduct(saleQuotation.getId());

                for(SaleOfferProduct saleOfferProduct:saleOfferProducts){

                    InquiryProductProperty inquiryProductProperty = new InquiryProductProperty(0,saleOfferProduct.getProductNo(),saleOfferProduct.getProductName(),saleOfferProduct.getCategory(),saleOfferProduct.getNum().toString(),saleOfferProduct.getUnit(),saleOfferProduct.getPrice().toString(),saleOfferProduct.getMoney().toString(),saleOfferProduct.getRemark(),setPushBackDate(createdate,10));

                    inquiryProductProperties.add(inquiryProductProperty);

                    totalCost(Integer.parseInt(inquiryProductProperty.getPronum()),Double.parseDouble(inquiryProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));

                }
            }else if(saleGoods != null){

                //存储被关联单据
                relation.setBeRelationName("销货单");
                relation.setBeRelationId(saleGoods.getId());
                relationLock = true;

                insert();

                orders.setUserData(0L);

                inquiryProduct();

                tax.getSelectionModel().select(saleGoods.getTax());

                currency.getSelectionModel().select(saleGoods.getCurrency());

                List<SaleGoodsProduct> saleGoodsProducts = iSaleGoodsProductService.listSaleGoodsProduct(saleGoods.getId()+"");


                for(SaleGoodsProduct saleGoodsProduct:saleGoodsProducts){

                    InquiryProductProperty inquiryProductProperty = new InquiryProductProperty(0,saleGoodsProduct.getProductNo(),saleGoodsProduct.getProductName(),saleGoodsProduct.getCategory(),saleGoodsProduct.getNum().toString(),saleGoodsProduct.getUnit(),saleGoodsProduct.getPrice().toString(),saleGoodsProduct.getMoney().toString(),saleGoodsProduct.getRemark(),setPushBackDate(createdate,10));

                    inquiryProductProperties.add(inquiryProductProperty);

                    totalCost(Integer.parseInt(inquiryProductProperty.getPronum()),Double.parseDouble(inquiryProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));

                }

            }else{
                findInquiry(1);
            }

            BaseController.clickEvent(tableView1, data ->{
                tableViewIndex = tableView1.getSelectionModel().getSelectedIndex();
                moreInquiryProductClick();
            }, 2);


            purpeopletype.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                    if(!"".equals(newValue) && newValue != null ){


                        EmployeeBasic employeeBasic =  employeeBasicService.selectEmployeeBasicByIsnum(newValue.toString());

                        purpeople.setText(employeeBasic.getEmpname());


                    }

                }
            });


        }


    }


}
