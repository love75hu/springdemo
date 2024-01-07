package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

import org.springframework.beans.factory.config.BeanPostProcessor;

/*
 *@title BeanDefinitionRegister_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/6 11:25
 */
public class BeanDefinitionRegister_Demo {
    /**
     * BeanDefinition 解析完后，最终转化为 bean 对象，在这之间有一个 BeanDefinition 存储（注册）随后解析 BeanDefinition 生成 bean 对象的过程，而统一管理 BeanDefinition 的核心就是 BeanDefinitionRegister
     * BeanDefinitionRegister 是维护BeanDefinition 注册的注册中心，它内部存放了IOC容器中 bean 的定义信息，同时 BeanDefinitionRegister 也是支撑其他组件和动态注册 Bean 的重要组件
     * Register 有注册表的意思，类似windows的系统注册表。支持对BeanDefinition的增删改查。
     *
     * BeanDefinition的设计意义？
     * 本来可以直接创建 bean，为什么还要多此一举设计一个 BeanDefinition？主要是为了应对复杂的场景及针对bean做一些附加处理（AOP代理、事务增强支持等）
     * 有了bean定义信息，按照既定规则，可以任意解析生成bean对象，也可以根据实际需求对解析和生成对的过程进行扩展。
     */

}
