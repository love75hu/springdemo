package cn.mediinfo.springdemo.controller.effctivedemo.example2;

/*
 *@title ClassesAndInterfaces2_要在公有类而非公有域中使用访问方法
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class ClassesAndInterfaces2_要在公有类而非公有域中使用访问方法 {
    /**
     * 公有类（Public Class）和公有域（Public Field）是Java中的两种访问控制机制。
     * 公有类（Public Class）：这是一个可以被任何其他类访问的类。它的类名、方法和属性都可以从其他任何地方访问。在Java中，每个Java文件可以有一个公有类，且文件名必须与公有类的名字相同。
     * 公有域（Public Field）：这是一个可以被任何其他类访问的类的成员（字段或方法）。公有字段可以直接被任何类访问和修改，而不需要通过getter和setter方法。然而，直接访问和修改公有字段通常被视为不良的编程实践，因为它破坏了封装性，使得代码更难维护和理解。
     */
}

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    //公有类而非公有域
    public double getX() {
        return x;
    }
    //公有类而非公有域
    public double getY() {
        return y;
    }
}
