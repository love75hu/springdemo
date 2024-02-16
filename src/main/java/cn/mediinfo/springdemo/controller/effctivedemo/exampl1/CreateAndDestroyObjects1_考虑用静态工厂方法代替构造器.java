package cn.mediinfo.springdemo.controller.effctivedemo.exampl1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

/*
 *@title 创建和销毁对象
 *@description
 *@author thj
 *@create 2024-02-05
 */
@RestController
@Slf4j
@RequestMapping("api/v1/CreateAndDestroyObjects")
@RequiredArgsConstructor
public class CreateAndDestroyObjects1_考虑用静态工厂方法代替构造器 {
    /**
     * 1、考虑用静态工厂方法代替构造器
     *
     * 在面向对象编程中，使用静态工厂方法和构造器都是创建对象的方式。下面我会简单介绍一下它们各自的适用场景。
     * 静态工厂方法：
     * 当类需要多种构造方式时，使用静态工厂方法可以避免重复的构造器。
     * 当创建对象的过程比较复杂或者需要根据不同的参数返回不同类型的对象时，可以使用静态工厂方法来隐藏实现细节。
     * 当类需要对外隐藏构造器时，可以使用静态工厂方法将构造器私有化并提供公共的访问方式。
     * 构造器方式：
     * 当类的属性比较少且简单时，使用构造器方式可以更加方便地创建对象。
     * 当类需要强制某些属性必须被初始化时，可以在构造器中进行强制要求。
     * 当需要创建子类对象时，构造器可以被重载以满足子类的特殊需求。
     *
     * 静态方法的优点：
     * 1-1-1、静态工厂方法有名称，可以更好的描述返回的对象，例如：Boolean.valueOf(true)。
     * 1-1-2、静态工厂方法可以返回原返回类型的任何子类型的对象。
     * 1-1-3、静态工厂方法可以不用每次调用的时候都创建一个新对象，可以重复利用已经创建好的对象。
     * 1-1-4、方法返回的对象所属类，在编写该静态工厂方法的时候可以不存在。
     */

    /**
     * 1、静态工厂方法官方使用场景-Boolean
     */
    @GetMapping("/example")
    public void example() throws IOException {
        Boolean b1 = Boolean.valueOf(true);
        Boolean b2 = Boolean.valueOf(false);
        log.info(b1.toString()); // true
        log.info(b2.toString()); // false

        //静态方法惯用名称 from（类型转换方法，它只有单个参数，返回该类型的一个相对实例）
        Date date = Date.from(Instant.now());

        //静态方法惯用名称 of （聚合方法，带有多个参数，返回该类型的一个实例,并把他们合并起来）
        Set<Rank> set = EnumSet.of(Rank.JUNIOR, Rank.MIDDLE, Rank.SENIOR);

        //静态方法惯用名称 valueOf（比from和of更繁琐的一种替代办法）
        Boolean b3 = Boolean.valueOf("true");

        //静态方法惯用名称 instance 或 getInstance（返回的实例是通过方法的参数来描述的，但是不能说是类型转换方法）
        StackWalker luck=StackWalker.getInstance(EnumSet.of(StackWalker.Option.RETAIN_CLASS_REFERENCE));

        //静态方法惯用名称 create 或 newInstance（像instance或getInstance一样，但是确保每次调用都返回一个新的实例）
        Object newArrays= Array.newInstance(String.class,10);

        //静态方法惯用名称 getType（像getInstance一样，但是在工厂方法处于不同的类中的时候使用）
        FileStore fs = Files.getFileStore(Paths.get("/"));

        //静态方法惯用名称 newType（像newInstance一样，但是在工厂方法处于不同的类中的时候使用）
        BufferedReader br = Files.newBufferedReader(Paths.get(""));

        //静态方法惯用名称 type（getType和newType的简单版本）
        List<Object> list = Collections.list(new StringTokenizer(""));

        /**
         * 静态工厂方法和共有构造器都有各自的用途，我们需要理解它各自的长处，静态工厂经常更加合适，因此切忌一开始反应就是提供共有构造器，而不考虑静态工厂。
         */
    }

    /**
     * 101、静态工厂方法-编写示例
     */
    @GetMapping("/example2")
    public void example2() {
        User u1 = User.getByName("张三");
        User u2 = User.getByName("李四");
        log.info(u1.name+System.identityHashCode(u1)); // 张三+hash码，如果每次请求一样，说明对象没有重复创建
        log.info(u2.name+System.identityHashCode(u1)); // 李四+hash码，如果每次请求一样，说明对象没有重复创建
    }


    /**
     * 静态工厂方法示例，通过静态工厂方法代替构造器
     */
    static class User{
        private String name;
        private int age;
        public static final User zhangSanUser = new User("张三");
        public static final User liSiUser = new User("李四");

        public  User(String name) {
           this.name = name;
        }

        static User getByName(String name){
            return name.equals("张三") ? zhangSanUser : liSiUser;
        }
    }

    public enum Rank{
        JUNIOR, MIDDLE, SENIOR
    }
}
