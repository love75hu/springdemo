package cn.mediinfo.springdemo.controller.javacore2;

import com.sun.jersey.json.impl.FilteringInputStream;

import javax.crypto.CipherInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.io.*;
import java.security.DigestInputStream;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.*;

/*
 *@title 输入与输出流
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/25 20:01
 */
public class 输入与输出流 {
    /**
     * 在 java api 中，输入流和输出流是以 InputStream 和 OutputStream 为基础的。InputStream 和 OutputStream 是抽象类，它们分别代表了字节输入流和字节输出流。
     * 可以从中读取一个字节的对象称为字节输入流，可以向其中写入一个字节的对象称为字节输出流。这些字节的来源可以是文件、网络连接、内存块等。
     *
     * *********核心知识点***********：
     * read()、write()方法在执行的时候都将阻塞，直至字节确实被写入或写出。意味着流如果不能被立即访问（通常是因为网络连接忙），那么当前的现场将被阻塞。
     * inputStream.available() // 可以检测当前读入的字节数量；
     * inputStream.transferTo() // 该方法可以将所有字节从输入流转到一个指定的输出流；
     *
     * 当我们完成输入/输出流后，应该通过 close()来关闭它。这个调用会释放掉十分有限的系统操作资源。如果一个应用打开了过多的输入/输出流而没有关闭，那么系统资源将耗尽。
     * 关闭一个输出流还会冲刷用于该输出/输出流的缓冲区：所有被临时置于缓冲区，以便于更大的包的形式传递的字节在关闭输出流时候都将被送出。特别是如果不关闭文件，那么写出字节的最后一个包可能永远也得不到传递。
     * 当然我们可以利用 flush()方法来强制冲刷缓冲区，但是这样做的话，我们就必须在每次写出字节后都调用 flush()方法。
     */

    /**
     * 1 读取字节 InputStream
     */
    void example() throws IOException {
        var inputStream2= new ByteArrayInputStream("hello world".getBytes());
        var inputStream3 = new FileInputStream("test.txt");
        var inputStream4 = new AudioInputStream(inputStream2, null, 0); // AudioInputStream 是 Java 音频系统中的一个类，它是 InputStream 的一个扩展，用于读取音频数据
        var inputStream5 = new PipedInputStream();
        var inputStream6 = new SequenceInputStream(inputStream2, inputStream3);
        var inputStream7 = new StringBufferInputStream("hello world"); //StringBufferInputStream 是 Java 中的一个类，它是 InputStream 的子类。这个类的主要作用是将一个 StringBuffer 对象转换为一个输入流，以便你可以从这个输入流中读取数据。
        var inputStream8 = new ObjectInputStream(inputStream2);

        var inputStream9 = new BufferedInputStream(inputStream2);
        var inputStream10 = new DataInputStream(inputStream2);
        var inputStream11 = new CheckedInputStream(inputStream2, new CRC32());
        var inputStream12 = new CipherInputStream(inputStream2, null);
        var inputStream13 = new DigestInputStream(inputStream2, null);
        var inputStream15 = new LineNumberInputStream(inputStream2);
        var inputStream16 = new PushbackInputStream(inputStream2);
        //var inputStream16 = new ProgressMonitorInputStream(, null);
        var inputStream17 = new DataInputStream(inputStream2);

        var inputStream18 = new InflaterInputStream(inputStream2);
        var inputStream19 = new GZIPInputStream(inputStream2);
        var inputStream20 = new ZipInputStream(inputStream2);
        var inputStream21 = new JarInputStream(inputStream2);

        //InputStream 有一个抽象方法 read()，用于读取一个字节并返回，如果已经读到输入流的末尾，返回 -1。
        InputStream inputStream = new FileInputStream("test.txt");

        byte[] allBytes= inputStream.readAllBytes(); // 读取所有字节

        int data = inputStream.read(); // 读取一个字节
        int data2 = inputStream.read(allBytes); // 读取一个字节组，并返回实际读取的字节数量，如果已经读到输入流的末尾，返回 -1。

        //总的来说，readNBytes 方法会尽可能多地读取字节，而 read 方法只会读取指定数量的字节。
        int data3 = inputStream.read(allBytes, 0, 10); //方法会尝试读取最多 10 个字节到 allBytes 数组的前 10 个位置。如果输入流中的可用字节少于 10 个，那么它只会读取可用的字节。如果输入流的末尾已经到达，那么它会立即返回 -1。
        int data4 = inputStream.readNBytes(allBytes,10,allBytes.length); //方法会尝试读取 allBytes.length 个字节到 allBytes 数组的第 10 个位置开始的位置。如果输入流中的可用字节少于 allBytes.length 个，那么它只会读取可用的字节。如果输入流的末尾已经到达，那么它会立即返回实际读取的字节数量，而不是 -1。

        long data5 = inputStream.transferTo(System.out); //方法会将输入流中的所有字节写入到指定的输出流中，并返回实际写入的字节数量。如果输入流的末尾已经到达，那么它会立即返回 0

        long data6 = inputStream.skip(10); //方法会跳过 10 个字节，然后返回实际跳过的字节数量。如果输入流的末尾已经到达，那么它会立即返回 0。
        inputStream.skipNBytes(10); //方法会跳过 10 个字节，然后返回实际跳过的字节数量。如果输入流的末尾已经到达，那么它会立即返回 0。

        int data7 = inputStream.available(); //方法会返回输入流中可用的字节数量。如果输入流已经到达末尾，那么它会返回 0。

        inputStream.close(); //关闭输入流

        inputStream.mark(10); //方法会在当前位置设置一个标记，以便后续调用 reset() 方法时可以回到这个位置。如果已经设置了标记，那么它会被重写。如果输入流的末尾已经到达，那么它会立即返回。
        inputStream.reset(); //方法会将输入流的位置重置到最近设置的标记位置。如果没有设置标记，那么它会抛出 IOException 异常。如果输入流的末尾已经到达，那么它会立即返回。
        inputStream.markSupported(); //方法会返回一个 boolean 值，表示输入流是否支持 mark() 和 reset() 方法。

        InputStream.nullInputStream();//方法会返回一个空的输入流，它的 read() 方法会返回 -1，available() 方法会返回 0，skip() 方法会返回 0，markSupported() 方法会返回 true。
    }

