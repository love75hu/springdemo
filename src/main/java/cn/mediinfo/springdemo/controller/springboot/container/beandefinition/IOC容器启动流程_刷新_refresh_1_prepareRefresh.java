package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

/*
 *@title IOC容器的刷新
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/13 21:01
 */
public class IOC容器启动流程_刷新_refresh_1_prepareRefresh {
    /**
     * 阅读本章节，应该先阅读以下两章节内容：
     * 1、Spring Boot启动流程（印象笔记）。{@link org.springframework.boot.SpringApplication#run(String... args) } //10 刷新spring上下文  refreshContext(context);
     * 2、IOC容器的启动流程
     * 本章节力求把 {@link org.springframework.context.support.AbstractApplicationContext#refresh()}具体实现进行详细的说明。也就是《IOC容器的启动流程》进一步进行详细的说明。
     */

     /**
      * 整个refresh()方法第一步是在容器刷新核心动作前做一些预处理，粗略的来看，{@link org.springframework.context.support.AbstractApplicationContext#prepareRefresh()} 方法大多数的动作都是前置性准备，
      * 其中 initPropertySources(); 、earlyApplicationEvents 两个步骤又是最为关键的。
      *
      * protected void prepareRefresh() {
      * 		// 记录刷新动作执行的时间
      * 		this.startupDate = System.currentTimeMillis();
      * 	   // 标记当前IOC容器已激活
      * 		this.closed.set(false);
      * 		this.active.set(true);
      *

      * 		if (logger.isDebugEnabled()) {
      * 			if (logger.isTraceEnabled()) {
      * 				logger.trace("Refreshing " + this);
      *                        }
      * 			else {
      * 				logger.debug("Refreshing " + getDisplayName());
      *            }* 		}
      *
      * 		// 7.1.1 初始化属性配置
      * 		initPropertySources();
      *
      * 		// 属性校验（通常无实际操作）
      * 		// see ConfigurablePropertyResolver#setRequiredProperties
      * 		getEnvironment().validateRequiredProperties();
      *
      * 		// 监听器的初始化（兼顾可以反复刷新的IOC容器）
      * 		if (this.earlyApplicationListeners == null) {
      * 			this.earlyApplicationListeners = new LinkedHashSet<>(this.applicationListeners);
      *                }
      * 		else {
      * 			// Reset local application listeners to pre-refresh state.
      * 			this.applicationListeners.clear();
      * 			this.applicationListeners.addAll(this.earlyApplicationListeners);
      *        }
      *
      * 		// 初始化早期事件的集合
      * 		// to be published once the multicaster is available...
      * 		this.earlyApplicationEvents = new LinkedHashSet<>();* 	}
      */



     /**
      *    initPropertySources(); 有多个实现，默认的抽象实现是一个空方法（模板方法），并无任何实现，而是留给子类进行重写的设计。
      *    初始化 PropertySources 的动作是想预先初始化一些属性配置（properties） 到ioc容器中（实际上是存放到 Environment 中），
      *
      *    子类：{@link org.springframework.web.context.support.GenericWebApplicationContext#initPropertySources()}
      *     @Override
      *        protected void initPropertySources() {
      * 		ConfigurableEnvironment env = getEnvironment();
      * 		if (env instanceof ConfigurableWebEnvironment configurableWebEnv) {
      * 			configurableWebEnv.initPropertySources(this.servletContext, null);
      *        }
      *    }
      *
      *    @Override
      *        public void initPropertySources(@Nullable ServletContext servletContext, @Nullable ServletConfig servletConfig) {
      * 		WebApplicationContextUtils.initServletPropertySources(getPropertySources(), servletContext, servletConfig);
      *    }
      *
      *    可以发现，initPropertySources 是希望获得一个 ConfigurableWebEnvironment ，并配置当前的 servletContext 。
      *    WebApplicationContextUtils 的静态方法  initServletPropertySources 是将 servletContext 以及 servletConfig 当作一个属性注入 ServletConfig ，
      *    后续加载 Environment 不仅会从 application.properties 中加载，也会从 servletContext 以及 servletConfig 中加载。
      *
      *    // initServletPropertySources 方法将 ServletContext、ServletConfig 封装为一个 PropertySource ，每次从 ServletConfig 获取配置属性时候，实际上是从  MutablePropertySources 中取值。
      *    // MutablePropertySources 通过遍历自身的集合尝试从中获取配置属性，直到找到配置属性值或者返回值null(或预先指定默认值). 当 ServletContext 聚合到 MutablePropertySources 后，相当于 ServletContext 的初始化参数也成为 ”属性配置源“的一部分。
      *    public static void initServletPropertySources(MutablePropertySources sources,
      *                        @Nullable ServletContext servletContext, @Nullable ServletConfig servletConfig) {
      *
      * 		Assert.notNull(sources, "'propertySources' must not be null");
      * 		String name = StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME;
      * 		if (servletContext != null && sources.get(name) instanceof StubPropertySource) {
      * 			sources.replace(name, new ServletContextPropertySource(name, servletContext));
      * 		}
      *
      * 		name = StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME;
      * 		if (servletConfig != null && sources.get(name) instanceof StubPropertySource) {
      * 			sources.replace(name, new ServletConfigPropertySource(name, servletConfig));
      *                }
      *          }
      *    }
      *
      *    public class ServletContextPropertySource extends EnumerablePropertySource<ServletContext> {
      *
      * 	public ServletContextPropertySource(String name, ServletContext servletContext) {
      * 		super(name, servletContext);
      *        }
      *
      *    @Override
      *    public String[] getPropertyNames() {
      * 		return StringUtils.toStringArray(this.source.getInitParameterNames());
      *    }
      *
      *    @Override
      *    @Nullable
      *    public String getProperty(String name) {
      *         // 获取属性的源头是 ServletContext
      * 		return this.source.getInitParameter(name);
      *    }
      * }
      */

     /**
       *  // 初始化早期事件的集合
       *  this.earlyApplicationEvents = new LinkedHashSet<>();
      *   从命名来看，earlyApplicationEvents 是一个早期事件集合，早期事件集合又是什么呢？它解决了什么问题？
      *
      *   //定义 earlyApplicationEvents
      *   private Set<ApplicationEvent> earlyApplicationEvents;
      *
      *   其实 earlyApplicationEvents 的设计是为了解决spring framework 3.x 的一个 场景bug。 如果一个类同时实现了 ApplicationEventPublisherAware 与 BeanPostProcessor (或其他后置处理器)
      *   会因为 BeanPostProcessor 的创建时机靠前，而导致提前回调 ApplicationEventPublisherAware 的 setter 方法。但是又因为此时  ApplicationEventMulticaster 还没有创建，
      *   所以导致如果在此期间广播事件，则事件暂时无法广播给监听器，监听器无法接收事件，从而产生bug.
      *
      *   earlyApplicationEvents 的设计是在所有的 ApplicationListener 之前，将事件添加到 earlyApplicationEvents 集合中，等到所有的 ApplicationListener 都创建完成后再逐个广播，此操作可以保证所有监听器都可以监听到自己该监听的事件。
      *
      */
}
