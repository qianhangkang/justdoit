package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author HANGKANG
 * @date 2018/6/7 下午3:28
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceTest {

    @Autowired
    private RedisService service;

    @Test
    public void getKey() {
        System.out.println(service.getKey("qqq"));

    }
}