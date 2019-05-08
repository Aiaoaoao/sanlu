package com.yc.education.controller;

import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.service.basic.EmployeeBasicService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName PasswordController
 * @Description TODO 修改用户密码  用户默认密码为 000000
 * @Author QuZhangJing
 * @Date 2019/2/27 14:27
 * @Version 1.0
 */
@Controller
public class PasswordController extends BaseController implements Initializable {

    @Autowired
    private EmployeeBasicService employeeBasicService;

    @FXML private TextField username; //用户名

    @FXML private PasswordField originalPassWord; //旧密码

    @FXML private PasswordField newPassWord; //新密码

    @FXML private PasswordField againPassWord; //再次输入新密码

    @FXML private Button okbtn;

    /**
     * 修改密码
     */
    public void updatePassWord(){

        String originalPass =  originalPassWord.getText();//旧密码

        String newPass =  newPassWord.getText();//新密码

        String againPass =  againPassWord.getText();//再次输入新密码

        if("".equals(originalPass) || "".equals(newPass) || "".equals(againPass)){
            alert_informationDialog("请填写完整信息！");
            return;
        }else if(!newPass.equals(againPass)){
            alert_informationDialog("两次密码不一致！");
            return;
        }else{

                EmployeeBasic employeeBasic = employeeBasicService.selectEmployeeBasicByIsnum(getUserName());

                if(employeeBasic != null){

                    if(originalPass.equals(employeeBasic.getPassword())){

                        employeeBasic.setPassword(againPass);

                        employeeBasicService.updateNotNull(employeeBasic);

                        alert_informationDialog("修改成功！");

                        closeWin();
                    }else{
                        alert_informationDialog("旧密码错误！");
                        return;
                    }


                }


        }



    }


    /**
     * 关闭当前窗体
     */
    public void closeWin(){
            Stage stage  =  (Stage)username.getScene().getWindow();

            stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


            EmployeeBasic employeeBasic = employeeBasicService.selectEmployeeBasicByIsnum(getUserName());

        /**
         * 权限管理 用户密码 修改 -- 1008_584_3
         */
        if(!getPermissions("1008_584_3")){
            originalPassWord.setEditable(false);
            newPassWord.setEditable(false);
            againPassWord.setEditable(false);
            okbtn.setDisable(true);
        }


            username.setText("("+getUserName()+")"+employeeBasic.getEmpname());
    }


}
