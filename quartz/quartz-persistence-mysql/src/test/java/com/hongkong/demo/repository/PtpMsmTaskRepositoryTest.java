package com.hongkong.demo.repository;

import com.hongkong.demo.data.model.PtpMsmTask;
import com.hongkong.demo.data.repository.PtpMsmTaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author HANGKANG
 * @date 2018/5/31 上午11:23
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class PtpMsmTaskRepositoryTest {

    @Autowired
    private PtpMsmTaskRepository repository;
    @Test
    public void findOne() {
    }

    @Test
    public void findListByStatus() {
    }

//    @Test
//    public void update() {
//        String name = "b9866cb9-7983-4fc9-b377-c573aa6dfb77";
//        PtpMsmTask job = repository.findOneByName(name);
//        Assert.assertNotNull(job);
//        job.setType(99);
//        System.out.println(repository.update(job));
//
//    }

    @Test
    public void findOneByName() {
        String name = "3cecc139-ca33-4cb7-9f42-11de359c01f8";
        PtpMsmTask job = repository.findOneByName(name);
        System.out.println(job);
    }
}