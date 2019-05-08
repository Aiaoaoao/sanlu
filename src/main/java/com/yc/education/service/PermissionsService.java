package com.yc.education.service;

import com.yc.education.model.Permissions;

import java.util.List;

/**
 * @ClassName PermissionsService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/1 11:26
 * @Version 1.0
 */
public interface PermissionsService extends IService<Permissions>  {

    /**
     * 根据父级编号和分类查询权限
     * @param parent
     * @return
     */
    List<Permissions> selectPermissionsByParent(long parent,int types);


    boolean selectPermissionsByTitleAndParent(long parent,String title);


    Permissions selectPermissionsByParentAndCodes(long parent,int status);


    Permissions selectPermissionsByTitleAndCodes(String title ,int status);


}
