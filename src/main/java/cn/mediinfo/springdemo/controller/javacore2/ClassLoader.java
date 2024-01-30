package cn.mediinfo.springdemo.controller.javacore2;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/*
 *@title ClassLoader 类加载器
 *@description
 *@author thj
 *@create 2024-01-29
 */
public class ClassLoader {
       /**
        * 类加载器是负责加载类的对象。类加载器有两个基本特征：
        * 1、类加载器将类的字节码从存储区加载到内存。
        * 2、类加载器将字节码转换成java.lang.Class的一个实例。
        * 类加载器的主要任务是查找和加载类。类加载器通常从一个类的全限定名开始工作，例如：java.lang.String。然后，类加载器将这个名字转换成一个字节码文件名，例如：java/lang/String.class。
        * 类加载器的另一个任务是检查这个类的字节码是否已经被加载到内存中。如果没有，类加载器会读取字节码文件，并且将其转换成一个java.lang.Class的实例。
        * 类加载器的最后一个任务是将这个类的字节码转换成一个java.lang.Class的实例。这个实例包含了这个类的所有信息，包括它的成员和方法。
        * 类加载器的工作方式是：当程序需要使用某个类时，类加载器就会负责查找这个类的字节码文件，并且将其转换成java.lang.Class的一个实例。
       */

       /**
          在Java中，类加载器（ClassLoader）主要用于以下几种场景：
          1、动态加载：类加载器可以在运行时动态加载类，这对于某些需要运行时扩展功能的应用程序非常有用。例如，一些插件化的系统，可以通过类加载器动态加载和卸载插件。
          2、隔离加载：通过使用不同的类加载器，可以实现类的隔离加载，防止类库之间的冲突。例如，应用服务器可以为每个部署的应用程序创建一个新的类加载器实例，以隔离各个应用程序的类库。
          3、热部署：在开发过程中，可以通过重新创建类加载器实例来实现代码的热部署，即不重启应用程序就可以加载新的代码。
          4、从非标准来源加载类：如果需要从数据库、网络或其他非文件系统的来源加载类，可以通过自定义类加载器来实现。
       */

       /**
        * @description
        */
       void example() throws MalformedURLException {
           //java.lang.Class
           java.lang.Class.class.getClassLoader(); //获取类加载器


           //java.lang.ClassLoader
           java.lang.ClassLoader classLoader= java.lang.ClassLoader.getPlatformClassLoader(); //获取平台类加载器
           java.lang.ClassLoader classLoader2= java.lang.ClassLoader.getSystemClassLoader(); //获取系统类加载器
           java.lang.ClassLoader classLoader3= java.lang.ClassLoader.getSystemClassLoader().getParent(); //获取系统类加载器的父类加载器,如果父类加载器是引导类加载器，则返回null

           //java.net.URLClassLoader
           //构建一个加载器，它可以从既定的URL处加载类。如果加载的URL以/结尾，那么它表示的是一个目录，否则表示的是一个JAR文件。
           URLClassLoader urlClassLoader = new URLClassLoader(new java.net.URL[]{new URL("file:/path/to/your/classes/")}, java.lang.ClassLoader.getSystemClassLoader());

           //java.lang.Thread
           Thread.currentThread().getContextClassLoader(); //获取当前线程的上下文类加载器
           Thread.currentThread().setContextClassLoader(classLoader); //设置当前线程的上下文类加载器
       }

       /**
        * 自定义类加载器的简单示例，该类加载器从字节数组中加载类
        */
       class ByteArrayClassLoader extends java.lang.ClassLoader {
           private Map<String, byte[]> classes = new HashMap<>();

           public ByteArrayClassLoader(java.lang.ClassLoader parent) {
               super(parent);
           }

           public void addClass(String name, byte[] bytes) {
               classes.put(name, bytes);
           }

           @Override
           protected Class<?> findClass(String name) throws ClassNotFoundException {
               byte[] classData = classes.get(name);
               if (classData == null) {
                   throw new ClassNotFoundException(name);
               }
               return defineClass(name, classData, 0, classData.length);
           }
       }
}
