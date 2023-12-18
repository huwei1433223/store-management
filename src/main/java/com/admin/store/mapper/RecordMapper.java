package com.admin.store.mapper;

import com.admin.store.pojo.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
* @author love
* @description 针对表【record】的数据库操作Mapper
* @createDate 2023-10-27 14:51:08
* @Entity com.admin.store.domain.Record
*/
public interface RecordMapper extends BaseMapper<Record> {
    List<Map<String,Object>> queryList(String goodsName,String storageId, String goodsTypeId, String userId);
}




