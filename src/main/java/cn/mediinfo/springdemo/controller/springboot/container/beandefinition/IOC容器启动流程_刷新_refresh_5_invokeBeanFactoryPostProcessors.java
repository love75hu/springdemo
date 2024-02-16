package cn.mediinfo.springdemo.controller.springboot.container.beandefinition;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Collection;
import java.util.List;

/*
 *@title IOC容器的刷新_refresh_prepareRefresh2
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/14 9:51
 */
public class IOC容器启动流程_刷新_refresh_5_invokeBeanFactoryPostProcessors {
    /**
     * 阅读本章节，应该先阅读以下两章节内容：
     * 1、Spring Boot启动流程（印象笔记）。{@link org.springframework.boot.SpringApplication#run(String... args) } //10 刷新spring上下文  refreshContext(context);
     * 2、IOC容器的启动流程
     * 本章节力求把 {@link org.springframework.context.support.AbstractApplicationContext#refresh()}具体实现进行详细的说明。也就是《IOC容器的启动流程》进一步进行详细的说明。
     */

    /**
     *  // 5.准备 beanFactory 创建后的后置处理
     *    invokeBeanFactoryPostProcessors(beanFactory);
     *    {@link org.springframework.context.support.AbstractApplicationContext#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory)}
     *
     *    BeanFactory的内部编程式后置处理完成后，接下来进入IOC容器刷新的第一个超级复杂的重难点：BeanFactoryPostProcessor 和 BeanDefinitionRegistryPostProcessor 的执行。
     *    invokeBeanFactoryPostProcessors 方法看上去非常简单，核心动作只有一行代码。
     *
     *    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
     *         //  核心执行方法，接下来将对该方法的具体实现进行拆解讲解
     *         PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, this.getBeanFactoryPostProcessors());
     *         ...
     *     }
     *
     *    5.1 现有的后置处理器分类
     *    {@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List)} )
     *    invokeBeanFactoryPostProcessors 方法首先会检查当前 BeanFactory 是否同时是一个 BeanDefinitionRegistry，由于目前的 BeanFactory 落地实现只有一个 DefaultListableBeanFactory，因此该逻辑一定为true.
     *    判断完成的if结构中需要对方法参数中传入的 beanFactoryPostProcessors 进行类型分离，并且最终分理出两个集合，分别是 BeanDefinitionRegistryPostProcessor 和 BeanFactoryPostProcessor。
     *    其中 BeanDefinitionRegistryPostProcessor 的 postProcessBeanDefinitionRegistry 方法会立即执行（由此可见，通过编程式注册的 BeanDefinitionRegistryPostProcessor其执行优先级是最高的）
     *
     *       public static void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {
     *         Set<String> processedBeans = new HashSet();
     *         ArrayList regularPostProcessors;
     *         ArrayList registryProcessors;
     *         int var9;
     *         ArrayList currentRegistryProcessors;
     *         String[] postProcessorNames;
     *         if (beanFactory instanceof BeanDefinitionRegistry registry) {
     *             regularPostProcessors = new ArrayList();
     *             registryProcessors = new ArrayList();
     *             Iterator var6 = beanFactoryPostProcessors.iterator();
     *
     *             //将方法参数传入的 beanFactoryPostProcessors 分离开
     *             while(var6.hasNext()) {
     *                 BeanFactoryPostProcessor postProcessor = (BeanFactoryPostProcessor)var6.next();
     *                 if (postProcessor instanceof BeanDefinitionRegistryPostProcessor registryProcessor) {
     *                     registryProcessor.postProcessBeanDefinitionRegistry(registry);
     *                     registryProcessors.add(registryProcessor);
     *                 } else {
     *                     regularPostProcessors.add(postProcessor);
     *                 }
     *             }
     *             .....
     *       }
     *
     *   5.2 执行最高优先级的 BeanDefinitionRegistryPostProcessor
     *   {@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List)} )
     *   处理好编程式注入的后置处理器后，下面要处理IOC容器中已有的后置处理器。由于 BeanDefinitionRegistryPostProcessor 的执行优先级较 BeanFactoryPostProcessor 靠前，因此 BeanDefinitionRegistryPostProcessor 先执行。
     *   而每种后置处理器（包括BeanFactoryPostProcessor、BeanPostProcessor、BeanDefinitionRegistryPostProcessor）都有三种不同类型的优先级，分别是 PriorityOrdered、Ordered、普通，其优先级依次降低。
     *   具体到某一个优先级的后置处理器，先会从 BeanFactory 中获取所有的 BeanDefinitionRegistryPostProcessor，并检查这些后置处理器是否实现了 PriorityOrdered 接口，如果实现了，则创建这些后置处理器，并依次执行。
     *   依照 PriorityOrdered 接口的中的 getOrder 方法返回的值进行排序，值越小，优先级越高，越先执行。
     *   除了主干动作，ProcessedBeans.add(ppName) 会将所有执行过的后置处理器记录下来，这样做的原因马上就可以看到。
     *
     *             ......
     *             currentRegistryProcessors = new ArrayList();
     *             postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
     *             String[] var16 = postProcessorNames;
     *             var9 = postProcessorNames.length;
     *
     *             int var10;
     *             String ppName;
     *             for(var10 = 0; var10 < var9; ++var10) {
     *                 ppName = var16[var10];
     *                 //首先，执行实现了 PriorityOrdered 接口的  BeanDefinitionRegistryPostProcessor
     *                 if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
     *                     currentRegistryProcessors.add((BeanDefinitionRegistryPostProcessor)beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
     *                     processedBeans.add(ppName);
     *                 }
     *             }
     *
     *             sortPostProcessors(currentRegistryProcessors, beanFactory);
     *             registryProcessors.addAll(currentRegistryProcessors);
     *             invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry, beanFactory.getApplicationStartup());
     *             currentRegistryProcessors.clear();
     *             .....
     *
     */
}
