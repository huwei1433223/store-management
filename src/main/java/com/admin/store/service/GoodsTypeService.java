package com.admin.store.service;

import com.admin.store.param.GoodsTypeListParam;
import com.admin.store.pojo.GoodsType;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author love
* @description 针对表【goods_type】的数据库操作Service
* @createDate 2023-10-27 14:51:00
*/
public interface GoodsTypeService extends IService<GoodsType> {

    Result queryList(GoodsTypeListParam goodsTypeListParam);
}
