package com.admin.store.controller;

import com.admin.store.param.StoragesListParam;
import com.admin.store.pojo.Goods;
import com.admin.store.pojo.Storage;
import com.admin.store.service.GoodsService;
import com.admin.store.service.StorageService;
import com.admin.store.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Name=StorageController
 * @Author：文以载道
 * @Description： :
 * time：2023/11/1的15:15
 * package：com.admin.store.controller
 */
@RequestMapping("/storage")
@RestController
@CrossOrigin
public class StorageController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private GoodsService goodsService;
    //根据仓库名进行模糊查询
    @Cacheable(cacheNames = "storage", key = "#storagesListParam")
    @PostMapping("/list")
    public Result storageList(@RequestBody StoragesListParam storagesListParam) {
        return storageService.queryList(storagesListParam);
    }

    @CacheEvict(value = "storage", allEntries = true)
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        if (!storageService.save(storage)) {
            return Result.error("保存失败");
        }
        return Result.success(null, "添加成功");
    }

    @CacheEvict(value = "storage", allEntries = true)
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        if (!storageService.updateById(storage)) {
            return Result.error("更新失败");
        }
        return Result.success(null, "更新成功");
    }

    @CacheEvict(value = {"storage", "goods"}, allEntries = true)
    @GetMapping("/delete/{storageId}")
    public Result delete(@PathVariable Integer storageId) {

        List<Goods> goods = goodsService.selectAll(storageId);
        if (goods.size()>0){
            goodsService.removeByIds(goods);
        }
        if (!storageService.removeById(storageId)) {
            return Result.error("删除失败");
        }
        return Result.success(null, "删除成功");
    }

    @Cacheable(cacheNames = "storage")
    @GetMapping("/listAll")
    public Result listAll() {
        return Result.success(storageService.list());
    }
}