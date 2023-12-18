package com.admin.store.service;

import com.admin.store.param.UserListParam;
import com.admin.store.param.UserLoginParam;
import com.admin.store.pojo.User;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author love
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-27 14:51:14
*/
public interface UserService extends IService<User> {
    Result<Object> login(UserLoginParam userLoginParam);

    Result queryList(UserListParam userListParam);
}
