package cn.mediinfo.springdemo.controller.effctivedemo.example2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
 *@title ClassesAndInterfaces10_静态成员优先于静态成员
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class ClassesAndInterfaces10_静态成员类优先于非静态成员类 {
    /**
     * 嵌套类：是指嵌套在一个类中的内部类。嵌套类存在的目的应该只是为它的外部类服务。如果嵌套类未来需要用于其他环境，应该将它提取为顶层类。
     * 嵌套类分为四种：静态成员类、非静态成员类、匿名类、局部类。
     * 静态成员是一种最简单的嵌套类。最好把它看作一个普通类，只是碰巧把它声明在另一个类的内部而已。如果一个嵌套类需要引用它的外部实例，那么它必须声明为非静态的；如果不需要，那么它必须声明为静态的。
     *
     * https://c.biancheng.net/view/1026.html
     */

    //静态成员类
    public static class StaticMemberClass {
        // ...
    }

    void example() {
        HashMap<String,String> map=new HashMap<String,String>();
        Map.Entry<String,String> entry=map.entrySet().iterator().next();
        Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByKey();
        var ss=  Map.Entry.comparingByValue();

        MathOperations.Calculator.calculateSum(1,2,3);
    }
}

 /**
  *
  * 静态成员类在java中的使用举例
  * 静态成员类（Static Member Class）在Java中有许多常见的使用场景。以下是一些例子：
  * 1、当你需要一个与外部类关联的辅助类时，可以使用静态成员类。例如，你可能需要一个特殊的迭代器或者一个特殊的值对象。
  * 2、当你需要一个可以访问外部类的私有字段（Private Fields）或方法（Private Methods）的类时，可以使用静态成员类。
  * 3、当你需要定义一个公共的辅助类，但这个类只对某个类有用时，可以使用静态成员类。这样可以避免将这个辅助类暴露给整个包。
  */
 class MathOperations {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static class Calculator {
        public static  int calculateSum(int a, int b, int c) {
            return add(add(a, b), c);
        }

        public static  int calculateDifference(int a, int b, int c) {
            return subtract(subtract(a, b), c);
        }
    }
}
