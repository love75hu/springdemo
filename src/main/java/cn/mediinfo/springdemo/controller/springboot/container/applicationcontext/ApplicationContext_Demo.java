package cn.mediinfo.springdemo.controller.springboot.container.applicationcontext;

import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/*
 *@title Application_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2023/12/25 20:47
 */
public class ApplicationContext_Demo {
    /**
     *  选择 BeanFactory 还是 ApplicationContext ？
     *  应该使用 ApplicationContext ，除非有充分的理由接解释不使用他的原因，一般情况下，我们推荐 GenericApplicationContext 及其子类 AnnotationConfigWebApplicationContext 作为自定义引导的常见实现。
     *  这些实现类是用于常见的目的 spring framework 核心容器主要入口点：加载配置文件，触发类路径扫描，编程式注册bean定义，和带注解的类。
     *
     *  BeanFactory 和 ApplicationContext 功能性对比：
     *  Bean instantiation/wiring ---bean实例化和属性注入  是  是
     *  生命周期管理 否 是
     *  Bean后置处理器支持 否 是
     *  BeanFactory后置处理器支持 否 是
     *  消息转化服务（国际化）否 是
     *  事件发布机制（事件驱动）否 是
     *
     *  由此可见，ApplicationContext 比 BeanFactory 功能更加强大，这也是解释了为什么日常开发基本上使用 ApplicationContext 很少接触 BeanFactory。
     */


    /**
     *  ApplicationContext 是基于 BeanFactory 的扩展，是日常操作spring framework的核心接口，在应用程序运行时，它是只读的。
     *  ApplicationContext 支持重新加载，在 AbstractRefreshableApplicationContext 中体现
     *  ApplicationContext 核心功能：支持访问bean、加载资源文件、事件发布、国际化支持、层级关系支持（与容器上下文要区分开，上下文包含容器，但不仅仅是容器，容器只负责管理bean,上下文还包含动态增强、资源加载、事件监听器等功能）
     *  ApplicationContext 除了标准的 BeanFactory 生命周期功能，ApplicationContext的实现类还调用实现了 ApplicationContextAware 、 ResourceLoaderAware 、 ApplicationEventPublisherAware 、 MessageSourceAware 接口的 bean
     */

    /**--------------------------ApplicationContext父接口 --------------------------------------------------------------**/
    /**
     *  ApplicationContext 父接口 EnvironmentCapable
     *  Capable 意为“有能力”，如果有该命名结尾的接口，通常意味着通过该接口可以获得某个特定的方法，例如 EnvironmentCapable 接口中定义了 getEnvironment() 方法，可以通过该方法获取 Environment 对象
     *  EnvironmentCapable 接口与 Environment 强关联，Environment 本身也是spring framework的核心接口，它是用来获取和设置环境变量的，而 EnvironmentCapable 接口是用来获取 Environment 对象，从而可以获取到 Environment 对象的各种方法
     *  EnvironmentCapable 与 ApplicationContext 的关系是，只要获取到 ApplicationContext 的实例，就可以借助 EnvironmentCapable 接口获取到 Environment。如果是 ConfigurableApplicationContext，则可以获取到  ConfigurableEnvironment
     */

    /**
     *  ApplicationContext 父接口 MessageSource
     *  MessageSource 是国际化支持的组件，ApplicationContext 使用委托机制实现国际化的支持。当我们调用 ApplicationContext 的国际化相关方法时候，内部直接调用转发到 MessageSource 的落地实现类。
     */

    /**
     *  ApplicationContext 父接口 ApplicationEventPublisher
     *  ApplicationEventPublisher 是一个事件发布器，spring framework 支持强大的的事件监听机制，而ApplicationContext 作为容器的最顶层，自然要实现观察者模式中广播器的角色。
     *
     *  spring framework 中，特显观察者模式的特性就是事件驱动和监听器。监听器充当订阅者，监听特定的事件，事件源充当被观察的主题，用来发布事件。IOC 容器本身也是事件广播器，也可以理解成为观察者。
     *  spring framework 事件驱动核心可以划分为4部分：事件源、事件、广播器、监听器
     *  事件源：发布事件的对象
     *  事件：事件源发布的信息/做出的动作
     *  广播器：事件真正广播给监听器的对象
     *  监听器：监听事件的对象
     *
     *  在这个模型中，ApplicationContext 的角色是广播器，因为它实现了 ApplicationEventPublisher 接口，具有事件广播器发布事件的能力：ApplicationContext 内部组合了  ApplicationEventPublisher，它组合了所有的监听器，具有事件广播器广播事件的能力
     *
     */


