package cn.mediinfo.springdemo.controller.javacore;

import java.util.Arrays;
import java.util.function.*;

/**
 * 基本类型的函数式接口,int\boolean\double等
 *
 * 整数类型：
 *
 * int：IntPredicate, IntConsumer, IntSupplier, IntFunction<R>, IntUnaryOperator, IntBinaryOperator
 * long：LongPredicate, LongConsumer, LongSupplier, LongFunction<R>, LongUnaryOperator, LongBinaryOperator
 * short：ShortPredicate, ShortConsumer, ShortSupplier, ShortFunction<R>, ShortUnaryOperator, ShortBinaryOperator
 * byte：BytePredicate, ByteConsumer, ByteSupplier, ByteFunction<R>, ByteUnaryOperator, ByteBinaryOperator
 * 浮点数类型：
 *
 * float：FloatPredicate, FloatConsumer, FloatSupplier, FloatFunction<R>, FloatUnaryOperator, FloatBinaryOperator
 * double：DoublePredicate, DoubleConsumer, DoubleSupplier, DoubleFunction<R>, DoubleUnaryOperator, DoubleBinaryOperator
 * 布尔类型：
 *
 * boolean：BooleanSupplier, BooleanConsumer, BooleanFunction<R>, BooleanUnaryOperator
 * 字符类型：
 *
 * char：IntPredicate（可用于判断字符范围），IntConsumer（可用于打印字符），IntSupplier（可用于生成字符），IntFunction<R>（可用于转换字符），IntUnaryOperator（可用于操作字符）
 *
 * IntPredicate：接受一个 int 类型的参数，返回一个 boolean 类型的结果。
 * IntConsumer：接受一个 int 类型的参数，无返回值。
 * IntSupplier：无参数，返回一个 int 类型的结果。
 * IntFunction<R>：接受一个 int 类型的参数，返回一个指定类型的结果。
 * IntUnaryOperator：接受一个 int 类型的参数，返回一个 int 类型的结果。
 */
public class lamdbaSimple2 {
    /**
     * IntPredicate：接受一个 int 类型的参数，返回一个 boolean 类型的结果。
     * 真实场景:对数组进行过滤
     */
    public void IntPredicateSimple() {
        //简单示例
        IntPredicate predicate = (num) -> {
            return num > 0;
        };

        var bool = IntPredicateSimple(1, predicate);

        //真实场景，对数组进行过滤
        int[] numbers = {5, 11, 3, 15, 8, 9, 12};
        Arrays.stream(numbers)
                .filter((i) -> {
                    return i > 10;
                })
                .toArray();
    }

    public boolean IntPredicateSimple(int value, IntPredicate predicate) {
        return predicate.test(value);
    }

    /**
     * IntConsumer：接受一个 int 类型的参数，无返回值。
     */
    public void IntConsumerSimple() {
        //简单示例
        IntConsumer intConsumer = (i) -> {
            System.out.println(i);
        };
        IntConsumerSimple2(1, intConsumer);

        //真实场景，对数组进行过滤
    }

    public void IntConsumerSimple2(int i, IntConsumer intConsumer) {
        intConsumer.accept(i);
    }


    /**
     * IntSupplier：无参数，返回一个 int 类型的结果。
     */
    public void IntSupplierSimple() {
        //简单示例
        IntSupplier intSupplier = () -> {
            return 10;
        };

        var res = IntSupplierSimple2(intSupplier);
    }

    public int IntSupplierSimple2(IntSupplier intSupplier) {
        return intSupplier.getAsInt() + 5;
    }


    /**
     * IntFunction<R>：接受一个 int 类型的参数，返回一个指定类型的结果。
     */
    public void IntFunctionSimple() {

        IntFunction<String> intFunction = (i) -> {
            return String.valueOf(i);
        };

        var res = IntFunctionSimple2(1, intFunction);
    }

    public String IntFunctionSimple2(int i, IntFunction<String> intFunction) {
        return intFunction.apply(i) + "自定义业务";
    }


    /**
     * IntUnaryOperator：接受一个 int 类型的参数，返回一个 int 类型的结果。
     */
    public void IntUnaryOperatorSimple() {
        IntUnaryOperator intUnaryOperator = (i) -> {
            return i;
        };

        var res = IntUnaryOperatorSimple2(1, intUnaryOperator);
    }

    public int IntUnaryOperatorSimple2(int i, IntUnaryOperator intUnaryOperator) {
        return intUnaryOperator.applyAsInt(i) + 100;
    }
}
