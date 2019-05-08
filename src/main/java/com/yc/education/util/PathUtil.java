package com.yc.education.util;

import javafx.scene.image.Image;

/**
 * @ClassName PathUtil
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/4/25 11:06
 * @Version 1.0
 */
public class PathUtil {

    public static boolean debug = false;
    /**项目所在路径*/
    public static final String projectPath = initProjectPathAndDebug();

    public static String path = PathUtil.projectPath.replaceAll("\\\\","\\\\\\\\");

    public static Image imageLeft = new Image("file:"+path+"\\images\\rightgo.png");

    public static Image imageRight = new Image("file:"+path+"\\images\\leftgo.png");


    /***
     * 获取项目根路径，无论是打包成jar文件。
     * 为了保证调试时获取项目路径，而不是bin路径，增加逻辑： 如果以bin目录接，则返回上一层目录
     * 例如：F:\eclipseworkJavaFX\PersonalAssistant  后面的bin目录会去掉
     * @return 例如：F:\eclipseworkJavaFX\AddressApp\build\dist
     */
    private static String initProjectPathAndDebug(){
        java.net.URL url = PathUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar"))  {
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        //如果以bin目录接，则说明是开发过程debug测试查询，返回上一层目录
        if (filePath.endsWith("bin/") || filePath.endsWith("bin\\") )  {
            debug = true;
            filePath = filePath.substring(0, filePath.lastIndexOf("bin"));
        }
        java.io.File file = new java.io.File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }

    /***
     * 这个方法打包位jar文件就无法获取项目路径了。
     * @return
     */
    public static String getRealPath() {
        String realPath = PathUtil.class.getClassLoader().getResource("").getFile();
        java.io.File file = new java.io.File(realPath);
        realPath = file.getAbsolutePath();//去掉了最前面的斜杠/
        try {
            realPath = java.net.URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realPath;
    }
    public static void main(String[] args) {
        System.out.println(projectPath);
    }



}
