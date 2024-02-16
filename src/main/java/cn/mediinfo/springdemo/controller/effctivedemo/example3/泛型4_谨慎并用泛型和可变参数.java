package cn.mediinfo.springdemo.controller.effctivedemo.example3;

import java.util.List;

/*
 *@title 泛型34_谨慎并用泛型和可变参数
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class 泛型4_谨慎并用泛型和可变参数 {
    void example(List<String> ... lists){

    }

    <T> void example2(List<? extends T> ... lists){

    }
}
