package com.yc.education.util;

import javafx.scene.control.Label;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName NumberUtil
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/22 14:08
 * @Version 1.0
 */
public class NumberUtil {


    /**
     * 26英文字母表
     */
    public static final String[] ALPHABET={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    /**
     * 供应商最大编号 A999、B999
     */
    public  static final int MAXNUMBER =999;

    /**
     * NULL
     */
    public static final  String NULLSTRING = "";


    /**
     *  新增
     */
    public static final  int INSERT = 1;

    /**
     * 修改
     */
    public static final  int UPDATE = 2;

    /**
     * 【待输入】
     */
    public static final  String WAITINPUT = "【待输入】";
    /**
     * 【查看】
     */
    public static final  String WAITSEE = "【查看】";


    public static Map<Boolean,String> resultMap =  new HashMap<>();




    /**
     * 修改窗体状态
     * @param stuats
     */
    public static void changeStatus(Label fxmlStatus, int stuats){

        if(stuats==1){
            //窗体状态
            fxmlStatus.setUserData(NumberUtil.INSERT);  // 默认:0  新增:1  修改:2
            fxmlStatus.setText(NumberUtil.WAITINPUT);

        }else if(stuats==2){
            //窗体状态
            fxmlStatus.setUserData(NumberUtil.UPDATE);  // 默认:0  新增:1  修改:2
            fxmlStatus.setText(NumberUtil.WAITINPUT);
        }else {
            fxmlStatus.setText(NumberUtil.WAITSEE);
        }

    }



    /**
     *@Description TODO  将数组转换成对象 注意参数要一一致
     *@Author QuZhangJing
     *@Date 17:54  2018/8/30
     *@Version 1.0
     *@param obj 数据数组
     *@param classType 类字节码
     */
    public static Object arrayToObject(Object[] obj,Class<?> classType) {

        Object stu1= null;
        try {
            stu1 = classType.newInstance();

            for(int i=0;i< classType.getDeclaredFields().length;i++){

                String setMethodName="set"+classType.getDeclaredFields()[i].getName().substring(0,1).toUpperCase()+classType.getDeclaredFields()[i].getName().substring(1);

                Method setMethod=classType.getDeclaredMethod(setMethodName, new Class[]{classType.getDeclaredFields()[i].getType()});

                setMethod.invoke(stu1,obj[i]);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
        return stu1;
    }



}
