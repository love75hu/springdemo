package cn.mediinfo.springdemo.controller.effctivedemo.exampl1;

import java.io.BufferedReader;
import java.io.InputStream;

/*
 *@title CreateAndDestroyObjects9_try_with_resource优先于try_finally
 *@description
 *@author thj
 *@create 2024-02-05
 */
public class CreateAndDestroyObjects9_try_with_resource优先于try_finally {
    /**
     * java类库中很多需要使用调用close来手工关闭资源。例如：InputStream、OutputStream、java.sql.Connection等。在Java7中引入了一个新的特性，叫做 try-with-resources，它可以自动关闭资源。
     * 根据经验,try-finaly语句也可以确保资源被适时的关闭，就算发生异常或者返回也一样。
     * 例如：
     */
    static String firstLineOfFile(String path) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    //上面这个方法也可以实现关闭资源，但是try-with-resources语句更加简洁，更加容易读懂。
    //使用try-with-resources语句，可以更加简洁的编写代码，而且更加容易读懂。下面为范例：
    //范例一
    static String firstLineOfFile2(String path) throws Exception {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {
            return br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //范例二
    static void copy(String src, String dst) throws Exception {
        try (InputStream in = new java.io.FileInputStream(src);
             java.io.OutputStream out = new java.io.FileOutputStream(dst)) {
            byte[] buf = new byte[100];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
