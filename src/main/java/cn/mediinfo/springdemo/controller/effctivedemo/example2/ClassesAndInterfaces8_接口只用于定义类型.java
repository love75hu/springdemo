package cn.mediinfo.springdemo.controller.effctivedemo.example2;

/*
 *@title ClassesAndInterfaces8_接口只用于定义类型
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class ClassesAndInterfaces8_接口只用于定义类型 {
    /**
     *
     * 当类实现接口时，接口就充当了类型，它定义了该类型的行为。因此，接口只用于定义类型。为了其他目的而定义接口是不合适的。
     */

}

//不良设计
interface ConstantInterface {
    static final int CONSTANT = 100;  //不要这样做，这个属于实现细节，不应该暴露给接口的使用者。应该将常量用一个专用的类来导出。
    void method();
}

//良好设计（将常量用一个专用的类来导出）
class ConstantClass {
    public static final int CONSTANT = 100;
}
