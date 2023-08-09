package cn.mediinfo.springdemo.context.annotation.Configuration;

import cn.mediinfo.springdemo.task.QuartzTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 手动配置Scheduler 调度器
 */
@Configuration
public class QuartzTaskConfiguration {
    /**
     * 创建具体的作业实例
     *
     * @return
     */
    @Bean
    public JobDetail quartzTaskForJobDetailBean() {
        return JobBuilder
                .newJob(QuartzTask.class)
                .withIdentity("quartz-Task") //作业名称，不设置则随机生成
                .withDescription("quartz job作业示例")
                .storeDurably() //是否保持存储状态，默认false
                .build();
    }

    /**
     * 定义作业触发时机
     *
     * @return
     */
    @Bean
    public Trigger quartzTaskForTriggerBean() {
        var cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/50 * * * * ? ");
        return TriggerBuilder.newTrigger()
                .forJob(quartzTaskForJobDetailBean())
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
