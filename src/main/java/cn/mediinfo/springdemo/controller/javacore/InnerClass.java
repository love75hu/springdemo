package cn.mediinfo.springdemo.controller.javacore;

import org.aspectj.weaver.ast.Call;

/**
 * java 内部类
 *
 * Java 内部类可以分为以下几种类型：
 *
 * 成员内部类（Member Inner Class）：
 * 成员内部类是定义在另一个类的内部，并且与外部类的实例关联。成员内部类可以访问外部类的所有成员变量和方法，包括私有成员。通过创建外部类对象，可以实例化成员内部类。
 *
 * 静态内部类（Static Inner Class）：
 * 静态内部类是在另一个类的内部定义的静态类。它与外部类的实例无关，可以直接访问外部类的静态成员，但不能访问非静态成员。静态内部类可以使用外部类的名称直接访问。
 *
 * 局部内部类（Local Inner Class）：
 * 局部内部类是定义在方法、构造函数或任意代码块内部的类。局部内部类只在所在的代码块中可见，并且不能被其他方法或代码块访问。局部内部类可以访问外部类的成员和局部变量，但外部类和局部变量必须声明为 final 或 effectively final。
 *
 * 匿名内部类（Anonymous Inner Class）：
 * 匿名内部类是没有显式定义类的名称的内部类。它通常用作接口的实现或抽象类的子类，并且在创建对象时进行实例化。匿名内部类不能有构造函数，但可以有实例初始化块。
 *
 * @see <a href="https://blog.csdn.net/yahid/article/details/125926582">java内部类</>
 */
public class InnerClass {
    /**
     * 核心是匿名内部类
     * 说明:匿名内部类是没有名称的内部类。在Java中调用某个方法时，如果该方法的参数是接口类型，除了可以传接口实现类外，还可以使用实现接口的匿名内部类作为参数，在匿名内部类中直接完成方法的实现。
     * 注意:匿名内部类里面只能重写父类中的方法，然后调用
     * 语法结构：
     * new 接口或类(参数列表){
     *        类体;
     * };
     * 特征:匿名内部类既是一个类，又是一个对象，既有定义类的特征，又有创建对象的特征。所以可以调用匿名内部类中的方法。
     * 使用场景：Adapter（适配器）、当作参数传递、Listener/Callback（监听器/回调）、Iterator/Iterable（迭代器/可迭代对象）、Runnable/Thread（线程）
     */
    public void  anonymousClassExample()
    {
        //使用方式一：匿名类的基本实现
        var people=new People();
        people.Method();

        //使用方式二:匿名类当作参数传递
        var china=new IPeopleService(){
            @Override
            public void getCountry() {
                System.out.println("中国人");
            }
        };
        people.Method(china);

        //使用方式三:匿名类当作参数传递
        var runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行啦！");
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();

    }
}

class People
{
    private String Name;

    /**
     * 该示例演示：一个转换器，可以通过一个接口实现不同的国家的姓名获取；
     */
    public void Method()
    {
        //1.和传统new Class()的区别是，我们没有定义接口的实现类，他会默认生成一个类，类名可能是：PeopleService$1 ,我们可以通过Object.getClass()查看生成的类名
        //2.直接new Interface()可以减少代码量，编写代码更加灵活
        //3.我们可以把接口当作参数在方法传递，lamdba的函数接口也是如此
        var china= new IPeopleService() {
            @Override
            public void getCountry() {
                System.out.println("中国人");
            }
        };

        //查看java默认生成的类名
        //china.getClass();

        var American= new IPeopleService() {
            @Override
            public void getCountry() {
                System.out.println("美国人");
            }
        };
    }

    /**
     * 该示例类演示：匿名类当作参数传递
     * @param service
     */
    public void Method(IPeopleService service){
        service.getCountry();
    }
}

interface IPeopleService{
    /**
     * 获取国家
     */
   void getCountry();
}
