package cn.mediinfo.springdemo.controller.springboot.container.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/*
 *@title HierarchicalBeanFactory
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/24 14:03
 */
public class HierarchicalBeanFactory_Demo {
    private final HierarchicalBeanFactory hierarchicalBeanFactory;
    private final ConfigurableBeanFactory configurableBeanFactory;
    public HierarchicalBeanFactory_Demo(HierarchicalBeanFactory hierarchicalBeanFactory, ConfigurableBeanFactory configurableBeanFactory){

        this.hierarchicalBeanFactory = hierarchicalBeanFactory;
        this.configurableBeanFactory = configurableBeanFactory;
    }

    public void test(){
        //获取父级容器指定的bean。
        hierarchicalBeanFactory.getParentBeanFactory().getBean("");

        //通过configurableBeanFactory设置父级容器
        configurableBeanFactory.setParentBeanFactory(null);

        //通过configurableBeanFactory获取父级容器
        var parentBeanFactory=configurableBeanFactory.getParentBeanFactory();
        parentBeanFactory.getBean("null");

    }

    /**
     * HierarchicalBeanFactory 体现了层次的BeanFactory。有了层次性，BeanFactory就有了父子结构。
     * HierarchicalBeanFactory 是 BeanFactory 子接口，实现了 HierarchicalBeanFactory 接口的IOC容器具有层次性，他本身有一个getParentBeanFactory().getBean（）法获取父级容器。
     * getBean()方法会从当前BeanFactory查找是否有指定的bean,如果没有就继续从依次父级查找，直到找到为止或者找不到抛出异常。
     * 思考：如果当前BeanFactory有指定的bean,父级BeanFactory还有同样的bean吗？有，即便存在父子关系，它们本质上也是不同的容器，因此可能找到多个相同的bean。也就是说：
     * @Scope声明的singleton 只是在单独的某一个容器实例是单例，但是有了层次结构后，对于整体的多个容器就不是单例的了。
     */
}
