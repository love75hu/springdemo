package cn.mediinfo.springdemo.controller.effctivedemo.exampl1;

import java.lang.ref.Cleaner;

/*
 *@title CreateAndDestroyObjects8_避免使用终结方法和清除方法
 *@description
 *@author thj
 *@create 2024-02-05
 */
public class CreateAndDestroyObjects8_避免使用终结方法和清除方法 {

    /**
     * 如果类的对象封装的资源（例如文件、或者线程）确实需要终止，需要让类实现 AutoCloseable 接口，并且提供一个 close 方法。
     * 这个方法将在try-with-resources语句结束时自动被调用，以确保资源被正确清理。
     */

    public void example() throws Exception {
        try(Room room = new Room(7)){
            System.out.println("Goodbye");
        }
    }
}

/**
 *  演示如何使用AutoCloseable接口和Cleaner类来确保资源在不再需要时被正确清理，而不是依赖于终结方法或清除方法。
 */
class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    // 房间的状态，由cleaner调用
    private final State state;
    //房间的清理器，用来执行关闭时的清理动作
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        this.state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Room is close");
        cleanable.clean();
    }

    private static class State implements Runnable {
        int numJunkPiles; // 这个房间里的垃圾堆数量

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // 由 close 方法或清理器调用
        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }
}
