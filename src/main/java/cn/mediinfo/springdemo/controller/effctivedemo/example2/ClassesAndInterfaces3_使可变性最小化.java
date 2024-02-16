package cn.mediinfo.springdemo.controller.effctivedemo.example2;

/*
 *@title ClassesAndInterfaces3_使可变性最小化
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class ClassesAndInterfaces3_使可变性最小化 {
    /**
     *  不可变类是指其实例不能被修改，每个实例包含的信息必须在创建该实例的时候就提供。并在对象的整个生命周期内固定不变；
     *  java平台包含许多不可变的类：例如String、基本类型的包装类、BigInteger和BigDecimal等。
     *  为了使类成为不可变的，需要遵循以下五个规则：
     *  1.不要提供任何会修改对象状态的方法；
     *  2.保证类不会被扩展；
     *  3.使所有的字段都是final的；
     *  4.使所有的字段都是私有的；
     *  5.确保对于任何可变组件的互斥访问。
     *
     *  不可变对象本质上是线程安全的，它们不需要同步。不可变对象可以被自由地共享。
     */

        public final Complex ZERO = new Complex(0, 0);
        public final Complex ONE = new Complex(1, 0);
        public final Complex I = new Complex(0, 1);

      //复杂不可变类的示例
      public final class Complex {
            private final double re;
            private final double im;
            public Complex(double re, double im) {
                this.re = re;
                this.im = im;
            }

            public double realPart() {
                return re;
            }

            public double imaginaryPart(){
                return im;
            }

            @Override
            public boolean equals(Object o){
                if (o==this)
                    return true;
                if (!(o instanceof Complex))
                    return false;
                Complex c=(Complex) o;
                return Double.compare(c.re,re)==0 && Double.compare(c.im,im)==0;
            }
      }
}
