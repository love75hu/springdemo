package cn.mediinfo.springdemo.controller.javacore2;

import java.util.List;
import java.util.stream.Stream;

/*
 *@title Record
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/21 10:10
 */
public class Record
{
    // Record 类型是一种特殊的类，它们被用来模拟一种值类型，而不是引用类型。当你声明一个 Record 类型时，编译器会自动为你生成一些方法，比如构造器、getter、setter、equals()、hashCode()、toString() 等等。
    // Record 的优点是，它可以让你更加方便地创建一个不可变的类，而不需要手动去实现这些方法。这样一来，你就可以专注于类的业务逻辑，而不需要关注这些方法的实现。
    // Record 使用场景是：当你需要创建一个不可变的类，而且这个类的主要目的是用来存储数据时，你就可以使用 Record 类型。
    // Record 类型的构造器是 private 的，所以你不能直接通过 new Record() 的方式来创建一个 Record 类型的实例。你可以通过 Record.of() 的方式来创建一个 Record 类型的实例。
    // Record 类型的实例是不可变的，所以它的属性也是不可变的。你可以通过 getter 方法来获取属性的值，但是你不能通过 setter 方法来修改属性的值。
    // Record 类型的实例是 final 的，所以你不能继承 Record 类型，也不能让 Record 类型去继承其他类。
    // Record 类型的实例是 final 的，所以你不能让 Record 类型去实现其他接口。
    Stream<Person> stream = Stream.of(new Person("Tom", 12), new Person("Jerry", 13));
    List<Person> list = List.of(new Person("Tom", 12), new Person("Jerry", 13));

    public record Person(String name, int age) {
        // 你可以在 Record 类型中定义静态方法，但是你不能在 Record 类型中定义静态属性。
        public static void sayHello() {
            System.out.println("Hello");
        }
    }
}
