package cn.mediinfo.springdemo.controller.javacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 比较器
 * 常常用于对集合进行排序或查找操作。
 *
 * 语法：int compareTo(Object o)
 *
 * 返回值：
 * 返回值是整型，它是先比较对应字符的大小(ASCII码顺序)，如果第一个字符和参数的第一个字符不等，结束比较，返回他们之间的长度差值，
 * 如果第一个字符和参数的第一个字符相等，则以第二个字符和参数的第二个字符做比较，以此类推,直至比较的字符或被比较的字符有一方结束。
 * 如果参数字符串等于此字符串，则返回值 0；
 * 如果此字符串小于字符串参数，则返回一个小于 0 的值；
 * 如果此字符串大于字符串参数，则返回一个大于 0 的值。
 */
public class comparatorSimple {
    ArrayList<String> list=new ArrayList<>(){
        {
            add("a");
            add("b");
            add("c");
            add("d");
        }
    };

    /**
     * 根据字符的Unicode进行排序
     */
    public void ComparatorExample() {

        Comparator<String> comparator=(s1,s2)->{
            return s1.compareTo(s2);
        };

        list.sort(comparator);
    }

    /**
     * 根据字符的length进行排序
     */
    public void ComparatorExample2() {
        Comparator<String> comparator=(s1,s2)->{
            return Integer.compare(s1.length(), s2.length());
        };

        list.sort(comparator);
    }

    /**
     * 查找、添加、删除
     * 原理差不多，都是先找到index,然后对指定的index进行操作
     */
    public void ComparatorExample3(){
        //方法引用写法，比lamdba更简洁
        Comparator<String> comparator=Comparator.comparing(String::length);

        //等价于lamdba写法
        //Comparator<String> comparator2=Comparator.comparing((s1)->s1.length());

        int index = Collections.binarySearch(list, "a", comparator);
        if (index >= 0) {
            System.out.println("找到了，索引位置：" + index);
        } else {
            System.out.println("未找到");
        }
    }


}
