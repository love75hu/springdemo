package cn.mediinfo.springdemo.controller.effctivedemo.example2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 *@title ClassesAndInterfaces4_复合优先于继承
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class ClassesAndInterfaces4_复合优先于继承 {
    /**
     * 复合优先于继承
     * 继承是一种强大的技术，但它并不总是合适的。在许多情况下，使用复合（组合）来代替继承是更好的选择。
     * 1.继承打破了封装性，子类依赖于超类的实现细节，超类的实现细节发生变化时，子类的行为可能会受到影响。
     * 2.继承是一种强耦合，子类和超类之间的关系是静态的，不能在运行时改变。
     * 3.继承是一种单继承，子类只能继承一个超类，这限制了子类的灵活性。
     * 4.继承是一种强制性的，子类必须继承超类的所有方法，即使子类并不需要这些方法。
     * 5.继承是一种不透明的，子类的行为依赖于超类的实现细节，这使得子类的行为更难预测。
     * 6.继承是一种不可扩展的，超类的实现细节是固定的，不能在运行时改变。
     *
     * 复合（组合）是一种更好的选择，它是一种更松散的关系，子类和超类之间的关系是动态的，可以在运行时改变。
     * 1.复合是一种松散的关系，子类和超类之间的关系是动态的，可以在运行时改变。
     * 2.复合是一种灵活的，子类可以包含多个超类，这增加了子类的灵活性。
     * 3.复合是一种可选的，子类可以选择性地包含超类的方法，这增加了子类的灵活性。
     * 4.复合是一种透明的，子类的行为不依赖于超类的实现细节，这使得子类的行为更容易预测。
     * 5.复合是一种可扩展的，子类可以包含多个超类，这增加了子类的灵活性。
     *
     */
}

/**
 * 通过继承来增强HashSet的功能
 * 该方案不能很好地工作，因为HashSet的add方法是通过addAll方法来实现的，所以addCount的值会被计算两次。
 */
class InstrumentedSet<E> extends HashSet<E> {
    private int addCount = 0;

    public InstrumentedSet() {
    }

    public InstrumentedSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

//通过复合来增强HashSet的功能
//该方案可以很好地工作，因为ForwardingSet类只是简单地将Set接口的方法转发给s，而不会修改这些方法的行为。

/**
 * 转发类
 */
class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;

    public ForwardingSet(Set<E> s) {
        this.s = s;
    }


    @Override
    public int size() {
        return s.size();
    }

    @Override
    public boolean isEmpty() {
        return s.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return s.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return s.iterator();
    }

    @Override
    public Object[] toArray() {
        return s.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return s.toArray(ts);
    }

    @Override
    public boolean add(E e) {
        return s.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return s.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return s.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return s.addAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return s.retainAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return s.removeAll(collection);
    }

    @Override
    public void clear() {
        s.clear();
    }
}

/**
 * 通过复合来增强HashSet的功能
 */
class InstrumentedSet2<E> extends ForwardingSet<E>{
    private int addCount = 0;
    public InstrumentedSet2(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c){
        addCount+=c.size();
        return super.addAll(c);
    }

    public int getAddCount(){
        return this.addCount;
    }
}


