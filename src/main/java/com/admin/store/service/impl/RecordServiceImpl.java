package com.admin.store.service.impl;


import com.admin.store.param.GoodsListParam;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.store.pojo.Record;
import com.admin.store.service.RecordService;
import com.admin.store.mapper.RecordMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author love
 * @description 针对表【record】的数据库操作Service实现
 * @createDate 2023-10-27 14:51:08
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
        implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Result queryList(GoodsListParam goodsListParam) {
        HashMap<String, Object> hashMap = goodsListParam.getMap();
        String goodsName = (String) hashMap.get("goodsName");
        String storageId = (String) hashMap.get("storageId");
        String goodsTypeId = (String) hashMap.get("goodsTypeId");
        String roleId = (String) hashMap.get("roleId");
        String userId = (String) hashMap.get("userId");
        if (!"2".equals(roleId)) {
            userId = "";
        }

        PageHelper.startPage(goodsListParam.getCurrentPage(), goodsListParam.getPageSize());
        List<Map<String, Object>> recordMap = recordMapper.queryList(goodsName, storageId, goodsTypeId, userId);
        //分页
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(recordMap);
        Map<String, Object> map = new HashMap<>();
        // 获取分页后的信息
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return Result.success(map);
    }
}




