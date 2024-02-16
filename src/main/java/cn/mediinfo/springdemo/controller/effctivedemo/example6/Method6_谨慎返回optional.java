package cn.mediinfo.springdemo.controller.effctivedemo.example6;

/*
 *@title Method6_谨慎返回optional
 *@description
 *@author thj
 *@create 2024-02-16
 */
public class Method6_谨慎返回optional {
    /**
     * 谨慎返回optional
     * --------------------------
     * 1、永远不要通过返回optional来返回null.这彻底违反了optional的初衷，optional是用来替代null的。
     * 2、optional本质上与用来检查异常类似
     *
     * 不应该使用optional作为返回值类型的场景：
     * --------------------------
     * 容器类型包括集合、映射、流等、数组和optional，它们都可以返回一个空容器，而不是一个optional对象。
     */
}
