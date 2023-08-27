package cn.mediinfo.springdemo.controller.javacore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * 常用函数接口
 * Runnable :表示可以在<Strore>一个线程中运行的任务</Strore>，没有输入参数和返回值。常用于启动新线程或者在当前线程中异步执行任务。
 *
 * Consumer : 表示一个供应商，它提供一个结果。没有输入参数，但可以产生一个结果。常用于延迟加载或者生成一些初始值。
 * BiConsumer : 表示一个消费者，它表示接受两个输入参数并且不返回任何结果的操作。其中，第一个参数表示输入的类型，第二个参数表示输入的类型。
 *
 * Supplier : 表示一个消费者，它接受一个参数并且不返回结果。常用于遍历、处理集合、回调函数、参数传递等场景。
 *
 * Function : 表示一个函数，它接受一个参数并且产生一个结果。常用于数据转换、映射、链式调用、(Optional )可选值处理等场景。
 * BiFunction : 它表示接受两个输入参数并返回一个结果的操作。(使用比较少，一般一个参数里面是一个对象就行了)
 * UnaryOperator:它表示接受一个参数并返回与参数类型相同的结果的操作。换句话说，它是一种特殊类型的 Function，其输入和输出类型相同。
 * BinaryOperator :与 UnaryOperator 类似，BinaryOperator 也是一种特殊类型的 Function 接口，但它接受两个参数并返回与参数类型相同的结果。
 *
 * Predicate:表示一个断言，它接受一个参数并返回一个布尔值。常用于过滤、筛选等场景。
 * BiPredicate:它表示接受两个输入参数并返回一个布尔值的操作。(使用比较少，一般一个参数里面是一个对象就行了)
 */
public class lamdbaSimple {
    public static Supplier<List<String>> getInventorySupplier() {
        Supplier<List<String>> inventorySupplier = () -> {
            return new ArrayList<String>(16) {
                {
                    add("鸡蛋");
                    add("鸭蛋");
                    add("狗蛋");
                }
            };
        };

        return inventorySupplier;
    }

    /**
     * 常用函数接口:Runnable
     * Runnable：表示可以在<Strore>一个线程中运行的任务</Strore>，没有输入参数和返回值。常用于启动新线程或者在当前线程中异步执行任务。
     * 使用的场景：当涉及到需要在后台执行长时间运行的任务时，Runnable 接口非常有用。一个具体的使用场景可以是在网站或应用程序中处理文件上传。
     * 场景举例：假设你正在开发一个在线云存储服务，用户可以通过你的网站或应用程序上传文件到服务器。为了提供良好的用户体验，你希望能够在后台处理文件上传任务，以便用户可以继续浏览和使用你的网站或应用程序而不被阻塞。
     */
    public void RunnableExample() {
        //注意;Runnable是一个接口，所以我们可以实现Runnable并自定义业务，然后执行
        Runnable run = () -> {
            System.out.println("执行辣!");
            // 执行文件上传逻辑
            //uploadFile(file);
        };

        //通过创建一个新的线程来执行
        Thread thread1 = new Thread(run);
        thread1.start();
        thread1.getState();
    }

    /**
     * 常用函数接口:Supplier
     * Supplier：表示一个供应商，它提供一个结果。没有输入参数，但可以产生一个结果。常用于延迟加载或者生成一些初始值。
     * 使用的场景：当你需要提供数据给其他方法或类时，可以使用 Supplier。它允许你将数据提供者与数据使用者解耦，并通过 get() 方法获取数据。例如，在编写测试用例时，你可以使用 Supplier 提供测试数据。
     * 场景举例：这种设计模式可以使你的代码更具可扩展性和灵活性。例如，你可以根据需要动态更新产品库存信息，只需更新 products 列表，并再次调用 showProductAvailability() 方法即可。
     */
    public void SupplierExample() {

        //获取蛋的种类清单
        //有点：多个地方需要获取清单的时候无需更改代码，只需要更改实现类就好了。
        //使用 Supplier 接口可以带来更好的代码组织和可维护性。当你的代码变得更加复杂，并且有多个地方需要获取库存信息时，使用 Supplier 接口可以提供一种一致的方式来获取数据，同时也减少了重复的代码。
        //代码定义的使用场景更加明确、清晰
        Supplier<List<String>> list = getInventorySupplier();
        list.get();
    }

