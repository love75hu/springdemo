package cn.mediinfo.springdemo.enums;

/**
 * 枚举的高级写法-定义具有字段和方法的枚举
 * 使用场景：获取枚举的自定义值或者一些关联的固定值
 */
public enum Direction {
    NORTH(0, "NORTH"),
    EAST(90, "EAST"),
    SOUTH(180, "SOUTH"),
    WEST(0, "WEST");

    Direction(int degrees, String name) {
        this.degrees = degrees;
        this.name = name;
    }

    private int degrees;
    private String name;

    public String getName() {
        return this.name;
    }

    public int getDegrees() {
        return this.degrees;
    }
}
