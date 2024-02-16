package cn.mediinfo.springdemo.controller.effctivedemo.example6;

/*
 *@title Method1_检查参数的有效性
 *@description
 *@author thj
 *@create 2024-02-16
 */
public class Method1_检查参数的有效性 {
    /**
     * 非公有方法通常应该使用断言(assert)来检查它们的参数。断言是一种内置的测试机制，它可以在测试时启用和禁用。断言的使用方式如下：
     */
    private static void test(int a) {
        assert a > 0;
    }


}
