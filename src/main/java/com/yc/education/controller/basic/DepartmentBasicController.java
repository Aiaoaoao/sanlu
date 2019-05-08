package com.yc.education.controller.basic;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.CompanyBasic;
import com.yc.education.model.basic.DepartmentBasic;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.model.basic.SupplierContact;
import com.yc.education.service.basic.DepartmentBasicService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName DepartmentBasicController
 * @Description TODO 公司部门设置
 * @Author QuZhangJing
 * @Date 2018-08-14 15:47
 * @Version 1.0
 */
@Controller
public class DepartmentBasicController extends BaseController implements Initializable {

    @Autowired
    private DepartmentBasicService departmentBasicService; //部门Service


    @FXML private TreeView treeView;



    @FXML
    Label fxmlStatus; //窗体状态

    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页


    @FXML private VBox submitvbox; //提交


    @FXML private VBox clearvbox; //清除

    @FXML private VBox insertvbox; //新增

    @FXML private VBox updatevbox; //修改

    @FXML private VBox deletevbox; //删除

    @FXML private VBox printingvbox; //删除




    @FXML TextField isNum; //备注

    @FXML TextField depname; //备注

    @FXML ComboBox parentid; //

    @FXML TextArea remarks; //备注

    @FXML TextField addpeople; //建档人

    @FXML TextField adddate; //建档日期

    @FXML TextField updatepeople; //最后修改人

    @FXML TextField updatedate;//最后修改日期


    @FXML private TableView tableViewDepartment; //部门查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn finddepartmentid; //部门编号
    @FXML private TableColumn finddepartmentname; //部门名称


    private Stage stage = new Stage();


    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @FXML private VBox find_fast;
    @FXML private VBox find_prev;
    @FXML private VBox find_next;
    @FXML private VBox find_last;

    @FXML private TextField textField_pagesize;

    private int pageSize = 0 ;


    @FXML private TextField order_textField;


