package com.admin.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.store.pojo.Goods;
import com.admin.store.service.GoodsService;
import com.admin.store.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author love
 * @description 针对表【goods】的数据库操作Service实现
 * @createDate 2023-10-27 14:50:09
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> queryList(QueryWrapper<Goods> queryWrapper) {
        return goodsMapper.queryList(queryWrapper);
    }

    @Override
    public List<Goods> selectAll(Integer storageId) {
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("storage_id",storageId);
        List<Goods> goods = goodsMapper.selectList(queryWrapper);
        return goods;
    }

    @Override
    public List<Goods> selectType(Integer goodsTypeId) {
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_type_id",goodsTypeId);
        List<Goods> goods = goodsMapper.selectList(queryWrapper);
        return goods;
    }
}




