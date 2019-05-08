package com.yc.education.service.impl;

import com.yc.education.mapper.PermissionsMapper;
import com.yc.education.model.Permissions;
import com.yc.education.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PermissionsServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/1 11:30
 * @Version 1.0
 */
@Service("PermissionsService")
public class PermissionsServiceImpl extends BaseService<Permissions> implements PermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public List<Permissions> selectPermissionsByParent(long parent, int types) {
        try {
            return permissionsMapper.selectPermissionsByParent(parent, types);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean selectPermissionsByTitleAndParent(long parent, String title) {

        Permissions permissions = permissionsMapper.selectPermissionsByTitleAndParent(parent, title);

        if(permissions != null){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Permissions selectPermissionsByParentAndCodes(long parent, int status) {
        try {
            return permissionsMapper.selectPermissionsByParentAndCodes(parent, status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Permissions selectPermissionsByTitleAndCodes(String title, int status) {
        try {
            Permissions permissions = permissionsMapper.selectPermissionsByTitleAndCodes(title, status);

                    if(permissions == null){
                        permissions = new Permissions();
                    }
            return permissions;
        } catch (Exception e) {
            return new Permissions();
        }
    }
}
