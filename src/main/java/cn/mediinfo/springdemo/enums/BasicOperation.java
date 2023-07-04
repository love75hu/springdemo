package cn.mediinfo.springdemo.enums;

/**
 * 枚举的高级写法-实现接口
 * 使用场景:状态机、策略模式、常量集合、单例模式
 */
public enum BasicOperation implements Operation{
    ADDITION("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    SUBTRACTION("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return symbol;
    }
}

interface Operation
{
    double apply(double x, double y);
}
