package cn.mediinfo.springdemo.controller.javacore;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream是Java 8新增的接口，Stream可以认为是一个高级版本的 Iterator。
 * <p>
 * Stream跟Iterator的差别
 * 无存储	Stream是基于数据源的对象，它本身不存储数据元素，而是通过管道将数据源的元素传递给操作。
 * 函数式编程	函数式编程：对Stream的任何修改都不会修改背后的数据源，比如对Stream执行filter操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新的Stream。
 * 延迟执行	Stream的操作由零个或多个中间操作（intermediate operation）和一个结束操作（terminal operation）两部分组成。只有执行了结束操作，Stream定义的中间操作才会依次执行，这就是Stream的延迟特性。
 * 可消费性	Stream只能被“消费”一次，一旦遍历过就会失效。就像容器的迭代器那样，想要再次遍历必须重新生成一个新的Stream。
 *
 * @link https://blog.csdn.net/yueerba126/article/details/131472361
 */
public class StreamSimple {
    List<String> list = new ArrayList<>() {
        {
            add("1");
            add("2");
            add("3");
        }
    };

    /**
     * stream() //顺序流
     * parallelStream() //并行流
     */
    public void example() {
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();
    }

    /**
     * 将数据组转换为流
     */
    public void example2() {
        Integer[] SS = new Integer[16];
        Stream<Integer> stream = Arrays.stream(SS);
    }

    /**
     * 静态方法
     */
    public void example3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);  // 不使用limit会无限生成
        Stream<Double> stream3 = Stream.generate(Math::random).limit(2); // 不使用limit会无限生成
    }

    /**
     * 将字符串分割为流
     */
    public void example4() {
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }

    /**
     * 规约操作
     * 规约操作又被称作折叠操作，是通过某个连接动作将所有元素汇总成一个汇总结果的过程。元素求和、求最大值或最小值、求出元素总个数、
     * 将所有元素转换成一个列表或集合，都属于规约操作。_Stream_类库有两个通用的规约操作reduce()和collect()，
     * 也有一些为简化书写而设计的专用规约操作，比如sum()、max()、min()、count()等。
     */
    public void example5() {
        //找出最长字符串
        Stream<String> stream = Stream.of("I", "Love", "you");
        var longString = stream.reduce((s1, s2) -> {
            return s1.length() >= s2.length() ? s1 : s2;
        });

        //求字符串长度和
        var s = stream.reduce("初始值", (str1, str2) -> str1 + str2);
        s.length();
    }

    /**
     * 收集操作
     * collect：接收一个Collector实例，将流中元素收集成另外一个数据结构。Collector<T, A, R> 是一个接口，有以下5个抽象方法：
     * <p>
     * Supplier supplier()	创建一个结果容器A
     * BiConsumer<A, T> accumulator()	消费型接口，第一个参数为容器A，第二个参数为流中元素T
     * BinaryOperator combiner()	函数接口，该参数的作用跟上一个方法(reduce)中的combiner参数一样，将并行流中各 个子进程的运行结果(accumulator函数操作后的容器A)进行合并
     * Function<A, R> finisher()	函数式接口，参数为：容器A，返回类型为：collect方法最终想要的结果R
     * Set characteristics()	返回一个不可变的Set集合，用来表明该Collector的特征。有以下三个特征CONCURRENT：表示此收集器支持并发。UNORDERED：表示该收集操作不会保留流中元素原有的顺序。IDENTITY_FINISH：表示finisher参数只是标识而已，可忽略。
     */
    public void example6() {
        Stream<String> stream = Stream.of("I", "Love", "you", "too");
        //收集流中的数据到集合中
        //1.收集流中的数据到 list
        List<String> list = stream.collect(Collectors.toList());

        //2.收集流中的数据到 set
        Set<String> collect = stream.collect(Collectors.toSet());

        //3.最大值
        stream.collect(Collectors.maxBy(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() >= o2.length() ? o1.length() : o2.length();
            }
        }));

        //4.总个数
        stream.collect(Collectors.counting());

        //5.分组操作-按照字符串分组
        Map<String, List<String>> group = stream.collect(Collectors.groupingBy(s1 -> s1));

        //6.分组操作-按照字符长度分组
        Map<String, List<String>> group2 = stream.collect(Collectors.groupingBy(s1 -> {
            if (s1.length() == 3) {
                return "长度3";
            } else {
                return "长度其他";
            }
        }));

        //7.分组操作-按照年龄分组,规约求每组的最大值(规约：reducing)
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 52, 56),
                new Student("杨颖", 56, 88),
                new Student("迪丽热巴", 56, 99),
                new Student("柳岩", 52, 53)
        );

        Map<Integer, Optional<Student>> reducingMap = studentStream.collect(
                Collectors.groupingBy(Student::getAge,
                        Collectors.reducing(
                                BinaryOperator.maxBy(
                                        Comparator.comparingInt(Student::getScore)
                                )
                        )
                )
        );

        //多级分组
        //8.先根据年龄分组,然后再根据成绩分组
        //分析:第一个Collectors.groupingBy() 使用的是(年龄+成绩)两个维度分组,所以使用两个参数 groupingBy()方法
        //    第二个Collectors.groupingBy() 就是用成绩分组,使用一个参数 groupingBy() 方法
        Map<Integer, Map<Integer, Map<String, List<Student>>>> map = studentStream.collect(Collectors.groupingBy(str -> str.getAge(), Collectors.groupingBy(str -> str.getScore(), Collectors.groupingBy((student) -> {
            if (student.getScore() >= 60) {
                return "及格";
            } else {
                return "不及格";
            }
        }))));


        //9拼接操作
        //无参:join()
        String joinStr1 = studentStream.map(s -> s.getName()).collect(Collectors.joining());
        System.out.println(joinStr1);
        //一个参数:joining(CharSequence delimiter)
        String joinStr2 = studentStream.map(s -> s.getName()).collect(Collectors.joining(","));
        //三个参数:joining(CharSequence delimiter, CharSequence prefix,CharSequence suffix)
        String joinStr3 = studentStream.map(s -> s.getName()).collect(Collectors.joining("—","^_^",">_<"));
    }

    @Data
    @AllArgsConstructor
    class Student {
        private String name;
        private int age;

        private int score;
    }
}
