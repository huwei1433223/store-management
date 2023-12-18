package com.admin.store.mapper;

import com.admin.store.param.GoodsTypeListParam;
import com.admin.store.pojo.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author love
* @description 针对表【goods_type】的数据库操作Mapper
* @createDate 2023-10-27 14:51:00
* @Entity com.admin.store.domain.GoodsType
*/
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    List<GoodsType> queryList(GoodsTypeListParam goodsTypeListParam);
}




