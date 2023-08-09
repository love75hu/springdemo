package cn.mediinfo.springdemo.context.datasource;

import java.lang.annotation.*;

/**
 * 定义数据源的注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface DynamicDataSource {
    String value() default "MasterDataSource";
}