    /**
     * 1 输入流 OutputStream
     */
    void example2() throws IOException {
        var outputStream2= new ByteArrayOutputStream();
        var outputStream3 = new FileOutputStream("test.txt"); //文件写入：FileOutputStream 是 OutputStream 的一个具体实现，它用于将数据写入到文件中。例如，你可以使用 FileOutputStream 来创建一个新文件并写入数据，或者向已存在的文件中追加数据。
        var outputStream4 = new PipedOutputStream(); //管道通信：PipedOutputStream 是 OutputStream 的一个具体实现，它用于向 PipedInputStream 写入数据。这在需要在两个线程之间传递数据的场景下非常有用。
        var outputStream5 = new ObjectOutputStream(outputStream2); //对象序列化：ObjectOutputStream 是 OutputStream 的一个具体实现，它用于将 Java 对象写入到输出流中。这在需要将对象的状态保存到文件或通过网络发送的场景下非常有用。
        var outputStream6 = new BufferedOutputStream(outputStream2);
        var outputStream7 = new DataOutputStream(outputStream2);
        var outputStream8 = new PrintStream(outputStream2);
        var outputStream9 = new ByteArrayOutputStream(10); //内存操作：ByteArrayOutputStream 是 OutputStream 的一个具体实现，它用于将数据写入到内存中的字节数组。这在需要将数据暂存到内存中的场景下非常有用，例如，当你需要先处理数据，然后再将其写入到文件或网络连接中。

        var outputStream10 = new DeflaterOutputStream( outputStream2); //压缩：DeflaterOutputStream 是 OutputStream 的一个具体实现，它用于将数据写入到输出流中，并对数据进行压缩。这在需要将数据压缩后再写入到文件或网络连接中的场景下非常有用。
        var outputStream11 = new GZIPOutputStream(outputStream2); //压缩：GZIPOutputStream 是 OutputStream 的一个具体实现，它用于将数据写入到输出流中，并对数据进行压缩。这在需要将数据压缩后再写入到文件或网络连接中的场景下非常有用。
        var outputStream12 = new ZipOutputStream( outputStream2); //压缩：ZipInputStream 是 InputStream 的一个具体实现，它用于从输入流中读取数据，并对数据进行解压缩。这在需要从文件或网络连接中读取压缩的数据的场景下非常有用。
        var outputStream13 = new JarOutputStream( outputStream2); //JarOutputStream 是 Java 中的一个类，它继承自 ZipOutputStream，用于写入 JAR 文件。JAR 文件是一种包含多个文件的压缩文件格式，通常用于分发 Java 类和相关的元数据和资源（文本、图片等）。


        //OutputStream 有一个抽象方法 write(int b)，用于写入一个字节。如果已经写入输出流的末尾，会抛出 IOException 异常。
        OutputStream outputStream = new FileOutputStream("test.txt");

        outputStream.write('中');
        outputStream.write(1);
        outputStream.write('a'); //写入一个字节,例如：outputStream.write('a');
        outputStream.write(new byte[]{1,2,3}); //写入一个字节组
        outputStream.write(new byte[]{1,2,3},0,3); //写入一个字节组的一部分

        outputStream.flush(); //方法会将输出流中的所有字节写入到目标设备中。如果输出流的末尾已经到达，那么它会立即返回。
        outputStream.close(); //关闭输出流

        OutputStream.nullOutputStream();//方法会返回一个空的输出流，它的 write() 方法会什么都不做。
    }
}
