package com.admin.store.config;

/**
 * @Name=RedisConfig
 * @Author：文以载道
 * @Description： :
 * time：2023/10/10的15:09
 * package：com.example.demo.config
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.Duration;

/**
 * 序列化和自定义缓存有效期
 */
@Configuration
public class RedisConfig {
    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisTemplate.getConnectionFactory())
                .withCacheConfiguration("user", getCacheConfigurationWithTtl(redisTemplate, 5 * 60))//5分钟
                .withCacheConfiguration("goods", getCacheConfigurationWithTtl(redisTemplate, 5 * 60))//5分钟
                .withCacheConfiguration("goods_type", getCacheConfigurationWithTtl(redisTemplate, 5 * 60))
                .withCacheConfiguration("record", getCacheConfigurationWithTtl(redisTemplate, 5 * 60))
                .withCacheConfiguration("storage", getCacheConfigurationWithTtl(redisTemplate, 5 * 60))
                .cacheDefaults(getCacheConfigurationWithTtl(redisTemplate, 60 * 60))//60分钟==1小时
                .transactionAware()
                .build();
        return redisCacheManager;
    }

    RedisCacheConfiguration getCacheConfigurationWithTtl(RedisTemplate<String, Object> redisTemplate, long seconds) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getStringSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(seconds));
    }

}
