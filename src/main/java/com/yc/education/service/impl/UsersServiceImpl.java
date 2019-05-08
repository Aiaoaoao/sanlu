package com.yc.education.service.impl;

import com.yc.education.mapper.UsersMapper;
import com.yc.education.model.Users;
import com.yc.education.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UsersServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018-08-07 11:46
 * @Version 1.0
 */
@Service("UsersService")
public class UsersServiceImpl extends BaseService<Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> selectUsersAll() {
        return usersMapper.selectUsersAll();
    }


}
