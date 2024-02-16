package cn.mediinfo.springdemo.controller.effctivedemo.example2;

/*
 *@title ClassesAndInterfaces5_要么设计继承并提供文档要么禁止继承
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class ClassesAndInterfaces5_要么设计继承并提供文档要么禁止继承 {
    /**
     * 用文档说明它可覆盖的方法的自用性，或者用final修饰符禁止继承
     * 如果设计类允许继承，那么要么设计并文档化它的继承行为，要么禁止继承。文档规范参照java.lang包中的类。
     */

     //java.util.AbstractList是一个设计用于继承的类，并且其文档写得非常好。这个类提供了一些默认的实现，并且在文档中详细地描述了哪些方法是可以被覆盖的，以及如何覆盖这些方法。这为开发者提供了一个清晰的指南，说明如何正确地继承和使用这个类。
     //https://docs.oracle.com/javase/8/docs/api/java/util/AbstractList.html
}

 class MyBaseClass {
    // 这个方法可以被子类覆盖
    public void overridableMethod() {
        // ...
    }

    // 这个方法不能被子类覆盖
    public final void nonOverridableMethod() {
        // ...
    }
}

//禁止继承的类
 final class MyFinalClass {
    // ...
}


