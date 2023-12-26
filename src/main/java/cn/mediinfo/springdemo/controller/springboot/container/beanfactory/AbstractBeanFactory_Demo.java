package cn.mediinfo.springdemo.controller.springboot.container.beanfactory;

import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

/*
 *@title AbstractBeanFactory_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/24 15:31
 */
public class AbstractBeanFactory_Demo {
    /**
     *  AbstractBeanFactory 是 BeanFactory 的抽象实现类，具有最基础的功能且可以从（XML\LDAP\DDBMS等）获取bean的定义信息,即BeanDefinition
     *  AbstractBeanFactory 可以提供对bean单实例的缓存
     *  spring framework 中大量使用模板方法模式来设计核心组件，设计思路是：父类提供规范逻辑，子类提供具体步骤实现。
     *  AbstractBeanFactory 是对 geteanDefinition 和 createBean的规范定义。
     */


}
