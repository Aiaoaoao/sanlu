package com.yc.education.mapper;

import com.yc.education.model.Permissions;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionsMapper extends MyMapper<Permissions> {


    /**
     * 根据父级编号和分类查询权限
     * @param parent
     * @return
     */
    List<Permissions> selectPermissionsByParent(@Param("parent")long parent,@Param("types")int types);


    Permissions selectPermissionsByTitleAndParent(@Param("parent")long parent,@Param("title")String title);


    Permissions selectPermissionsByParentAndCodes(@Param("parent")long parent,@Param("status")int status);

    Permissions selectPermissionsByTitleAndCodes(@Param("title")String title ,@Param("status")int status);

}