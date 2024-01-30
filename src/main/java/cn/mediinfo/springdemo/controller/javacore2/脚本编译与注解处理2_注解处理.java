package cn.mediinfo.springdemo.controller.javacore2;

import lombok.NonNull;

import java.util.List;

/*
 *@title 脚本编译与注解处理2_注册处理
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/28 11:09
 */
public class 脚本编译与注解处理2_注解处理 {
    /**
     * 注解是哪些插入到源代码中使用其他工具可以进行处理的标签。这些工具可以在源码的层次上进行操作，或者可以处理编译器在其中放置了注解的类文件。
     * 注解不会改变程序的编译方式。java编译器对于包含注解和不包含注解的代码会生成相同的虚拟机指令。
     * 为了能够收益于注解，你需要一个处理工具，然后向你的处理工具可以理解的代码中插入注解，之后运用该处理工具处理代码。
     * 注解的使用范围还是很广泛的，并且这种广泛性让人咋一看会觉得杂乱无章。下面是关于注解的一些可能的语法：
     * 1、附属文件的自动生成，例如：部署描述符或者bean信息类。
     * 2、测试、日志、事务语义等代码的自动生成。
     */

    /**
     * 注解类型用法
     */
    void example(@NonNull String userId) {
        // @NonNull String userId 断言 userId 不为 null
        // @NonNull 是 Checker Framework 提供的注解，它是一个用于 Java 的静态分析框架，可以用来检查 Java 程序中的类型错误。 https://checkerframework.org/
        // 通过使用  Checker Framework 这个框架，就可以在程序中断言，例如：某个参数不能为空，或者某个String 包含一个正则表达式。然后，静态分析工具将检查在既定的源代码中这些断言是否有效。
    }

    void example2(List<@NonNull String> userIds) {
        //List<@NonNull String> userIds 断言 userIds 中的所有元素都不为 null
    }

    void example3() {
        @NonNull String text;
    }

    /**
     * 类型用法注解-数组中的使用
     */
    void example4() {
        @NonNull String[][] words = new @NonNull String[10][]; //word[i][j]不为null

        String @NonNull [][] words2 = new String[10][]; //word不为null

        String[] @NonNull [] words3 = new String[10][]; //word[i]不为null
    }

    public class exampleClass {
        public @NonNull String text2;
        @NonNull String text3;
        //可以将注解方法private、static、final这样的修饰符后面或者前面。习惯（但不是必须）的做法。
        private @NonNull String text;
        @NonNull
        private String text4;
    }

    /**
     * 标准注解
     * java.lang \ java.lang.annotation \ javax.annotation包中包含了大量的注解接口。其中四个是元注解，用于描述注解接口的行为属性。其他的是规则接口，可以用他们来注解你的源代码中的项。
     * 注解接口                应用场合                   目的
     * ------------------------------------------------------------------
     * @Deprecated 全部                      将项目标记为过时
     * @SuppressWarnings 除了包和注解之外的所有情况     阻止特定的编译器警告
     * @SafeVarargs 方法和构造器                断言 varargs 参数可以安全使用，
     * @Override 方法                      检查该方法是否覆盖了某一个超类
     * @Serial 方法                      指明该字段可以被序列化
     * @FunctionalInterface 接口                      将接口标注为只有一个抽象方法的函数式接口
     * @Generated 全部                      将项标记为由某个工具生成的源代码
     * @Target 注解                      指明这个注解可以应用到哪些项上
     * @Retention 注解                      指明这个注解可以保留多长时间
     * @Documented 注解                      指明这个注解应该被包含在被注解项的文档中
     * @Inherited 注解                      指明当这个注解应用于一个类的时候，能够自动被它的子类继承
     * @Repeatable 注解                      指明这个注解可以在同一个声明上使用多次
     */
    public class point {

        /**
         * 标准注解-用于编译的注解
         * @Deprecated 当你使用了一个过时的方法、类或者字段的时候，编译器会发出警告。如果你确定这个警告是不必要的，可以使用@SuppressWarning注解来抑制这个警告。
         * @SuppressWarnings 注解可以用来抑制编译器警告。它有一个可选的参数，用来指明你想要抑制哪种警告。例如，如果你想要抑制所有的警告，可以使用@SuppressWarning("all")。
         * @Generated 注解可以用来标记由工具生成的代码。例如，如果你使用了一个由IDL（接口定义语言）生成的类，那么这个类就应该被标记为@Generated。
         */

        /**
         * 标准注解-元注解
         * @Target 注解用来指明这个注解可以应用于哪些项上。例如，如果你想要编写一个注解，它可以应用于类和方法上，那么你就应该使用@Target({ElementType.TYPE, ElementType.METHOD})。
         * 元素类型              注解适用场合
         * --------------------------------------------------
         * ANNOTATION_TYPE     注解类型声明
         * CONSTRUCTOR         构造器声明
         * FIELD               字段声明（包括枚举常量）
         * LOCAL_VARIABLE      局部变量声明
         * METHOD              方法声明
         * PACKAGE             包声明
         * PARAMETER           参数声明
         * TYPE                类、接口（包括注解类型）或者枚举声明
         * TYPE_PARAMETER      类型参数声明（Java SE 8）
         * TYPE_USE            类型使用声明（Java SE 8）
         * --------------------------------------------------
         * 如果注解没有使用 @Target  声明，则可以应用于所有的项上面。
         *
         * @Retention 注解用来指明这个注解可以保留多长时间。例如，如果你想要编写一个注解，它可以保留到运行时，那么你就应该使用@Retention(RetentionPolicy.RUNTIME)。
         * 保留策略              注解适用场合
         * SOURCE              不包括在类文件中的注解，因此无法通过反射机制读取它们，这个策略适用于编译器需要的注解
         * CLASS               包括在类文件中的注解，但是在运行时会被丢弃，因为虚拟机不需要将它们载入
         * RUNTIME             包括在类文件中的注解，在运行时被载入，因此可以通过反射机制读取它们
         *
         * @Documented 这个使用会被 javadoc 或其他类似的工具检测到并包含在生成的文档中。。例如，如果你想要编写一个注解，它可以被包含在被注解项的文档中，那么你就应该使用@Documented。
         *
         * @Inherited 只能应用于对类的注解，如果一个类具有继承注解，那么它的子类都具有同样的注解。例如你定义一个注解 @Test，该注解使用了@Inherited ,并且 class user使用了注解@test,那么它的子类也会具有 @test 注解。
         *
         *
         */
    }

    /**
     * 源码级注解处理
     * 注解的另一种用法是自动处理源代码以及产生更多源代码、配置文件、脚本以及其他任何我们需要的生成的东西。
     */
    public class point2 {

        /**
         * 源码级注解处理-注解处理器
         * 注解处理器已经被内置到Java编译器中，在编译过程中，你可以通过以下命令来调用注解处理器：javac -processor processorclass file.java
         * 编译器会自动定位源文件中的注解，每个注解处理器会依次执行，并得到他感兴趣的注解。如果某个注解处理器创建了一个新的源文件，那么上述过程将重复执行。如果某次执行没有产生新的源文件， 那么就编译所有源文件。
         * （注解处理器只能产生新的文件，不能修改源文件）
         */

        /**
         * 源码级注解处理-使用注解来生成源码
         */
    }
}
