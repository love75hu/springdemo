package cn.mediinfo.springdemo.controller.effctivedemo.example3;

/*
 *@title 泛型2_消除非受检的警告
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class 泛型2_消除非受检的警告 {
    /**
     * 消除非受检的警告
     * -----------------------------------------------------
     * 1、如果你在使用泛型集合类或者数组时遇到编译器警告，而又确定这些代码是类型安全的，那么可以通过@SuppressWarnings("unchecked")注解来消除这些警告。
     * 2、如果你无法消除警告，同时又可以证明引起警告的代码是类型安全的，那么可以通过注解来禁止这条警告。
     * 3、每当使用@SuppressWarnings("unchecked")注解时，都要添加一条注释，说明为什么这么做是安全的。
     * 4、尽量减少使用@SuppressWarnings("unchecked")注解的范围，最好是只用在尽可能小的范围内。
     */
}
