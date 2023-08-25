package cn.mediinfo.springdemo.controller.javacore;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java中的动态代理（Dynamic Proxy）是一种机制，允许在运行时创建一个实现特定接口的代理类对象。动态代理可用于在不修改原始类的情况下，为该类提供额外的功能或行为。
 * 典型的应用场景是Spring AOP
 *
 * @see <a href="https://blog.csdn.net/Passer_hua/article/details/122617628">动态代理</a>
 * <p>
 * 静态代理（Static Proxy）：静态代理是指在编译时就已经确定了代理类和被代理类的关系。在静态代理中，需要手动创建代理类并实现被代理接口，然后在代理类中调用被代理对象的方法，并可以在必要时添加额外的逻辑。
 * 动态代理（Dynamic Proxy）：动态代理是在运行时创建代理对象的机制。
 * CGLIB代理：CGLIB（Code Generation Library）是一个强大的第三方类库，用于在运行时生成Java类的字节码，并动态地加载和修改它们。
 * CGLIB代理是通过继承被代理类来实现的，它不需要被代理类实现任何接口。CGLIB代理可以用于代理非接口类型的类，例如，对类的方法进行代理和增强。
 */
public class DynamicProxy {
    /**
     * CGLIB代理实现
     */
    public void example() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 创建Enhancer对象，用于生成代理对象
        Enhancer enhancer = new Enhancer();
        // 设置代理的目标Class
        enhancer.setSuperclass(TargetClassDemo.class);
        //设置代理目标拦截器
        //enhancer.setCallback(new MyInterceptor());
        enhancer.setCallback(MyInterceptor.class.getConstructor().newInstance());//通过反射创建，和new MyInterceptor()一样

       // 动态代理的生成
        TargetClassDemo target = (TargetClassDemo)enhancer.create();
        // 生成之后会调用
        target.doSomething();
    }
}

/**
 * 目标代理类
 */
 class TargetClassDemo {
    public void doSomething() {
        System.out.println("主体业务方法");
    }
}

/**
 * 目标拦截器
 */
 class MyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 在方法执行前插入自定义逻辑
        System.out.println("Before method execution...");

        // 调用被代理对象的方法
        Object result = proxy.invokeSuper(obj, args);

        // 在方法执行后插入自定义逻辑
        System.out.println("After method execution...");

        return result;
    }
}

