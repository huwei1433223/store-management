package com.admin.store.controller;

import com.admin.store.param.UserListParam;
import com.admin.store.param.UserLoginParam;
import com.admin.store.pojo.User;
import com.admin.store.service.UserService;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Name=UserController
 * @Author：文以载道
 * @Description： :
 * time：2023/10/27的15:28
 * package：com.admin.store.controller
 */

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param userLoginParam
     * @param result
     * @return
     */
    @PostMapping("/login")
    public Result<Object> login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error("账号密码不能为空");
        }
        return userService.login(userLoginParam);
    }

    @Cacheable(cacheNames = "user", key = "#userListParam")
    @PostMapping("/list")
    public Result list(@RequestBody UserListParam userListParam) {
        return userService.queryList(userListParam);
    }

    @Cacheable(cacheNames = "user", key = "#user")
    @GetMapping("/findByUsername")
    public Result findByUsername(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User user1 = userService.getOne(lambdaQueryWrapper);
        if (user1 != null) {
            return Result.success(null, "账号存在");
        }
        return Result.error("账号不存在！");
    }

    @CacheEvict(value = "user", allEntries = true)
    @PostMapping("/save")
    public Result save(@RequestBody @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error("参数异常保存失败");
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User ifUser = userService.getOne(lambdaQueryWrapper);
        if (ifUser != null) {
            return Result.error("用户已存在，添加失败");
        }
        if (!userService.save(user)) {
            return Result.error("保存失败");
        }
        return Result.success(null, "添加成功");
    }

    @CacheEvict(value = "user", allEntries = true)
    @PostMapping("/update")
    public Result update(@RequestBody @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error("参数异常更新失败");
        }
        if (!userService.updateById(user)) {
            return Result.error("更新失败");
        }
        return Result.success(null, "更新成功");
    }

    @CacheEvict(value = "user", allEntries = true)
    @GetMapping("/delete/{userId}")
    public Result delete(@PathVariable Integer userId) {
        if (!userService.removeById(userId)) {
            return Result.error("删除失败");
        }
        return Result.success(null, "删除成功");
    }
}