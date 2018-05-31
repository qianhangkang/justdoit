package com.hongkong.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.util.Random;

/**
 * @author HANGKANG
 * @date 2018/5/30 下午7:09
 */

@Slf4j
public class MyJob implements Job {

    private static Random random = new Random();

    /**
     * Job执行的方法
     * @param context job上下文
     */
    @Override
    public void execute(JobExecutionContext context) {
        //得到传入job的参数
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String parameter = dataMap.getString("parameter");
        //quartz自动生成的唯一id
        String instanceId = context.getFireInstanceId();
        log.info("任务{}，参数为{}开始执行任务", instanceId, parameter);

        try {
            int time = random.nextInt(5000) + 1000;
            Thread.sleep(time);
            log.info("任务{}，参数为{}，时间为{}执行结束", instanceId, parameter, time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.setResult(String.format("%s say 'hello' to listener",instanceId));
    }
}
