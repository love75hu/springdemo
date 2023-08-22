package cn.mediinfo.springdemo.controller.javacore;

import cn.mediinfo.springdemo.model.ClientscopeEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用
 * 静态方法引用 语法：类名::静态方法名 示例：Math::max
 *
 * 实例方法引用
 * 对象方法引用：语法：对象名::实例方法名  示例：str::length
 * 超类方法引用：语法：super::实例方法名  示例：super::toString
 * 类名方法引用：语法：类名::实例方法名    示例：String::valueOf
 *
 * 构造方法引用：语法：类名::new  示例：ArrayList::new
 */
public class MethodReferenceSimple {

    /**
     * 构造方法引用
     * 说明：
     * 1、通过使用构造方法引用，将创建和获取对象分离开来；
     * 2、直接new object是直接创建并初始化对象
     */
    public  void  ConstructorMethodReferenceExample()
    {
        //创建对象
        Supplier<ClientscopeEntity> supplier= ClientscopeEntity::new;

        //获取对象
        ClientscopeEntity entity= supplier.get();
    }

    /**
     * 通过函数式接口创建对象
     */
    public  void  ConstructorMethodReferenceExample2()
    {
        ClientsSopeFactory factory=ClientscopeEntity::new;
        var entity= factory.create("id","scope","clientoid");


        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    }
}

/**
 * 定义一个函数式接口
 * @param <P>
 */
@FunctionalInterface
interface ClientsSopeFactory<P extends ClientscopeEntity> {
    /**
     * 定义一个创建ClientscopeEntity的方法，注意：参数一定要和ClientscopeEntity的构造方法参数一致
     * 因为，一个方法的签名与构造方法匹配，则构造方法引用将自动关联到该方法。
     * @param id
     * @param scope
     * @param clientid
     * @return
     */
    P create(String id,String scope, String clientid);
}
