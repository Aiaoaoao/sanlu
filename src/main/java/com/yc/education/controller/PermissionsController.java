package com.yc.education.controller;

import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.model.*;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.service.*;
import com.yc.education.service.basic.EmployeeBasicService;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName PermissionsController
 * @Description TODO 权限设置
 * @Author QuZhangJing
 * 添加权限 快捷查看SQL：( select * from permissions s,(select p.* from permissions p where p.title='客户基本资料') k where  k.id =  s.parent )
 * @Date 2019/3/1 9:35
 * @Version 1.0
 */
@Controller
public class PermissionsController extends BaseController implements Initializable {

    //权限组和权限用户
    @FXML
    TreeView permissions_treeview;

    @Autowired
    private RolesService rolesService; //角色   权限组

    @Autowired
    private PermissionsEmployeeService permissionsEmployeeService;  //权限用户

    @Autowired
    private PermissionsService permissionsService; //权限

    @Autowired
    private RolePermissionsService rolePermissionsService; //角色 -  权限

    @Autowired
    private RoleEmployeeService roleEmployeeService;//角色  --  用户

    @Autowired
    private EmployeeBasicService employeeBasicService;


    @FXML
    private TableView permissionsTableView;
    @FXML
    private TableColumn qx; //全选
    @FXML
    private TableColumn title; //标题
    @FXML
    private TableColumn insert; //新增
    @FXML
    private TableColumn delete; //删除
    @FXML
    private TableColumn update; //修改
    @FXML
    private TableColumn select; //查询
    @FXML
    private TableColumn sh; //审核
    @FXML
    private TableColumn changesh; //取消审核
    @FXML
    private TableColumn cope; //打印
    @FXML
    private TableColumn reset; //配置


    @FXML
    private Button updateBtn;

    @FXML
    private Button aplicationBtn;

    @FXML
    private Button determineBtn;

    @FXML
    private Button closeBtn;


    private String parentName = "";

    private String  roleClick = "";

    @FXML
    private TextField groupName;
    @FXML
    private TextArea nameArea;

    private ObservableList<SelectEmployeesProperty> selectEmployeesProperties = FXCollections.observableArrayList();

    private ObservableList<AddEmployeesProperty> addEmployeesProperties = FXCollections.observableArrayList();

    @FXML
    private TableView selectEmployeeTabelView;

    @FXML
    private TableColumn checked;
    @FXML
    private TableColumn selectEmployeeOrder;
    @FXML
    private TableColumn selectEmployeeName;


    @FXML
    private TableView addEmployeeTabelView;

    @FXML
    private TableColumn addchecked;
    @FXML
    private TableColumn addEmployeeOrder;
    @FXML
    private TableColumn addEmployeeName;


    //添加
    List<Long> insetOrder = new ArrayList<>();
    //删除
    List<Long> deleteOrder = new ArrayList<>();

    List<Long> addinsetOrder = new ArrayList<>();

    //全选按钮
    ComboBox comboBox = new ComboBox();
    CheckBox qxCheckBox =  new CheckBox();
    CheckBox insertCheckBox =  new CheckBox();
    CheckBox deleteCheckBox =  new CheckBox();
    CheckBox updateCheckBox =  new CheckBox();
    CheckBox selectCheckBox =  new CheckBox();
    CheckBox shCheckBox =  new CheckBox();
    CheckBox changeshCheckBox =  new CheckBox();
    CheckBox copeCheckBox =  new CheckBox();
    CheckBox resetCheckBox =  new CheckBox();

    ObservableList<PermissionsProperty> permissionsProperties = FXCollections.observableArrayList();


    private Stage stage = new Stage();
    private Stage stage2 = new Stage();
    private Stage stage3 = new Stage();
    private Stage stage4 = new Stage();
    private Stage stageAddGroup = new Stage();
    private Stage stageRole = new Stage();
    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @FXML
    private TextField uOrder;//用户编号
    @FXML
    private TextField uName;//用户名
    @FXML
    private TextField uLoginName;//登录名
    @FXML
    private Button btnUpdate; //修改用户属性
    @FXML
    private Button btnChoice;//选择员工
    @FXML
    private Button btnResetPwd; // 重置密码
    @FXML
    private TextArea roleArea; //角色组
    @FXML
    private Button  btnInsert; //确定
    @FXML
    private Button btnDelete; //取消
    @FXML
    private Button btnok;
    @FXML
    private Button insertHome;
    @FXML
    private Button deleteHome;
    @FXML
    private Button loadHome;

