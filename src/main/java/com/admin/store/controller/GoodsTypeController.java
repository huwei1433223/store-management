package com.admin.store.controller;

import com.admin.store.param.GoodsTypeListParam;
import com.admin.store.pojo.Goods;
import com.admin.store.pojo.GoodsType;
import com.admin.store.pojo.User;
import com.admin.store.service.GoodsService;
import com.admin.store.service.GoodsTypeService;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Name=GoodsTypeController
 * @Author：文以载道
 * @Description： :
 * time：2023/11/1的16:08
 * package：com.admin.store.controller
 */
@RequestMapping("/goodstype")
@RestController
@CrossOrigin
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private GoodsService goodsService;
    //根据货物类型名进行模糊查询
    @Cacheable(cacheNames = "goods_type", key = "#goodsTypeListParam")
    @PostMapping("/list")
    public Result storageList(@RequestBody GoodsTypeListParam goodsTypeListParam) {
        return goodsTypeService.queryList(goodsTypeListParam);
    }

    @CacheEvict(value = "goods_type", allEntries = true)
    @PostMapping("/save")
    public Result save(@RequestBody GoodsType goodsType) {
        LambdaQueryWrapper<GoodsType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(GoodsType::getGoodsTypeName, goodsType.getGoodsTypeName());
        GoodsType ifGoodsType= goodsTypeService.getOne(lambdaQueryWrapper);
        if (ifGoodsType != null) {
            return Result.error("该物品分类已经存在，添加失败");
        }
        if (!goodsTypeService.save(goodsType)) {
            return Result.error("保存失败");
        }
        return Result.success(null, "添加成功");
    }

    @CacheEvict(value = "goods_type", allEntries = true)
    @PostMapping("/update")
    public Result update(@RequestBody GoodsType goodsType) {
        if (!goodsTypeService.updateById(goodsType)) {
            return Result.error("更新失败");
        }
        return Result.success(null, "更新成功");
    }

    @CacheEvict(value = {"goods","goods_type"}, allEntries = true)
    @GetMapping("/delete/{goodsTypeId}")
    public Result delete(@PathVariable Integer goodsTypeId) {
        List<Goods> goods = goodsService.selectType(goodsTypeId);
        if (goods.size()>0){
            goodsService.removeByIds(goods);
        }
        if (!goodsTypeService.removeById(goodsTypeId)) {
            return Result.error("删除失败");
        }
        return Result.success(null, "删除成功");
    }

    @Cacheable(cacheNames = "goods_type")
    @GetMapping("/listAll")
    public Result listAll() {
        return Result.success(goodsTypeService.list());
    }
}
