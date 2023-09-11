package cn.mediinfo.springdemo.controller.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSimple {
    /**
     * 设置中断状态的意义和用途在于：
     * <p>
     * 通知线程中断：可以通过设置中断状态来向线程发出中断信号，告知线程应该停止执行。当线程处于阻塞状态（如等待、睡眠、输入输出阻塞等）时，中断状态能够打破线程的阻塞状态，使其提前结束阻塞并继续执行后续代码。
     * <p>
     * 安全地终止线程：通过设置中断状态，线程可以根据自身的逻辑来判断是否终止执行。线程可以在合适的时机检查中断状态，并主动结束自己的执行，从而实现线程的安全终止。
     * <p>
     * 协作与线程间通信：中断状态也可以用于线程间的协作与通信。在某些情况下，可以使用中断状态来作为线程间传递信号的一种方式。一个线程可以设置另一个线程的中断状态，从而触发特定的行为或操作。
     * <p>
     * 需要注意的是，Thread.currentThread().interrupt() 方法仅仅是设置中断状态，并不会直接中断线程的执行。具体的中断处理需要线程自身负责，在适当的时机检查中断状态，并决定如何处理中断。通常情况下，线程应该在收到中断信号后，进行清理操作并 gracefully 地终止自己的执行。
     */
    public void Example() {
        //设置当前线程的中断状态为"中断"。这个方法会将当前线程的中断标志位设为true。
        //方式一：
        Thread.interrupted();
        //方式二：
        Thread.currentThread().interrupt();

        try {
            //如果线程被阻塞，就无法检测中断状态，在一个被sleep或者wait调用阻塞的线程数调用interrupt，会被InterruptedException异常中断
            Thread.sleep(50);
            //检测线程是否设置了中断状态
            while (!Thread.currentThread().isInterrupted()) {
                //如果线程没有设置中断状态
            }
        } catch (InterruptedException exception) {
            System.out.println("获取线程中断状态异常：" + exception);
        }
    }


    /**
     * 守护线程
     */
    public void DeamonExample() {
        Thread thread = new Thread();
        thread.setDaemon(true);
        //设置线程名称
        thread.setName("线程名");
        thread.start();
    }


    /**
     * 设置未捕获异常处理器来处理未被捕获的线程异常
     * 线程的run方法不能抛出任何检查异常，但是非检查异常会导致线程终止，在终止之前，会将异常传递到一个用于处理未捕获异常的处理器UncaughtExceptionHandler
     */
    public void UncaughtExceptionExample() {
        //lamdba写法
        Thread thread = new Thread(() -> {
            System.out.println("执行线程任务！");
        });

        //匿名方法写法
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行线程任务！");
            }
        });

        thread.setName("thread1");

        //通过匿名方法实现自定义UncaughtExceptionHandler，以便于处理未捕获异常
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("执行线程:" + t.getName() + "异常：" + e.getMessage());
            }
        });

        //设置线程优先级
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    //-------------线程同步----------------

    /**
     * 线程同步方式一：SynchronizedThread
     * 使用用同步方法实现、用同步代码块实现来实现排队
     */
    public void SynchronizedThreadExample() {
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行线程任务！");
                //执行加锁的具体业务逻辑
                var s = new SynchronizedThread();
                s.save(100);
                s.save1(100);
            }
        });
    }

    /**
     * 线程同步方式二： ReentrantLock
     * ReentrantLock类是可重入、互斥、实现了Lock接口的锁，它与使用synchronized方法具有相同的基本行为和语义，并且扩展了其能力。
     * synchronized 与 Lock 的对比
     * <p>
     * ReentrantLock是显示锁,手动开启和关闭锁，别忘记关闭锁；
     * <p>
     * synchronized 是隐式锁，出了作用域自动释放;
     * <p>
     * ReentrantLock只有代码块锁,synchronized 有代码块锁和方法锁;
     * <p>
     * 使用 ReentrantLock锁，JVM 将花费较少的时间来调度线程，线程更好,并且具有更好的扩展性（提供更多的子类）;
     * <p>
     * 优先使用顺序：
     * <p>
     * ReentrantLock> synchronized 同步代码块> synchronized 同步方法
     */
    public void ReentrantLockExample() {
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行线程任务！");
                //执行加锁的具体业务逻辑
                var r = new ReentrantLockThread();
                r.save(100);
            }
        });
    }

    /**
     * 线程同步方式三：使用原子变量实现线程同步
     * 为了完成线程同步，我们将使用原子变量(Atomic***开头的)来实现。
     * <p>
     * 比如典型代表：AtomicInteger类存在于java.util.concurrent.atomic中,该类表示支持原子操作的整数，采用getAndIncrement方法以原子方法将当前的值递加。
     */
    public void AtomicIntegerExample() {
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行线程任务！");
                //执行加锁的具体业务逻辑
                AtomicIntegerThread r = new AtomicIntegerThread();
                r.save(100);
            }
        });
    }


    /**
     * 线程同步方式四：使用 ThreadLocal实现线程同步
     * 如果使用ThreadLocal管理变量，则每一个使用该变量的线程都获得该变量的副本，副本之间相互独立，这样每一个线程都可以随意修改自己的变量副本，而不会对其他线程产生影响，从而实现线程同步。
     */
    public void ThreadLocalExample() {
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行线程任务！");
                //执行加锁的具体业务逻辑
                ThreadLocalThread r = new ThreadLocalThread();
                r.save(100);
            }
        });
    }

}

class ThreadLocalThread {
    //创建一个本地线程变量
    ThreadLocal<Integer> t = new ThreadLocal<Integer>();

    public void save(int money) {
        //将值存入本地线程变量
        t.set(money);
        System.out.println("保存金额：" + money);
    }
}

class AtomicIntegerThread {
    //声明一个原子变量
    private AtomicInteger account = new AtomicInteger(100);

    public void save(int money) {
        //利用原子变量设置值
        account.addAndGet(money);
        System.out.println("保存金额：" + money);
    }
}

class ReentrantLockThread {
    //需要声明这个锁
    private Lock lock = new ReentrantLock();
    //创建一个此Lock实例的新Condition实例
    Condition condition = lock.newCondition();
    private boolean conditionMet = false;

    public void save(int money) {
        lock.lock();
        try {
            while (!conditionMet) {
                condition.await();  // 线程等待条件满足（ 使当前线程等待，直到发出信号或 中断。）
            }
            //条件满足后执行操作
            System.out.println("保存金额：" + money);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public void setCondition() {
        lock.lock();
        try {
            conditionMet = true;
            condition.signal();  //通知等待的线程可以唤醒
        } finally {
            lock.unlock();
        }
    }
}

class SynchronizedThread {
    /**
     * 用同步方法实现
     *
     * @param money
     */
    public synchronized void save(int money) {
        System.out.println("保存金额：" + money);
    }

    /**
     * 用同步代码块实现
     *
     * @param money
     */
    public void save1(int money) {
        synchronized (this) {
            System.out.println("保存金额：" + money);
        }
    }
}
