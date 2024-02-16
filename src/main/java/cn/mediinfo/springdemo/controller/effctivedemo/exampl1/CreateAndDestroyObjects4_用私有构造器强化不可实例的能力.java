package cn.mediinfo.springdemo.controller.effctivedemo.exampl1;

/*
 *@title CreateAndDestroyObjects4_用私有构造器强化不可实例的能力
 *@description
 *@author thj
 *@create 2024-02-05
 */
public class CreateAndDestroyObjects4_用私有构造器强化不可实例的能力 {
    /**
     * 有时候只需要编写包含静态方法和静态域的类，例如：java.lang.Math 或者 java.util.Arrays，这样的类是不需要被实例化的，但是如果不小心编写了一个公有的构造器，那么该类就可以被实例化，为了保证该类不被实例化，只需要将该类的构造器私有化即可。
     *
     */

    //声明私有构造方法
    private CreateAndDestroyObjects4_用私有构造器强化不可实例的能力() {

    }
}
