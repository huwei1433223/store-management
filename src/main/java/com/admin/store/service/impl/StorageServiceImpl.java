package com.admin.store.service.impl;

import com.admin.store.param.StoragesListParam;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.store.pojo.Storage;
import com.admin.store.service.StorageService;
import com.admin.store.mapper.StorageMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author love
 * @description 针对表【storage】的数据库操作Service实现
 * @createDate 2023-10-27 14:51:11
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage>
        implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public Result queryList(StoragesListParam storagesListParam) {
        //调用PageHelper中API
        PageHelper.startPage(storagesListParam.getCurrentPage(), storagesListParam.getPageSize());
        //查询数据
        List<Storage> storages = null;
        storages = storageMapper.queryList(storagesListParam);
        //分页
        PageInfo<Storage> pageInfo = new PageInfo<>(storages);
        Map<String, Object> map = new HashMap<>();
        // 获取分页后的信息
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return Result.success(map);
    }
}




