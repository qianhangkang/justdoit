package com.hongkong.demo.service;

import com.hongkong.demo.enumeration.TaskStatusEnum;
import com.hongkong.demo.model.PtpMsmTask;
import com.hongkong.demo.repository.PtpMsmTaskRepository;
import com.hongkong.demo.task.MyJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author HANGKANG
 * @date 2018/5/30 下午6:38
 */

@Service
@Slf4j
public class QuartzService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private PtpMsmTaskRepository repository;

    /**
     * schedule被注入后执行的初始化函数
     */
    @PostConstruct
    public void init() {
        List<PtpMsmTask> list = repository.findListByStatus(TaskStatusEnum.NOT_PERFORME.getType());
        if (Objects.isNull(list) || list.isEmpty()) {
            log.info("{}时间点之前不存在未执行的任务",new Date());
            return;
        }
        //将数据库中未执行的任务重新加入至schedule中

        String name = null;
        String group = null;
        Date startDate = null;
        JobDetail jobDetail = null;
        Trigger trigger = null;
        for (PtpMsmTask job : list) {
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("parameter", job.getParameter());
            name = job.getJobName();
            group = job.getJobGroup();
            startDate = job.getExecuteDate();
            jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job---"+name,group).setJobData(dataMap).build();
            trigger = TriggerBuilder.newTrigger().withIdentity("trigger---"+name,group).startAt(startDate).build();
            try {
                scheduler.scheduleJob(jobDetail,trigger);
                log.info("job---{}加入至schedule",name);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 添加一个定时任务
     * @param parameter 传入任务的参数
     * @param startDate 执行的时间
     */
    public void addJob(String parameter,Date startDate) {
        //设置一个标识符
        String id = UUID.randomUUID().toString();
        //设置传入job的参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("parameter", parameter);
        //jobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job---"+id,id).withDescription("startAt").setJobData(dataMap).build();
        //trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger---"+id,id).startAt(startDate).build();
        //写入数据库
        PtpMsmTask job = PtpMsmTask.builder()
                .jobName(id)
                .jobGroup(id)
                .parameter(parameter)
                .executeDate(startDate)
                .status(TaskStatusEnum.NOT_PERFORME.getType())
                .build();
        long count = repository.insert(job);
        if (count == 0) {
            log.info("任务{}写入数据库失败", job);
            return;
        }
        try {
            scheduler.scheduleJob(jobDetail,trigger);
            log.info("job---{}加入至schedule",id);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加立即执行的任务
     * @param parameter
     */
    public void add(String parameter) {
        //设置一个标识符
        String id = UUID.randomUUID().toString();
        //设置传入job的参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("parameter", parameter);
        //jobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job---"+id,id).setJobData(dataMap).build();
        //trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger---"+id,id).startNow().build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
            log.info("job---{}加入至schedule",id);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
