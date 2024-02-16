package cn.mediinfo.springdemo.controller.effctivedemo.example4;

/*
 *@title EnumAndAnnotation4_用接口模拟可扩展的枚举
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class EnumAndAnnotation5_用接口模拟可扩展的枚举 {
    /**
     * 针对 {@link com.example.effctivedemo.example4.Operation2} 进一步深入扩展，用接口模拟可扩展的枚举
     */
    public void example() {
        test(BasicOperation3.class, 3, 4);
        test(ExtendedOperation3.class, 3, 4);
    }

    private  <T extends Enum<T> & Operation3> void test(Class<T> opSet, double x, double y) {
        for(Operation3 op : opSet.getEnumConstants()){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }
}

//用接口模拟可扩展的枚举
interface Operation3 {
    double apply(double x, double y);
}

//枚举实现接口
enum BasicOperation3 implements Operation3 {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    BasicOperation3(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

//枚举实现接口
enum ExtendedOperation3 implements Operation3 {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation3(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
}
