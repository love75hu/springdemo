package cn.mediinfo.springdemo.controller.javacore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * java 基础一些推荐写法
 */
public class javaSimple {

    /**
     * 字符串构建高效写法
     *
     * @return
     */
    public String characterString() {
        StringBuilder SB = new StringBuilder();
        SB.append("a");
        SB.append("b");
        return SB.toString();
    }

    /**
     * 字符串拼接
     *
     * @return
     */
    public String StringJoin() {
        return String.join("a", "b");
    }

    /**
     * 文本块
     *
     * @return
     */
    public String TextBlock() {
        return """
                1111
                2222
                3333
                """;
    }

    /**
     * break与yield 都是终止执行，但是 yield 必须生成一个值
     *
     * @return
     */
    public int SwitchDemo() {
        return switch (3) {
            case 1 -> {
                yield 1;
            }
            case 2 -> 22;
            default -> 3;
        };
    }

    /**
     * HashSet初始化必须声明初始大小
     */
    public void HasSetDemo() {
        HashSet<String> hs = new HashSet(16);

        //循环实例
        for (String i : hs) {

        }

        //如果需要循环操作集合里面的对象，请使用迭代器
        var iterator = hs.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
        }
    }

    /**
     * 数组拷贝
     */
    public void ArrayCopyDemo() {
        ArrayList<Integer> arrayList = new ArrayList<>(16);
        arrayList.add(1);

        var NewArrayList = Arrays.copyOf(arrayList.toArray(), arrayList.size());
    }

    /**
     * {@code DateDemo()}日期使用<strong>LocalDate</strong>类操作
     *
     * @since java 17
     * 备注指定管理的方法 @see #ArrayCopyDemo()
     * 备注指定管理的方法 @see cn.mediinfo.springdemo.controller.javacore#ArrayCopyDemo()
     * 备注指定超链接 @see <a href="http://baidu.com">http://baidu.com</a>
     */
    public void DateDemo() {
        LocalDate.now().getDayOfYear();
    }

    /**
     * 模式匹配
     */
    public void instantofDemo() {
        double a = 1F;
        if (Double.class.isInstance(a)) {

        }
    }

    /**
     * 使用hashcode来比较两个对象是否相等
     */
    public void hashCodeDemo() {
        int[] a = {1, 2, 3};
        ArrayList<Integer> b = new ArrayList<>();
        b.add(1);
        b.add(2);
        b.add(3);
        if (a.hashCode() == b.hashCode()) {

        }
    }

    /**
     * 可变参数
     *
     * @param args
     */
    public void DaymicArg(String... args) {
        for (String arg : args
        ) {
            System.out.printf("参数值为：%s", arg);
        }

        //通过反射获取类名
        args.getClass().getName();
    }

}
