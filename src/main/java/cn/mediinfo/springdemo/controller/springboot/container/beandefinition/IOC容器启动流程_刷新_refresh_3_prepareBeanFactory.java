package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

/*
 *@title IOC容器的刷新_refresh_prepareRefresh2
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/14 9:51
 */
public class IOC容器启动流程_刷新_refresh_3_prepareBeanFactory {
    /**
     * 阅读本章节，应该先阅读以下两章节内容：
     * 1、Spring Boot启动流程（印象笔记）。{@link org.springframework.boot.SpringApplication#run(String... args) } //10 刷新spring上下文  refreshContext(context);
     * 2、IOC容器的启动流程
     * 本章节力求把 {@link org.springframework.context.support.AbstractApplicationContext#refresh()}具体实现进行详细的说明。也就是《IOC容器的启动流程》进一步进行详细的说明。
     */

    /**
     *  //3.beanFactory 预处理配置
     *  prepareBeanFactory(beanFactory);
     *
     * 	protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
     * 		// 设置 beanFactory 加载器，表达式解析器等
     * 		beanFactory.setBeanClassLoader(getClassLoader());
     * 		beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver(beanFactory.getBeanClassLoader()));
     * 		beanFactory.addPropertyEditorRegistrar(new ResourceEditorRegistrar(this, getEnvironment()));
     *
     * 		// 7.3.1配置一个可以回调注入 ApplicationContext 的 BeanPostProcessor
     * 	    //	ignoreDependencyInterface 的作用：ApplicationContext内部既有 BeanPostProcessor 的增强设计，又有 beanFactory 负责自动依赖注入，这意味这着 Aware 系列接口必然会产生选择。
     * 	        spring framework 选择使用 BeanPostProcessor 作为注入逻辑，而放弃这些 Aware 系列接口。换句话说：当使用 beanFactory 的自动依赖注入，而遇到 Aware 系列接口时不予注入。
     * 		beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
     * 		beanFactory.ignoreDependencyInterface(EnvironmentAware.class);
     * 		beanFactory.ignoreDependencyInterface(EmbeddedValueResolverAware.class);
     * 		beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
     * 		beanFactory.ignoreDependencyInterface(ApplicationEventPublisherAware.class);
     * 		beanFactory.ignoreDependencyInterface(MessageSourceAware.class);
     * 		beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);
     * 		beanFactory.ignoreDependencyInterface(ApplicationStartupAware.class);
     *
     * 		// 7.3.2自动注入的支持
     * 	    //registerResolvableDependency 方法的作用是 使 BeanFactory 遇到指定的类型需要注入的时候，直接使用指定的对象进行注入，该方法的参数明显是一对映射，
     * 	    //BeanFactory 的内部就应该有一个 Map 专门负责存储这些被指定的接口和对应的实现类的对象之间的映射关系，以备后续进行依赖注入的时候遇到这些类型就可以直接从 Map 中提取。
     * 		beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
     * 		beanFactory.registerResolvableDependency(ResourceLoader.class, this);
     * 		beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
     * 		beanFactory.registerResolvableDependency(ApplicationContext.class, this);
     *
     * 		// 7.3.3配置一个可以加载所有监听器的组件
     * 		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this));
     *
     * 		// DLoadTimeWeaver的支持
     * 		if (!NativeDetector.inNativeImage() && beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
     * 			beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
     * 			// Set a temporary ClassLoader for type matching.
     * 			beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
     *       }
     *
     * 		//  向 beanFactory 注入 environment 、 系统配置属性 、系统环境信息等
     * 	    //	environment 本身对于 beanFactory 来讲也是一个bean
     * 		if (!beanFactory.containsLocalBean(ENVIRONMENT_BEAN_NAME)) {
     * 			beanFactory.registerSingleton(ENVIRONMENT_BEAN_NAME, getEnvironment());
     *        }
     * 		if (!beanFactory.containsLocalBean(SYSTEM_PROPERTIES_BEAN_NAME)) {
     * 			beanFactory.registerSingleton(SYSTEM_PROPERTIES_BEAN_NAME, getEnvironment().getSystemProperties());
     *        }
     * 		if (!beanFactory.containsLocalBean(SYSTEM_ENVIRONMENT_BEAN_NAME)) {
     * 			beanFactory.registerSingleton(SYSTEM_ENVIRONMENT_BEAN_NAME, getEnvironment().getSystemEnvironment());
     *        }
     * 		if (!beanFactory.containsLocalBean(APPLICATION_STARTUP_BEAN_NAME)) {
     * 			beanFactory.registerSingleton(APPLICATION_STARTUP_BEAN_NAME, getApplicationStartup());
     *        }
     *   }
     *
     *   纵观 prepareBeanFactory 方法的逻辑，看起来很多，实际上并不多，只要分段提取，就比较容器理解。
     *   7.3.1配置一个可以回调注入 ApplicationContext 的 BeanPostProcessor ,使用到了 ApplicationContextAwareProcessor
     *   在设置 beanFactory 加载器，表达式解析器等后，第一个关键动作就是注册 ApplicationContextAwareProcessor ，并让 beanFactory 忽略几种接口的依赖，那么 ApplicationContextAwareProcessor 有什么作用？
     *   ApplicationContextAwareProcessor 本身是一个 BeanPostProcessor ，它的作用是给需要注入 ApplicationContext 或者其他 Bean 对象注入的组件。
     *
     *   下面对该源码进行具体分析：
     *   {@link org.springframework.context.support.ApplicationContextAwareProcessor}
     *
     *   	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
     *      //如果被处理的bean不是指定的 Aware 类型接口，则不予处理
     * 		if (!(bean instanceof EnvironmentAware || bean instanceof EmbeddedValueResolverAware ||
     * 				bean instanceof ResourceLoaderAware || bean instanceof ApplicationEventPublisherAware ||
     * 				bean instanceof MessageSourceAware || bean instanceof ApplicationContextAware ||
     * 				bean instanceof ApplicationStartupAware)) {
     * 			return bean;
     *                }
     *
     *      //执行  Aware 接口的回调注入
     * 		invokeAwareInterfaces(bean);
     * 		return bean;
     * 		}
     *
     * 	   private void invokeAwareInterfaces(Object bean) {
     * 	    //判断实现的接口，进行强转，调用其setter方法
     * 		if (bean instanceof Aware) {
     * 			if (bean instanceof EnvironmentAware environmentAware) {
     * 				environmentAware.setEnvironment(this.applicationContext.getEnvironment());
     *                        }
     * 			if (bean instanceof EmbeddedValueResolverAware embeddedValueResolverAware) {
     * 				embeddedValueResolverAware.setEmbeddedValueResolver(this.embeddedValueResolver);
     *            }
     * 			if (bean instanceof ResourceLoaderAware resourceLoaderAware) {
     * 				resourceLoaderAware.setResourceLoader(this.applicationContext);
     *            }
     * 			if (bean instanceof ApplicationEventPublisherAware applicationEventPublisherAware) {
     * 				applicationEventPublisherAware.setApplicationEventPublisher(this.applicationContext);
     *            }
     * 			if (bean instanceof MessageSourceAware messageSourceAware) {
     * 				messageSourceAware.setMessageSource(this.applicationContext);
     *            }
     * 			if (bean instanceof ApplicationStartupAware applicationStartupAware) {
     * 				applicationStartupAware.setApplicationStartup(this.applicationContext.getApplicationStartup());
     *            }
     * 			if (bean instanceof ApplicationContextAware applicationContextAware) {
     * 				applicationContextAware.setApplicationContext(this.applicationContext);
     *            }
     *        }
     *    }
     *
     *    postProcessBeforeInitialization 方法中会判断一个 bean 对象的所属类型是否实现了指定的 Aware 接口，只要检测到对象所属类有一个 ApplicationContextAwareProcessor 系列接口实现，
     *    ApplicationContextAwareProcessor 就会尝试将其转换为对应的 Aware 接口，并调用 其 setter 方法，完成接口的回调注入。
     *
     *    note:ApplicationContext 本身已经继承了 ResourceLoader 、MessageSource, ApplicationEventPublisher 接口，因此当注入这些父类接口时，本质性上还是注入 ApplicationContext 本身
     *
     *
     *    7.3.2自动注入的支持
     *      beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
     * 		beanFactory.registerResolvableDependency(ResourceLoader.class, this);
     * 		beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
     * 		beanFactory.registerResolvableDependency(ApplicationContext.class, this);
     *
     * 	    registerResolvableDependency 方法的作用是 使 BeanFactory 遇到指定的类型需要注入的时候，直接使用指定的对象进行注入，该方法的参数明显是一对映射，
     *      BeanFactory 的内部就应该有一个 Map 专门负责存储这些被指定的接口和对应的实现类的对象之间的映射关系，以备后续进行依赖注入的时候遇到这些类型就可以直接从 Map 中提取。
     *
     *
     *   7.3.3配置一个可以加载所有监听器的组件
     *   beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this));
     *   处理完依赖类型后，prepareBeanFactory 方法最后会向 beanFactory 注册一个后置处理器 ApplicationListenerDetector ，之前并未接触该后置处理器，但是从类名可以看出，它是一个检测 ApplicationListener 接口的组件。
     *   ApplicationListenerDetector 负责的工作是在bean对象的初始化阶段检测当前bean对象是否为 ApplicationListener ，如果是则会进行一些额外的处理。处理逻辑如下：
     *
     *   {@link org.springframework.context.support.ApplicationListenerDetector#postProcessAfterInitialization(Object bean, String beanName)}
     *   public Object postProcessAfterInitialization(Object bean, String beanName) {
     * 		if (bean instanceof ApplicationListener<?> applicationListener) {
     * 			// potentially not detected as a listener by getBeanNamesForType retrieval
     * 			Boolean flag = this.singletonNames.get(beanName);
     * 			if (Boolean.TRUE.equals(flag)) {
     * 				// singleton bean (top-level or inner): register on the fly
     * 				this.applicationContext.addApplicationListener(applicationListener);
     *                        }
     * 			else if (Boolean.FALSE.equals(flag)) {
     * 				if (logger.isWarnEnabled()  && !this.applicationContext.containsBean(beanName)) {
     * 					// 日志
     *                }
     * 				this.singletonNames.remove(beanName);
     *            }
     *        }
     * 		return bean;
     *    }
     *
     *    由代码可以看出，ApplicationListenerDetector 会检测被后置处理的 bean 对象是否为 ApplicationListener，如果是并且对象是一个单实例，则加入 applicationContext 的监听器集合中
     *    (applicationContext 本身也是时间发布器，内部组合了事件广播器)
     *
     *     {@link org.springframework.context.support.ApplicationListenerDetector#postProcessBeforeDestruction(Object bean, String beanName)}
     *     public void postProcessBeforeDestruction(Object bean, String beanName) {
     * 		if (bean instanceof ApplicationListener<?> applicationListener) {
     * 			try {
     * 		        //将监听事件从广播中移除
     * 				ApplicationEventMulticaster multicaster = this.applicationContext.getApplicationEventMulticaster();
     * 				multicaster.removeApplicationListener(applicationListener);
     * 				multicaster.removeApplicationListenerBean(beanName);
     *                        }
     * 			catch (IllegalStateException ex) {
     * 				// ApplicationEventMulticaster not initialized yet - no need to remove a listener
     *            }
     *        }
     *    }
     *    另外留意一个细节，ApplicationListenerDetector 实现的接口是 DestructionAwareBeanPostProcessor ，说明它对bean对象还有销毁阶段的处理。观察ApplicationListenerDetector 会在销毁阶段将 bean对象从事件广播中移除。
     *    总结：ApplicationListenerDetector 负责将单实例bean加入到 applicationContext 的事件广播器集合中，在销毁阶段将其从件广播器集合中移除。
     */
}
