package com.hongkong.demo.controller;

import com.hongkong.demo.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author HANGKANG
 * @date 2018/5/30 下午9:42
 */

@RestController
@Slf4j
public class QuartzController {
    @Autowired
    private QuartzService service;

    @Autowired
    private Scheduler scheduler;

    /**
     * 添加一个任务，60s后执行
     * @param parameter 传入的参数
     * @return
     */
    @RequestMapping(value = "/add/{parameter}")
    public String add(@PathVariable(value = "parameter") String parameter) {
        //设置60s后执行
        Date now = new Date();
        log.info("现在时间为{}",now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.SECOND, 60);
        //执行时间
        Date startDate = calendar.getTime();
        log.info("任务执行的时间为{}", startDate);
        service.addJob(parameter, startDate);
        return "add successfully";
    }

    /**
     * 得到当前任务执行的列表
     * @return
     */
    @RequestMapping(value = "/job")
    public List job() {
        List<String> res = new ArrayList<>();
        try {
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    //get trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    Date nextFireTime = triggers.get(0).getNextFireTime();
                    res.add("[jobName] : " + jobName + ",[groupName] : "
                            + jobGroup + " - " + nextFireTime);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 添加自定义数量的job
     * @param count job的数量
     */
    @RequestMapping(value = "test/{count}")
    public void test(@PathVariable(value = "count") int count) {
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < count; i++) {
            calendar.setTime(new Date());
            calendar.add(Calendar.SECOND, 10);
            service.addJob(Integer.toString(i),calendar.getTime());
        }
    }
}