    @FXML
    private Button insertEmp;
    @FXML
    private Button updateEmp;
    @FXML
    private Button deleteEmp;
    @FXML
    private Button okEmp;


    @FXML
    private TableView addRoleTabelView;
    @FXML
    private TableColumn addRoleChecked;
    @FXML
    private TableColumn addRoleOrder; //编号
    @FXML
    private TableColumn addRoleName; //权限组

    private int typesFinal = 0;


    public void btnStatus(boolean status){
        btnUpdate.setDisable(status);
        btnChoice.setDisable(status);
        btnResetPwd.setDisable(status);
        btnInsert.setDisable(status);
        btnDelete.setDisable(status);
    }


    /**
     * 加载权限列表
     */
    public void loadPermissions(int types){


        List<Permissions> permissions = permissionsService.selectPermissionsByParent(0,types);


//        permissionsTableView.setEditable(true);

        qx.setCellFactory(CheckBoxTableCell.forTableColumn(qx));
        insert.setCellFactory(CheckBoxTableCell.forTableColumn(insert));
        delete.setCellFactory(CheckBoxTableCell.forTableColumn(delete));
        update.setCellFactory(CheckBoxTableCell.forTableColumn(update));
        select.setCellFactory(CheckBoxTableCell.forTableColumn(select));
        sh.setCellFactory(CheckBoxTableCell.forTableColumn(sh));
        changesh.setCellFactory(CheckBoxTableCell.forTableColumn(changesh));
        cope.setCellFactory(CheckBoxTableCell.forTableColumn(cope));
        reset.setCellFactory(CheckBoxTableCell.forTableColumn(reset));

        qx.setCellValueFactory(new PropertyValueFactory("qx"));
        title.setCellValueFactory(new PropertyValueFactory("title"));
        insert.setCellValueFactory(new PropertyValueFactory("insert"));
        delete.setCellValueFactory(new PropertyValueFactory("delete"));
        update.setCellValueFactory(new PropertyValueFactory("update"));
        select.setCellValueFactory(new PropertyValueFactory("select"));
        sh.setCellValueFactory(new PropertyValueFactory("sh"));
        changesh.setCellValueFactory(new PropertyValueFactory("changesh"));
        cope.setCellValueFactory(new PropertyValueFactory("cope"));
        reset.setCellValueFactory(new PropertyValueFactory("reset"));

        qxCheckBox.setSelected(false);

        comboBox.getItems().setAll("全部","采购","销售","考勤管理","库存","基本资料","客户关系","统计汇总","账款","其他");
        comboBox.setPrefWidth(300.00);
        if(types == 0)comboBox.getSelectionModel().select(0);
       changeStatus(true);
        qxCheckBox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {


                        CheckBox checkBox = (CheckBox)event.getSource();

                        for (PermissionsProperty permissionsProperty:permissionsProperties) {

                            if(checkBox.isSelected()){
                                permissionsProperty.setQx(true);
                                permissionsProperty.setInsert(true);
                                permissionsProperty.setDelete(true);
                                permissionsProperty.setUpdate(true);
                                permissionsProperty.setSelect(true);
                                permissionsProperty.setSh(true);
                                permissionsProperty.setChangesh(true);
                                permissionsProperty.setCope(true);
                                permissionsProperty.setReset(true);
                            }else{
                                permissionsProperty.setQx(false);
                                permissionsProperty.setInsert(false);
                                permissionsProperty.setDelete(false);
                                permissionsProperty.setUpdate(false);
                                permissionsProperty.setSelect(false);
                                permissionsProperty.setSh(false);
                                permissionsProperty.setChangesh(false);
                                permissionsProperty.setCope(false);
                                permissionsProperty.setReset(false);
                            }

                        }


                    }
                });
        qx.setGraphic(qxCheckBox);
        title.setGraphic(comboBox);
        insert.setGraphic(insertCheckBox);
        delete.setGraphic(deleteCheckBox);
        update.setGraphic(updateCheckBox);
        select.setGraphic(selectCheckBox);
        sh.setGraphic(shCheckBox);
        changesh.setGraphic(changeshCheckBox);
        cope.setGraphic(copeCheckBox);
        reset.setGraphic(resetCheckBox);

        permissionsProperties.clear();

        if(permissions.size() > 0 ){

            for (Permissions permissions1:permissions) {

                 if(!permissionsService.selectPermissionsByTitleAndParent(permissions1.getId(),"新增")){
                     insert.setGraphic(new CheckBox());
                 }

                PermissionsProperty permissionsProperty = new PermissionsProperty(permissions1.getId(),permissions1.getTitle(),false,false,false,false,false,false,false,false,false);

                permissionsProperties.add(permissionsProperty);
            }

        }

        permissionsTableView.setItems(permissionsProperties);


    }

