package cn.mediinfo.springdemo.controller.effctivedemo.example5;

/*
 *@title LambdaAndStream2_方法引用优先于Lambda
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class LambdaAndStream2_方法引用优先于Lambda {
    /**
     *
     * 与匿名类相比，Lambda通常更加简洁。但是，方法引用比Lambda更加简洁。只要方法引用比Lambda更加简洁，就应该使用方法引用。如果使用Lambda更加简洁，就应该使用Lambda。
     */
    void example() {
        //Lambda
        Thread t = new Thread(() -> System.out.println("Hello World"));

        //方法引用
        Thread t1 = new Thread(System.out::println);
    }

    /**
     * 方法引用类型             范例                        lambda等价
     * 静态                 Integer::parseInt            str -> Integer.parseInt(str)
     * 有限制的实例           Instant.now()::isAfter       Instant then = Instant.now(); t -> then.isAfter(t)
     * 无限制的实例           String::toLowerCase          str -> str.toLowerCase()
     * 类构造器              TreeMap<K,V>::new            () -> new TreeMap<K,V>()
     * 数组构造器            int[]::new                   len -> new int[len]
     */
}
