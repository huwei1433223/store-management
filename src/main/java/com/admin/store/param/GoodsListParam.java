package com.admin.store.param;

import lombok.Data;

import java.util.HashMap;

/**
 * @Name=GoodsListParam
 * @Author：文以载道
 * @Description： :
 * time：2023/11/2的9:30
 * package：com.admin.store.param
 */
@Data
public class GoodsListParam {

    private int currentPage = 1;//默认第一页
    private int pageSize = 10;//默认10
    private HashMap<String, Object> map = new HashMap<>();
}
