package com.admin.store.param;

import lombok.Data;

/**
 * @Name=PageParam
 * @Author：文以载道
 * @Description： :
 * time：2023/10/4的10:47
 * package：com.hhj.commons.param
 */
@Data

public class PageParam {
    private int currentPage = 1;//默认第一页
    private int pageSize = 10;//默认10
}
