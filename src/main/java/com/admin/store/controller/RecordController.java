package com.admin.store.controller;

import com.admin.store.param.GoodsListParam;
import com.admin.store.pojo.Goods;
import com.admin.store.pojo.Record;
import com.admin.store.service.GoodsService;
import com.admin.store.service.RecordService;
import com.admin.store.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Name=RecordController
 * @Author：文以载道
 * @Description： :
 * time：2023/11/2的10:54
 * package：com.admin.store.controller
 */
@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {
    @Autowired
    private RecordService recordService;

    @Autowired
    private GoodsService goodsService;

    @Cacheable(cacheNames = "record", key = "#goodsListParam")
    @PostMapping("/list")
    public Result queryList(@RequestBody GoodsListParam goodsListParam) {
        return recordService.queryList(goodsListParam);
    }

    @CacheEvict(value = {"record", "goods"}, allEntries = true)
    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoodsId());
        int n = record.getCount();
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Timestamp timestamp = Timestamp.valueOf(nowTime);
        //出库
        if ("2".equals(record.getAction())) {
            n = -n;
            record.setCount(n);
        }
        if ((goods.getCount() + n) < 0) {
            return Result.error("库存不足,无法出库");
        }
        goods.setCount(goods.getCount() + n);
        goodsService.updateById(goods);
        record.setCreateTime(timestamp);
        return recordService.save(record) ? Result.success(null, "操作成功") : Result.error("操作失败");
    }
}
