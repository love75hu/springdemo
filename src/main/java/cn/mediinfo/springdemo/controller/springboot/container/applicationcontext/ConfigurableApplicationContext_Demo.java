package cn.mediinfo.springdemo.controller.springboot.container.applicationcontext;

/*
 *@title ConfigurableApplicationContext_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/25 20:57
 */
public class ConfigurableApplicationContext_Demo {
    /**
     * ConfigurableApplicationContext 为 ApplicationContext 提供了可写功能，实现该接口的实现类可以由客户端代码修改其内部的某些配置
     * ConfigurableApplicationContext 会被绝大多数 ApplicationContext 的最终实现类实现，除了 ApplicationContext 接口中应用程序上下文客户端方法，还提供了用于配置应用程序上下文的配置方法
     * ConfigurableApplicationContext 接口的实现类可以修改其内部的某些配置，例如，它可以修改其 Environment 对象的属性，或者修改其 ResourceLoader 对象的属性，或者修改其 MessageSource 对象的属性，或者修改其 ApplicationEventPublisher 对象的属性，或者修改其 ResourcePatternResolver 对象的属性，或者修改其 ApplicationContext 对象的属性，或者修改
     * 与生命周期相关的方法都封装在 ConfigurableApplicationContext 接口中，目的是避免暴露给 ApplicationContext的调用者，除了 刷新容器 refresh() 方法、关闭容器 close() 方法，其他方法都不希望开发者直接调用，
     * 直接调用指的是业务开发者，如果是为了定制 ApplicationContext 或者对其扩展，那么应该使用 ConfigurableApplicationContext 接口则应该成为主要目标
     *
     */
}
