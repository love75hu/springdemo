package cn.mediinfo.springdemo.context.annotation.Configuration;

import cn.mediinfo.springdemo.task.QuartzTask;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 生产环境中，任务特别多，一个一个配置不方便，所以需要动态配置
 * 动态添加Scheduler 调度器
 */
@RequiredArgsConstructor
@Configuration
public class QuartzTaskDyamicConfiguration {
    private static final String SIMPLE_TASK = "quartz-task";
    private final SchedulerFactoryBean schedulerFactoryBean;

    @PostConstruct
    public void init() throws SchedulerException {
        var scheduler = schedulerFactoryBean.getScheduler();
        var exists = scheduler.checkExists(JobKey.jobKey(SIMPLE_TASK));
        if (!exists) {
            scheduler.scheduleJob(quartzTaskForJobDetailBean(),quartzTaskForTriggerBean());
        }
    }

    /**
     * 创建具体的作业实例
     *
     * @return
     */
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
    public Trigger quartzTaskForTriggerBean() {
        var cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ? ");
        return TriggerBuilder.newTrigger()
                .forJob(quartzTaskForJobDetailBean())
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
