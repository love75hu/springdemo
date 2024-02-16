package cn.mediinfo.springdemo.controller.effctivedemo.exampl1;

import java.io.Serializable;

/*
 *@title CreateAndDestroyObjects3_用私有构造器或者枚举类型强化Singletion属性
 *@description
 *@author thj
 *@create 2024-02-05
 */
public class CreateAndDestroyObjects3_用私有构造器或者枚举类型强化Singletion属性 {
    /**
     Singleton 通常指仅仅被实例化一次的类。这种类通常表示无状态对象，例如函数对象或者工具类。为了使一个类成为Singleton，需要使它的构造器私有，并且提供一个静态的工厂方法来返回唯一的实例。
     */
}

/**
 * 实现方式一：
 * 实现 Singleton 的第一种方法是公有的静态成员是 final 的，这种方法简单，但是它的主要缺点是无法延迟初始化，即使该实例从未被使用过，它也会在类加载的时候被创建。
 */
class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public void leaveTheBuilding() {
    }
}

/**
 * 实现方式二：
 * 公有的成员是个静态工厂方法（1、好处在于可以清楚的表名这个类是一个 Singleton；2、我们可以随时改变该类是否是Singleton的想法，而不影响调用者；）
 * 注意：如果需要设置为可序列化的，只需要让该类实现 Serializable 接口，并且声明所有实例域为瞬时（transient）,并提供一个 readResolve 方法。
 */
class Elvis2 implements Serializable {
    private static final Elvis2 INSTANCE = new Elvis2();

    private Elvis2() {
    }

    public static Elvis2 getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

/**
 * 实现方式三：
 * 声明一个包含单个元素的枚举类型(该方法与方法二相似，但是更加简洁，无偿提供序列化机制，绝对防止多次实例化，即使是在复杂的序列化或者反射攻击的情况下。但是并未广泛采用，但是是单个元素枚举类型成为 Singleton 实现的最佳方法)
 * 注意：如果 Singleton 必须扩展一个超类，而不是扩展 Enum 的话，那么该方法就不能使用（虽然可以声明枚举方法去实现接口）。
 */
 enum Elvis3 {
  INSTANCE;

  public void leaveTheBuilding() { }
}
