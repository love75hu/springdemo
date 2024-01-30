package cn.mediinfo.springdemo.controller.javacore2;

/*
 *@title StreamExample
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/20 12:16
 */

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.*;

/**
 * java8 中引入的流式编程。
 * 与集合相比，流提供了一种可以让我们在更高的概念级别上指定计算任务的数据视图。通过使用留，我们可以指明想要什么任务，而不是指明去如何实现它。
 * 我们将操作的调度留给具体实现去解决，例如：我们想要计算某个属性的平均值，我们就可以指定数据源和该属性，然后，流库就可以对计算进行优化，例如使用多线程来计算综合与个数，并将结果合并起来。返回给我们的调用者。
 *
 * 流和集合操作的差异：
 * 1、流不存储元素，流的操作不会改变源对象，相反，他们会返回一个持有结果的新流。例如，filter操作会产生一个包含所有符合谓词的元素的新流。
 * 2、流的操作是尽可能惰性执行的。例如，filter操作不会遍历整个流来查找匹配的元素，而是在遍历过程中，找到了一个匹配的元素就立即返回，这种操作被称为短路。
 *
 * filter、map、flatMap方法
 * 流的转换会产生一个新流，它的元素派生自另一个流中的元素。filter会转换产生一个新流。
 * map 是将指定的函数映射到每个元素上，并且结果包含了函数执行后的结果的流。
 * flatMap方法会将流中的每个值都转换为另一个流，然后把所有的流连接起来成为一个流。
 * 例如：假设有一个包含两个单词的流["Hello","World"]，你想要返回一个包含这两个单词中所有不同字符的列表，可以使用map方法，但是这样会返回一个包含两个流的流，而不是一个流，这时候就可以使用flatMap方法，它会将两个流合并成一个流。
 */
