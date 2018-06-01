package com.hongkong.demo.config;

import com.hongkong.demo.listener.QuartzListener;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author HANGKANG
 * @date 2018/5/30 下午6:36
 */

/**
 * quartz的Java配置文件
 */
@Configuration
@Slf4j
public class QuartzConfig {

    @Autowired
    private QuartzListener listener;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setSchedulerName("MsmJobSchedule");
        //quartz参数
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "MsmJobSchedule");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        //设置property
        schedulerFactoryBean.setQuartzProperties(prop);
        //延时启动10s
        schedulerFactoryBean.setStartupDelay(10);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(DataSource dataSource) {
        Scheduler scheduler = schedulerFactoryBean(dataSource).getScheduler();
        try {
            //每次schedule初始化时添加监听器，否则程序重启后之前的任务无法被监听
            log.info("添加监听器至schedule");
            scheduler.getListenerManager().addJobListener(listener);//listener自动注入
        } catch (SchedulerException e) {
            log.debug("监听器添加至schedule失败");
            e.printStackTrace();
        }
        return scheduler;
    }
}
