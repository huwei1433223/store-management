package com.admin.store.mapper;

import com.admin.store.param.StoragesListParam;
import com.admin.store.pojo.Storage;
import com.admin.store.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author love
* @description 针对表【storage】的数据库操作Mapper
* @createDate 2023-10-27 14:51:11
* @Entity com.admin.store.domain.Storage
*/
public interface StorageMapper extends BaseMapper<Storage> {

    List<Storage> queryList(StoragesListParam storagesListParam);
}




