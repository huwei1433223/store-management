package com.admin.store.mapper;

import com.admin.store.pojo.Goods;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* @author love
* @description 针对表【goods】的数据库操作Mapper
* @createDate 2023-10-27 14:50:09
* @Entity com.admin.store.domain.Goods
*/
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> queryList(@Param(Constants.WRAPPER)  QueryWrapper<Goods> queryWrapper);
}




