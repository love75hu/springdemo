package cn.mediinfo.springdemo.context.datasource;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
// 保证该 AOP 在 @Transactional 之前执行
@Order(-10)
public class RoutingDataSourceAspect {
    @Before(value = "@annotation(source)")
    public void changeDataSource(JoinPoint point, DynamicDataSource source) {
        String currentSource = source.value();
        DataSorceContextHolder.setPrefix(currentSource);
    }

    @After(value = "@annotation(source)")
    public void restoreDataSource(JoinPoint point, DynamicDataSource source) {
        // 方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DataSorceContextHolder.clearPrefix();
    }
}
