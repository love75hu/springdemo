package cn.mediinfo.springdemo.controller.springboot.container;

/*
 *@title 命名规范
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/7 10:05
 */
public class 命名规范 {
    /**
     *  *Aware 结尾，回调类接口，实现Aware接口
     *  *Resolver 结尾，表示解析器类接口
     *  Default* ，标识***接口的默认实现
     *  Abstract** 抽象类接口、类
     *  Autowire** 自动处理类解耦、类
     *  **Processor 后置处理器相关接口、类
     *  **Definition **定义类接口、类
     *  **Capable 意为“有能力”，如果有该命名结尾的接口，通常意味着通过该接口可以获得某个特定的方法，例如 EnvironmentCapable 接口中定义了 getEnvironment() 方法，可以通过该方法获取 Environment 对象
     *  Mutable** 开头通常是一个List组合封装，例如:MutablePropertySources
     *  Configurable** 配置类
     *  invoke** 调用** 例如:invokeMethod()
     *  **Handler **处理策略，例如:AuthenticationSuccessHandler
     *  after**   之后处理，例如:afterPropertiesSet()
     *  before**  之前处理
     *  additional** 附加处理，例如:additionalAuthenticationChecks
     */
}
