package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;

/*
 *@title BeanPostProcessor_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/6 11:53
 */
public class BeanPostProcessor_Demo {
    /**
     * BeanPostProcessor 后置处理器
     * spring framework 设计的后置处理器主要分类两类：
     * 1、针对bean对象的后置处理器 BeanPostProcessor
     * 2、针对 BeanDefinition 对象的后置处理器 BeanFactoryPostProcessor
     *
     * 这两种都是主要针对 IOC容器中的Bean，在其生命周期中进行一些切入处理和干预。
     * 1、BeanPostProcessor 的切入时机是在bean对象的初始化阶段前后添加自定义处理逻辑。
     * 2、BeanFactoryPostProcessor 的切入时机是在IOC容器的生命周期中，所有 BeanDefinition 都注册到 BeanDefinitionRegister 后切入回调，它的主要工作就是访问、修改已经存在的 BeanDefinition 对象。
     * 同时BeanPostProcessor、BeanFactoryPostProcessor还有一些扩展子接口，他们的切入时机也不尽相同。
     *
     *
     * ❤❤❤❤❤
     * 后置处理器在IOC容器与bean对象生命周期的切入时机如下：
     * 应用启动
     *   |
     * IOC容器初始化（刷新）
     *   |
     * 解析注解配置类,加载BeanDefinition
     *   |
     *   |  -> BeanDefinitionRegistryPostProcessor
     *   |  -> BeanFactoryPostProcessor
     *   |
     * 初始化bean对象
     *   |  -> InstantiationAwareBeanPostProcessor
     *   |  -> MergedBeanDefinitionPostProcessor
     * 实例化对象
     *   |  -> InstantiationAwareBeanPostProcessor
     * 属性赋值&依赖注入
     *   |  -> InstantiationAwareBeanPostProcessor
     * 初始化逻辑回调 -> BeanPostProcessor、
     *   |  -> SmartInitializingSingleton
     * IOC容器初始化完毕
     *❤❤❤❤❤
     *
     * BeanPostProcessor 是针对bean对象的后置处理器，它本身设计是一个包含两个方法的接口：
     * //该方法会在任何bean对象的初始化回调逻辑（例如InitializingBean的afterPropertiesSet或者自定义的init-method）之前执行，
     * default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
     * 		return bean;
     *        }
     *
     * //该方法会在任何bean对象的初始化回调逻辑之后执行
     * default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
     * 		return bean;
     *    }
     *
     *  ❤❤❤❤
     *  BeanPostProcessor切入时机：
     *  实例化bean对象
     *    |
     *  属性赋值&依赖注入
     *    | -> BeanPostProcessor-> postProcessBeforeInitialization
     *  初始化回调逻辑1、@PostConstruct 2、InitializingBean 3、init-method
     *    |-> BeanPostProcessor-> postProcessAfterInitialization
     *  bean对象创建完成
     *  ❤❤❤❤
     *
     *BeanPostProcessor的扩展
     * BeanPostProcessor只是一个根接口，下面海派生了子接口(挑几个重要的)，分别是：
     * 1、InstantiationAwareBeanPostProcessor 会对对象的实例化阶段进行干预。也会拦截bean对象的属性注入和自动装配，并在此控制流程。
     *
     * 2、DestructionAwareBeanPostProcessor 会对bean对象的销毁阶段进行干预。当IOC容器关闭时候，会先销毁容器中所有的单实例bean,销毁过程中，除了回调bean对象本身定义的@PreDestory 注解的方法、Destory-method等方法，
     * 还会回调DestructionAwareBeanPostProcessor 后置处理器进行干预。spring framework有一个 DestructionAwareBeanPostProcessor 的内置实现：监听器的引用释放回调。由于 ApplicationContext 内置了一些 ApplicationListener 监听器，
     * 因此需要将这些引用断开，这样才可以对对象进行销毁和回收。
     *
     * 3、MergedBeanDefinitionPostProcessor 带有合并的概念，这里解释一下 BeanDefinition 合并，如果我们向IOC容器注册bean是一个带由父类的派生类，那么spring framework在收集bean信息的时候，不仅收集当前类，还要搜集父类的信息，
     * 而收集信息的工作就交由 MergedBeanDefinitionPostProcessor  完成。
     * spring framework 中有一个非常重要的 MergedBeanDefinitionPostProcessor 的实现 AutowiredAnnotationBeanPostProcessor ,它负责给bean对象实现基于注解的自动注入（@Autowired\@Resource\@Inject等）,而注入的依据是
     * 它的 postProcessMergedBeanDefinition 方法执行后整理的一组标记，后续工作会根据这组标记为bean对象依次注入属性/bean对象。
     *
     *
     *
     *
     * BeanFactoryPostProcessor 是针对 BeanDefinition 对象的后置处理器，它可以在 Bean 对象初始化之前修改 bean 定义信息。也就是说可以对  BeanDefinition 信息进行修改。
     *
     *
     * BeanDefinitionRegistryPostProcessor 是针对 BeanDefinition 注册的后置处理器，执行时机比 BeanFactoryPostProcessor 早，也就意味着可以在 BeanFactoryPostProcessor 之前注册新的 BeanDefinition 。
     * 从设计上来讲，BeanFactoryPostProcessor 负责修改、扩展 BeanDefinition， BeanDefinitionRegistryPostProcessor 负责注册新的 BeanDefinition 。
     * 注意：因为 BeanDefinitionRegistryPostProcessor 也实现了 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法，所以 执行完 BeanDefinitionRegistryPostProcessor的接口方法后，会立即执行这些类的 postProcessBeanFactory 方法。
     *
     *
     */

     /**
     *             后置处理器对比：
      *            BeanPostProcessor      BeanFactoryPostProcessor                        BeanDefinitionRegistryPostProcessor
      * 处理目标     bean对象                BeanDefinition                                  BeanDefinition、.class文件等
      *
      * 执行时机     bean对象初始化阶段前后     BeanDefinition解析完比并注册进                     配置文件、配置类已解析完毕并注册进BeanFactory，但还没有被BeanFactoryPostProcessor 处理
      *           （已经创建bean对象）        BeanFactory 之后（此时的Bean未实例化）
      *
      * 可操作空间   给bean对象赋值，          在BeanDefinition增删属性，移除BeanDefinition等      向BeanDefinition注册新的BeanDefinition
      *            创建代理等
     */
}