    /**
     * 生成编号
     * @return
     */
    public String createIsnum(){
        String maxIsnum = departmentBasicService.selectMaxIdnum();
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



    public void findSearch(){

        String pageSizes =textField_pagesize.getText();


        if(!"".equals(pageSizes) || pageSizes != null  ){
            pageSize = Integer.parseInt(pageSizes);
            loadMoreDepartment(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }


    }


    public void findPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreDepartment(pageNum);

    }




    /**
     * 点击弹出 查询部门
     */
    public void moreDepartmentButtonClick(){

        stage.setTitle("现有部门查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/department_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreDepartment(1);
    }


    /**
     * 现有部门查询
     */
    public void loadMoreDepartment(int pageNum){

        List<DepartmentBasic> departmentBasics = departmentBasicService.selectDepartmentBasic("".equals(order_textField.getText()) || order_textField.getText() == null ? "" : order_textField.getText(),pageNum,pageSize);

        PageInfo<DepartmentBasic> pageInfo = new PageInfo<>(departmentBasics);

        find_fast.setUserData(1); //首页

        find_prev.setUserData(pageInfo.getPrePage()); //上一页

        find_next.setUserData(pageInfo.getNextPage());//下一页

        find_last.setUserData(pageInfo.getPages());//尾页


        ObservableList<DepartmentBasic> list = FXCollections.observableArrayList();


        tableViewDepartment.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

//        depid.setCellValueFactory(new PropertyValueFactory("id"));
        finddepartmentid.setCellValueFactory(new PropertyValueFactory("idnum"));
        finddepartmentname.setCellValueFactory(new PropertyValueFactory("depname"));

        for (DepartmentBasic departmentBasic:departmentBasics) {

            list.add(departmentBasic);

        }

        tableViewDepartment.setItems(list); //tableview添加list

        tableViewDepartment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DepartmentBasic>() {
            @Override
            public void changed(ObservableValue<? extends DepartmentBasic> observableValue, DepartmentBasic oldItem, DepartmentBasic newItem) {
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    isNum.setUserData(newItem.getId());
                }
            }
        });


        tableViewDepartment.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });


    }

    public void closeSureWin(){
        long id =(long)isNum.getUserData();
        DepartmentBasic departmentBasic =  departmentBasicService.selectByKey(id);
        loadData(departmentBasic);
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }





















    /**
     * 加载数据
     */
    public void  loadData(DepartmentBasic departmentBasic){

        long selectId=0;

        ObservableList<String> list = FXCollections.observableArrayList();

        List<DepartmentBasic> departmentBasicss = departmentBasicService.selectDepartmentBasic();

        for (int i=0;i<departmentBasicss.size();i++) {
            list.add(departmentBasicss.get(i).getDepname());
            if(departmentBasicss.get(i).getId()==departmentBasic.getParentid()){
                selectId=i;
            }
        }
        parentid.setItems(list);
        parentid.getSelectionModel().select((int)selectId);



        isNum.setUserData(departmentBasic.getId());

        isNum.setText(departmentBasic.getIdnum());

        depname.setText(departmentBasic.getDepname());

        int  cout = departmentBasic.getParentid().intValue();
        parentid.getSelectionModel().select(--cout);

        remarks.setText(departmentBasic.getRemarks());

        addpeople.setText(departmentBasic.getAddpeople());

        adddate.setText(departmentBasic.getAdddate());

        updatepeople.setText(departmentBasic.getUpdatepeople());

        updatedate.setText(departmentBasic.getUpdatedate());

        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);


        departmentTreeValue();

        //权限管理
        matchingPermissions("公司部门设置",insertvbox,deletevbox,updatevbox,printingvbox,clearvbox);

    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findSupplier(int pageNum){
        List<DepartmentBasic> departmentBasics  = departmentBasicService.selectDepartmentBasic(pageNum,1);
        PageInfo<DepartmentBasic> pageInfo = new PageInfo<>(departmentBasics);


        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        departmentBasics.forEach(companyBasic1 ->loadData(companyBasic1));

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

        depname.setDisable(!status);

        parentid.setDisable(!status);

        remarks.setDisable(!status);

        addpeople.setDisable(!status);

        adddate.setDisable(!status);

        updatepeople.setDisable(!status);

        updatedate.setDisable(!status);

    }




    /**
     * 清空
     */
    public void clearValue(){


        isNum.setText(NumberUtil.NULLSTRING);

        depname.setText(NumberUtil.NULLSTRING);

        remarks.setText(NumberUtil.NULLSTRING);

        addpeople.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

//        parentid.getSelectionModel().select(0);

        addpeople.setText(NumberUtil.NULLSTRING);

        adddate.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

        updatedate.setText(NumberUtil.NULLSTRING);


    }




    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改


        /*    SupplierBasic supplierBasic = new SupplierBasic();*/

        String idnums ="";

        if(submitStuts==2){
            idnums=isNum.getText();
        }

      /*  int istopval=0;
        if(isstop.isSelected()){
            istopval=1;
        }else{
            istopval=0;
        }*/


        Object[] values = {
                0L,
                idnums,
                depname.getText(),
                parentid.getSelectionModel().getSelectedItem()==null?0:departmentBasicService.selectDepartmentBasicByDepName(parentid.getSelectionModel().getSelectedItem().toString()).getId(),
                remarks.getText(),
                addpeople.getText(),
                adddate.getText(),
                updatepeople.getText(),
                updatedate.getText(),
        };

        DepartmentBasic departmentBasic =(DepartmentBasic)NumberUtil.arrayToObject(values,DepartmentBasic.class);

        int rows =0;
        if(submitStuts==1){
            String isNums = this.createIsnum();
            isNum.setText(isNums);
            departmentBasic.setIdnum(isNums);
            rows = departmentBasicService.save(departmentBasic);
        }else if(submitStuts ==2){
            departmentBasic.setId((long)isNum.getUserData());
            rows = departmentBasicService.updateNotNull(departmentBasic);
        }
        departmentBasic.setIdnum(isNum.getText());
        this.loadData(departmentBasic); //重新加载数据

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);


    }





    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
        long id = (long)isNum.getUserData();
        int rows = departmentBasicService.delete(id);
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
        this.changeEditable(true);
        lastUpdatePeopel(updatepeople,updatedate); //最后修改人 和最后修改日期
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
        this.changeEditable(true);
        clearValue();//清空控件
        createPeople(addpeople,adddate);//建档人、建档日期
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

            String value = isNum.getText();

            if(!"".equals(value)){

                DepartmentBasic departmentBasic = departmentBasicService.selectDepartmentBasicByIsnum(value);

                if(departmentBasic != null){
                    this.loadData(departmentBasic);
                }

            }

        }

    }







    /**
     * 公司部门设置 树形图
     */
    public  void departmentTreeValue(){

        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        String companyName ="上海三禄贸易有限公司";
        long   companyId  =1L;

         List<DepartmentBasic> departmentBasics = departmentBasicService.selectDepartmentBasicByParentId(0);

         if(departmentBasics.size()>0){
             for (DepartmentBasic departmentBasic:departmentBasics) {
                 companyName = departmentBasic.getDepname();
                 companyId = departmentBasic.getId();
             }
         }

        TreeItem<String> items = new TreeItem<>(companyName);

        items.setExpanded(true);

        List<DepartmentBasic> departmentBasicsTow = departmentBasicService.selectDepartmentBasicByParentId(companyId);

        setItems(items,departmentBasicsTow);

        rootItem.getChildren().add(items);

        treeView.setShowRoot(false);

        treeView.setRoot(rootItem);
    }

    /**
     * 设置 树值
     * @param item
     * @param departmentBasics
     */
    public void setItems(TreeItem<String> item,List<DepartmentBasic> departmentBasics){
        for (DepartmentBasic departmentBasic:departmentBasics) {
            TreeItem<String> items = new TreeItem<> ("("+departmentBasic.getIdnum()+")"+departmentBasic.getDepname());
            List<DepartmentBasic> departmentBasicList = departmentBasicService.selectDepartmentBasicByParentId(departmentBasic.getId());
            if(departmentBasicList.size()>0){
                setItems(items,departmentBasicList);
            }
            item.getChildren().add(items);
            items.setExpanded(true);
        }
    }








    @Override
    public void initialize(URL location, ResourceBundle resources) {

        treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                Node node = event.getPickResult().getIntersectedNode();
                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    String newName = (String) ((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue();
                        //(A001)销售部
                    /**
                     * 根据名称查询部门
                     */
                    loadData(departmentBasicService.selectDepartmentBasicByDepName(newName.substring(newName.indexOf(")")+1,newName.length())));
                }
            }
        });

     /*   departmentTreeValue();*/



        String newStr = location.toString();

        int index = newStr.indexOf("department_basic");

        if(index != -1){

            findSupplier(1); //加载数据

            NumberUtil.changeStatus(fxmlStatus,0); //查看


        }



    }



}
