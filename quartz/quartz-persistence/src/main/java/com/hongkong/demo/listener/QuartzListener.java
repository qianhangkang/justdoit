package com.hongkong.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

/**
 * @author HANGKANG
 * @date 2018/5/29 下午9:55
 */

@Slf4j
@Component
public class QuartzListener implements JobListener {
    @Override
    public String getName() {
        return "QuartzListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        //监听job执行之前

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        //监听job的trigger被触发，但是job被JobListener禁止执行事件

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        //监听job执行完成后
        log.info("监听器收到job执行结果:{}", context.getResult());//打印job执行结果
    }
}
