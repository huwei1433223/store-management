package com.admin.store.service;

import com.admin.store.pojo.Goods;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author love
* @description 针对表【goods】的数据库操作Service
* @createDate 2023-10-27 14:50:09
*/
public interface GoodsService extends IService<Goods> {

    List<Goods> queryList(QueryWrapper<Goods> queryWrapper);

    List<Goods> selectAll(Integer storageId);

    List<Goods> selectType(Integer goodsTypeId);
}
