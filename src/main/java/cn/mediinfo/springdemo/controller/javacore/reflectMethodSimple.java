package cn.mediinfo.springdemo.controller.javacore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 演示通过反射获取方法并进行调用的示例
 */
public class reflectMethodSimple {
    public reflectMethodSimple() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //方法名 square， 参数 double.class),参数一定要传，可能会有同名参数不同的方法
        Method square= reflectMethodSimple.class.getMethod("square",double.class);
        Method printTable= reflectMethodSimple.class.getMethod("printTable",double.class);

        printTable(1,10,10,square);
        printTable(1,10,10,printTable);
    }

    /**
     * 计算方法
     *
     * @param x
     * @return
     */
    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) throws InvocationTargetException, IllegalAccessException {
        System.out.println(f);

        double dx = (to - from) / (n - 1);

        for (double x = from; x <= to; x += dx) {
            //调用当前的方法，对于静态方法，第一个参数会忽略，即可以设置为null
            //通过反射调用方法比直接调用性能上慢得多
            //更好的办法是使用接口及java8引入的lambda表达式
            double y = (double) f.invoke(null, x);
            System.out.print(String.format("%d,%d", x, y));
        }
    }
}
