package cn.mediinfo.springdemo.controller.javacore;

import org.springframework.scheduling.config.Task;

import java.util.concurrent.*;

/**
 * Future是一种表示可能还没有完成的异步任务的抽象。它可以用于获取异步任务的执行结果或取消任务的执行。
 * 需要注意的是，Future接口只提供了基本的异步操作控制，而对于更复杂的异步编程模型，可以使用Java 8引入的CompletableFuture类，它提供了更强大和灵活的异步编程功能。
 *
 * @see <a href="https://blog.csdn.net/weixin_40719914/article/details/108818121">Future 的局限性</a>
 */
public class FutureSimple {
   /**
    * 演示一个线程获取耗时任务，使用Future的示例
    * @throws ExecutionException
    * @throws InterruptedException
    * @throws TimeoutException
    */
    public void FutureExample() throws ExecutionException, InterruptedException, TimeoutException {
       //创建了一个固定大小为1的线程池
       ExecutorService executor= Executors.newFixedThreadPool(1);

       Callable<String> callable=()->{
          Thread.sleep(3000);
          return "返回值";
       };

       //执行任务，得到一个异步执行的结果
       Future<String> future= executor.submit(callable);

       //后续业务，注意后续future.get期间会线程阻塞，等到完成才会执行后续业务
       //所以：一些任务放到future.get之前执行，等到需要使用future.get的返回结果的时候在get，就可以缩短程序的响应时间

       //指定获取结果的等待时间
       try {
          var res= future.get(2, TimeUnit.SECONDS);
          System.out.println(res);
       }
       catch (InterruptedException e) {
          System.err.println("任务被中断");
       } catch (ExecutionException e) {
          System.err.println("任务执行异常：" + e.getMessage());
       } catch (TimeoutException e) {
          System.err.println("任务超时");
       } finally {
          executor.shutdown(); // 关闭线程池
       }
    }
}
