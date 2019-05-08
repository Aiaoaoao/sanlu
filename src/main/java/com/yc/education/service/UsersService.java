package com.yc.education.service;

import com.yc.education.model.Users;

import java.util.List;

/**
 * @ClassName UsersService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018-08-07 11:46
 * @Version 1.0
 */
public interface UsersService extends IService<Users> {

    /**
     * 查询所有用户
     * @return
     */
    List<Users> selectUsersAll();


}
