package com.yc.education.controller.basic;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.CompanyBasic;
import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.service.basic.CompanyBasicService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import com.yc.education.util.PathUtil;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName CompanyBasicController
 * @Description TODO 公司基本资料
 * @Author QuZhangJing
 * @Date 2018-08-14 13:47
 * @Version 1.0
 */
@Controller
public class CompanyBasicController  extends BaseController implements Initializable {


    @Autowired
    private CompanyBasicService companyBasicService;  //公司基本资料 Service




    @FXML Label fxmlStatus; //窗体状态

    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页


    @FXML private VBox submitvbox; //提交

    @FXML private VBox clearvbox; //清除

    @FXML private VBox insertvbox; //新增

    @FXML private VBox updatevbox; //修改

    @FXML private VBox deletevbox; //删除

    @FXML private VBox printingvbox; //打印


    /*************************************************表单控件 Start *************************************************************/

    @FXML TextField idnum; //编号
    @FXML TextField comdes; //公司简称
    @FXML TextField comname; //公司名称
    @FXML TextField regadd; //注册地址
    /*基本资料*/
    @FXML ComboBox country; //国家类型
    @FXML TextField area; //地区
    @FXML TextField postalcode; //邮政编码
    @FXML TextField phone; //电话
    @FXML TextField fax; //传真
    @FXML TextField address;//送货地址
    @FXML TextField remarks; //备注
    @FXML TextField addpeople; //建档人
    @FXML TextField adddate; //建档日期
    @FXML TextField updatepeople; //最后修改人
    @FXML TextField updatedate;//最后修改日期
    @FXML CheckBox isstop;//是否暂停使用
    @FXML TextField stopdes; //暂停描述
    /*发票信息*/
    @FXML TextField openbank;//开户银行
    @FXML TextField bankaccount;//银行账号
    @FXML TextField taxaccount; //税务登记号
    @FXML TextField invoicehead; //发票抬头
    @FXML TextField invoiceadd; //发票地址


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

    private int pageSize = 10;

    @FXML private CheckBox companyCheckBox;

    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    /**
     * 生成编号
     * @return
     */
    public String createIsnum(){
        String maxIsnum = companyBasicService.selectMaxIdnum();
        if(maxIsnum != null){
            String maxAlphabet = maxIsnum.substring(0,1);
            int currenNumber = Integer.parseInt(maxIsnum.substring(1,maxIsnum.length()));
            for (int i = 0; i< NumberUtil.ALPHABET.length; i++){
                if(currenNumber == NumberUtil.MAXNUMBER){
                    if( maxAlphabet.equals(NumberUtil.ALPHABET[i])  ){
                        return NumberUtil.ALPHABET[i+1]+"001";
                    }
                }
            }
            if(currenNumber>0 && currenNumber < 9){
                return maxAlphabet +"00"+(currenNumber+1);
            }else if(currenNumber >= 9 && currenNumber< 99){
                return maxAlphabet +"0"+(currenNumber+1);
            }else{
                return maxAlphabet +(currenNumber+1);
            }
        }
        return "A001";
    }


    /**
     * 加载数据
     * @param companyBasic
     */
    public void  loadData(CompanyBasic companyBasic){

        idnum.setUserData(companyBasic.getId());
        idnum.setText(companyBasic.getIdnum());
        comdes.setText(companyBasic.getComdes());


        if(companyBasic.getIsstop() == 1){
            comdes.setText(companyBasic.getComdes()+"(停用)");
        }else{
            comdes.setText(companyBasic.getComdes());
        }

        comname.setText(companyBasic.getComname());
        regadd.setText(companyBasic.getRegadd());
        int  cout = companyBasic.getCountry(); //国家
        country.getSelectionModel().select(--cout);
        area.setText(companyBasic.getArea());
        postalcode.setText(companyBasic.getPostalcode());
        phone.setText(companyBasic.getPhone());
        fax.setText(companyBasic.getFax());
        address.setText(companyBasic.getAddress());
        remarks.setText(companyBasic.getRemarks());
        addpeople.setText(companyBasic.getAddpeople());
        adddate.setText(companyBasic.getAdddate());
        updatepeople.setText(companyBasic.getUpdatepeople());
        updatedate.setText(companyBasic.getUpdatedate());
        if(companyBasic.getIsstop()==1){
            isstop.setSelected(true);
        }else{
            isstop.setSelected(false);
        }
        stopdes.setText(companyBasic.getStopdes());
        openbank.setText(companyBasic.getOpenbank());
        bankaccount.setText(companyBasic.getBankaccount());
        taxaccount.setText(companyBasic.getTaxaccount());
        invoicehead.setText(companyBasic.getInvoicehead());
        invoiceadd.setText(companyBasic.getInvoiceadd());

        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

        //权限管理
        matchingPermissions("公司基本资料",insertvbox,deletevbox,updatevbox,printingvbox,clearvbox);
    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findSupplier(int pageNum){

        List<CompanyBasic> companyBasic = companyBasicService.selectCompanyBasic(0,pageNum,1);

        PageInfo<CompanyBasic> pageInfo = new PageInfo<>(companyBasic);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        companyBasic.forEach(companyBasic1 ->loadData(companyBasic1));

    }



    /**
     * 上下首尾跳页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));
        findSupplier(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);
    }




    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){
        comdes.setDisable(!status);
        comname.setDisable(!status);
        regadd.setDisable(!status);
       country.setDisable(!status);
        area.setDisable(!status);
        postalcode.setDisable(!status);
          /*phonetype.setDisable(!status);*/
        phone.setDisable(!status);
          /*faxtype.setDisable(!status);*/
        address.setDisable(!status);
        fax.setDisable(!status);
        remarks.setDisable(!status);
        addpeople.setDisable(!status);
         adddate.setDisable(!status);
        updatepeople.setDisable(!status);
          updatedate.setDisable(!status);
         isstop.setDisable(!status);
        stopdes.setDisable(!status);

        openbank.setDisable(!status);
        bankaccount.setDisable(!status);
        taxaccount.setDisable(!status);
        invoicehead.setDisable(!status);
        invoiceadd.setDisable(!status);

    }




