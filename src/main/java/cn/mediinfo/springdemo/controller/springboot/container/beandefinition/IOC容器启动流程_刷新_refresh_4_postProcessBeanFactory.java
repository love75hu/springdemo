package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/*
 *@title IOC容器的刷新_refresh_prepareRefresh2
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/14 9:51
 */
public class IOC容器启动流程_刷新_refresh_4_postProcessBeanFactory {
    /**
     * 阅读本章节，应该先阅读以下两章节内容：
     * 1、Spring Boot启动流程（印象笔记）。{@link org.springframework.boot.SpringApplication#run(String... args) } //10 刷新spring上下文  refreshContext(context);
     * 2、IOC容器的启动流程
     * 本章节力求把 {@link org.springframework.context.support.AbstractApplicationContext#refresh()}具体实现进行详细的说明。也就是《IOC容器的启动流程》进一步进行详细的说明。
     */

    /**
     *  // 4.准备 beanFactory 完成后进行的后置处理
     *     postProcessBeanFactory(beanFactory);
     *     {@link org.springframework.context.support.AbstractApplicationContext#postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)}
     *
     *     postProcessBeanFactory(beanFactory) 默认是空实现。
     *     在默认整合WebMvc 时，启用的是AbstractApplicationContext的一个子实现：{@link org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext}
     *     AnnotationConfigServletWebServerApplicationContext 中重写了 postProcessBeanFactory方法，并做了三件事:
     *     1、回调父类 ServletWebServerApplicationContext#postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)方法。
     *     2、组件扫描
     *     3、注册解析手动传入的配置类。
     *
     *     {@link org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext#postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) }
     *     protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
     *      //7.4.1 回调父类方法
     * 		super.ServletWebServerApplicationContext(beanFactory);
     * 	    //7.4.2 组件扫描
     * 		if (this.basePackages != null && this.basePackages.length > 0) {
     * 			this.scanner.scan(this.basePackages);
     *        }
     *      //7.4.3 配置解析
     * 		if (!this.annotatedClasses.isEmpty()) {
     * 			this.reader.register(ClassUtils.toClassArray(this.annotatedClasses));
     *        }
     *    }
     *
     *    7.4.1 回调父类方法
     *    这一步回调的父类来自继承的 ServletWebServerApplicationContext ，它的 ServletWebServerApplicationContext 方法中注册了一个后置处理器和一组基于web 应用的作用域。代码如下：
     *    {@link org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext#postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)}
     *    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
     *
     *      //注册ServletContext回调函数
     * 		beanFactory.addBeanPostProcessor(new WebApplicationContextServletContextAwareProcessor(this));
     * 		beanFactory.ignoreDependencyInterface(ServletContextAware.class);
     *
     * 	    //注册web相关的作用域
     * 		registerWebApplicationScopes();
     *    }
     *
     *
     */
}
