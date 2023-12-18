package com.admin.store.mapper;

import com.admin.store.param.UserListParam;
import com.admin.store.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author love
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-10-27 14:51:14
* @Entity com.admin.store.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    List<User> queryList(UserListParam userListParam);

}




