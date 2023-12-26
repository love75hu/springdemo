package cn.mediinfo.springdemo.controller.springboot.container.beanfactory;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 *@title BeanFactory
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/24 13:31
 */
public class BeanFactory_Demo {
    /**
     * spring framework官方在IOC的两种实现上的权衡：推荐使用依赖注入（DI），尽可能避免依赖查找（DL）
     * 推：通过setter或者构造方法注入的方式配置应用程序（推荐）
     * 拉：如借助BeanFactory进行依赖查找。  // 通过BeanFactory获取 Bean HelloService helloService = (HelloService) factory.getBean("helloService"); （尽量避免）
     */

    /**
     * BeanFactory 是IOC容器的顶层抽象，他仅定义最基础的bean对象的管理。下面的扩展都是为了实现某些额外的特性。（层次、可搜索性、可配置性等0）
     * BeanFactory 包含多个bean 对象的定义 (BeanDefinition)实现，每个bean对象定义均由name进行唯一标识。根据bean对象的定义，spring framework中的工厂会返回所包含对象的独立实例原模型（prototype）。
     * 或者返回单个共享实例，即单例模式（singleton）的替代方案，其中实例是单个工厂作用域中的单实例。返回bean对象的类型取决工厂bean对象工厂的配置 @Scope。所有的工厂均由@Scope指定实例模式。
     * BeanFactory 支持多种类型的Bean配置源。Bean定义存储方式没有任何的限制，可以是XML文件、Java注解、Java代码、RDBMS（关系型数据库）、LDAP（轻型目录访问协议）等。
     * BeanFactory 可实现层次性，与ListableBeanFactory 中的方法相比，BeanFactory 中的所有操作还将检查父级（BeanFactory本身分支持父级结构，结构由HierarchicalBeanFactory工厂完成）如果BeanFactory本身实例没有找到，则在父级工厂搜索。
     * BeanFactory 设计有完整的生命周期控制机制，BeanFactory内部实现尽可能支持标准Bean 生命周期接口（Aware系列接口、用于初始化的InitializingBean接口、用于容器初始化完成后启动的Lifecycle接口等）
     * 总结BeanFactory基础特性：
     * 1、最基础的容器
     * 2、定义了作用域的概念
     * 3、集成环境支持
     * 4、层次性设计
     * 5、完整的生命周期控制机制
     */




}

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)  //指定bean对象实例的的模式为单例，默认是singleton，prototype、singleton
class dog {

}