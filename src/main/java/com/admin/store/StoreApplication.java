package com.admin.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//开启缓存
@EnableCaching
// 开启事务,让Redis数据库与MySQL数据库中的数据保持一致
@EnableTransactionManagement
@MapperScan(basePackages = {"com.admin.store.mapper"})
public class StoreApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(StoreApplication.class, args);
    }
}
