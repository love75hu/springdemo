package cn.mediinfo.springdemo.controller.javacore2;

import org.mortbay.log.Log;

import javax.script.*;
import javax.swing.*;
import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@title 脚本编译与注解处理
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/27 10:07
 */
public class 脚本编译与注解处理1_脚本编译 {
/**
 * java 平台的脚本机制
 * -----------------------------------------
 * 脚本语言是一种通过在运行时解释程序文本，从而避免使用通常的编辑/编译/连接/运行的过程，来运行程序的语言。
 * 优势：
 * 1.便于快速变更，鼓励不断实验
 * 2.可以修改运行中的程序行为
 * 3.支持程序用户的定制化
 * 缺点：
 * 1.脚本语言缺乏可以编写复杂应用受益的特性，如强类型、封装和模块化。
 * <p>
 * Renjin项目（wwww.renjin.org）提供了一个R编程语言的JAVA实现和相应的脚本API的“引擎”，R语言被广泛用于统计编程中。
 * <p>
 * java 平台的脚本机制
 */

    /**
     * 获取脚本引擎
     * -----------------------------------------
     * 脚本引擎是一个可以执行某种特定语言编写的脚本的类库。当虚拟机启动的时候，它会发现可用的脚本引擎。为了美剧这些引擎，需要构造一个ScriptEngineManager对象。并调用getEngineFactories()方法。
     * 可以向每个引擎工厂询问他们所支持的引擎名、MIME类型和文件扩展名。
     * <p>
     * 引擎                          名字                                        MIME类型                                                                        扩展名
     * Rhion(Javascript)      ->    rhino、Rhino、JavaScript、javascript    ->   application/javascript,application/ecmascript,text/javascript,text/ecmascript   js
     * Groovy                 ->    groovy                                 ->   无                                                                              groovy
     * Renjin                 ->    renjin                                 ->   text/x-R                                                                        R,r,s,S
     * <p>
     * 通常，你知道所需要的引擎，因此可以通过名字、MIME类型或文件扩展名来请求它，例如：
     * ScriptEngineManager manager = new ScriptEngineManager();
     * ScriptEngine engine = manager.getEngineByName("JavaScript");
     */

    /**
     * 脚本引擎的使用
     * ScriptEngineManager 、ScriptEngine 、 ScriptEngineFactory
     */
    void example() throws ScriptException, FileNotFoundException {
        //1.ScriptEngineManager
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> scriptEngineManagers = manager.getEngineFactories(); //获取所有发现发现的引擎工厂列表
        ScriptEngine engine = manager.getEngineByName("JavaScript"); //根据既定名称获取脚本引擎
        ScriptEngine engine2 = manager.getEngineByMimeType("application/javascript"); //根据MIME类型获取脚本引擎
        ScriptEngine engine3 = manager.getEngineByExtension("js"); //根据文件扩展名获取脚本引擎

        //2.ScriptEngineFactory
        ScriptEngineFactory factory = engine.getFactory(); //获取脚本引擎的工厂
        String engineName = factory.getEngineName(); //获取引擎名称
        String engineVersion = factory.getEngineVersion(); //获取引擎版本
        String languageName = factory.getLanguageName(); //获取语言名称
        String languageVersion = factory.getLanguageVersion(); //获取语言版本
        String name = factory.getNames().get(0); //获取脚本引擎的名称
        String mimeTypes = factory.getMimeTypes().get(0); //获取脚本引擎支持的MIME类型
        String extensions = factory.getExtensions().get(0); //获取脚本引擎支持的扩展名

        //3.脚本计算与绑定
        //一旦拥有了引擎，就可也通过下面的调用来直接调用脚本：
        engine.eval("print('Hello, World')"); //eval方法接受一个字符串参数，其中包含了要执行的脚本。如果脚本执行成功，eval方法返回null。如果脚本执行失败，eval方法将抛出一个ScriptException异常。

        //如果脚本存储在文件中，那么需要先打开一个reader，然后调用eval方法：
        engine.eval(new FileReader("script.js"));

        //可以在同一个引擎上调用多个脚本。如果一个脚本定义了变量、函数或类，那么大多数引擎都会保留这些定义，以供将来使用,例如：
        engine.eval("var message='Hello, World'");
        Object message = engine.get("message"); //获取变量
        System.out.println(message); //输出：Hello, World ，可以看到引擎下一次调用还可以获取到上一次定义的变量。

        //重点：要知道在多线中并发执行脚本是否安全，可以调用 object result=factory.getParameter("THREADING"); 来检查脚本引擎是否支持多线程。如果支持，那么可以在多个线程中同时执行脚本。如果不支持，那么在多个线程中执行脚本可能会导致不可预知的结果。
        //返回值说明：
        //1.null : 并发执行不安全
        //2.MULTITHREADED : 并发执行安全，一个线程的执行结果对另外一个线程有可能是可视的
        //3.THREAD-ISOLATED : 并发执行安全，除了“MULTITHREADED”，还会为每个线程维护不同的变量绑定
        //4.STATELESS : 并发执行安全，除了“THREAD-ISOLATED”，脚本还不会改变变量绑定

        //我们经常希望能够向引擎中添加新的变量绑定，绑定由名字及关联的Java对象构成，例如：
        //(引擎作用域-自定义变量)
        engine.put("message", "Hello, World");
        engine.eval("print(message)"); //代码从绑定的变量 message 中获取值，并将其打印到控制台上。

        engine.put("button", new JButton("Click Me"));
        engine.eval("button.setSize(200, 80)"); //代码从绑定的变量 button 中获取值，并调用 setSize 方法。

        //除了引擎作用域外，还有全局作用域：
        //(全局作用域-自定义变量)
        Bindings scope = engine.createBindings(); //创建一个新的绑定对象
        scope.put("message", "Hello, World"); //向绑定对象中添加变量
        engine.eval("print(message)", scope); //将绑定对象作为参数传递给eval方法

        // 除了引擎作用域和全局作用域，我们可能还希望其他作用域，例如，web容器可能需要请求作用域或会话作用域。但是这个需要我们自己实现，实现 ScriptContext接口，
        // 可以用来创建自定义的作用域（系统提供了提供了一个SimpleScriptContext类，但是它只有全局作用域、引擎作用域）。
    }


