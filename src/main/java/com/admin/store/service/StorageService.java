package com.admin.store.service;

import com.admin.store.param.StoragesListParam;
import com.admin.store.pojo.Storage;
import com.admin.store.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author love
* @description 针对表【storage】的数据库操作Service
* @createDate 2023-10-27 14:51:11
*/
public interface StorageService extends IService<Storage> {

    Result queryList(StoragesListParam storagesListParam);
}
