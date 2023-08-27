package cn.mediinfo.springdemo.controller.thread;

public class ThreadSimple {
    /**
     * 设置中断状态的意义和用途在于：
     *
     * 通知线程中断：可以通过设置中断状态来向线程发出中断信号，告知线程应该停止执行。当线程处于阻塞状态（如等待、睡眠、输入输出阻塞等）时，中断状态能够打破线程的阻塞状态，使其提前结束阻塞并继续执行后续代码。
     *
     * 安全地终止线程：通过设置中断状态，线程可以根据自身的逻辑来判断是否终止执行。线程可以在合适的时机检查中断状态，并主动结束自己的执行，从而实现线程的安全终止。
     *
     * 协作与线程间通信：中断状态也可以用于线程间的协作与通信。在某些情况下，可以使用中断状态来作为线程间传递信号的一种方式。一个线程可以设置另一个线程的中断状态，从而触发特定的行为或操作。
     *
     * 需要注意的是，Thread.currentThread().interrupt() 方法仅仅是设置中断状态，并不会直接中断线程的执行。具体的中断处理需要线程自身负责，在适当的时机检查中断状态，并决定如何处理中断。通常情况下，线程应该在收到中断信号后，进行清理操作并 gracefully 地终止自己的执行。
     */
    public void Example(){
        //设置当前线程的中断状态为"中断"。这个方法会将当前线程的中断标志位设为true。
        //方式一：
        Thread.interrupted();
        //方式二：
        Thread.currentThread().interrupt();

        try{
            //如果线程被阻塞，就无法检测中断状态，在一个被sleep或者wait调用阻塞的线程数调用interrupt，会被InterruptedException异常中断
            Thread.sleep(50);
            //检测线程是否设置了中断状态
            while (!Thread.currentThread().isInterrupted()){
                //如果线程没有设置中断状态
            }
        }
        catch (InterruptedException exception){
              System.out.println("获取线程中断状态异常："+exception);
        }
    }
}