    /**
     * 清空
     */
    public void clearValue(){


        idnum.setText(NumberUtil.NULLSTRING);

        comdes.setText(NumberUtil.NULLSTRING);

        comname.setText(NumberUtil.NULLSTRING);

        regadd.setText(NumberUtil.NULLSTRING);

        country.getSelectionModel().select(0);

        area.setText(NumberUtil.NULLSTRING);

        postalcode.setText(NumberUtil.NULLSTRING);

        phone.setText(NumberUtil.NULLSTRING);

        address.setText(NumberUtil.NULLSTRING);

        fax.setText(NumberUtil.NULLSTRING);

        remarks.setText(NumberUtil.NULLSTRING);

        addpeople.setText(NumberUtil.NULLSTRING);


        adddate.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

        updatedate.setText(NumberUtil.NULLSTRING);

        isstop.setSelected(false);

        stopdes.setText(NumberUtil.NULLSTRING);




    }




    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改


        /*    SupplierBasic supplierBasic = new SupplierBasic();*/

        String idnums ="";

        if(submitStuts==2){
            idnums=idnum.getText();
        }

        int istopval=0;
        if(isstop.isSelected()){
            istopval=1;
        }else{
            istopval=0;
        }

        String newsupdes = comdes.getText();

        if(newsupdes.indexOf("(停用)") != -1){
            newsupdes = newsupdes.substring(0,newsupdes.indexOf("("));
        }


        Object[] values = {
                0L,
                idnums,
                newsupdes,
                comname.getText(),
                regadd.getText(),
                country.getSelectionModel().getSelectedIndex()+1,
                area.getText(),
                postalcode.getText(),
                phone.getText(),
                fax.getText(),
                address.getText(),
                remarks.getText(),
                addpeople.getText(),
                adddate.getText(),
                updatepeople.getText(),
                updatedate.getText(),
                istopval,
                stopdes.getText(),
                openbank.getText(),
                bankaccount.getText(),
                taxaccount.getText(),
                invoicehead.getText(),
                invoiceadd.getText(),
                0
        };

        CompanyBasic companyBasic =(CompanyBasic)NumberUtil.arrayToObject(values,CompanyBasic.class);

