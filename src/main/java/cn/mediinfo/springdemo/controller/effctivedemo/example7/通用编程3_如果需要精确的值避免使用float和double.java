package cn.mediinfo.springdemo.controller.effctivedemo.example7;

/*
 *@title 通用编程3_如果需要精确的值避免使用float和double
 *@description
 *@author thj
 *@create 2024-02-16
 */
public class 通用编程3_如果需要精确的值避免使用float和double {
    /**
     * 如果需要精确的值避免使用float和double
     * 1、float和double类型主要用于科学计算和工程计算，它们执行二进制浮点运算，这种运算对于很多十进制小数不能精确表示。
     * 2、如果需要精确的结果，如货币计算，应该使用int、long、BigDecimal或者int或long的原生数据类型。
     * 3、如果需要精确的结果，应该使用BigDecimal，它是一个任意精度的十进制数值计算类。
     * 4、BigDecimal的构造函数接受一个字符串或者一个整数，不要使用double类型的构造函数，因为double类型的构造函数会导致不可预知的精度损失。
     * 5、BigDecimal的运算方法不会改变它的操作数，而是返回一个新的BigDecimal对象。
     * 6、BigDecimal的compareTo方法用于比较两个BigDecimal对象的大小，equals方法用于比较两个BigDecimal对象的值是否相等。
     *
     *
     */

    void example(){
        //double示例
        double funds = 1.03;
        double price = 0.42;
        double change = funds - price;
        System.out.println(change);//0.6100000000000001 二进制浮点运算，并非我们期望的0.61
    }
}
