package cn.mediinfo.springdemo.controller.effctivedemo.example3;

/*
 *@title 泛型1_请不要使用原生态类型
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class 泛型1_请不要使用原生态类型 {

    /**
     * 原生态类型（编译器不会检查到出错，实际运行才会发现）
     *     List list=new ArrayList<>();
     *     List<Object> list2=new ArrayList<>();
     *
     * 泛型术语
     * -----------------------------------------------------
     * 术语                  范例
     * 实际类型参数            String
     * 参数化类型             List<String>
     * 泛型                  List<E>
     * 形式类型参数            E
     * 无限制通配符类型        List<?>
     * 有限制通配符类型        List<? extends Number>
     * 泛型方法              static <E> List<E> asList(E[] a)
     * 类型令牌              String.class
     * 递归类型限定           <T extends Comparable<T>>
     *
     */
}
