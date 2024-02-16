package cn.mediinfo.springdemo.controller.effctivedemo.example4;

import java.util.EnumSet;

/*
 *@title EnumAndAnnotation3_用EnumSet替代位域
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class EnumAndAnnotation3_用EnumSet替代位域 {
    /**
     *
     * 如果一个枚举类型的元素主要用在集合中，一般就使用int枚举模式。
     */

    public static final int STYLE_BOLD = 1 << 0; // 1
    public static final int STYLE_ITALIC = 1 << 1; // 2
    public static final int STYLE_UNDERLINE = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8

    public void applyStyles(int styles) {
        // ...
    }
}

//将上面范例改为用枚举替代位域之后的代码
class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}
    public void applyStyles(EnumSet<Style> styles) {
        // ...
    }
}
