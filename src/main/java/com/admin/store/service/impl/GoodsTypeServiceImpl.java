package com.admin.store.service.impl;

import com.admin.store.param.GoodsTypeListParam;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.store.pojo.GoodsType;
import com.admin.store.service.GoodsTypeService;
import com.admin.store.mapper.GoodsTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author love
 * @description 针对表【goods_type】的数据库操作Service实现
 * @createDate 2023-10-27 14:51:00
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType>
        implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public Result queryList(GoodsTypeListParam goodsTypeListParam) {
        //调用PageHelper中API
        PageHelper.startPage(goodsTypeListParam.getCurrentPage(), goodsTypeListParam.getPageSize());
        //查询数据
        List<GoodsType> goodsTypes = null;
        goodsTypes = goodsTypeMapper.queryList(goodsTypeListParam);
        //分页
        PageInfo<GoodsType> pageInfo = new PageInfo<>(goodsTypes);
        Map<String, Object> map = new HashMap<>();
        // 获取分页后的信息
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return Result.success(map);
    }
}




