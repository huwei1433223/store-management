package com.admin.store.controller;

import com.admin.store.param.GoodsListParam;
import com.admin.store.pojo.Goods;
import com.admin.store.service.GoodsService;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Name=GoodsController
 * @Author：文以载道
 * @Description： :
 * time：2023/10/27的14:58
 * package：com.admin.store.controller
 */
@RequestMapping("/goods")
@RestController
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Cacheable(cacheNames = "goods", key = "#goodsListParam")
    @PostMapping("/list")
    public Result queryList(@RequestBody GoodsListParam goodsListParam) {
        HashMap<String, Object> hashMap = goodsListParam.getMap();
        String goodsName = (String) hashMap.get("goodsName");
        String storageId = (String) hashMap.get("storageId");
        String goodsTypeId = (String) hashMap.get("goodsTypeId");
        //开始定义页面参数
        PageHelper.startPage(goodsListParam.getCurrentPage(), goodsListParam.getPageSize());
        //查询
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(goodsName) && !"".equals(goodsName)) {
            queryWrapper.like("goods_name", goodsName);
        }
        if (storageId != null && !"".equals(storageId)) {
            queryWrapper.eq("storage_id", storageId);
        }
        if (goodsTypeId != null && !"".equals(goodsTypeId)) {
            queryWrapper.eq("goods_type_id", goodsTypeId);
        }
        List<Goods> goods = goodsService.queryList(queryWrapper);
        //分页结果
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return Result.success(map);
    }

    //保存货物信息
    @CacheEvict(value = "goods", allEntries = true)
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getGoodsName, goods.getGoodsName());
        Goods ifGoods = goodsService.getOne(lambdaQueryWrapper);
        if (ifGoods != null) {
            return Result.error(goods.getGoodsName() + "已经存在，添加失败");
        }
        if (!goodsService.save(goods)) {
            return Result.error("保存失败");
        }
        return Result.success(null, "保存成功");
    }

    @CacheEvict(value = "goods", allEntries = true)
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
        if (!goodsService.updateById(goods)) {
            return Result.error("更新失败");
        }
        return Result.success(null, "更新成功");
    }

    @CacheEvict(value = "goods", allEntries = true)
    @GetMapping("/delete/{goodsId}")
    public Result delete(@PathVariable Integer goodsId) {
        if (!goodsService.removeById(goodsId)) {
            return Result.error("删除失败");
        }
        return Result.success(null, "删除成功");
    }
}
