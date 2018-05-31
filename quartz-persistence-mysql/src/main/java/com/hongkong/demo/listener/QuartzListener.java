package com.hongkong.demo.listener;

import com.hongkong.demo.enumeration.TaskStatusEnum;
import com.hongkong.demo.model.PtpMsmTask;
import com.hongkong.demo.repository.PtpMsmTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author HANGKANG
 * @date 2018/5/30 下午6:38
 */

@Slf4j
@Component
public class QuartzListener implements JobListener {
    @Autowired
    private PtpMsmTaskRepository repository;

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
        String description = context.getJobDetail().getDescription();
        log.info("des:{}",description);
        if ("startAt".equals(description)) {
            //延时执行的任务，而不是立即执行的任务
            String name = context.getJobDetail().getKey().getName();
            PtpMsmTask job = repository.findOneByName(name);
            if (Objects.isNull(job)) {
                log.debug("任务name为{}无法在数据库中找到", name);
                return;
            }
            job.setStatus(TaskStatusEnum.SUCCESS.getType());
            int count = repository.update(job);
            if (count == 0) {
                log.debug("更新job{}失败", job);
                return;
            }
        }
    }
}
