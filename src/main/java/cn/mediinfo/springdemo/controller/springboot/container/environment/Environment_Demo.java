package cn.mediinfo.springdemo.controller.springboot.container.environment;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.PropertyResolver;

/*
 *@title PropertyResolver
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/26 20:23
 */
public class Environment_Demo {
    /**
     *  Environment 与 IOC容器的关系
     *  1、Environment 中包含了 Profile 和 property ，这些信息都会影响IOC容器的Bean的创建和注册。
     *  2、Environment 是在 ApplicationContext 创建的后再创建的，所以 Environment 伴随着 ApplicationContext存在而存在。
     *  3、ApplicationContext 同时包含了 Environment 和 普通的 Bean组件对象，从 BeanFactory 视角来看，Environment 也是一个Bean。只不过地位比较特殊。
     */

    /**
     *  Environment 父类 PropertyResolver
     *  PropertyResolver 是一个属性解析器，它可以处理XML配置文件、注解配置类、普通Bean中用到的${}属性配置占位符。
     *  Environment 继承自 PropertyResolver，并且 Environment 中存放着profile 和 property 属性配置。就意味着 PropertyResolver 可以解析 ${} 占位符，并提取出 Environment 中的 property 完成赋值操作。
     *  PropertyResolver 接口主要有两个工作：配置属性获取、占位符的解析。所以 Environment 也可以获取属性配置元信息，同时也支持解析占位符的信息。
     */

    /**
     * Environment 子类 ConfigurableEnvironment
     *      void setActiveProfiles(String... profiles); 激活指定的Profile
     *  	void addActiveProfile(String profile); 追加激活指定的Profile
     *  	void setDefaultProfiles(String... profiles); 设置默认情况下激活的Profile
     *  	MutablePropertySources getPropertySources(); 获取PropertySources的组合体，就是所有的PropertySources对象集合
     * spring命名规范，Mutable开头通常是一个List组合封装。
     * 可以简单理解为一个 PropertySource 对象对应了一个配置源，这个配置源可能来自 property 文件，也可能来自系统变量，项目信息。也可能是一个自定义的加载方式（如数据库），
     * 不管来自哪里，都会封装为键值对形式存储在 PropertySource 对象中。统一由 Environment 管理。
     *
     */

    @Configuration
    @Profile("dev") //指定的Profile下才生效
    class config{

    }
}
