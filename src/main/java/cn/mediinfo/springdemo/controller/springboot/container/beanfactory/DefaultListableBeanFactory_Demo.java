package cn.mediinfo.springdemo.controller.springboot.container.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/*
 *@title DefaultListableBeanFactory_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/24 15:44
 */
public class DefaultListableBeanFactory_Demo {
    private final DefaultListableBeanFactory defaultListableBeanFactory;

    public DefaultListableBeanFactory_Demo(DefaultListableBeanFactory defaultListableBeanFactory) {
        this.defaultListableBeanFactory = defaultListableBeanFactory;
    }

    private void test(){

    }
    /**
     *  是唯一目前底层正在使用的BeanFactory的落地实现，它的设计相当重要。是一个成熟的落地实现。
     *  当DefaultListableBeanFactory获取 bean 之前，应该需要先将bean信息注册进去，由此可以看出 DefaultListableBeanFactory 在 AbstractAutowireCapableBeanFactory
     *  基础上完成了bean定义信息的动作。而这个动作就是通过 BeanDefinitionRegistry 完成的。
     *  完整的BeanFactory 对 Bean 的管理应该是，先定义bean的信息，再完成bean的创建和初始化动作。
     */
}
