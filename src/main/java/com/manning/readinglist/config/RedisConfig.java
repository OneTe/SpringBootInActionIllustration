package com.manning.readinglist.config;

import com.manning.readinglist.entity.Book;
import com.manning.readinglist.util.RedisObjectSerializer;
import com.manning.readinglist.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by wangcheng  on 2018/7/18.
 */
@Configuration
public class RedisConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String,Book> redisTemplate() {
        RedisTemplate<String,Book> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        System.out.println("---------aaaaaaaaa--------");
        return template;
    }
}