    /**
     * 重定向输入和输出
     * 通过调用脚本上下文的 setReader 和 setWriter 方法，可以重定向脚本的输入和输出
     */
    void example2() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        //将所有的print、println调用重定向到一个StringWriter对象中，这样做的好处是可以更灵活地处理脚本引擎的输出。例如，你可以在脚本执行完毕后，获取这个字符串并进行进一步的处理，如日志记录、格式化输出等。而不是直接将输出打印到控制台，这样就失去了对输出的控制。
        var writer = new StringWriter();
        var printWriter = new PrintWriter(writer, true); //实现一个PrintWriter对象，它会将所有的输出写入到一个StringWriter对象中
        engine.getContext().setWriter(printWriter); //重定向输出

        engine.eval("print('Hello, World')"); //执行脚本
        //获取输出的字符串，然后进一步处理,例如：将print的信息记录到日志中
        Log.info(writer.toString());
    }

    /**
     * 调用脚本的函数和方法
     * 对于许多脚本引擎而言，我们都可以调用脚本引擎的函数，而不必对实际的脚本代码进行计算，如果允许用户他们所选择的脚本语言来实现服务，那么这种机制就很有用了。
     * 提供这种功能的脚本引擎实现了 Invocable 接口。特别是 rhion 引擎就是实现了  Invocable接口.
     * 如果要调用一个函数，需要用函数名来调用 InvokeFunction 方法，函数名后面就是函数的参数。
     */
    void example3() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        //1.调用脚本函数
        //定义函数
        engine.eval("function gree(how,whom){ return how+','+whom }");

        //调用函数，并接收返回值
        Object result = ((Invocable) engine).invokeFunction("gree", "hello", "word");
        Log.info(result.toString());

        //如果脚本语言是面向对象的，就可以调用 invokeMethod
        engine.eval("function gree(how){this.how=how;}");
        engine.eval("Greeter.prototype.welcome=gree('hello word');");

        //构建对象实例
        Object yo = engine.eval("new Greeter()");

        //调用对象的方法,参数：对象实例，方法名，参数
        Object result2 = ((Invocable) engine).invokeMethod("yo", "welcome", "hello word");


        //2.调用java接口
        ScriptEngine javaEngine = manager.getEngineByName("Groovy");
        Greeter g = ((Invocable) javaEngine).getInterface(Greeter.class);
        Object result3 = g.gree("hello word", "thj");

        //总之，如果你希望从 Java 中调用脚本代码，同时又不想因为这种脚本语言的语法而受到困扰，那么 Invocable 接口就是你的最佳选择。
    }

    /**
     * 编译脚本
     * 某些脚本引擎处于对执行效率的考虑，可以将脚本编译为某种中间格式。这些引擎实现了 Compilable 接口。
     * 当我们的脚本需要重复执行的时候，就需要考虑编译脚本，如果只执行一次，可以不考虑编译脚本。
     */
    void example4() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        //可以将执行的脚本编译后的内容存储缓存，便于以后直接执行，这里不再编写具体的示例代码，只是提供一个思路。
        Map<String, CompiledScript> scripts = new HashMap<>();

        //1.编译脚本
        if (engine instanceof Compilable) {
            Compilable compilable = (Compilable) engine;
            try {
                CompiledScript script = compilable.compile("print('Hello, World')");
                //编译后的脚本可以多次执行
                script.eval();
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        } else {
            //如果引擎不支持编译，那么就直接执行脚本
            engine.eval("print('Hello, World')");
        }
    }

    ;

    /**
     * 编译器API
     * -----------------------------------------
     * 许多工具都需要编译java代码。
     * 通过 CompilationTask 接口，可以发起一个编译任务，这个接口的实现类可以将脚本编译为字节码或者其他格式。
     */
    void example5() throws ScriptException, ClassNotFoundException {
        /**
         * 1.调用编译器（最基础的编译执行）
         */

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        OutputStream outPutStream = OutputStream.nullOutputStream();
        OutputStream errPutStream = OutputStream.nullOutputStream();

        int result = compiler.run(null, outPutStream, errPutStream, "-sourcepath", "src", "test.java");
        if (result == 0) {
            //编译成功
        } else {
            //编译失败
        }

        /**
         * 2.发起编译任务（高级编译任务）
         * 当我们需要对编译的过程进行更多的控制的时候，例如：如果要从字符串中提供源码，在内存中捕获类文件，或者处理错误和经过消息，这样做就很有用。接口定义如下：
         * JavaCompiler.CompilationTask getTask(Writer out,
         *                                      JavaFileManager fileManager,
         *                                      DiagnosticListener<? super JavaFileObject> diagnosticListener,
         *                                      Iterable<String> options,
         *                                      Iterable<String> classes,
         *                                      Iterable<? extends JavaFileObject> compilationUnits)
         *
         * out：这是一个Writer对象，用于接收有关编译器输出的信息。例如，如果编译过程中有错误，错误信息会被写入这个Writer。如果你不需要这些信息，可以传入null。
         * fileManager：这是一个JavaFileManager对象，它定义了如何读取源文件和类文件，以及如何写入类文件。如果你传入null，那么编译器会使用一个默认的文件管理器。
         * diagnosticListener：这是一个DiagnosticListener对象，它会接收有关编译过程中诊断信息的通知。如果你传入null，那么诊断信息会基于Writer对象进行格式化。
         * options：这是一个字符串的Iterable，它包含了要传递给编译器的选项。每个选项都应该是一个字符串。例如，你可以传入List.of("-d", "bin")来设置类文件的输出目录。
         * classes：这是一个字符串的Iterable，它包含了要进行注解处理的类的名称。如果你不需要进行注解处理，或者你希望处理所有的类，那么可以传入null。
         * compilationUnits：其中最后一个参数 compilationUnits 是 JavaFileObject 实例的 Iterable，如果想要编译磁盘文件，就可以获取一个StandardJavaFileManager实例，然后调用getJavaFileObjectsFromFiles()方法来获取JavaFileObject实例。
         * 这个方法会返回一个CompilationTask对象，你可以调用它的call()方法来执行编译任务。
         */

         /**
          * 3-0.发起编译任务
          * 最后三个参数是Iterable实例，选项可以按照下面的示例进行指定，
          */
        Iterable<String> options = List.of("-d", "bin");
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(List.of(new File("test.java")));
        JavaCompiler.CompilationTask task2 = compiler.getTask(Writer.nullWriter(), null, null, options, null, compilationUnits);
        boolean success = task2.call(); //调用call方法，开始编译


        /**
         * 3-1.捕获诊断消息
         * 为了监听错误消息，需要安装一个DiagnosticListener对象，这个监听器在收到编译器发出的告警或者错误消息时候会收到一个 Diagnostic 接口对象。DiagnosticCollector类实现该接口， 对象包含了消息的详细信息，例如消息的类型、位置、内容等。
         * 它收集所有的诊断消息，使你可以在编译完成后遍历这些信息。
         *
         * Diagnostic 对象包含有挂问题位置的信息（包括文件名、行号、列号）以及人类可以阅读的描述/还可以在标准的文件管理器上安装一个 DiagnosticListener 对象，这样就可以捕获到文件缺失的消息了。
         */
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();

        JavaCompiler.CompilationTask task3 = compiler.getTask(Writer.nullWriter(), null, collector, options, null, null);
        for( Diagnostic<? extends JavaFileObject> javaFileObject : collector.getDiagnostics()){
            //处理诊断消息
            System.out.println(javaFileObject);
        }

        /**
         * 3-2.从java文件读取源文件
         * 如果是从文件系统读取源文件，那么可以使用标准的文件管理器，它会将源文件读取到内存中。
         */
        StandardJavaFileManager fileManager2 = compiler.getStandardFileManager(collector, null, null);
        Iterable<? extends JavaFileObject> compilationUnits2 = fileManager2.getJavaFileObjectsFromFiles(List.of(new File("test.java")));
        JavaCompiler.CompilationTask task33 = compiler.getTask(Writer.nullWriter(), fileManager2, collector, options, null, compilationUnits2);
         /**
         * 3-2.从内存中读取源文件
         * 如果动态的生成了源代码，那么就可以从内存中获取它来进行编译，而无需在磁盘上保存文件。
         */
         List<StringSource> compilationUnits3= List.of(new StringSource("test", "public class test{}"));
         JavaCompiler.CompilationTask task4=compiler.getTask(Writer.nullWriter(), fileManager2, collector, options, null, compilationUnits3);

        /**
         * 3-3.将字节码写出到内存中
         * 如果动态的编译类，那么就无需将类文件写出到磁盘。可以将他们存储到内存中，并立即加载他们。
         */
        List<ByteArrayClass> classes=new ArrayList<>();
        StandardJavaFileManager fileManager3 = compiler.getStandardFileManager(null, null, null);

        //配置文件管理器为使用这些类作为输出：
        JavaFileManager fileManager4=new ForwardingJavaFileManager<JavaFileManager>(fileManager3){
            public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
                if (kind==JavaFileObject.Kind.CLASS){
                    ByteArrayClass file=new ByteArrayClass(className);
                    classes.add(file);
                    return file;
                }
                else {
                    return super.getJavaFileForOutput(location, className, kind, sibling);
                }
            }
        };

        //使用类加载器调用 Class.forName() 方法来加载类：
        ByteArrayClassLoader classLoader=new ByteArrayClassLoader(classes);
       // Class<?> testClass=Class.forName("className", true, classLoader);
    }

    interface Greeter {
        String gree(String how, String whom);
    }

    /**
     * 持有代码的类
     */
    public class StringSource extends SimpleJavaFileObject {
        private String code;
        public StringSource(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return code;
        }
    }

    /**
     * 持有字节的类
     */
    public class ByteArrayClass extends SimpleJavaFileObject{
        private ByteArrayOutputStream outputStream;
        public ByteArrayClass(String name) {
            super(URI.create("bytes:///" + name.replace('.', '/') + Kind.CLASS.extension), Kind.CLASS);
            outputStream = new ByteArrayOutputStream();
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return outputStream;
        }

        public byte[] getBytes() {
            return outputStream.toByteArray();
        }
    }

    /**
     * 类加载器
     */
    public class ByteArrayClassLoader extends ClassLoader {
        private Iterable<ByteArrayClass> classes;
        public ByteArrayClassLoader(Iterable<ByteArrayClass> classes) {
            this.classes = classes;
        }

//        public Class<?> findClass(String name) throws ClassNotFoundException {
//            for (ByteArrayClass clazz : classes) {
//                if (clazz.getName().equals(name)) {
//                    byte[] bytes = clazz.getBytes();
//                    return defineClass(name, bytes, 0, bytes.length);
//                }
//            }
//            throw new ClassNotFoundException(name);
//        }
    }
}