public void changeStatus(boolean status){

    qxCheckBox.setDisable(status);
    insertCheckBox.setDisable(status);
    deleteCheckBox.setDisable(status);
    updateCheckBox.setDisable(status);
    selectCheckBox.setDisable(status);
    shCheckBox.setDisable(status);
    changeshCheckBox.setDisable(status);
    copeCheckBox.setDisable(status);
    resetCheckBox.setDisable(status);

}

    public  void checkTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 考勤管理
        TreeItem<String> item4 = new TreeItem<>("权限组");
        item4.setExpanded(true);

        List<Roles> roles =  rolesService.findRoles();

        for (Roles roles1:roles) {
            TreeItem<String> item = new TreeItem<> (roles1.getRolename());
            item4.getChildren().add(item);
        }
        rootItem.getChildren().add(item4);

        // 参数设定
        TreeItem<String> item3 = new TreeItem<>("权限用户");
        item3.setExpanded(true);

         List<PermissionsEmployee> permissionsEmployees =  permissionsEmployeeService.findPermissionsEmployee();

        for (PermissionsEmployee permissionsEmployee:permissionsEmployees) {
            TreeItem<String> item = new TreeItem<> (permissionsEmployee.getUname());
            item3.getChildren().add(item);
        }
        rootItem.getChildren().add(item3);


        // 更节点隐藏
        permissions_treeview.setShowRoot(false);
        permissions_treeview.setRoot(rootItem);
    }


    //修改
    public void updateSure(){
        updateBtn.setDisable(true);
        determineBtn.setDisable(false);
        aplicationBtn.setDisable(false);
        permissionsTableView.setEditable(true);
        changeStatus(false);
    }
        //应用
    public void applicationSure(){

        updateBtn.setDisable(false);
        determineBtn.setDisable(true);
        aplicationBtn.setDisable(true);
        permissionsTableView.setEditable(false);
        changeStatus(true);
        savePermissions();
    }

    //确定
    public void determineSure(){
        updateBtn.setDisable(false);
        determineBtn.setDisable(true);
        aplicationBtn.setDisable(true);
        permissionsTableView.setEditable(false);
        changeStatus(true);
        savePermissions();
        close();
    }

    //退出
    public void close(){
         Stage stage = (Stage)updateBtn.getParent().getScene().getWindow();
        stage.close();
    }


    //保存权限
    public void savePermissions(){

        if("权限组".equals(parentName)){
            Roles roles = rolesService.findRolesByRoleName(roleClick);

            for (PermissionsProperty permissionsProperty:permissionsProperties) {


                if(permissionsProperty.isInsert()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),1);

                   if(permissions != null){
                       RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                       if(rolePermissions == null){

                           rolePermissions = new RolePermissions();

                           rolePermissions.setRoleid(roles.getId());
                           rolePermissions.setRolename(roles.getRolename());
                           rolePermissions.setPermissionsid(permissions.getId());
                           rolePermissions.setPermissionscodes(permissions.getCodes());

                           rolePermissionsService.save(rolePermissions);
                       }
                   }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),1);

                   if(permissions != null){
                       RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                       if(rolePermissions != null){
                           rolePermissionsService.delete(rolePermissions.getId());
                       }

                   }

                }

                if(permissionsProperty.isDelete()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),2);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setRoleid(roles.getId());
                            rolePermissions.setRolename(roles.getRolename());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),2);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }



                if(permissionsProperty.isUpdate()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),3);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setRoleid(roles.getId());
                            rolePermissions.setRolename(roles.getRolename());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),3);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }


                if(permissionsProperty.isSelect()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),4);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setRoleid(roles.getId());
                            rolePermissions.setRolename(roles.getRolename());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),4);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isSh()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),5);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setRoleid(roles.getId());
                            rolePermissions.setRolename(roles.getRolename());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),5);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isChangesh()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),6);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setRoleid(roles.getId());
                            rolePermissions.setRolename(roles.getRolename());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),6);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isCope()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),7);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setRoleid(roles.getId());
                            rolePermissions.setRolename(roles.getRolename());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),7);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isReset()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),8);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setRoleid(roles.getId());
                            rolePermissions.setRolename(roles.getRolename());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),8);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByIdAndRoleid(roles.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }





            }

        }else {
            //用户权限
            EmployeeBasic employeeBasic = employeeBasicService.selectEmployeeByUname(roleClick);


            for (PermissionsProperty permissionsProperty:permissionsProperties) {


                if(permissionsProperty.isInsert()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),1);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setUid(employeeBasic.getId());
                            rolePermissions.setIdnum(employeeBasic.getIdnum());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),1);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isDelete()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),2);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setUid(employeeBasic.getId());
                            rolePermissions.setIdnum(employeeBasic.getIdnum());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),2);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }



                if(permissionsProperty.isUpdate()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),3);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setUid(employeeBasic.getId());
                            rolePermissions.setIdnum(employeeBasic.getIdnum());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),3);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }


                if(permissionsProperty.isSelect()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),4);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setUid(employeeBasic.getId());
                            rolePermissions.setIdnum(employeeBasic.getIdnum());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),4);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isSh()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),5);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setUid(employeeBasic.getId());
                            rolePermissions.setIdnum(employeeBasic.getIdnum());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),5);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isChangesh()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),6);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setUid(employeeBasic.getId());
                            rolePermissions.setIdnum(employeeBasic.getIdnum());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),6);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isCope()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),7);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setUid(employeeBasic.getId());
                            rolePermissions.setIdnum(employeeBasic.getIdnum());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),7);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }

                if(permissionsProperty.isReset()){

                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),8);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions == null){

                            rolePermissions = new RolePermissions();

                            rolePermissions.setUid(employeeBasic.getId());
                            rolePermissions.setIdnum(employeeBasic.getIdnum());
                            rolePermissions.setPermissionsid(permissions.getId());
                            rolePermissions.setPermissionscodes(permissions.getCodes());

                            rolePermissionsService.save(rolePermissions);
                        }
                    }

                }else{
                    //106
                    Permissions permissions = permissionsService.selectPermissionsByParentAndCodes(permissionsProperty.getId(),8);

                    if(permissions != null){
                        RolePermissions rolePermissions = rolePermissionsService.selectRolePermissionsByUidAndPermiss(employeeBasic.getId(),permissions.getId());

                        if(rolePermissions != null){
                            rolePermissionsService.delete(rolePermissions.getId());
                        }

                    }

                }





            }




        }

    }



    public void loadSelect(){

        List<String> permissionsCode = new ArrayList<>();

        if("权限组".equals(parentName)){
            permissionsCode = rolePermissionsService.selectRolePermissions(roleClick);
        }else{
            permissionsCode =  rolePermissionsService.selectRolePermissionsByEmployee(roleClick);
        }

        for (PermissionsProperty permissionsProperty:permissionsProperties) {

            for (String str:permissionsCode) {

                long parentId = Long.parseLong(str.substring(0,str.indexOf("_")));

                if(permissionsProperty.getId() == parentId){

                    int status  =  Integer.parseInt(str.substring(str.lastIndexOf("_")+1,str.length()));

                    switch (status){
                        case 1:
                            permissionsProperty.setInsert(true);
                            break;
                        case 2:
                            permissionsProperty.setDelete(true);
                            break;
                        case 3:
                            permissionsProperty.setUpdate(true);
                            break;
                        case 4:
                            permissionsProperty.setSelect(true);
                            break;
                        case 5:
                            permissionsProperty.setSh(true);
                            break;
                        case 6:
                            permissionsProperty.setChangesh(true);
                            break;
                        case 7:
                            permissionsProperty.setCope(true);
                            break;
                        case 8:
                            permissionsProperty.setReset(true);
                            break;
                        default:
                            break;

                    }



                }

            }

        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        permissions_treeview.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                Node node = event.getPickResult().getIntersectedNode();

                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {

                    loadPermissions(typesFinal); //重新加载权限表
                        //权限组    权限用户
                        parentName = (String) ((TreeItem)permissions_treeview.getSelectionModel().getSelectedItem()).getParent().getValue();
                        //角色  用户名
                    roleClick = (String) ((TreeItem)permissions_treeview.getSelectionModel().getSelectedItem()).getValue();

                    loadSelect();

                }
            }
        });




        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {



                switch (newValue.toString()){
                    case "采购": typesFinal = 1 ;
                                break;
                    case "销售": typesFinal =2;
                                break;
                    case "考勤管理": typesFinal =3;
                        break;
                    case "库存": typesFinal =4;
                        break;
                    case "基本资料": typesFinal =5;
                        break;
                    case "客户关系": typesFinal =6;
                        break;
                    case "统计汇总": typesFinal =7;
                        break;
                    case "账款": typesFinal =8;
                        break;
                    case "其他": typesFinal =9;
                        break;
                        default: typesFinal = 0 ;
                        break;
                }


                loadPermissions(typesFinal);
                loadSelect();
            }
        });


        /**
         * 权限设定修改  code : 1006_579_3
         */

        if(!getPermissions("1006_579_3")){
            updateBtn.setDisable(true);
            insertHome.setDisable(true);
            deleteHome.setDisable(true);
            loadHome.setDisable(true);
        }


        checkTreeValue();

        loadPermissions(0);

        determineBtn.setDisable(true);
        aplicationBtn.setDisable(true);

