package cn.mediinfo.springdemo.controller.effctivedemo.exampl1;

/*
 *@title CreateAndDestroyObjects5_优先考虑依赖注入来引用资源CreateAndDestroyObjects6_避免创建不必要的对象
 *@description
 *@author thj
 *@create 2024-02-05
 */
public class CreateAndDestroyObjects6_避免创建不必要的对象 {
    /**
     * 有时候，为了提高性能，需要避免创建不必要的对象，例如：在循环中创建对象，可以将对象的创建放在循环外部，或者使用静态工厂方法，或者使用重用对象的方式。
     * 或者在做一些表达式解析、编译代码的时候 可以使用 LinkHashMap、WeakHashMap 等缓存集合，或者使用 java.lang.ref包中的类，如SoftReference、WeakReference和PhantomReference。 将结果缓存起来。
     */
    public static void main(String[] args) {
        //错误示例
        String s = new String("bikini");
        //正确示例
        String s1 = "bikini";

        //错误示例
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        //正确示例（优先使用基本类型）
        long sum1 = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum1 += i;
        }

        //错误示例
        Long sum2 = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum2 += i;
        }
        //正确示例（使用静态工厂方法）
        Long sum3 = Long.valueOf(0L);
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum3 += i;
        }
    }
}
