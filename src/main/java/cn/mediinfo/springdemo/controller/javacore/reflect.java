package cn.mediinfo.springdemo.controller.javacore;

import cn.mediinfo.springdemo.model.ClientscopeEntity;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

/**
 * java 反射的使用
 */
public class reflect {
    /**
     * 反射获取类名
     */
    public void getNames() throws NoSuchMethodException {
        reflect.class.getName();

    }

    /**
     * 根据类名称创建一个实例  Class.getName()
     */
    public void getInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String ClassName = "cn.mediinfo.springdemo.controller.javacore.reflect";
        Class cl = Class.forName(ClassName);
        Object o = cl.getConstructor().newInstance();
    }

    /**
     * 根据className返回一个Class对象
     */
    public void getClassForClassName() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> classs = Class.forName(reflect.class.getName());
        var Instance = (reflect) classs.getConstructor().newInstance();
        if (Instance instanceof reflect && Instance != null) {
            Instance.getClassForClassName();
        }
    }

    /**
     * 通过反射获取reflect.class同目录下的文件资源，当然这个路径也可以改变为其他的
     */
    public void getResource() throws IOException {
        Class cl = reflect.class;
        var url = cl.getResource("a.gif");
        var icon = new ImageIcon(url);

        //如果没有找到资源，则返回null
        InputStream stram = cl.getResourceAsStream("a.txt");
        var about = new String(stram.readAllBytes(), StandardCharsets.UTF_8);
    }

//利用反射的分析能力检查类的结构
//reflet有三个类：field、Method、Constructor，每个类都有一个getName()方法。还有一个getModifiers()方法，返回一个整数
// 用不同的0/1位来表示使用的修饰符，比如：public和static
//也可以用Modifier类的静态方法getModifiers()来获取返回的这个整数。
// 可以使用Modifier类的isPublic、isPrivate、isFinal来判断一个方法或构造器是public\private\final


    /**
     * 使用反射获取运行时的对象（获取实例对象的属性值）
     */
    public void getInstanceFiledValue() throws NoSuchFieldException, IllegalAccessException {
        var entity = ClientscopeEntity.builder().id("1111");
        Class cl = entity.getClass();
        Field f = cl.getField("id");
        String id = (String) f.get(entity);

        //重新设置值
        f.set(entity, "2222222");

        //todo:id在类是private权限，这个示例是不可能成功设置值的，不过可以调用setAccessible覆盖访问控制
        //检查调用者是否可以通过field\method\Constructor访问obj，对于静态方法或者字段传null,对于构造器也传null
        if (f.canAccess(entity)) {
            //为这个可以访问对象设置可访问标志，如果拒绝则返回false
            f.trySetAccessible();
        }
    }

    //使用反射copy泛型数组
    public Object ArrayCopyOf(Object a, int newlength) {
//      var a=new ArrayList<String>();
//      a.add("1");
//      a.add("2");

        Class cl = a.getClass();
        if(!cl.isArray()){
            return null;
        }

        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);

        //返回指定长度的数组
        Object newArray = Array.newInstance(componentType, newlength);

        //将值copy到新的数组
        System.arraycopy(a,0,newArray,0,Math.min(length,newlength));
        return newArray;
    }


}