    /**
     * 常用函数接口:Consumer
     * Supplier：表示一个消费者，它接受一个参数并且不返回结果。常用于遍历、处理集合、回调函数、参数传递等场景。
     * 使用的场景：
     * 场景举例：default void forEach(Consumer<? super T> action) {}
     */
    public void ConsumerExample() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello, " + name));

        //调用示例
        ConsumerExample2(names, (a) -> {
            System.out.println("a的值是：" + a);
            //执行业务逻辑
        });
    }

    /**
     * <? super String> 表示 action 参数可以是String或者String的超类
     *
     * @param list
     * @param actions 支持同时传入多个 action
     */
    public void ConsumerExample2(List<String> list, Consumer<? super String>... actions) {
        for (var action : actions) {
            action.accept("1");
        }
    }


    /**
     * 常用函数接口:BiConsumer(使用比较少，一般一个参数里面是一个对象就行了)
     * BiConsumer：表示一个消费者，它表示接受两个输入参数并且不返回任何结果的操作。其中，第一个参数表示输入的类型，第二个参数表示输入的类型。
     * 使用的场景：
     * 场景举例：default void forEach(Consumer<? super T> action) {}
     */
    public void BiConsumerExample() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        BiConsumerExampl2(names,(t1,t2)->{
            System.out.println("t1和t2组合的值是：" + t1+t2);
        });
    }

    public void BiConsumerExampl2(List<String> list, BiConsumer<String,String> action) {
            action.accept("prefix","1");
    }

    /**
     * 常用函数接口:Function
     * Function:表示一个函数，它接受一个参数并且产生一个结果。常用于数据转换、映射、链式调用、(Optional )可选值处理等场景。
     * BiFunction:它表示接受两个输入参数并返回一个结果的操作。(使用比较少，一般一个参数里面是一个对象就行了)
     * 使用的场景：
     * 场景举例：将Integer转换为String
     */
    public void FunctionExample() {
        //单个调用1
        Integer a = 1;
        String value = IntToString(a, (i) -> {
            return i.toString();
        });

        //链式调用2
       // <T,R> T入参 R返回值
        Function<Integer, Integer> func=(i)->i;
        Function<Integer, Integer> func2=(i)->i+100;
        Function<Integer, Integer> func3=func.andThen(func2);
        String value2 = IntToString(a, (i) -> {
            return i.toString();
        });
    }

    private String IntToString(Integer t, Function<Integer, String> func) {

        String value = func.apply(t);
        String newValue = value + "根据用户传入的表达式执行后，执行我的业务";
        return newValue;
    }


    /**
     * 常用函数接口:UnaryOperator,一种特殊的Function
     * UnaryOperator:它表示接受一个参数并返回与参数类型相同的结果的操作。换句话说，它是一种特殊类型的 Function，其输入和输出类型相同。
     * BinaryOperator :与 UnaryOperator 类似，BinaryOperator 也是一种特殊类型的 Function 接口，但它接受两个参数并返回与参数类型相同的结果。
     * 使用的场景：
     * 场景举例：将Integer计算结果后返回Integer
     */
    public void UnaryOperatorExample() {
        IntToInt(1,(i)->{
            return i*2;
        });
    }
    private Integer IntToInt(Integer t, UnaryOperator<Integer> func) {

        Integer value = func.apply(t);
        return  value+10;
    }


    /**
     * 常用函数接口:Predicate
     * Predicate:表示一个断言，它接受一个参数并返回一个布尔值。常用于过滤、筛选等场景。
     * BiPredicate:它表示接受两个输入参数并返回一个布尔值的操作。(使用比较少，一般一个参数里面是一个对象就行了)
     * 使用的场景：
     * 场景举例：集合过滤
     */
    public void PredicateExample() {
        //自定义Predicate写法
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Predicate<Integer> pre=(i)->{
             return i>5;
        };
        numbers.stream().filter(pre);

        //原生写法
        numbers.stream().filter((i)->{
           return i>5;
        });
    }
}
