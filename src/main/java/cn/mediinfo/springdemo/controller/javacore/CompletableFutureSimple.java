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
    public void CompletableFutureExample4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

        //通过调用 thenCombine() 方法，将这两个 CompletableFuture 组合在一起，在它们都完成时执行一个 BiFunction 函数来处理它们的结果。
        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) ->
                result1 + " " + result2);

        String result = combinedFuture.get();
        System.out.println(result);  // 输出 "Hello World"

    }

    /**
     * 组合多个CompletableFuture
     * exceptionally:该方法允许你在异步操作中发生异常时提供一个默认的返回值或处理方式。
     * handle:该方法允许你在异步操作完成后处理结果，包括正常结果和异常情况(如果异常发生，res参数将是 null，否则，ex将是 null。)
     */
    public void CompletableFutureExample5() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello").exceptionally(ex->{return "默认值";});
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 5).handle((value,ex)->{
            //如果异常发生，res参数将是 null，否则，ex将是 null。
            if (ex!=null)
            {
                //发生异常，执行异常逻辑
            } else if (value!=null) {
                //未发生异常，对返回值做二次加工
            }
            return value;
        });
        CompletableFuture<Boolean> future3 = CompletableFuture.supplyAsync(() -> true);

        //我们使用 CompletableFuture.allOf() 方法将所有的 CompletableFuture 对象组合到一个新的 CompletableFuture 中。然后，使用 thenApply() 方法来处理所有 CompletableFuture 的结果。
        //使用exceptionally处理回调异常
        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(future1, future2, future3)
                .exceptionally(ex->{
                    // exceptionally()回调给你一个从原始Future中生成的错误恢复的机会。你可以在这里记录这个异常并返回一个默认值。
                    System.out.println(ex.getMessage());
                    return null;
                });

        CompletableFuture<String> resultFuture = allFutures.thenApply(v ->
                future1.join() + future2.join() + future3.join()
        );

        String result = resultFuture.get();
        System.out.println(result);

    }
}
