package cn.mediinfo.springdemo.controller.effctivedemo.example3;

/*
 *@title 泛型3_列表优于数组
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class 泛型3_列表优于数组 {
    /**
     *
     * 1、数组与泛型的区别
     * -----------------------------------------------------
     * 1.1、数组是协变的，泛型是不可变的。即String[]是Object[]的子类型，而List<String>不是List<Object>的子类型。
     * 1.2、数组是具体化的，会在运行时才知道并检查他们的元素类型，而泛型是通过擦除来实现的，这意味着他们只有一个表示，即原始类型。
     */
}
