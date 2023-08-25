package cn.mediinfo.springdemo.controller.javacore;

import java.util.ServiceLoader;

/**
 * ServiceLoader是Java标准库中的一个类，用于加载和实例化实现特定接口或抽象类的服务提供者。它是Java提供的一种服务发现机制，允许开发者在应用程序中以插件方式使用第三方实现。
 * 在spring boot里面，使用@Service注解可以替代ServiceLoader，所以作为了解即可
 */
public class ServiceLoaderSimple {
//    // 定义服务接口
//    public interface MyService {
//        void doSomething();
//    }
//
//    // 创建服务提供者实现
//    public class MyServiceImpl1 implements MyService {
//        public void doSomething() {
//            System.out.println("Implementation 1");
//        }
//    }
//
//    public class MyServiceImpl2 implements MyService {s
//        public void doSomething() {
//            System.out.println("Implementation 2");
//        }
//    }
//
//// 在服务提供者配置文件中添加服务提供者
//// 在 META-INF/services/ 目录下添加 com.example.MyService 文件，并在文件中添加以下内容：
//// com.example.MyServiceImpl1
//// com.example.MyServiceImpl2
//
//    // 使用 ServiceLoader 加载和遍历服务提供者
//    ServiceLoader<MyService> serviceLoader = ServiceLoader.load(MyService.class);
//for (MyService service : serviceLoader) {
//        service.doSomething();
//    }

}
