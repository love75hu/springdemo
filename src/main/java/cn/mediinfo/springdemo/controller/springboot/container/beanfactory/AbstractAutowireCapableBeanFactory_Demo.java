package cn.mediinfo.springdemo.controller.springboot.container.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/*
 *@title AbstractAutowireCapableBeanFactory_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/24 15:38
 */
public class AbstractAutowireCapableBeanFactory_Demo {
    /**
     * 他是AutowireCapableBeanFactory 接口的具体实现，意味着它的内部实现的组件自动装配功能逻辑。
     * 它实现了默认bean对象创建按逻辑的 BeanFactory 接口抽象，它除了实现 AbstractBeanFactory 的 createbean方法，还额外实现了 AutowireCapableBeanFactory 接口方法。
     *  意味着bean 真正创建动作（创建、属性、赋值、依赖注入、bean初始化逻辑）都在  AbstractAutowireCapableBeanFactory 中完成实现。
     */


}
