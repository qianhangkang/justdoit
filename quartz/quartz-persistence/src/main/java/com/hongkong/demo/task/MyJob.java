package com.hongkong.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Random;

/**
 * @author HANGKANG
 * @date 2018/5/29 下午10:00
 */

@Slf4j
public class MyJob implements Job {
    private static Random random = new Random();

    /**
     * Job执行的方法
     * @param context job上下文
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //得到传入job的参数
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String parameter = dataMap.getString("parameter");
        //quartz自动生成的唯一id
        String instanceId = context.getFireInstanceId();
        log.info("任务{}，参数为{}开始执行任务",instanceId,parameter);
        int time = random.nextInt(5000)+1000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("任务{}，参数为{}，时间为{}执行结束",instanceId,parameter,time);
        context.setResult(String.format("%s say 'hello' to listener",instanceId));
    }
}
