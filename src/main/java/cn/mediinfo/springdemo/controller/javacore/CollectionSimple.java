package cn.mediinfo.springdemo.controller.javacore;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 集合
 * collection是所有集合的基本接口,所有的集合都有迭代器 Iterator
 * java不同的集合使用适用场景不一样，请根据实际情况进行合理的选择：
 * <p>
 * ArrayList：动态数组实现，提供了快速随机访问元素的能力。适用于需要频繁访问和修改元素的场景。
 * <p>
 * ArrayDeque:
 * LinkedList：双向链表实现，提供了高效的插入和删除操作。适用于需要频繁插入和删除元素的场景。
 * <p>
 * HashSet：基于哈希表实现的无序集合，使用哈希算法存储元素，提供了快速的查找和插入操作。适用于需要快速查找和去重的场景。
 * <p>
 * TreeSet：基于红黑树实现的有序集合，按照元素的自然顺序或自定义比较器的顺序进行排序。适用于需要有序存储和遍历元素的场景。
 * <p>
 * HashMap：基于哈希表实现的无序键值对映射，提供了快速的查找和插入操作。适用于需要根据键快速查找值的场景。
 * <p>
 * TreeMap：基于红黑树实现的有序键值对映射，按照键的自然顺序或自定义比较器的顺序进行排序。适用于需要根据键的顺序遍历和查找值的场景。
 * <p>
 * LinkedHashMap：基于哈希表和双向链表实现的有序键值对映射，保持了元素插入的顺序。适用于需要保持插入顺序的场景。
 * <p>
 * PriorityQueue：基于堆实现的优先队列，每次出队操作返回优先级最高的元素。适用于需要按照优先级处理元素的场景。
 * <p>
 * ConcurrentHashMap：线程安全的哈希表实现的无序键值对映射，提供了高并发的访问和修改操作。适用于多线程环境下的高并发场景。
 * <p>
 * 以上仅是一些常见的集合类，Java还提供了其他诸如LinkedHashSet、WeakHashMap、IdentityHashMap等特殊用途的集合类。
 */
public class CollectionSimple {
    /**
     * 集合迭代器的使用
     * 使用迭代器的好处是：可以做执行的集合进行数据操作，例如：remove
     */
    public void IteratorExample() {
        List<String> list = Arrays.asList("a", "b", "c");

        ArrayList<String> arrayList = new ArrayList<>() {
            {
                add("a");
                add("b");
            }
        };

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            //必须先.next()才能.remove()，因为两个方法存在依赖性；
            iterator.next();
            iterator.remove();
        }
    }

    /**
     * 集合泛型操作
     *
     * @param c
     * @param o
     * @param <T>
     * @return
     */
    public <T extends String> T Example2(Collection<T> c, Object o) {
        AtomicReference<T> res = null;
        c.forEach((value) -> {
            if (value.equals(o)) {
                res.set(value);
            }
        });
        return res.get();
    }

    /**
     * 集合排序
     */
    public void Example3() {
        ArrayList<String> arrayList = new ArrayList<>() {
            {
                add("a");
                add("b");
            }
        };

        //通过字符的ascall码进行排序
        arrayList.sort(String::compareTo);

        //通过长度排序
        arrayList.sort((a, b) -> {
            return Integer.compare(a.length(), b.length());
        });

        //多条件排序
        arrayList.sort(Comparator.comparingInt(String::length)
                      .thenComparing(String::compareTo));
    }

    /**
     * 自定义算法，求集合最大元素
     * @param c
     * @return
     * @param <T>
     */
    public <T extends Comparable> T max(Collection<T> c) {
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }

        Iterator<T> iterator=c.iterator();
        var largest= iterator.next();
        while (iterator.hasNext()){
           T next= (T) iterator.next();
           if (largest.compareTo(next)<0){
               largest=next;
           }
        }
        return largest;
    }

    /**
     * @description set集合遍历
     * @author thj
     * @throws  * @return String 
     * @time 2023/12/22 14:31
     */
    public String toString() {
        Set<Map.Entry<String, Object>> entrySet=new HashSet<>();
        Iterator<Map.Entry<String, Object>> entries = entrySet.iterator();
        StringBuilder sb = new StringBuilder("{");
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue().toString());
            if (entries.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
