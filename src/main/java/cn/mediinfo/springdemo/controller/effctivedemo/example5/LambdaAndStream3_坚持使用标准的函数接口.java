package cn.mediinfo.springdemo.controller.effctivedemo.example5;

/*
 *@title LambdaAndStream3_坚持使用标准的函数接口
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class LambdaAndStream3_坚持使用标准的函数接口 {
    /**
     *
     * java.util.function包中有许多标准的函数接口，可以满足大多数需求。只要标准的函数接口能满足需求，就不要使用自己的函数接口。这样会使API更加容易理解和更加容易被其他人使用。
     * java.util.function一共有43个接口，别指望全部记住他们，但是你应该知道有这么一个包，以及这个包中有一些常用的6基础个接口。通过这6个基础接口，必要时就可以推断出其余接口了。
     * 接口              函数签名                范例
     * Predicate<T>      boolean test(T t)     Collection::isEmpty
     * Function<T,R>     R apply(T t)          Arrays::asLists
     * Supplier<T>       T get()               Instant::now
     * Consumer<T>       void accept(T t)      System.out::println
     * UnaryOperator<T>  T apply(T t)          String::toLowerCase
     * BinaryOperator<T> T apply(T t1, T t2)   BigInteger::add
     */
}