        int rows =0;
        if(submitStuts==1){
            String isNums = this.createIsnum();
            idnum.setText(isNums);
            companyBasic.setIdnum(isNums);
            rows = companyBasicService.save(companyBasic);
        }else if(submitStuts ==2){
            companyBasic.setId((long)idnum.getUserData());
            rows = companyBasicService.updateNotNull(companyBasic);
        }
        companyBasic.setIdnum(idnum.getText());
        this.loadData(companyBasic); //重新加载数据

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);


    }





    /**
     * 删除
     */
    public void delete(){

        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long)idnum.getUserData();
            int rows = companyBasicService.delete(id);
            if(rows>0){
                findSupplier((int)prev.getUserData()); //初始化第一条数据
            }
            NumberUtil.changeStatus(fxmlStatus,0); //修改为修改状态
            this.changeEditable(false);

        }

    }




    /**
     * 修改
     */
    public void update(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.UPDATE);//修改为修改状态

        lastUpdatePeopel(updatepeople,updatedate); //最后修改人 和最后修改日期

        this.changeEditable(true);
        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);

    }


    /**
     *  新增按钮
     */
    public void insert(){

        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态

        clearValue();//清空控件

        createPeople(addpeople,adddate);//建档人、建档日期

        this.changeEditable(true);

        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);
    }


    /**
     * 回车查询
     * @param event
     */
    public void isNumKey(KeyEvent event){


        if(event.getCode()== KeyCode.ENTER){

            String value = idnum.getText();

            if(!"".equals(value)){

                CompanyBasic companyBasic = companyBasicService.selectCompanyBasicByIsnum(value);

                if(companyBasic != null){
                    this.loadData(companyBasic);
                }

            }

        }

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


    /**
     * 点击弹出 查询供应商
     */
    public void moreCompanyButtonClick(){

        stage.setTitle("现有公司查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/company_find.fxml"));
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

       List<CompanyBasic> companyBasics =companyBasicService.selectCompanyBasic(companyCheckBox.isSelected() ? 1: 0,pageNum,pageSize);

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
                    idnum.setUserData(newItem.getId());
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
        long id =(long)idnum.getUserData();
        CompanyBasic supplierBasic =  companyBasicService.selectByKey(id);
        loadData(supplierBasic);
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }








    @Override
    public void initialize(URL location, ResourceBundle resources) {

        isstop.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                boolean isbool =(boolean)newValue;

                if(isbool){
                    stopdes.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
                }else{
                    stopdes.setText("");
                }


            }
        });



        String newStr = location.toString();

        int index = newStr.indexOf("company_basic");

        if(index != -1){
            findSupplier(1); //加载数据
            NumberUtil.changeStatus(fxmlStatus,0); //查看

            setComboBox(28L,country,0);//国家
        }


    }







    @FXML
    private ImageView rightImage;

    @FXML
    private TableView rightSupplierTableView;
    @FXML
    private TableColumn rightSupplierSort;
    @FXML
    private TableColumn rightSupplierOrder;
    @FXML
    private TableColumn rightSupplierDes;

    @FXML
    private CheckBox rightCheckBox;

    /**
     * 右侧查询Panne 公司基本资料
     */
    public void shoRightPane(){

//        C:\Users\jzdsh\Desktop\login

//        String path = PathUtil.projectPath.replaceAll("\\\\","\\\\\\\\");
//        System.out.println("PATH:"+PathUtil.projectPath);
//        System.out.println("PATH:"+path);
//        Image image = new Image("file:"+path+"\\images\\rightgo.png");
//
//        rightImage.setImage(image);

        Stage stageStock = (Stage) StageManager.CONTROLLER.get("rightWinStage");

        StageManager.rightWin = !StageManager.rightWin;

        if(StageManager.rightWin){
            attrImages(rightImage, PathUtil.imageLeft);
            stageStock.setTitle("公司基本资料");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/basic/loadMoreCompany.fxml"));
            Scene scene = new Scene(pane);
            stageStock.setScene(scene);
            Stage stageHome = (Stage)StageManager.CONTROLLER.get("homeStage");

            stageStock.setX(stageHome.getScene().getWindow().getWidth()+stageHome.getScene().getWindow().getX()-15);
            stageStock.setY(stageHome.getScene().getWindow().getY());

            loadCompanyRight(false);
            stageStock.setResizable(false); //禁止窗体缩放
            stageStock.show();
        }else{
            attrImages(rightImage,PathUtil.imageRight);
            stageStock.close();
        }

    }


    public void loadCompanyRight(boolean isStop){

        rightCheckBox.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                boolean isbool =(boolean)newValue;

                loadCompanyRight(isbool);

            }
        });

        List<CompanyBasic> companyBasics =  isStop ? companyBasicService.selectCompanyBasic(0) : companyBasicService.selectCompanyBasic("");

        ObservableList<CompanyBasic> companyBasics1 = FXCollections.observableArrayList();


        rightSupplierSort.setCellValueFactory(new PropertyValueFactory("sort"));
        rightSupplierOrder.setCellValueFactory(new PropertyValueFactory("idnum"));
        rightSupplierDes.setCellValueFactory(new PropertyValueFactory("comdes"));

        if(companyBasics != null && companyBasics.size() > 0){

            for (int i=0,len = companyBasics.size();i < len;i++) {
                companyBasics.get(i).setSort(i+1);
                if(companyBasics.get(i).getIsstop() == 1){
                    companyBasics.get(i).setComdes(companyBasics.get(i).getComdes()+"(停用)");
                }
                companyBasics1.add(companyBasics.get(i));
            }
        }

        rightSupplierTableView.setItems(companyBasics1);


        rightSupplierTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CompanyBasic>() {
            @Override
            public void changed(ObservableValue<? extends CompanyBasic> observableValue, CompanyBasic oldItem, CompanyBasic newItem) {
//               alert_informationDialog("=="+newItem.getId());

                CompanyBasic companyBasic = companyBasicService.selectByKey(newItem.getId());

                loadData(companyBasic);

            }
        });

    }




}
