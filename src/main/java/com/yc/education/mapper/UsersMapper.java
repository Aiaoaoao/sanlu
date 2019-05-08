package com.yc.education.mapper;

import com.yc.education.model.Users;
import com.yc.education.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName UsersMapper
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018-08-07 11:38
 * @Version 1.0
 */
@Repository
public interface UsersMapper extends MyMapper<Users> {

    /**
     * 查询所有用户
     * @return
     */
    List<Users> selectUsersAll();



}
