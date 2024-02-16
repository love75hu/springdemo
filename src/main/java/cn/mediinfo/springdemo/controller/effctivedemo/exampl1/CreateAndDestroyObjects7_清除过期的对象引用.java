package cn.mediinfo.springdemo.controller.effctivedemo.exampl1;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/*
 *@title CreateAndDestroyObjects7_清除过期的对象引用
 *@description
 *@author thj
 *@create 2024-02-05
 */
public class CreateAndDestroyObjects7_清除过期的对象引用 {
    // 用缓存的集合 - WeakHashMap，是Java集合框架中的一部分，它存储键值对，其中键是弱引用。当没有强引用指向键时，键值对将被垃圾收集器自动删除。
    // 但是，这个缓存的值是不会自动过期的，我们可以由一个后台线程（ScheduledThreadPoolExecutor）来清除过期的对象引用。
    WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();


    // 适用缓存的集合 - LinkedHashMap
    // 但是，这个缓存的值是不会自动过期的，我们可以在添加新的条目时，删除最老的条目。例如：限制缓存的大小，当缓存达到限制时，删除最老的条目。
    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

    // 适用缓存的集合 - java.lang.ref包中的类
    // java.lang.ref包中的类，如SoftReference、WeakReference和PhantomReference，可以用于实现更复杂的缓存。这些引用类型允许你在对象不再需要时，由垃圾收集器自动删除它们，这对于实现内存敏感的缓存非常有用。

    //内存泄漏排查工具Heap(Heap Profiler)
}