//        btnUpdate.setDisable(true);
//        btnChoice.setDisable(true);
//        BtnResetPwd.setDisable(true);
//        btnInsert.setDisable(true);
//        btnDelete.setDisable(true);

    }



    public void moreSupplierButtonClick(){

        if("权限组".equals(parentName)){
            permisssionsGroupSet();
        }else if("权限用户".equals(parentName)){
            permissionsEmployeeSet();
        }



    }


    public void permissionsEmployeeSet(){

        stage4.setTitle("权限用户属性");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/permissions_employee.fxml"));
        Scene scene = new Scene(pane);
        stage4.setScene(scene);
        stage4.setResizable(false);
        /*\stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage4.show();

        if(!getPermissions("1006_579_3")){
            uOrder.setEditable(false);
            uName.setEditable(false);
            uLoginName.setEditable(false);
            btnUpdate.setDisable(true);
            btnChoice.setDisable(true);
            btnResetPwd.setDisable(true);
            roleArea.setEditable(false);
            btnInsert.setDisable(true);
            btnDelete.setDisable(true);
            btnok.setDisable(true);
        }

        loadEmployeeInfo();
    }




    //加载用户信息
    public void loadEmployeeInfo(){

        EmployeeBasic employeeBasic = employeeBasicService.selectEmployeeByUname(roleClick);

         List<RoleEmployee> roleEmployees = roleEmployeeService.findRoleEmployeeByIdnum(employeeBasic.getIdnum());


        uOrder.setText(employeeBasic.getIdnum());
        uName.setText(employeeBasic.getEmpname());
        uLoginName.setText(employeeBasic.getEnglishname());

         if(roleEmployees != null){

             for (RoleEmployee roleEmployee:roleEmployees) {
                 roleArea.setText(roleEmployee.getRolename());
             }

         }

    }



    public void permisssionsGroupSet(){

        stage.setTitle("权限组属性");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/role_employee.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        /*\stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();

        if(!getPermissions("1006_579_3")){
            insertEmp.setDisable(true);
            deleteEmp.setDisable(true);
            okEmp.setDisable(true);
            updateEmp.setDisable(true);
        }

        loadEmployee();
    }



    public void loadEmployee(){

        groupName.setText(roleClick);
        groupName.setEditable(false);
        nameArea.setEditable(false);

        List<RoleEmployee> roleEmployees =   roleEmployeeService.findRoleEmployeeByRoleName(roleClick);

        String empName = "";


        if(roleEmployees != null){

            for (int i=0,len=roleEmployees.size();i<len;i++){

                EmployeeBasic employeeBasic = employeeBasicService.selectByKey(roleEmployees.get(i).getUid());

                if(i != 0)empName += "\n";

                empName += employeeBasic.getEmpname()+"("+employeeBasic.getEnglishname()+")";

            }


        }

        nameArea.setText(empName);
    }


    public void coseWin(){
        stage.close();
    }
    public void coseWin2(){
        stage2.close();
    }
    public void coseWin3(){
        stage3.close();
    }
    public void coseWin4(){
       stage4.close();
    }

    public void selectEmployees(){

        stage2.setTitle("选择员工");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/select_employee.fxml"));
        Scene scene = new Scene(pane);
        stage2.setScene(scene);
        stage2.setResizable(false);
        /*\stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage2.show();
        selectLoadEmployees();
    }

    public void insertEmployee(){

        nameArea.setUserData(1);

        selectEmployees();
    }

    public void deleteEmployee(){
        nameArea.setUserData(2);
        selectEmployees();
    }




    public void selectLoadEmployees(){

        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();



        checked.setCellFactory(CheckBoxTableCell.forTableColumn(checked));

        checked.setCellValueFactory(new PropertyValueFactory("cecked"));
        selectEmployeeOrder.setCellValueFactory(new PropertyValueFactory("order"));
        selectEmployeeName.setCellValueFactory(new PropertyValueFactory("name"));

        insetOrder = new ArrayList<>();
        deleteOrder = new ArrayList<>();

        selectEmployeesProperties.clear();
        selectEmployeeTabelView.setEditable(true);

        if(employeeBasics.size()>0){

            for (EmployeeBasic employeeBasic:employeeBasics) {

                SelectEmployeesProperty selectEmployeesProperty = new SelectEmployeesProperty(false,employeeBasic.getId(),employeeBasic.getIdnum(),employeeBasic.getEmpname());

                selectEmployeesProperties.add(selectEmployeesProperty);
            }

        }

        selectEmployeeTabelView.setItems(selectEmployeesProperties);

    }


    public void searchEmployees() {

        String employees = nameArea.getText();

        int status = (int) nameArea.getUserData();
        String newStr = "";

        if (status == 1) {

            for (SelectEmployeesProperty selectEmployeesProperty : selectEmployeesProperties) {

                if (selectEmployeesProperty.isCecked()) {


                    EmployeeBasic employeeBasic = employeeBasicService.selectByKey(selectEmployeesProperty.getId());

                    List<RoleEmployee> roleEmployee = roleEmployeeService.findRoleEmployeeByIdnum(employeeBasic.getIdnum());

                    String newEmploees = employeeBasic.getEmpname() + "(" + employeeBasic.getEnglishname() + ")";

                    int indexIs = employees.indexOf(newEmploees);

                    if (indexIs == -1 && roleEmployee.size() == 0) {

                        insetOrder.add(employeeBasic.getId());

                        newStr += newEmploees + "\n";

                    }
                }
            }
                nameArea.setText(employees + newStr);
            }else{

                for (SelectEmployeesProperty selectEmployeesProperty : selectEmployeesProperties) {

                    if (selectEmployeesProperty.isCecked()) {


                        EmployeeBasic employeeBasic = employeeBasicService.selectByKey(selectEmployeesProperty.getId());

                        String newEmploees = employeeBasic.getEmpname() + "(" + employeeBasic.getEnglishname() + ")";

                        int indexIs = employees.indexOf(newEmploees);

                        if (indexIs != -1) {

                            deleteOrder.add(employeeBasic.getId());

                            newStr = employees.replace(newEmploees, "");
                        }

                    }
                }

                nameArea.setText(newStr);
            }
            coseWin2();


        }

        //确定
        public void readyOperation(){

        if(insetOrder.size() > 0){
            for(int i=0,len=insetOrder.size();i<len;i++){

                EmployeeBasic employeeBasic = employeeBasicService.selectByKey(insetOrder.get(i).longValue());

                Roles roleEmployee = rolesService.findRolesByRoleName(roleClick);

                RoleEmployee roleEmployee1 = new RoleEmployee();

                roleEmployee1.setUid(employeeBasic.getId());
                roleEmployee1.setIdnum(employeeBasic.getIdnum());
                roleEmployee1.setRoleid(roleEmployee.getId());
                roleEmployee1.setRolename(roleEmployee.getRolename());
                roleEmployeeService.save(roleEmployee1);

            }
        }

        if(deleteOrder.size() > 0){

            for(int i=0,len=deleteOrder.size();i<len;i++){

                EmployeeBasic employee = employeeBasicService.selectByKey(deleteOrder.get(i).longValue());

                List<RoleEmployee> employee1 = roleEmployeeService.findRoleEmployeeByIdnum(employee.getIdnum());

                for (RoleEmployee roleEmployee:employee1) {
                    roleEmployeeService.delete(roleEmployee.getId());
                }

            }

        }

        coseWin();

        }

    public void addGrouptFxml(){

        stageAddGroup.setTitle("添加权限组");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/add_group.fxml"));
        Scene scene = new Scene(pane);
        stageAddGroup.setScene(scene);
        stageAddGroup.setResizable(false);
        /*\stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stageAddGroup.show();

    }


    @FXML
    private TextField insertName;

        //添加权限组或者权限用户
    public void saveGroupAndEmployees(){

        System.err.println(parentName+"==="+roleClick);

        if("权限组".equals(parentName)){
            addGrouptFxml();
        }else{
            saveEmployeeName();
        }
    }


    public void saveInsertName(){

       String insName =  insertName.getText();

       if("".equals(insName)){
           alert_informationDialog("请输入组名!");
       }else{
            Roles roles = rolesService.findRolesByRoleName(insName);

            if(roles == null){
                roles = new Roles();
                roles.setRolename(insName);
                roles.setAddtime(new Date());
                rolesService.save(roles);
                alert_informationDialog("添加成功!");
                coseAddGroupt();
                checkTreeValue();
            }else{
                alert_informationDialog("该组名已存在!");
            }
       }

    }


    public void saveEmployeeName(){

        stage3.setTitle("选择员工");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/add_employee.fxml"));
        Scene scene = new Scene(pane);
        stage3.setScene(scene);
        stage3.setResizable(false);
        /*\stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage3.show();

        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();



        addchecked.setCellFactory(CheckBoxTableCell.forTableColumn(addchecked));

        addchecked.setCellValueFactory(new PropertyValueFactory("cecked"));
        addEmployeeOrder.setCellValueFactory(new PropertyValueFactory("order"));
        addEmployeeName.setCellValueFactory(new PropertyValueFactory("name"));

        addinsetOrder = new ArrayList<>();

        addEmployeesProperties.clear();
        addEmployeeTabelView.setEditable(true);

        if(employeeBasics.size()>0){

            for (EmployeeBasic employeeBasic:employeeBasics) {

               AddEmployeesProperty addEmployeesProperty = new AddEmployeesProperty(false,employeeBasic.getId(),employeeBasic.getIdnum(),employeeBasic.getEmpname());

                addEmployeesProperties.add(addEmployeesProperty);
            }

        }

        addEmployeeTabelView.setItems(addEmployeesProperties);

    }


    //保存权限用户
    public void saveEmployeeBtn(){

        for (AddEmployeesProperty addEmployeesProperty:addEmployeesProperties) {

            if(addEmployeesProperty.isCecked()){

                PermissionsEmployee permissionsEmployee = new PermissionsEmployee();
                permissionsEmployee.setUid(addEmployeesProperty.getId());
                permissionsEmployee.setIdnum(addEmployeesProperty.getOrder());
                permissionsEmployee.setUname(addEmployeesProperty.getName());
                permissionsEmployeeService.save(permissionsEmployee);
                alert_informationDialog("添加成功！");
                checkTreeValue();
                coseWin3();
            }

        }

    }




    //删除权限组或者权限用户
    public void deleteGroupAndEmployees(){

        if("权限组".equals(parentName)){
            if(f_alert_confirmDialog("提示","确定删除吗？")){
                Roles roles = rolesService.findRolesByRoleName(roleClick);
                rolesService.delete(roles.getId());
                alert_informationDialog("删除成功!");
                checkTreeValue();
            }
        }else{
            if(f_alert_confirmDialog("提示","确定删除吗？")){
                PermissionsEmployee permissionsEmployee = permissionsEmployeeService.findPermissionsEmployeeByUname(roleClick);
                permissionsEmployeeService.delete(permissionsEmployee.getId());
                alert_informationDialog("删除成功!");
                checkTreeValue();
            }

        }
    }



    public void coseAddGroupt(){
        stageAddGroup.close();
    }

    ObservableList<AddRoleProperty> addRoleProperties = FXCollections.observableArrayList();

    private boolean roleStatus = true;

    private long operactionOrder = 0;

    public void insertRoleEmployee(){
        roleStatus = true;
        insertRoleAsEmployee();
    }

    public void deleteRoleEmplyee(){
        roleStatus =  false;
        insertRoleAsEmployee();
    }




    //权限用户 添加所属
    public void insertRoleAsEmployee(){

        stageRole.setTitle("选择员工");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/add_roles.fxml"));
        Scene scene = new Scene(pane);
        stageRole.setScene(scene);
        stageRole.setResizable(false);

        /*\stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/

        stageRole.show();

        List<Roles> roles = rolesService.findRoles();



        addRoleChecked.setCellFactory(CheckBoxTableCell.forTableColumn(addchecked));

        addRoleChecked.setCellValueFactory(new PropertyValueFactory("cecked"));
        addRoleOrder.setCellValueFactory(new PropertyValueFactory("order"));
        addRoleName.setCellValueFactory(new PropertyValueFactory("name"));


        addRoleProperties.clear();

        addRoleTabelView.setEditable(true);

        if(roles.size()>0){

            for (Roles roles1:roles) {

                AddRoleProperty addRoleProperty = new AddRoleProperty(false,roles1.getId(),roles1.getId().toString(),roles1.getRolename());

                addRoleProperties.add(addRoleProperty);
            }

        }

        addRoleTabelView.setItems(addRoleProperties);



    }


    public void saveRoleBtn(){

        for (AddRoleProperty addRoleProperty:addRoleProperties) {

            if(addRoleProperty.isCecked()){

                   if(roleStatus){
                           roleArea.setText(addRoleProperty.getName());
                   }else{
                        String newStr = roleArea.getText();
                        if(!"".equals(newStr)){
                            roleArea.setText(newStr.replace(addRoleProperty.getName(),""));
                        }
                   }

                operactionOrder = addRoleProperty.getId();

                coseWinRole();
            }
        }
    }

    public void  coseWinRole(){
        stageRole.close();
    }

    public void saveRoleEmployee(){

         if(operactionOrder != 0){

             if(roleStatus){

                 EmployeeBasic employeeBasic = employeeBasicService.selectEmployeeByUname(roleClick);

                 Roles roles =  rolesService.selectByKey(operactionOrder);

                         RoleEmployee roleEmployee = new RoleEmployee();
                         roleEmployee.setUid(employeeBasic.getId());
                         roleEmployee.setIdnum(employeeBasic.getIdnum());
                         roleEmployee.setRoleid(roles.getId());
                         roleEmployee.setRolename(roles.getRolename());
                         roleEmployeeService.save(roleEmployee);
             }else{
                 rolesService.delete(operactionOrder);
             }


         }

        coseWin4();
    }





}
