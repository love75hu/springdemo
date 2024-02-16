package cn.mediinfo.springdemo.controller.effctivedemo.example4;

/*
 *@title EnumAndAnnotation
 *@description
 *@author thj
 *@create 2024-02-15
 */
/**
 * 1、用enum替代int枚举模式
 */
public class EnumAndAnnotation1_用enum替代int常量 {
    //int枚举模式
    //缺点：不具有类型安全，也没有描述性可言。
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

    void example() {
        Planet.EARTH.surfaceGravity(1000);
        Operation.DIVIDE.apply(100, 10);
    }

    //枚举
    public enum Apple {FUJI, PIPPIN, GRANNY_SMITH}


    public enum Orange {NAVEL, TEMPLE, BLOOD}
}

/**
 * 2、用枚举定义行星的表面重力示例
 */
enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6);

    //物体的质量
    private final double mass;
    //物体的半径
    private final double radius;
    //物体的表面重力
    private final double surfaceGravity;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = mass / (radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity(double mass) {
        return mass * surfaceGravity;
    }
}

/**
 * 3-1、用枚举类来实现策略模式(常规方式，不建议使用)
 * 这个方式很脆弱，如果没有throw new AssertionError("Unknown op:"+this);这一行，将无法编译；
 * 如果添加了一个新的枚举常量，但忘记在switch语句中添加相应的case，编译器也不会警告。
 */
enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    public double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("Unknown op:" + this);
    }
}

/**
 * 3-2、用枚举类来实现策略模式(推荐方式)
 */
enum Operation2 {
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


    public abstract double apply(double x, double y);

    private final String symbol;

    Operation2(String symbol) {
        this.symbol = symbol;
    }
}

/**
 * 4-1、工资计算示例(不推荐)
 * 该方式虽然可以实现，而且代码简单，但是如果新增一个枚举值，但是又没有在pay方法进行参数维护，可能导致计算值不正确，而且我们很难发现
 */
enum PayrollDay{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    private static final int MINS_PER_SHIFT = 8 * 60;

    int pay(int minutesWorked, int payRate) {
        int basePay = minutesWorked * payRate;
        int overtimePay;
        switch (this) {
            case SATURDAY: case SUNDAY:
                overtimePay = minutesWorked * payRate / 2;
                break;
            default:
                overtimePay = minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
        }
        return basePay + overtimePay;
    }
}

/**
 * 4-2、工资计算示例（推荐）
 * 该方式使用了策略枚举，可以很好的解决4-1的问题
 */
enum PayrollDay2 {
    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WEDNESDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY), FRIDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay2(PayType payType) {
        this.payType = payType;
    }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    //策略枚举
    private enum PayType {
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 : (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        private static final int MINS_PER_SHIFT = 8 * 60;

        abstract int overtimePay(int minsWorked, int payRate);

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}


