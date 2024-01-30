package cn.mediinfo.springdemo.controller.springboot.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 *@title AOP_Demo
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/8 20:17
 */
@Aspect
public class AOP_Demo {
    /**
     * AOP术语
     * 1、Target 目标对象，即被代理对象
     * 2、Proxy 代理对象，即经过代理后生成的对象(如 Proxy.newProxyInstance(); 返回的结果)
     * 3、JoinPoint 连接点，即目标对象所属类中定义的所有方法
     * 4、PointCut 切入点，即哪些被拦截、被增强的链接点
     *    切入点何连接点的关系应该是包含关系，切入点是0个或者多个（甚至全部）连接点的组合
     *    切入点一定是连接点，连接点却不一定是切入点
     * 5、Advice 通知，即增强的逻辑，也就是增强的代码 （proxy 代理对象 = Target 目标对象 + Advice 通知）
     * 6、Aspect 切面，即切入点和通知的组合（Aspect 切面=PointCut 切入点 + Advice 通知）
     * 7、Weaving 织入，这是一个动词，它是将通知（Advice）应用到目标对象 (Target)进而生成代理对象的过程  （proxy 代理对象 = Target 目标对象 + Advice 通知）这个算式中的+就是织入
     * 8、Introduction 引介，这个概念对标的是通知Advice），通知是针对切入点提供增强的逻辑，而引介是针对class类，它可以在不修改类的代码的前提下在运行时个原始类添加方法，属性。
     * */


     /**
     * 通知类型
      * spring framework中提供了5种通知类型
      * 1、Before 前置通知,在方法执行之前执行
      * 2、After 后置通知,在方法执行之后执行
      * 3、AfterReturning 返回通知，目标方法调用成功，在结果返回值之后执行
      * 4、AfterThrowing 异常通知，目标方法调用失败，在异常抛出之后执行
      *    注意：AfterReturning、AfterThrowing是互斥的，如果调用方法成功无异常，有返回值；如果方法抛出异常，则不会有返回值。
      * 5、Around 环绕通知，编程式调用目标方法调用
      *           环绕通知是所有通知类型中可操作范围最大的一种，它可以控制目标对象及要执行的方法，所以环绕通知可以任意在目标调用方法前后扩展甚至不调用目标对象的方法
      */
     public class UserService {
         private void addUser(String username) throws InstantiationException, IllegalAccessException {
             System.out.println("Adding user: " + username);

             //实例化代理对象
            var proxy = (UserService)Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class<?>[]{UserService.class}, LogInvocationHandler.class.newInstance());
             proxy.addUser(username);
         }
     }

     @EnableAspectJAutoProxy
     @Aspect
     @Component
    public class LoggingAspect {
        @Before("execution(* cn.mediinfo.springdemo.controller.springboot.aop.AOP_Demo.UserService.addUser(String)) && args(username)")
        public void beforeAddUser(String username) {
            System.out.println("Before adding user: " + username);
        }
    }

    public class LogInvocationHandler implements InvocationHandler {
        private Object target;

        public LogInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoking " + method.getName());
            Object result = method.invoke(target, args);
            System.out.println("After invoking " + method.getName());
            return result;
        }
    }


    /**
     * @EnableAspectJAutoProxy AOP的开关，用于开启注解驱动。它有两个属性
     * 1、boolean proxyTargetClass() default false; 是否直接代理目标类（即强制使用Cglib代理）
     * 2、boolean exposeProxy() default false; 是否暴露当前线程的AOP上下文（开启后，AopContext可以获取到当前的代理对象本身）
     *
     * 该注解还导入 @Import(AspectJAutoProxyRegistrar.class)
     * AspectJAutoProxyRegistrar 是一个基于Aspectj支持的自动代理注册器。那它注册了什么呢？
     * 1、基于既定的@EnableAspectJAutoProxy 注解，根据当前 BeanDefinitionRegister 在适当的位置注册  AnnotationAwareAspectJAutoProxyCreator
     */
}
