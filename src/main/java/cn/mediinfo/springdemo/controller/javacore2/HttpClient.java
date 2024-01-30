package cn.mediinfo.springdemo.controller.javacore2;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *@title Http客户端
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/26 20:24
 */
public class HttpClient
{
     /**
     * HttpClient 提供了更便捷的 API 和对HTTP/2的支持。从Java11开始，HttpClient 位于java.net.http包中，它是一个标准的模块，不需要额外的依赖。
     */

    /**
     * 1 HttpClient 类
     */
     void example(){
         //构建client
         java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

         //构建client2
         java.net.http.HttpClient client2 = java.net.http.HttpClient.newBuilder()
                 .version(java.net.http.HttpClient.Version.HTTP_2)
                 .build();
     }

    /**
     * 2 HttpRequest 类和实体发布器
     */
    void example2(){
        //URI 是指统一资源标识符，在使用时，它与http相同，但是它不是URL，URL是URI的子集
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .headers("Content-Type", "application/json", "Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("json"))
                .build();
    }

    /**
     * 3 HttpResponse 类和实体发布器
     */
    void example3(){
       //在发送请求时候，必须告诉客户端如何处理响应，如果只是想将体当作字符串处理，那么就可也像下面这样用HttpResponse.BodyHandlers.ofString()方法
        java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    /**
     * 4 HttpResponse 异步处理
     */
    void example4() throws ExecutionException, InterruptedException {
        //可以异步处理响应，在构建客户端时候，提供一个执行器：
        ExecutorService executor= Executors.newCachedThreadPool();

        java.net.http.HttpClient client = java.net.http.HttpClient.newBuilder()
                .executor(executor)
                .build();

        //构建一个请求，然后再该客户端上调用sendAsync()方法，该方法接收一个CompletableFuture<HttpResponse<String>>类型的参数，该参数是一个异步任务，它会在响应到达时候被执行。
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .GET()
                .build();

      CompletableFuture<HttpResponse<byte[]>> response= client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray());
      response.get().body().toString();
    }
}
