package cn.mediinfo.springdemo.controller.javacore;

import java.util.concurrent.*;

/**
 * CompletableFuture用于异步编程，异步编程是编写非阻塞的代码，运行的任务在一个单独的线程，与主线程隔离，并且会通知主线程它的进度，成功或者失败。
 */
public class CompletableFutureSimple {
    /**
     * runAsync() 运行异步计算 如果你想异步的运行一个后台任务并且不想改任务返回任务东西，这时候可以使用 CompletableFuture.runAsync()方法，它持有一个Runnable 对象，并返回 CompletableFuture<Void>
     * supplyAsync() 运行一个异步任务并且返回结果
     */
    public void CompletableFutureExample() throws ExecutionException, InterruptedException, TimeoutException {
        // CompletableFuture.runAsync
        //使用默认的ForkJoinPool线程池来执行任务
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
           System.out.println("执行后台任务，无返回值！");
        });

        //supplyAsync()
        //创建一个线程池，让supplyAsync从创建的线程池获取一个线程来执行任务,通过创建自定义的Executor，你可以有更多的控制力来管理线程池的行为。例如，你可以设置线程池的大小、队列策略、超时时间等。
        ExecutorService executor= Executors.newFixedThreadPool(1);
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println("执行后台任务，有返回值！");
            return "返回值";
        },executor);

        //获取执行结果,最多等待5s,超过则会异常
        var res=  completableFuture2.get(5, TimeUnit.SECONDS);
    }


    /**
     *在 CompletableFuture 转换和运行
     * 当CompletableFuture的结果不满足我们的需求的时候，可以手动改变CompletableFuture结果，进行再次处理
     */
    public void CompletableFutureExample2() throws ExecutionException, InterruptedException {
        //supplyAsync()
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println("执行后台任务，有返回值！");
            return "返回值:aaaaa";
        });

        //当CompletableFuture的结果不满足我们的需求的时候，可以手动改变CompletableFuture结果，进行再次处理
        CompletableFuture completableFuture= completableFuture2.thenApply((arg)->{
            return String.join("返回执行结果",arg);
        });

        //获取执行结果
        completableFuture.get();
    }

    /**
     *在 CompletableFuture 转换和运行
     * 如果你不想从你的回调函数中返回任何东西，仅仅想在Future完成后运行一些代码片段，你可以使用thenAccept()和 thenRun()方法，这些方法经常在调用链的最末端的最后一个回调函数中使用。
     */
    public void CompletableFutureExample3() throws ExecutionException, InterruptedException {
        //supplyAsync()
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println("执行后台任务，有返回值！");
            return "返回值:aaaaa";
        });

        completableFuture2.thenAccept((arg)->{
             System.out.println("继续执行一些台任务，无返回值");
        });

        completableFuture2.thenRun(()->{
            System.out.println("继续执行一些台任务，无返回值2");
        });
    }


    /**
     * 组合两个CompletableFuture
     * 使用 thenCompose()组合两个独立的future 假设你想从一个远程API中获取一个用户的详细信息，一旦用户信息可用，你想从另外一个服务中获取他的贷方。
     */
    public void CompletableFutureExample4(){

    }
}
