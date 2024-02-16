package cn.mediinfo.springdemo.controller.effctivedemo.example6;

/*
 *@title Method4_慎用可变参数
 *@description
 *@author thj
 *@create 2024-02-16
 */
public class Method4_慎用可变参数 {

    /**
     * 可变参数示例
     * 问题：需要一个或者多个参数的时候，可以使用可变参数，但是要慎用，因为可变参数会使方法的参数列表变得模糊，不容易理解。
     */
    static int sum(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    /**
     * 解决方案一：需要一个或者多个参数的时候，定义为两个参数，一个是必传参数，一个是可变参数
     */
    static int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs) {
            if (arg < min) {
                min = arg;
            }
        }
        return min;
    }

    /**
     * 解决方案二：95%以上可能值使用3个及以下参数，这个时候可以定义多个重载方法，最后定义一个可变参数的方法
     */
    public void min(){}
    public void min(int a1){}
    public void min(int a1,int a2){}
    public void min(int a1,int a2,int a3){}
    public void min(int a1,int a2,int a3,int ... rest){}
}
