package cn.mediinfo.springdemo.controller.springboot.container.beanfactory;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/*
 *@title AutowireCapableBeanFactory_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/24 15:12
 */
public class AutowireCapableBeanFactory_Demo {
    /**
     * AutowireCapableBeanFactory 是一个支持自动注入的 BeanFactory，可以为现有的一些没有被spring framework管理的对象提供自动注入的功能。正常的项目开发中不会用到。
     * 利用此接口来连接到无法被spring管理的bean实例，有哪些场景呢？
     * 假设我们要编写一个自定义的servlet，在这个servlet中需要注入spring foundation的bean，但是这个servlet是没有被spring framework管理的，那么我们就需要使用到AutowireCapableBeanFactory来连接到这个servlet。
     * 如何实现？
     * 1、依赖查找：由servlet获取到HttpServletRequest后获得 ServletContext ，借助 WebApplicationContextUtils可以获取。
     * 2、依赖注入：给需要注入的组件标注 @autowired注解，借助 AutowireCapableBeanFactory 辅助实现。
     *
     * 总结： AutowireCapableBeanFactory API一般不需要我们操作，因为正常项目不会使用。
     */
}
