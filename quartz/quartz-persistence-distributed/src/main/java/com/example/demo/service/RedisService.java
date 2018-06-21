package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author HANGKANG
 * @date 2018/6/7 下午2:57
 */

@Service
@Slf4j
public class RedisService {

    @Autowired
    private StringRedisTemplate template;




    public String getKey(String key) {
        return template.opsForValue().get(key);
    }



}
