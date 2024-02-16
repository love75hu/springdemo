package cn.mediinfo.springdemo.controller.effctivedemo.example7;

/*
 *@title 通用编程2_for_each循环优先于传统的for循环
 *@description
 *@author thj
 *@create 2024-02-16
 */
public class 通用编程2_for_each循环优先于传统的for循环 {
    /**
     * for-each循环优先于传统的for循环,是for循环的增强版
     * for-each循环的优点：
     * 1、代码更简洁
     * 2、不容易出错
     * 3、不需要索引
     * 4、不需要迭代器
     * 5、不需要初始化
     * 6、不需要条件
     * 7、不需要增量
     * 8、不需要获取元素
     * 9、不需要获取数组的长度
     * 10、不需要获取数组的元素
     * 11、不需要获取数组的元素的类型
     *
     * for-each循环的无法使用的情况：
     * 1、结构过滤：如果要遍历集合并删除选定的元素，就需要显示的使用迭代器，以便于调用它的remove方法
     * 2、并行迭代：如果要并行迭代两个或多个数组，就需要显示的使用传统的for循环
     * 3、转换：如果要遍历列表或者数组，并取代它的部分或者全部元素值，就需要列表迭代器或者数组索引，以便设定元素值
     *
     * 注意：只要实现了Iterable接口的类，都可以使用for-each循环
     */

    public static void main(String[] args) {
        String[] arr = {"a", "b", "c", "d"};
        // 传统的for循环
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        // for-each循环
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
