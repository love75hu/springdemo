package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.AbstractApplicationContext;

/*
 *@title IOC容器启动流程
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/6 21:06
 */
public class IOC容器启动流程 {
    /**
     *  ApplicationContext 的启动，其核心就是内部的一个刷新容器的动作。就是 AbstractApplicationContext的 refresh方法：
     *
     *  	public void refresh() throws BeansException, IllegalStateException {
     * 		synchronized (this.startupShutdownMonitor) {
     * 			StartupStep contextRefresh = this.applicationStartup.start("spring.context.refresh");
     *
     * 			// 1.初始化前的预处理
     * 			prepareRefresh();
     *
     * 			// 2.获取beanFactory ，加载所有bean的定义信息（未实例化）
     * 			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
     *
     * 		   //	3.beanFactory 预处理配置
     * 			prepareBeanFactory(beanFactory);
     *
     * 			try {
     * 				// 4.准备 beanFactory 完成后进行的后置处理
     * 				postProcessBeanFactory(beanFactory);
     *
     * 				StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
     * 				// 5.准备 beanFactory 创建后的后置处理
     * 				invokeBeanFactoryPostProcessors(beanFactory);
     *
     * 				// 6.注册 bean后置处理器
     * 				registerBeanPostProcessors(beanFactory);
     * 				beanPostProcess.end();
     *
     * 				// 7.初始化 MessageSource.初始化国际化组件
     * 				initMessageSource();
     *
     * 				// 8.初始化 事件广播器
     * 				initApplicationEventMulticaster();
     *
     * 				// 9.子类的多态
     * 				onRefresh();
     *
     * 				// 10.注册监听器
     * 				registerListeners();
     *              //至此，beanFactory创建完成。
     * 				// 11.初始化所有剩下的实例
     * 				finishBeanFactoryInitialization(beanFactory);
     *
     * 				// 12.完成容器的创建工作
     * 				finishRefresh();
     *                        }
     *
     * 			catch (BeansException ex) {
     * 				if (logger.isWarnEnabled()) {
     * 					logger.warn("Exception encountered during context initialization - " +
     * 							"cancelling refresh attempt: " + ex);
     *                }
     *
     * 				// Destroy already created singletons to avoid dangling resources.
     * 				destroyBeans();
     *
     * 				// Reset 'active' flag.
     * 				cancelRefresh(ex);
     *
     * 				// Propagate exception to caller.
     * 				throw ex;
     *            }
     *
     * 			finally {
     * 				// Reset common introspection caches in Spring's core, since we
     * 				// might not ever need metadata for singleton beans anymore...
     * 			    // 13.清除缓存
     * 				resetCommonCaches();
     * 				contextRefresh.end();
     *            }
     *    }
     *
     *    整个 refresh 分为13个步骤
     *    1.prepareRefresh() 初始化前的预处理，这一步将大多数的动作都是前置性准备，包含切换IOC容器状态、初始化配置属性、属性校验、早期事件容器准备等动作。
     *    2.obtainFreshBeanFactory() 获取beanFactory ，加载所有bean的定义信息（未实例化）。
     *      2-1：该步骤在不同的ApplicationContext实现中，其行为不同，基于注解配置类的 ApplicationContext，其行为是：在基础实现类 GenericApplicationContext的 RefreshBeanFactory() 方法中，
     *      会调用 GenericApplicationContext 的 createBeanFactory() 方法,它控制 GenericApplicationContext 不能反复刷新；而基于XML配置文件的 ApplicationContext中，在该步会解析XML文件，封装 BeanDefinition 注册到  BeanDefinitionRegistry 中。
     *      2-2：由此得出一个结论：基于XML配置文件的 ApplicationContext 可以反复刷新，基于注解配置类的 ApplicationContext 不能反复刷新（只能刷新一次）。
     *    3.prepareBeanFactory(beanFactory) beanFactory 预处理配置动作；
     *      3-1：这个方法内部处理的内容看似多，但是总体非常有条理，主要包含三件事：
     *           3-1-1：设置默认组件（类加载器、表达式解析器等），并注册到 Environment 抽象（也是 Environment 也是IOC容器的一个Bean）
     *           3-1-2: 编程式注册 ApplicationContextAwareProcessor ,它负责支持6个 Aware 回调注入接口（包含 EnvironmentAware、ApplicationContextAware在内的6个Aware写接口）。
     *           3-1-3：绑定 beanFactory 与 ApplicationContext的依赖注入映射，当其他Bean需要注入 beanFactory 是谷歌，IOC容器会自动将当前正在处理的 beanFactory 注入。，当其他Bean需要注入 ApplicationContext 是谷歌，IOC容器会自动将当前正在处理的 ApplicationContext 注入。
     *      3-2：总结起来，就是都在为   beanFactory  做准备。
     *    4.postProcessBeanFactory(beanFactory) 准备 beanFactory 完成后进行的后置处理
     *      4-1：这本身是一个模板方法，在 AbstractApplicationContext 中没有具体的实现，在WEB环境的 ApplicationContext 实现 GenericWebApplicationContext 中有扩展行为：
     *           4-1-1：编程式注册 ServletContextAwareProcessor 回调接口，支持 ServletContextAware 回调注入。
     *           4-1-2：注册新的Bean的作用域（Request、Session、Application）,并关联绑定到ServletRequest\ServletResponse 等多个类型的依赖注入映射。
     *           4-1-3：注册 ServletContext\ServletConfig等对象到容器中。
     *      4-2：在 spring boot的支持嵌入式容器的 ServletWebServer ApplicationContext 实现基类中它覆盖了原有的实现，主要变化的是没有注册 application 作用域（因为此时嵌入式web容器还没有初始化，没有servletContext可以获取）。
     *    5.invokeBeanFactoryPostProcessors(beanFactory) 执行 BeanFactoryPostProcessors
     *      5-1:该步骤会回调执行所有的 BeanDefinitionRegistryPostProcessor 与 BeanFactoryPostProcessor
     *          5-1-1：BeanDefinitionRegistryPostProcessor 的主要职责是对  BeanDefinitionRegistry 中存放的 BeanDefinition 进行处理（可以注册新的 BeanDefinition ，也可以移除现有的BeanDefinition），典型的场景就是：注解配置类的解析与组件导入
     *          5-1-2：BeanFactoryPostProcessor 的主要职责是对  BeanDefinitionRegistry  中现有的 BeanDefinition 进行修改操作（注意：仅限于修改操作，原则上不允许移除，注册现在的 BeanDefinition），执行时机比 BeanDefinitionRegistryPostProcessor 较晚。
     *     5-2：回调这两种后置处理器设计的核心工作是组件扫描、注解配置类解析。
     *   6.registerBeanPostProcessors(beanFactory); 初始化 BeanPostProcessor
     *    6-1：该步骤会回调执行所有的 BeanPostProcessor
     *    6-2：BeanPostProcessor 主要负责 Bean 对象的后置处理，包括：Bean 对象的属性注入、Bean 对象的初始化、Bean 对象的销毁等
     *    6-3：当前步骤初始化 BeanPostProcessor 时，容器还没有初始化任何业务相关的bean对象，所有后续初始化的所有bean对象都经过 BeanPostProcessor 干预。
     *   7.initMessageSource() -初始化国际化组件
     *    7-1：初始化国际化组件，主要是加载国际化资源文件，并将其注册到 MessageSource 中。默认实现 DelegateMessageSource 在不做任何附加配置的情况下不会处理任何国际化工作，只有手动向IOC容器中注入 MessageSource 时，才会触发国际化资源文件的加载。
     *   8.initApplicationEventMulticaster() - 初始化事件广播器
     *     8-1：该步骤会默认初始化一个 ApplicationEventMulticaster 的简单实现 SimpleApplicationEventMulticaster 并注入到IOC容器中，
     *     8-2：ApplicationContext 本身具备的事件广播能力是依赖 ApplicationEventMulticaster 实现的（功能组合的体现）
     *  9.onRefresh(); - 子类扩展的刷新动作
     *    9-1：该方法也是一个模板方法，默认 spring framework 范围的 ApplicationContext 都没有扩展他。
     *    9-2：spring boot中支持的嵌入式web容器 ApplicationContext 在此处有扩展，它用于初始化嵌入式web容器。
     *  10.registerListeners(); - 注册监听器
     *    10-1：该步骤将 BeanDefinitionRegistry 中注册的监听器(ApplicationContextListener)的 beanName 取出，绑定到事件广播器 ApplicationEventMulticaster 中
     *    10-2：只绑定beanName 而不直接去除监听器对象的原因是，考虑到 ApplicationContextListener 作为IOC 容器的 bean ,应该放到在一起，统一创建和初始化（就是下面的 finishBeanFactoryInitialization(beanFactory) 动作），目的是希望 BeanPostProcessor 去干预它。
     *  11.finishBeanFactoryInitialization(beanFactory) - 初始化剩余的单实例bean对象
     *    11-1：该步骤主要完成两件事：
     *          11-1-1：初始化用于类型转换和表达式解析器（ConversionService 与 EmbeddedValueResolver ）
     *          11-1-2：该步骤执行完成后，BeanFactory 初始化工作结束。
     *          11-1-3：该步骤包含一个对bean对象的主要生命周期规程，内容很复杂
     *  12.finishRefresh(); - 完成刷新后的动作
     *     12-1：该步骤主要完成以下小动作：
     *          12-1-1：清除资源缓存
     *          12-1-2：初始化生命周期处理器
     *          12-1-3：传播生命周期动作，回调所有 Lifecycle 类型 Bean 的 start() 方法
     *          12-1-4：广播 ContextRefreshedEvent 事件
     *  13.resetCommonCaches(); - 清除缓存
     *     13-1：最后步骤会清除一切无用缓存，因为IOC容器刷新工作已经完成，缓存也就没用了。
     *
     *
     */


}
