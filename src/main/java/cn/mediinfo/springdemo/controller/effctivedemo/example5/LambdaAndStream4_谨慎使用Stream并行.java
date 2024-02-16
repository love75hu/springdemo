package cn.mediinfo.springdemo.controller.effctivedemo.example5;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

/*
 *@title LambdaAndStream4_谨慎使用Stream并行
 *@description
 *@author thj
 *@create 2024-02-16
 */
@RestController
@RequestMapping("api/v1/LambdaAndStream")
@RequiredArgsConstructor
public class LambdaAndStream4_谨慎使用Stream并行 {
    private static final BigInteger TWO = BigInteger.valueOf(2);
    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    /**
     * 使用并行打印素数
     * ---------------------------------
     * 本来期望通过 parallel 并行来提高性能，但是实际上并没有提高性能，反而程序并未打印出素数；
     * 为什么会出现这种情况呢？
     * 简单的说：stream 类库不知道如何并行这个pipeline 以及如何探索失败；即便在最佳环境下，如果源头是来自 Stream.iterate 或者是使用了中间操作 limit，那么并行操作也不会提升性能；
     * 通过 parallel 并行来提高性能最好是通过 ArrayList、 HashMap 、HashSet 、ConcurrentHashMap 实例，数组，int范围和long范围等数据结构来实现；
     * 这些数据结构的共性是都有可以被精准、轻松的分成任意大小的子范围，使并行线程更加的轻松。
     * 这些数据结构的另一个重要特性是，在进行顺序处理时，他们提供了优异的引用局部性：序列化的元素引用一起保存在内存中，被哪些引用访问到的对象在内存中可能不是一个紧挨着一个，这降低了引用局部性。
     * 引用局部性对并行处理至关重要，没有它线程就会出现闲置，具有最佳引用局部性的数据结构是基本类型数组（int[]、chart[]、boolean[]、byte[]、short[]、long[]、float[]double[]），因为它数据本身是相邻地保存在内存中的。
     *
     * 并行最佳做法
     * ---------------------------------
     * 1、用一个Stream 的 reduce 方法来合并结果；
     * 2、预先打包像min、max、sum、average、count等聚合操作；
     * 3、如anyMatch、allMatch、noneMatch、findFirst、findAny等搜索操作；
     *
     * 注意
     * ---------------------------------
     * 尽量不要使用并行流，除非你有充分的理由，因为并行流的性能并不总是比顺序流的性能更好。
     */
    @GetMapping("/example")
    public void example(){
        primes().map(p ->TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                //.parallel() //本来期望通过并行来提高性能，但是实际上并没有提高性能，反而程序并未打印出素数
                .forEach(System.out::println);

    }

    @GetMapping("/example2")
    public void example2(){
        ArrayList<Integer> list = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            list.add(rand.nextInt());
        }

        //list.parallelStream().forEach(System.out::println);
        list.forEach(System.out::println);
    }
}
