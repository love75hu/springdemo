package cn.mediinfo.springdemo.controller.springboot.container.beanfactory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;

/*
 *@title ConfigurableBeanFactory_Demo
 *@description spring命名有很强的规则性，当我们看到命名是以Configurable开头的，说明这个接口有写的行为，而去掉Configurable前缀，则说明只有读的动作。
 *@author thj
 *@version 1.0
 *@create 2023/12/24 15:21
 */
public class ConfigurableBeanFactory_Demo {
    private final ConfigurableBeanFactory configurableBeanFactory;

    public ConfigurableBeanFactory_Demo(ConfigurableBeanFactory configurableBeanFactory) {
        this.configurableBeanFactory = configurableBeanFactory;
    }
    public void test(){
        //必须对以下三个方法了解透彻，才能使用该接口，否则不要进行操作
//        configurableBeanFactory.setBeanClassLoader(getClass().getClassLoader());
//        configurableBeanFactory.setTypeConverter();
//        configurableBeanFactory.addBeanPostProcessor();
    }
    /**
     *  BeanFactory已经提供了带配置的功能，调用它可以定义对BeanFactory进行修改、扩展等操作，但是spring framework并不希望开发者利用  ConfigurableBeanFactory ，而是坚持使用最基础的 BeanFactory。
     *  原因是 程序运行期间不应该对 BeanFactory 再进行频繁的变动，此刻只应该只有读的动作（除非确定要有对 BeanFactory 进行写的操作，且有把握）。
     */


}
