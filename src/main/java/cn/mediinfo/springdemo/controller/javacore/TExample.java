package cn.mediinfo.springdemo.controller.javacore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 泛型
 */
public class TExample {
    /**
     * 通配符泛型示例
     */
    public void  example(){
        //  List<T extends Serializable> list2 = new ArrayList<String>();
        //  通配符示例，接收Serializable及子类的所有类型，但是不能修改值,赋值，因为通配符类型系统不知道真正的类型。
        //但是可以作为参数接收Serializable及子类型
        List<? extends Serializable> list = new ArrayList<>();
        //list.add("1");  //因为?无法确定类型，所以无法为list添加值，这个是<? extends Serializable>本身所决定的


        List<String> list2 = new ArrayList<>();
        list2.add("1");
    }

    /**
     * 泛型参数的示例
     * @param value
     * @return
     */
    public Serializable example2(Supplier<? extends Serializable> value) {
        return value.get();
    }


    /**
     * 获取泛型classl类（反射）
     * @param value
     * @return
     */
    public Class<? extends Serializable> example3(Supplier<? extends Serializable> value) {
        //获得泛型类型变量
        value.getClass().getTypeParameters();
        //获得这个类型所声明超类的泛型类型
        value.getClass().getGenericSuperclass();
        //获得这个泛型所声明接口的泛型类型
        value.getClass().getGenericInterfaces();

        return (Class<? extends Serializable>) value.getClass();
    }

    /**
     * 泛型同时继承多个父类的示例
     * @param value
     * @return
     * @param <T>
     */
    public <T extends String & Serializable> T example4(Supplier<T> value){
        return value.get();
    }
}
