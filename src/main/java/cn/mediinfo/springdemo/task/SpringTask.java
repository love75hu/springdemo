package cn.mediinfo.springdemo.task;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component //用于标记一个类为 Spring 容器中管理的组件
@Log4j2
public class SpringTask {

    /**
     * 任务计划
     */
    @Async //开启任务异步执行
    @Scheduled(cron = "*/50 * * * * *")
    public void printTask() {
        log.info("SpringTask任务计划执行啦！");
    }
}
