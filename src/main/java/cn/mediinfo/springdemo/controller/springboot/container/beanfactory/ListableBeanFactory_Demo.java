package cn.mediinfo.springdemo.controller.springboot.container.beanfactory;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/*
 *@title ListableBeanFactory_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/24 14:52
 */
public class ListableBeanFactory_Demo {
    private final ListableBeanFactory listableBeanFactory;

    public ListableBeanFactory_Demo(ListableBeanFactory listableBeanFactory) {
        this.listableBeanFactory = listableBeanFactory;
    }


    public void test() {
        //列举当前容器的bean对象
        this.listableBeanFactory.getBeanDefinitionNames();

        //获取当前容器及父容器的bean
        BeanFactoryUtils.beanNamesForTypeIncludingAncestors(listableBeanFactory, Object.class);
    }
    /**
     * ListableBeanFactory 是BeanFactory接口的扩展，接口可以将容器中所有的bean对象都列举出来。
     * 如果当前 BeanFactory 也是HierarchicalBeanFactory，则返回值会忽略 BeanFactory 的层次结构，只会列举当前容器的bean对象。如果想要获取所有的bean对象，可以通过 BeanFactoryUtils 工具类来获取。
     * ListableBeanFactory 获取的bean是有选择性的，只获取遵循当前工厂的 bean 定义。忽略其他方式注册(configurableBeanFactory.registerSingleton()方法)的任何单实例的bean.
     * 这样设计的目的？就像win系统不希望用于改变一些内部文件，则会默认隐藏一样。
     */
}
