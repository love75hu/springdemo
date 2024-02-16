package cn.mediinfo.springdemo.controller.effctivedemo.example7;

import java.util.Arrays;
import java.util.Iterator;

/*
 *@title 通用编程1_将局部变量的作用域最小化
 *@description
 *@author thj
 *@create 2024-02-16
 */
public class 通用编程1_将局部变量的作用域最小化 {
    /**
     * 将局部变量的作用域最小化
     * --------------------------
     * 1、在定义变量的地方就初始化它
     * 2、使用for循环代替while循环
     * 3、尽量使用for-each循环
     * 4、尽量使用局部变量
     */

    void example() {
        // 1、在定义变量的地方就初始化它
        String name = "thj";

        // 2、使用for循环代替while循环
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        // 3、尽量使用for-each循环,遍历集合的首选做法
        int[] arr = {1, 2, 3, 4, 5};
        for (int i : arr) {
            System.out.println(i);
        }

        for(Iterator<Integer> s = Arrays.stream(arr).iterator(); s.hasNext();){
            System.out.println(s.next());
        }

        // 4、尽量使用局部变量
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println(c);
    }
}