public class StreamExample {
    /**
     * 1.流的创建
     * 1.1 从集合创建
     * 1.2 从数组创建
     * 1.3 从文件创建
     * 1.4 从函数创建
     * 1.5 从流创建
     * 1.6 从字符串创建
     */
    public void example() throws IOException {
        // 1.1 从集合创建
        // 1.1.1 通过Collection.stream()方法用集合创建流
        // 1.1.2 通过Collection.parallelStream()方法用集合创建并行流
        Collection<String> collection = Arrays.asList("a", "b", "c");
        collection.parallelStream().forEach(System.out::println);
        // 1.2 从数组创建
        // 1.2.1 通过Arrays.stream(T[] array)方法用数组创建流
        int[] array = {1, 2, 3, 4, 5};
        Arrays.stream(array).forEach(System.out::println);
        // 1.2.2 通过Stream.of(T... values)方法用数组创建流
        Stream.of(array).forEach(System.out::println);
        Stream.of(array,array,0,1).forEach(System.out::println); // of方法具有可变参数，可以传入多个数组,并且可以指定数组的起始位置和长度，来创建一个流。
        // 1.3 从文件创建
        // 1.3.1 通过Files.lines(Path path)方法用文件创建流
        var context = Files.readString(Path.of("data.txt"));
        List<String> words = List.of(context.split("\\PL+"));
        long count = words.stream().filter(w -> w.length() > 12).count();// 顺序流
        long count2 = words.parallelStream().filter(w -> w.length() > 12).count(); // 并行流
        // 1.3.2 通过BufferedReader.lines()方法用文件创建流
        BufferedReader reader = Files.newBufferedReader(Path.of("data.txt")); //BufferedReader 是一个流，它的每个元素都是文件中的一行。它的优点是，可以按行读取文件，而不是一次读取一个字符或一个字节。
        reader.lines().forEach(System.out::println);
        // 1.4 从函数创建
        // 1.4.1 通过Stream.iterate()方法创建无限流
        //seed:种子值，f:在seed上调用f产生的值、在前一个元素上调用产生的值
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println); // 生成10个偶数,limit方法用于限制流的大小,如果不加limit方法，就会生成一个无限流。
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE)).limit(10).forEach(System.out::println);// 生成10个整数,limit方法用于限制流的大小,如果不加limit方法，就会生成一个无限流。
        // 1.4.2 通过Stream.generate()方法创建无限流
        Stream.generate(Math::random).limit(10).forEach(System.out::println); // 生成10个随机数的,limit方法用于限制流的大小,如果不加limit方法，就会生成一个无限流。
        // 1.4.3 通过Stream.empty()方法创建空流
         var stream= Stream.empty();
        // 1.5 从流创建
        // 1.5.1 通过Stream.map()方法用流创建流
        Stream<String> stringStream =Stream.of(collection).map(n -> n+"处理执行" );
        // 1.5.2 通过Stream.mapMulti方法用流创建流,和map方法类似，但是mapMulti方法可以接收一个Consumer参数，该参数可以对流中的每个元素进行处理。
        Stream<String> stringStream3 =Stream.of(collection).mapMulti((n,consumer)->{
            consumer.accept(n+"处理执行");
        });
        // 1.5.3 通过Stream.flatMap()方法用流创建流
        Stream stringStream2 =Stream.of(collection).flatMap(n -> Stream.of(n+"处理执行"));
        // 1.6 从字符串创建
        // 1.6.1 通过String.chars()方法用字符串创建流
        String str = "abc";
        str.chars().forEach(System.out::println);  //输出的是字符的ASCII码 97，98，99
        // 1.6.2 通过Pattern.splitAsStream()方法用字符串创建流
    }

    /**
     * 抽取子流和组合流
     */
    public void example2(){
         // 抽取子流和组合流
         // 2.1 通过Stream.skip()方法抽取子流
         Stream.generate(Math::random).skip(10).limit(10).forEach(System.out::println); // 生成10个随机数的,limit方法用于限制流的大小,skip方法用于跳过前面的元素，如果不加limit方法，就会生成一个无限流。
         // 2.2 通过Stream.limit()方法抽取子流
         // 2.3 通过Stream.takeWhile()方法抽取子流
        Stream.generate(Math::random).takeWhile(n->n>0.5).forEach(System.out::println); // takeWhile 调用会在谓词为真时返回元素，一旦谓词变为假，就会停止返回元素。
        Stream<String> stringStream =Arrays.asList("1","a","2","3").stream().takeWhile(s->"01234567989".contains(s));//收集字符为数字的元素
         // 2.4 通过Stream.dropWhile()方法抽取子流
        Stream<Double> stringStream2 =Stream.generate(Math::random).dropWhile(n->n>0.5); // dropWhile 调用会在谓词为假时返回元素，一旦谓词变为真，就会停止返回元素。
         // 2.5 通过Stream.concat()方法组合流
        Stream.concat(stringStream,stringStream2).forEach(System.out::println); // concat方法可以将两个流合并成一个流。
    }

    /**
     * 其他的流转换
     */
    public void example3(){
        // 3.1 通过Stream.distinct()方法去除重复元素
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).distinct().forEach(System.out::println); // distinct方法可以去除流中的重复元素。
        // 3.2 通过Stream.sorted()方法排序
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).sorted().forEach(System.out::println); // sorted方法可以对流中的元素进行排序。
        // 3.3 通过Stream.peek()方法查看流中的元素
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).peek(System.out::println).forEach(System.out::println); // peek方法可以查看流中的元素，但是不会消费流。
    }

    /**
     * 简单简约
     */
    public void example4(){
        // 4.1 通过Stream.count()方法计算流中的元素个数
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).count(); // count方法可以计算流中的元素个数。
        // 4.2 通过Stream.max()方法计算流中的最大值
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).max(Integer::compareTo); // max方法可以计算流中的最大值。
        // 4.3 通过Stream.min()方法计算流中的最小值
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).min(Integer::compareTo); // min方法可以计算流中的最小值。
        // 4.4 通过Stream.findFirst()方法查找流中的第一个元素
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).findFirst(); // findFirst方法可以查找流中的第一个元素。
        // 4.5 通过Stream.findAny()方法查找流中的任意元素
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).findAny(); // findAny方法可以查找流中的任意元素。
        // 4.6 通过Stream.allMatch()方法判断流中的元素是否全部满足条件
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).allMatch(n->n>0); // allMatch方法可以判断流中的元素是否全部满足条件。
        // 4.7 通过Stream.anyMatch()方法判断流中的元素是否有满足条件的元素
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).anyMatch(n->n>0); // anyMatch方法可以判断流中的元素是否有满足条件的元素。
        // 4.8 通过Stream.noneMatch()方法判断流中的元素是否没有满足条件的元素
        Stream.of(1,2,3,4,5,6,7,8,9,1,2,3,4,5).noneMatch(n->n>0); // noneMatch方法可以判断流中的元素是否没有满足条件的元素。
    }

    /**
     * Optional类型
     */
    public void example5(){
        //Optional<T>类型对象是一种包装器对象，要么包装了类型T的对象，要么没有包装如何对象。
        // 5.1 通过Optional.empty()方法创建空Optional对象
        // 5.2 通过Optional.of(T value)方法创建Optional对象
        // 5.3 通过Optional.ofNullable(T value)方法创建Optional对象
        Optional<String> optional= Optional.ofNullable(null);
        // 5.4 通过Optional.isPresent()方法判断Optional对象是否为空
        // 5.5 通过Optional.get()方法获取Optional对象中的值
        // 5.6 通过Optional.orElse(T other)方法获取Optional对象中的值，如果为空则返回指定的值
        // 5.7 通过Optional.orElseGet(Supplier<? extends T> other)方法获取Optional对象中的值，如果为空则返回Supplier接口中的值
        // 5.8 通过Optional.orElseThrow()方法获取Optional对象中的值，如果为空则抛出异常
        // 5.9 通过Optional.ifPresent(Consumer<? super T> consumer)方法判断Optional对象中的值是否为空，如果不为空则执行Consumer接口中的方法
        optional.isPresent();

        //将Optional对象转换为流
        Stream<String> stringStream = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString());
        stringStream.map(Optional::ofNullable).forEach(System.out::println);// 将Optional对象转换为流,如果Optional对象为空，则会生成一个空流。
        Stream<String> stringOptional= stringStream.flatMap(s->Optional.of(s).stream());// 将Optional对象转换为流
        Stream<Optional<String>> stringOptional2= stringStream.map(Optional::of);// 将Optional对象转换为流
    }

    /**
     * 收集结果
     */
    public void example6(){
        // 当处理完流后，通常会想要查看其结果，此时可以调用iterator()或forEach()方法，但是这两个方法都是终止操作，会消费流，因此，如果想要再次查看流中的元素，就需要重新创建流。
        // 6.1 通过ForEach方法消费流
        Stream<String> stringStream = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString());
        stringStream.forEach(System.out::println); // forEach方法可以消费流，但是它是终止操作，会消费流，因此，如果想要再次查看流中的元素，就需要重新创建流。

        // 6.2 通过Iterator方法消费流
        stringStream.iterator().forEachRemaining(System.out::println); //iterator方法可以消费流，但是它是终止操作，会消费流，因此，如果想要再次查看流中的元素，就需要重新创建流。

        // 6.3 通过Collectors.toList()方法收集流中的元素
        List<String> stringStream2 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString()).toList();

        // 6.4 通过Collectors.toSet()方法收集流中的元素
        Set<String> stringStream3 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString()).collect(Collectors.toSet());

        // 6.5 通过Collectors.toCollection()方法收集流中的元素
        List<String> stringStream4 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString()).collect(Collectors.toCollection(ArrayList::new));

        // 6.6 通过Collectors.joining()方法收集流中的元素
        String stringStream5 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString()).collect(Collectors.joining(","));

        // 6.7 通过Collectors.counting()方法收集流中的元素
        Long stringStream6 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString()).collect(Collectors.counting());

        // 6.8 通过Collectors.summingInt()方法收集流中的元素
        //如果想要将流的结果简约为总和、数量、平均值、最大值或最小值，可以使用summingInt、summingLong、summingDouble、averagingInt、averagingLong、averagingDouble、maxBy、minBy等方法。
        //实现方式一
        Integer stringStream7 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString()).collect(Collectors.summingInt(Integer::parseInt)); // 将流中的元素转换为Integer类型，然后求和。
        double stringStream8 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString()).collect(Collectors.summingDouble(Double::parseDouble));
        double stringStream9 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).map(n -> n.toString()).collect(Collectors.averagingInt(Integer::parseInt));
        String stringStream10 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).collect(Collectors.maxBy(Comparator.comparing(Object::toString))).get().toString();
        String stringStream11 = Arrays.stream(Arrays.asList("1", "2", "3", "4", "5").toArray()).collect(Collectors.minBy(Comparator.comparing(Object::toString))).get().toString();

        //实现方式二
        LongSummaryStatistics stats = Arrays.stream(new String[]{"1", "2", "3", "4", "5"})
                .mapToLong(Long::parseLong)
                .summaryStatistics();
        long count2 = stats.getCount();
        long sum = stats.getSum();
        double average = stats.getAverage();
        long min = stats.getMin();
        long max = stats.getMax();
    }

    /**
     * 收集到映射表中
     * note:toMap和toConcurrentMap方法可以将流中的元素收集到Map中，toMap方法可以指定key和value的映射关系，toConcurrentMap方法可以并行收集流中的元素到ConcurrentMap中。
     */
    public void example7(){
         // 假设我们有一个Stream<Person>,并且想要将元素映射到一个表中，这样就可以后续通过id来查找人员了。
         // 7.1 通过Collectors.toMap()方法收集流中的元素到Map中
        Stream<Person> personStream = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        personStream.collect(Collectors.toMap(Person::id, Person::name)).forEach((k,v)-> System.out.println(k+":"+v)); // 将流中的元素收集到Map中，key为id，value为name。

        // 7.2 通过Collectors.toConcurrentMap()方法收集流中的元素到ConcurrentMap中,toConcurrentMap和toMap的区别是，toConcurrentMap可以并行收集流中的元素到ConcurrentMap中。
        Stream<Person> personStream2 = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        personStream2.collect(Collectors.toConcurrentMap(Person::id, Person::name)).forEach((k,v)-> System.out.println(k+":"+v)); // 将流中的元素收集到ConcurrentMap中，key为id，value为name。

        // 7.3 通过Collectors.toMap()方法收集流中的元素到Map中，如果key重复，则抛出异常
        Stream<Person> personStream3 = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        personStream3.collect(Collectors.toMap(Person::id, Person::name,(k1,k2)->k1)).forEach((k,v)-> System.out.println(k+":"+v)); // 将流中的元素收集到Map中，key为id，value为name，如果key重复，则抛出异常。

        // 7.4 通过Collectors.toMap()方法收集流中的元素到Map中，如果key重复，则使用自定义的合并函数
        Stream<Person> personStream4 = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        personStream4.collect(Collectors.toMap(Person::id, Person::name,(k1,k2)->k1)).forEach((k,v)-> System.out.println(k+":"+v)); // 将流中的元素收集到Map中，key为id，value为name，如果key重复，则使用自定义的合并函数。

        Stream<Locale> locals=Stream.of(Locale.getAvailableLocales());
        Map<String,String> languageNames=  locals.collect(Collectors.toMap(Locale::getDisplayCountry,Locale::getDisplayLanguage,(k1,k2)->k1));

        // 7.5 通过Collectors.toMap()方法收集流中的元素到Map中，如果key重复，则使用自定义的合并函数，并且指定Map的类型
        Stream<Person> personStream5 = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        TreeMap<Integer,String> treeMap = personStream5.collect(Collectors.toMap(Person::id, Person::name,(k1,k2)->k1, TreeMap::new)); // 将流中的元素收集到Map中，key为id，value为name，如果key重复，则使用自定义的合并函数，并且指定Map的类型。

    }

    /**
     * 群组和区分
     */
    public void example8(){
       // 8.1 通过Collectors.groupingBy()方法将流中的元素分组
        Stream<Person> personStream = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        Map<Integer, List<Person>> map = personStream.collect(Collectors.groupingBy(Person::id)); // 将流中的元素分组，key为id，value为Person对象的集合。

        // 8.2 通过Collectors.groupingBy()方法将流中的元素分组，并且指定Map的类型
        Stream<Person> personStream2 = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        TreeMap<Integer, List<Person>> treeMap = personStream2.collect(Collectors.groupingBy(Person::id, TreeMap::new, Collectors.toList())); // 将流中的元素分组，key为id，value为Person对象的集合，并且指定Map的类型。

        // 8.3 通过Collectors.groupingBy()方法将流中的元素分组，并且指定Map的类型和Set的类型
        Stream<Person> personStream3 = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        TreeMap<Integer, Set<Person>> treeMap2 = personStream3.collect(Collectors.groupingBy(Person::id, TreeMap::new, Collectors.toSet())); // 将流中的元素分组，key为id，value为Person对象的集合，并且指定Map的类型和Set的类型。

        // 8.4 通过Collectors.partitioningBy()方法将流中的元素分区
        Stream<Person> personStream4 = Stream.of(new Person("张三", 1), new Person("李四", 2), new Person("王五", 3));
        Map<Boolean, List<Person>> map2 = personStream4.collect(Collectors.partitioningBy(n -> n.id() > 2)); // 将流中的元素分区，key为true或false，value为Person对象的集合。

    }

    /**
     * 下游收集器 collect
     * groupingBy()方法会产生一个映射表，它的每一个值都是一个列表，如果想要对这些列表进行进一步的操作，就可以使用下游收集器。
     */
    public void example9(){
        // 9.1 通过Collectors.toSet()方法将流中的元素收集到Set中
        Map<String,Set<Locale>> countryLocalSet= Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry,Collectors.toSet()));

        // 9.2 通过Collectors.counting()方法计算流中的元素个数
        Map<String,Long> countryLocalCount= Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry,Collectors.counting()));

        // 9.3 通过Collectors.maxBy()方法计算流中的最大值
        Map<String,Optional<Locale>> countryLocalMax= Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry,Collectors.maxBy(Comparator.comparing(Locale::getDisplayName))));

        // 9.4 filtering()方法可以对流中的元素进行过滤
        Map<String,Set<Locale>> countryLocalSet2= Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry,Collectors.filtering(l->l.getLanguage().equals("en"),Collectors.toSet())));

        // 9.5 通过teeing()方法将两个收集器的结果合并,当你在下游一个流中计算多个值时候，它会非常有用。假设我们要收集城市名并计算他们的平均人口。
        List<City> cities=List.of(new City("北京",100),new City("上海",200),new City("广州",300),new City("深圳",400));
        Pair<List<String>,Double> result = cities.stream().collect(Collectors.teeing(Collectors.mapping(City::name, Collectors.toList()), Collectors.averagingInt(City::population), Pair::new));
    }

    /**
     * 简约操作 reduce
     * reduce 方法可以将流中的元素反复结合起来，得到一个值。它和简约简单操作相同的特性，都是将流中的元素反复结合起来，得到一个值。
     * 在实际开发中，一般不会用到reduce方法，因为它的功能可以通过简约操作来实现，但是在某些情况下，reduce方法会更加方便。
     */
    public void example10(){
            // 10.1 通过Stream.reduce()方法简约流中的元素
            Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
            Optional<Integer> optional = integerStream.reduce((n1, n2) -> n1 + n2); // reduce方法可以将流中的元素反复结合起来，得到一个值。
            System.out.println(optional.get());

            // 10.2 通过Stream.reduce()方法简约流中的元素，并且指定初始值
            Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4, 5);
            Integer optional2 = integerStream2.reduce(0, (n1, n2) -> n1 + n2); // reduce方法可以将流中的元素反复结合起来，得到一个值，并且指定初始值。
            System.out.println(optional2);

            // 10.3 通过Stream.reduce()方法简约流中的元素，并且指定初始值和合并函数
            Stream<Integer> integerStream3 = Stream.of(1, 2, 3, 4, 5);
            Integer optional3 = integerStream3.reduce(0, (n1, n2) -> n1 + n2, (n1, n2) -> n1 + n2); // reduce方法可以将流中的元素反复结合起来，得到一个值，并且指定初始值和合并函数。
            System.out.println(optional3);
    }

    /**
     * 基本类型流
     * 通常我们都是将整数集收集到Stream<Integer>中，尽管这样做很方便，但是它也有一些缺点，因为Integer是一个类，所以它会占用更多的内存，而且它的操作也会比较慢。
     * 常见的基本类型：double、int、long、short、byte、char、float、boolean
     * 流库提供了三种基本类型流：IntStream、LongStream、DoubleStream。用来直接存储基本类型值，而不是装箱到一个对象中。
     * 如果需要存储short、byte、char、boolean 值，可以使用IntStream，因为它们都可以转换为int值。
     * 如果需要存储float 值，可以使用DoubleStream，因为它们都可以转换为double值。
     */
    public void example11(){
        //11.1创建IntStream-使用IntStream.of
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        IntStream intStream2 = IntStream.range(1, 5); // range方法可以创建一个从1到5的IntStream，但是不包含5。
        IntStream intStream3 = IntStream.rangeClosed(1, 5); // rangeClosed方法可以创建一个从1到5的IntStream，包含5。
        IntStream intStream4 = IntStream.generate(() -> 1); // generate方法可以创建一个无限流，它的元素都是1。
        IntStream intStream5 = IntStream.iterate(1, n -> n + 1); // iterate方法可以创建一个无限流，它的元素都是从1开始，每次加1。

        //11.2创建IntStream-使用Arrays.stream
        int[] array = {1, 2, 3, 4, 5};
        IntStream intStream6 = Arrays.stream(array);

        //11.3创建LongStream
        LongStream longStream = LongStream.of(1, 2, 3, 4, 5);
        LongStream longStream2 = LongStream.range(1, 5); // range方法可以创建一个从1到5的LongStream，但是不包含5。
        LongStream longStream3 = LongStream.rangeClosed(1, 5); // rangeClosed方法可以创建一个从1到5的LongStream，包含5。
        LongStream longStream4 = LongStream.generate(() -> 1); // generate方法可以创建一个无限流，它的元素都是1。
        LongStream longStream5 = LongStream.iterate(1, n -> n + 1); // iterate方法可以创建一个无限流，它的元素都是从1开始，每次加1。

        //11.4创建DoubleStream
        DoubleStream doubleStream = DoubleStream.of(1, 2, 3, 4, 5);
        DoubleStream doubleStream2 = DoubleStream.generate(() -> 1); // generate方法可以创建一个无限流，它的元素都是1。
        DoubleStream doubleStream3 = DoubleStream.iterate(1, n -> n + 1); // iterate方法可以创建一个无限流，它的元素都是从1开始，每次加1。

        //将基本类型流转换为对象流
    }

    /**
     * 并行流
     * 流使并行处理操作变得容易，只需要调用parallel方法就可以将流转换为并行流，如果想要将并行流转换为顺序流，可以调用sequential方法。
     */
    public void example12() throws IOException {
       // 12.1 通过Stream.parallel()方法将流转换为并行流
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        integerStream.parallel().forEach(System.out::println); // parallel方法可以将流转换为并行流。

        // 12.2 通过Stream.sequential()方法将并行流转换为顺序流
        Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4, 5);
        integerStream2.parallel().sequential().forEach(System.out::println); // parallel方法可以将流转换为并行流，sequential方法可以将并行流转换为顺序流。

        // 12.3 通过Stream.isParallel()方法判断流是否为并行流
        Stream<Integer> integerStream3 = Stream.of(1, 2, 3, 4, 5);
        integerStream3.isParallel(); // isParallel方法可以判断流是否为并行流。

        // 12.4 计算所有单词中长度小于12的单词
        var context = Files.readString(Path.of("data.txt"));
        List<String> words = List.of(context.split("\\PL+"));
        long count = words.stream().filter(w -> w.length() > 12).count();// 顺序流
        long count2 = words.parallelStream().filter(w -> w.length() > 12).count(); // 并行流
        Map<Integer,Long> count3= words.parallelStream().filter(w -> w.length() > 12).collect(Collectors.groupingBy(String::length,Collectors.counting())); // 并行流

    }

    public record Person(String name, int id) {

    }

    //Pair 是一个在 Java 中的记录（record），这是从 Java 14 开始引入的预览功能，并在 Java 16 中正式确定。记录是 Java 中的一种特殊类，用于模拟只包含最终字段的简单数据聚合，这是创建仅保存数据且没有任何行为的类的简洁方式。
    //Pair 是一个泛型记录，它包含两个字段：类型为 S 的 first 和类型为 T 的 second。这可以用来保存任何两种类型的一对数据。
    public record Pair<S,T> (S first,T second){

    }

    public record City(String name, int population) {

    }
}