    /**
     *  ApplicationContext 父接口 ResourcePatternResolver
     *  ResourcePatternResolver 是 ApplicationContext 继承接口中最复杂的一个。是资源模式解析器。
     *  ResourcePatternResolver 的作用是根据特定的路径解析资源文件，是 ResourceLoader 的扩展。它可以支持带 * 的路径解析。可以根据特殊路径返回资源文件。
     *  ApplicationContext 可以将所需要的资源文件加载到应用程序中，从而完成功能配置、属性赋值等操作。
     *
     *  ANT路径形式匹配写法
     *  /WEB-INF/**.XML
     *  /WEB-INF/beans-*.XML
     */

    /**--------------------------ApplicationContext实现类---------------------------------------------------------------**/
    /**
     *  ApplicationContext 实现类 AbstractApplicationContext
     *  AbstractApplicationContext 是 ApplicationContext 的顶层抽象，这个类极为重要，它定义和实现了绝大部分应用上下文的特性和功能，一定要重视。
     *  AbstractApplicationContext 内部使用了大量的模板方法来规范整体功能逻辑，具体实现由子类负责.
     *  ApplicationContext 相较于 BeanFactory 对于特殊bean处理的扩展，与普通 BeanFactory 相比，ApplicationContext 能够检测在其内部 bean 对工厂中定义的特殊 bean.
     *  这类自动注册在上下文定义为 bean 包括 BeanFactoryPostProcessor 、 BeanPostProcessor 、 ApplicationListener 等，其实这些bean也是一些组件对象，只不过在 ApplicationContext 中发挥更重要的作用。所以 ApplicationContext 将他们区分出来，并给他们发挥作用的机会。
     *  ApplicationContext 实现了国际化接口 MessageSource，事件广播接口 ApplicationEventPublisher，作为容器，它会把自己看成一个 bean,以支持不同类型的组件注入需要。
     *  AbstractApplicationContext 提供了默认加载资源的策略，默认情况下，AbstractApplicationContext 加载文件是直接继承 DefaultResourceLoader 的策略，从类路径加载。但web 项目加载策略可能会发生改变，可以从 ServletContext中加载。
     */

     /**
      *  ApplicationContext 实现类 GenericApplicationContext
      *  spring framework 的 IOC 容器最终落地有基于XML配置和注解器两种，已经抛弃了XML方式，主要为注解器方式。
      *  GenericApplicationContext 是一个 ApplicationContext 通用实现类，该实现拥有一个内部 DefaultListableBeanFactory 实例，并且不采用特定的Bean定义格式。另外还实现了   BeanDefinitionRegistry 接口，以便润许任何 bean 读取器 应用于该容器中。
      *  GenericApplicationContext 实现了 BeanDefinitionRegistry 接口，可以自定义注册一些 bean . 然而在 GenericApplicationContext 中，它实现的定义注册方法 registerBeanDefinition 在底层该事故调用的 DefaultListableBeanFactory执行 registerBeanDefinition方法。
      *  GenericApplicationContext 中组合了一个 DefaultListableBeanFactory ，而这个 BeanFactory  在 GenericApplicationContext的构造方法中就已经初始完毕，而初始化完毕的 BeanFactory 不允许在运行期间被重复刷新。
      *  web环境相关配置：对于XML bean 定义的典型情况，只需要使用 ClassPathXmlApplicationContext 或 FileSystemXmlApplicationContext , 而在 web环境中，GenericApplicationContext 替代方案是 XmlWebApplicationContext
      */

    /**
     *  ApplicationContext 实现类 AnnotationConfigWebApplicationContext
     *  AnnotationConfigWebApplicationContext 是 spring framework  中最常用的注解IOC驱动容器，它本身继承自 GenericApplicationContext ，那么它自然只能刷新一次。
     *  AnnotationConfigWebApplicationContext 是一个独立的注解驱动的 ApplicationContext ，它接受组件类作为输入，它更重视 @Configuration 注解的类。还可以使用普通容器 @Component 注解的类和符合 JSR-330规范的类。
     *  AnnotationConfigWebApplicationContext 允许使用 register(Class ...)方法直接传入指定的配置类，以及使用scan（String ...）方法进行类路径的包扫描。如果有多个 @Configuration 类，则在后面的 @Bean 将覆盖先前类中定义的方法。这个可以通过额外的 @Configuration  故意覆盖某些 BeanDefinition
     */

    /**
     *  ApplicationContext 实现类 AbstractRefreshableApplicationContext
     *  AbstractRefreshableApplicationContext 是 基于XML配置驱动的IOC容器，优点是可以重复刷新，目前 spring boot已经不再使用，仅做了解。
     */

}
