package com.hongkong.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author HANGKANG
 * @date 2018/5/30 下午9:07
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class QuartzServiceTest {

    @Autowired
    private QuartzService service;

    @Test
    public void addJob() {
        String parameter = "helloworld";
        Date now = new Date();
        log.info("现在时间为{}",now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.SECOND, 60);
        //执行时间
        Date startDate = calendar.getTime();
        log.info("任务执行的时间为{}", startDate);
        service.addJob(parameter, startDate);

    }

    @Test
    public void add() {
    }
}