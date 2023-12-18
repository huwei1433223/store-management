package com.admin.store.param;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Name=UserListParam
 * @Author：文以载道
 * @Description： :
 * time：2023/10/30的15:15
 * package：com.admin.store.param
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListParam {

    private String name;
    private Integer sex;
    private Integer roleId;
    private int currentPage = 1;//默认第一页
    private int pageSize = 10;//默认10
}
