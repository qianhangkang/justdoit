package com.hongkong.demo.service;

import com.hongkong.demo.task.MyJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author HANGKANG
 * @date 2018/5/29 下午10:07
 */

@Service
@Slf4j
public class QuartzService {

    @Autowired
    private Scheduler scheduler;


    /**
     * 添加一个定时任务
     * @param parameter 传入任务的参数
     * @param startDate 执行的时间
     */
    public void addJob(String parameter,Date startDate) {
        //设置一个标识符
        String name = UUID.randomUUID().toString();
        //设置传入job的参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("parameter", parameter);
        //jobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity(JobKey.jobKey(name))
                .setJobData(dataMap)
                .build();
        //trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TriggerKey.triggerKey(name))
                .startAt(startDate)
                .build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
            log.info("job---{}加入至schedule",name);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
