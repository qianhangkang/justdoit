package com.hongkong.demo.listener;

import com.hongkong.demo.enumeration.TaskStatusEnum;
import com.hongkong.demo.data.model.PtpMsmTask;
import com.hongkong.demo.data.repository.PtpMsmTaskRepository;
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
        /*
         * 得到任务的name
         * 根据name查找数据库
         * 若不存在记录，记录异常
         * 否则，判断传入的异常是否为空，更新对应的任务状态
         */
        String name = context.getJobDetail().getKey().getName();
        PtpMsmTask job = repository.findOneByName(name);
        if (Objects.isNull(job)) {
            log.debug("数据库中无法查询到name为{}的任务",name);
            return;
        }
        //存在记录，判断是否执行时抛出异常
        if (Objects.isNull(jobException)) {
            //执行成功，没有异常
            job.setStatus(TaskStatusEnum.SUCCESS.getType());
            int count = repository.updateByName(job,name);
            if (count == 0) {
                log.debug("更新job{}失败", job);
                return;
            }
            log.info("任务为{}，描述为{}的任务执行完成",name, context.getJobDetail().getDescription());
            return;
        }
        //执行时发生异常
        job.setStatus(TaskStatusEnum.FAIL.getType());
        int count = repository.updateByName(job,name);
        if (count == 0) {
            log.debug("更新job{}失败", job);
            return;
        }
        log.info("任务为{}，描述为{}的任务执行发生异常{}",name, context.getJobDetail().getDescription(),jobException.getMessage());
    }
}
