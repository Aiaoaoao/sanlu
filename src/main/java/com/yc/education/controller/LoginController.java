package com.yc.education.controller;

import com.yc.education.application.NetworkConfiguration;
import com.yc.education.model.DataSetting;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.service.UsersService;
import com.yc.education.service.basic.EmployeeBasicService;
import com.yc.education.util.PropertyUtil;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *@Description TODO 登录窗体
 *@Author QuZhangJing
 *@Date 13:02  2018-08-07
 *@Version 1.0
 */
@Controller
public class LoginController extends BaseController implements Initializable {


    @Autowired
    private UsersService usersService;

    @Autowired
    private EmployeeBasicService employeeBasicService;

    @FXML private Button closeButton;

    @FXML private ComboBox networkComboBox;


    @FXML private ComboBox emporder;

    @FXML private TextField empname;


    @FXML private  PasswordField password;
    private  Stage stageStock = new Stage();

    /**
     * 登录按钮
     */
    public void login(){

        String order = "";

        if(emporder.getSelectionModel().getSelectedIndex() != -1)order = emporder.getSelectionModel().getSelectedItem().toString();

        String name = empname.getText();

        String passwords = password.getText();

        if("".equals(order)){
            alert_informationDialog("请选择用户编号");
            return;
        }else if("".equals(name)){
            alert_informationDialog("请输入用户名称");
            return;
        }else if("".equals(passwords)){
            alert_informationDialog("请输入密码");
            return;
        }else{
            //进行账号校验
//                EmployeeBasic employeeBasic =   employeeBasicService.selectEmployeeLogin(order,name,passwords);

            EmployeeBasic employeeBasic =   employeeBasicService.selectEmployeeBasicByIsnum(order);

                if(employeeBasic != null){
                    if(name.equals(employeeBasic.getEmpname()) ){
                        if(passwords.equals(employeeBasic.getPassword())){
                            //登录成功


                            try {
                                //获取当前执行的用户
                                Subject currentUser = SecurityUtils.getSubject();
                                //设置永不过期
                                SecurityUtils.getSubject().getSession().setTimeout(-1000l);
                                //创建token令牌，用户名/密码
                                UsernamePasswordToken token = new UsernamePasswordToken(order,passwords);
                                token.setRememberMe(true);
                                currentUser.login(token);

                                closeWin();

                                stageStock.setScene(new Scene(new Pane()));
                                StageManager.CONTROLLER.put("rightWinStage",stageStock);
                                NetworkConfiguration.homeShow();
                            } catch (AuthenticationException e) {
                                System.err.println("登录失败");
                                return;
                            }



                        }else {
                            alert_informationDialog("登录失败,密码错误！");
                        }
                    }else{
                        alert_informationDialog("登录失败,用户名错误！");
                    }
                }else{
                    alert_informationDialog("登录失败,该用户不存在！");
                }

        }

//        closeWin();
//
//        NetworkConfiguration.homeShow();

        //获取当前项目的绝对路径
//        String pathUrl = System.getProperty("user.dir").replace("\\", "/");

        //得到配置文件某个属性的值
//        System.err.println(PropertyUtil.getPro(pathUrl+"\\src\\main\\resources\\jdbc.properties","jdbc.url")+"(2)::  *************************************************************************************************");

        //修改配置文件中某个属性的值
//        PropertyUtil.updatePro(pathUrl+"\\src\\main\\resources\\jdbc.properties","jdbc.url","jdbc:mysql://192.168.1.122:3306/sanlu?characterEncoding=UTF-8");

    }

    //退出程序
    public void closeButton(){
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
        System.exit(0);
    }


    //关闭当前窗体
    public void closeWin(){
        System.err.println("关闭当前窗体");
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
    }


    //网络配置
    public void networkConfiguration(){

        //加载配置窗体
        NetworkConfiguration.display();

     /*   ObservableList<String> options = FXCollections.observableArrayList("Option 1","Option 2","Option 3");

        networkComboBox.setItems(options);*/

        //初始化 协议下拉默认值
        networkComboBox.getItems().addAll(
                "https",
                "http",
                "ftp"
        );
    }


    /**
     * 网络配置 ---确定
     */
    public void relyConfig(){

        System.err.println("网络配置 ---确定");

        closeWin();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();

        ObservableList<String> str = FXCollections.observableArrayList();

        for (EmployeeBasic employeeBasic:employeeBasics) {
            str.add(employeeBasic.getIdnum());
        }
        emporder.setItems(str);





        emporder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                EmployeeBasic employeeBasic =  employeeBasicService.selectEmployeeBasicByIsnum(newValue.toString());

                if(employeeBasic != null)empname.setText(employeeBasic.getEmpname());

            }
        });

    }




}
