package cn.mediinfo.springdemo.controller.effctivedemo.example2;

/*
 *@title ClassesAndInterfaces9_类层次优先于标签
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class ClassesAndInterfaces9_类层次优先于标签 {
    /**
     * 如果你发现自己在一个类中使用了标签属性，那么你应该考虑将这个类分解为多个类，每个类都包含一个标签属性。
     * 例如，考虑下面的类层次结构，它包含了一个标签属性的类：
     */
    //不好的设计
    class Figure {
        enum Shape {RECTANGLE, CIRCLE};

        // 标签域 - 枚举
        final Shape shape;

        // 这些域仅在shape为RECTANGLE时使用
        double length;
        double width;

        // 这些域仅在shape为CIRCLE时使用
        double radius;

        // 矩形构造器
        Figure(double length, double width) {
            shape = Shape.RECTANGLE;
            this.length = length;
            this.width = width;
        }

        // 圆形构造器
        Figure(double radius) {
            shape = Shape.CIRCLE;
            this.radius = radius;
        }

        // 计算面积（因该考虑抽象该代码）
        double area() {
            switch (shape) {
                case RECTANGLE:
                    return length * width;
                case CIRCLE:
                    return Math.PI * (radius * radius);
                default:
                    throw new AssertionError();
            }
        }
    }

    //好的设计
    abstract class Figure1 {
        abstract double area();
    }

    class Circle extends Figure1 {
        final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * (radius * radius);
        }
    }

    class Rectangle extends Figure1 {
        final double length;
        final double width;

        Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        @Override
        double area() {
            return length * width;
        }
    }
}
