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
 * @date 2018/5/29 下午10:14
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class QuartzServiceTest {

    @Autowired
    private QuartzService service;

    @Test
    public void addJob() {
        //设置10s后执行
        Date now = new Date();
        log.info("现在时间为{}",now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.SECOND, 10);
        //执行时间
        Date startDate = calendar.getTime();
        log.info("任务执行的时间为{}", startDate);
        String parameter = "hello world~~";

        service.addJob(parameter,startDate);

        //单元测试这里需要暂停一些时间，否则调用service.addJob后单元测试就结束了，以致于定时任务还没来得及执行
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}