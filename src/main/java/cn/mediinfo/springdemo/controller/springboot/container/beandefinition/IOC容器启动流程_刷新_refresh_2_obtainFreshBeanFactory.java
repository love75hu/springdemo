package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

/*
 *@title IOC容器的刷新_refresh_prepareRefresh2
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/14 9:51
 */
public class IOC容器启动流程_刷新_refresh_2_obtainFreshBeanFactory {
    /**
     * 阅读本章节，应该先阅读以下两章节内容：
     * 1、Spring Boot启动流程（印象笔记）。{@link org.springframework.boot.SpringApplication#run(String... args) } //10 刷新spring上下文  refreshContext(context);
     * 2、IOC容器的启动流程
     * 本章节力求把 {@link org.springframework.context.support.AbstractApplicationContext#refresh()}具体实现进行详细的说明。也就是《IOC容器的启动流程》进一步进行详细的说明。
     */

    /**
     *  //2.获取beanFactory ，加载所有bean的定义信息（未实例化）
     *  ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
     *
     *  protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
     * 		refreshBeanFactory();
     * 		return getBeanFactory();
     *     }
     *
     *  public final ConfigurableListableBeanFactory getBeanFactory() {
     * 		DefaultListableBeanFactory beanFactory = this.beanFactory;
     * 		if (beanFactory == null) {
     * 			throw new IllegalStateException("BeanFactory not initialized or already closed - " +
     * 					"call 'refresh' before accessing beans via the ApplicationContext");
     *                }
     * 		return beanFactory;*
     * 	}
     *
     * 	obtainFreshBeanFactory 的动作非常简单，先刷新 refreshBeanFactory(); 再获取它。获取的动作也相当简单，该方法返回当前 ApplicationContext 中内嵌的 BeanFactory （即 DefaultListableBeanFactory）
     *
     * 	接下来对 refreshBeanFactory(); 的实现进行详细解释：refreshBeanFactory（）本身也是一个抽象方法，需要子类去实现：
     * 	{@link org.springframework.context.support.GenericApplicationContext#refreshBeanFactory()}  基于XML配置文件驱动的IOC容器实现
     *  {@link org.springframework.context.support.AbstractRefreshableApplicationContext#refreshBeanFactory()}  基于注解驱动的IOC容器实现
     *
     *  对XML配置文件驱动的IOC容器和注解驱动的IOC容器，以上是他们的具体的实现代码类。
     *
     * 1、基于XML配置文件驱动的IOC容器实现：
     *  默认引入webmvc 依赖时对应的 AnnotationConfigServletWebServerApplicationContext 继承自 GenericApplicationContext，具体代码如下：
     *  protected final void refreshBeanFactory() throws IllegalStateException {
     * 		if (!this.refreshed.compareAndSet(false, true)) {
     * 			throw new IllegalStateException(
     * 					"GenericApplicationContext does not support multiple refresh attempts: just call 'refresh' once");
     *                }
     * 		this.beanFactory.setSerializationId(getId());
     * 	}
     *
     * 	代码逻辑很简单，就是设置了 beanFactory 的序列化ID而已；
     * 	基于XML的 IOC 容器 做的事情比较复杂，不过由于 spring boot全系列使用基于注解的IOC容器，，因此只需要简单了即可。
     *
     * 	2、基于注解驱动的IOC容器实现：
     * 	protected final void refreshBeanFactory() throws BeansException {
     * 		if (hasBeanFactory()) {
     * 	        //允许重复刷新IOC容器，内部的bean也是可以重新加载的
     * 	        //故此处有销毁 bean 和 关闭 beanFactory 的动作
     * 			destroyBeans();
     * 			closeBeanFactory();
     *                }
     * 		try {
     * 	        //创建 beanFactory （会组合父 beanFactory 形成层级关系）
     * 			DefaultListableBeanFactory beanFactory = createBeanFactory();
     * 			beanFactory.setSerializationId(getId());
     * 		   //自定义配置 beanFactory
     * 			customizeBeanFactory(beanFactory);
     * 		   // 解析、加载XML 中定义的 beanDefinition
     * 			loadBeanDefinitions(beanFactory);
     * 			this.beanFactory = beanFactory;
     *        }
     * 		catch (IOException ex) {
     * 			throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
     *        }
     *    }
     *
     */
}
