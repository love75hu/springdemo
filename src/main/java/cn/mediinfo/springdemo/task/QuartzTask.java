package cn.mediinfo.springdemo.task;

import lombok.extern.log4j.Log4j2;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Log4j2
public class QuartzTask  extends QuartzJobBean {


    /**
     * quartz 需要对每个任务提供额外的配置，以下几个类型的bean会自动关联到 Scheduler 调度器：
     * JobDetail:用于创建一个具体的作业实例
     * Calendar:用于指定、排除特定的时间
     * Trigger:用于定义作业触发时机
     */

    /**
     * quartz job作业接口
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
       log.info("quartz job作业接口执行啦！");
    }
}
