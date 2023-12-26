package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

/*
 *@title BeanDefinition_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/26 20:55
 */
public class BeanDefinition_Demo {
    /**
     * 理解元信息，也可以理解为元定义，它就是定义的定义。
     * 举例：张三，男，18岁，-它的元信息就是姓名，性别，年龄。
     * class 有元定义吗？ 有，属性、方法、继承实现、注解等都是class的元定义。
     * bean 有元定义吗？ 有，描述bean的元定义就是 BeanDefinition
     */

    /**
     * BeanDefinition 描述了 bean 的元信息。包含 bean 的类信息、bean 的属性、方法、继承实现、注解等信息。并且可以再IOC容器的初始化阶段被拦截处理。
     * BeanDefinition 接口方法定义主要包含一下几类：
     *    1、beanClassName ： bean 的类名
     *    2、scope ： bean 的作用域 ， 是否默认Bean(primary) ,描述信息（Description）
     *    3、bean 的行为特性：是否延迟加载（lazy）,是否自动注入（ autowiredCandidate）,初始化、销毁方法（initMethodName,destroyMethodName）
     *    4、bean与其他 bean的关系，父 bean 名称（parentName）,依赖的 bean(dependsOn)
     *    5、bean 的配置属性：构造方法参数(ConstructorArgumentValue),属性变量值（PropertyValues）
     */


    /**
     *  BeanDefinition 的父接口 AttributeAccessor
     *  AttributeAccessor 是属性访问器，可以对 Bean 属性进行 get、set、remove、container等
     */


    /**
     *  BeanDefinition 的父接口 BeanMetadataElement
     *  BeanMetadataElement 是 BeanDefinition的另外一个父接口，包含了 metadata 元信息的概念。该接口只有一个getSource()获取bean资源来源的方法。
     *  bean资源来源 指的是 bean文件的/url路径。
     */


    /**
     *  BeanDefinition 的子类 AbstractBeanDefinition
     *  AbstractBeanDefinition 是 BeanDefinition 的基本抽象实现。AbstractBeanDefinition有完整的bean实现定义。
     *  但是既然有完整的实现定义，为什么是一个抽象类？因为它剔除了 GenericBeanDefinition、RootBeanDefinition、ChildBeanDefinition 的共有属性。因为不同的BeanDefinition落地实现内部属性还是有差异的，需要单独实现。
     *  BeanDefinition落地实现：
     *  1、AbstractBeanDefinition ： 完全构成了 BeanDefinition 的所有属性，并且提供了默认的实现。
     *  2、GenericBeanDefinition ： 是 AbstractBeanDefinition 非抽象扩展，GenericBeanDefinition 具有层次性，parentName 属性已经告诉了我们一切。
     *  3、ChildBeanDefinition ： 是 GenericBeanDefinition 的子类，ChildBeanDefinition 继承了 GenericBeanDefinition，并且提供了对 parentName 属性的扩展。
     *  4、RootBeanDefinition ： 类名具有根的概念，意味着 RootBeanDefinition 只能作为单独的  BeanDefinition  或者父 BeanDefinition 出现。
     *      RootBeanDefinition的核心成员：
     *      1、private BeanDefinitionHolder decoratedDefinition;   BeanDefinition的引用持有，存放bean的别名。
     *      2、volatile ResolvableType targetType; bean中的泛型
     *      3、private AnnotatedElement qualifiedElement; bean上面的注解信息。
     *      4、volatile Class<?> resolvedTargetType; bean对应的真实bean
     *      5、volatile Boolean isFactoryBean; 是否是FactoryBean bean
     *      6、volatile ResolvableType factoryMethodReturnType; bean方法的返回类型
     *      7、volatile Method factoryMethodToIntrospect; 工厂bean对应的方法引用
     */


}
