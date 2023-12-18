package com.admin.store.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.admin.store.mapper.MenuMapper;
import com.admin.store.param.UserListParam;
import com.admin.store.param.UserLoginParam;
import com.admin.store.pojo.Menu;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.store.pojo.User;
import com.admin.store.service.UserService;
import com.admin.store.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author love
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-10-27 14:51:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 首先进行密码加密
     * 再次进行账户密码对比
     * 返回结果
     *
     * @param userLoginParam
     * @return
     */
    @Override
    public Result<Object> login(UserLoginParam userLoginParam) {
        //明文加密
        String password = SecureUtil.md5(userLoginParam.getPassword());
        userLoginParam.setPassword(password);
        //查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userLoginParam.getUsername());
        queryWrapper.eq("password", userLoginParam.getPassword());
        User user = userMapper.selectOne(queryWrapper);
        //检验是否存在账户密码
        if (user == null) {
            return Result.error("账号或密码出错了");
        }
        QueryWrapper<Menu> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.like("menu_right", user.getRoleId());
        List<Menu> menu = menuMapper.selectList(queryWrapper1);
        //比对成功，返回密码设为空
        user.setPassword(null);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user", user);
        hashMap.put("menu", menu);
        return Result.success(hashMap);
    }

    @Override
    public Result queryList(UserListParam userListParam) {
        //调用PageHelper中API
        PageHelper.startPage(userListParam.getCurrentPage(), userListParam.getPageSize());
        //查询数据
        List<User> users = null;
        users = userMapper.queryList(userListParam);
        //分页
        PageInfo<User> pageInfo = new PageInfo<>(users);
        Map<String, Object> map = new HashMap<>();
        // 获取分页后的信息
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return Result.success(map);
    }
}




