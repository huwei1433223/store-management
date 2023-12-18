package com.admin.store.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name=GoodsTypeConller
 * @Author：文以载道
 * @Description： :
 * time：2023/11/1的16:09
 * package：com.admin.store.param
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsTypeListParam {
    private String GoodsTypeName;
    private int currentPage = 1;//默认第一页
    private int pageSize = 10;//默认10
}

