package com.hongkong.demo.service;

import com.hongkong.demo.enumeration.TaskStatusEnum;
import com.hongkong.demo.data.model.PtpMsmTask;
import com.hongkong.demo.data.repository.PtpMsmTaskRepository;
import com.hongkong.demo.enumeration.TaskTypeEnum;
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
     *
     * 先读取数据库中执行状态为"未执行"（0）的任务
     * 接着根据任务类型为对应的任务构建相应的trigger并放入schedule中
     */
    @PostConstruct
    public void init() {
        List<PtpMsmTask> list = repository.findListByStatus(TaskStatusEnum.NOT_PERFORME.getType());
        if (Objects.isNull(list) || list.isEmpty()) {
            log.info("{}时间点之前不存在未执行的任务",new Date());
            return;
        }
        //将数据库中未执行的任务重新加入至schedule中
        String name ;
        int type;
        JobDetail jobDetail;
        Trigger trigger = null;
        boolean right = true;//表示是否需要加入到schedule中
        for (PtpMsmTask job : list) {
            type = job.getType();
            name = job.getJobName();
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("parameter", job.getParameter());
            jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(name,name).setJobData(dataMap).build();
            switch (type) {
                case 0:
                    //立即执行
                    trigger = TriggerBuilder.newTrigger()
                            .withIdentity(name,name)
                            .startNow()
                            .build();
                    break;
                case 1:
                    //延时执行一次
                    trigger = TriggerBuilder.newTrigger()
                            .withIdentity(name,name)
                            .startAt(job.getExecuteDate())
                            .build();
                    break;
                case 2:
                    //利用cron表达式
                    String cron = job.getCron();
                    if (Objects.isNull(cron)) {
                        log.error("利用cron表达式出错，cron表达式为空");
                        right = false;
                        break;
                    }
                    trigger = TriggerBuilder.newTrigger()
                            .withIdentity(name, name)
                            .startNow()
                            .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                            .build();
                    break;
                default:
                    log.error("任务{}的状态出错",job);
                    right = false;
                    break;
            }
            if (right) {
                try {
                    scheduler.scheduleJob(jobDetail,trigger);
                    log.info("job---{}加入至schedule",name);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }
            right = true;
        }
    }


    /**
     * 添加一个指定时间执行的定时任务
     *
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
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity(id,id)
                .setJobData(dataMap)
                .build();
        //trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(id,id)
                .startAt(startDate)
                .build();
        //写入数据库
        PtpMsmTask job = PtpMsmTask.builder()
                .jobName(id)
                .parameter(parameter)
                .executeDate(startDate)
                .status(TaskStatusEnum.NOT_PERFORME.getType())
                .type(TaskTypeEnum.LATER.getType())
                .build();
        long count = repository.insert(job);
        if (count == 0) {
            log.info("任务{}写入数据库失败，任务不执行", job);
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
     *
     * @param parameter 短信参数
     */
    public void addJob(String parameter) {
        //设置一个标识符
        String id = UUID.randomUUID().toString();
        //设置传入job的参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("parameter", parameter);
        //jobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(id,id).setJobData(dataMap).build();
        //trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(id,id).startNow().build();
        //写入数据库
        PtpMsmTask job = PtpMsmTask.builder()
                .jobName(id)
                .parameter(parameter)
                .executeDate(new Date())
                .status(TaskStatusEnum.NOT_PERFORME.getType())
                .type(TaskTypeEnum.NOW.getType())
                .build();
        long count = repository.insert(job);
        if (count == 0) {
            log.info("任务{}写入数据库失败，任务不执行", job);
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
     * 根据cron表达式执行任务
     *
     * @param parameter 短信参数
     * @param cron cron表达式
     */
    public void addJob(String parameter, String cron) {
        //设置一个标识符
        String id = UUID.randomUUID().toString();
        //设置传入job的参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("parameter", parameter);
        //jobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(id,id).setJobData(dataMap).build();
        //trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(id,id).startNow().withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        //写入数据库
        PtpMsmTask job = PtpMsmTask.builder()
                .jobName(id)
                .parameter(parameter)
                .executeDate(new Date())
                .cron(cron)
                .status(TaskStatusEnum.NOT_PERFORME.getType())
                .type(TaskTypeEnum.CRON.getType())
                .build();
        long count = repository.insert(job);
        if (count == 0) {
            log.info("任务{}写入数据库失败，任务不执行", job);
            return;
        }
        try {
            scheduler.scheduleJob(jobDetail,trigger);
            log.info("job---{}加入至schedule",id);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
