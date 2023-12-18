package com.admin.store.service;

import com.admin.store.param.GoodsListParam;
import com.admin.store.pojo.Record;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* @author love
* @description 针对表【record】的数据库操作Service
* @createDate 2023-10-27 14:51:08
*/
public interface RecordService extends IService<Record> {


    Result queryList(GoodsListParam goodsListParam);
}
