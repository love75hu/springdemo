package cn.mediinfo.springdemo.context.spel;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * spel 在@Value 使用示例
 * 注意：这里容易和Spring中的properties混淆。properties是使用${}符号
 *    @Value("${java.starter.message}")
 *    private String message;
 *
 *    本质上是@Value支持两类值，一个是properties属性值，一个是SpEL表达式
 */
@Data
public class SpELProperties {

    //逻辑运算符
    @Value("#{ 1 + 1 }")
    private double add;

    @Value("#{ '姓名：' + '谭' }")
    private String addString;

    //逻辑比较符号
    @Value("#{ '1' eq '1' }")
    private  boolean eq;

    @Value("#{ 1 == 1 }")
    private  boolean eq2;

    //逻辑关系运算符
    @Value("#{ 1 == 1 or 2==2 }")
    private  boolean or;

    @Value("#{ 1 == 1 and 2==2 }")
    private  boolean and;

    //三元表达式& Elvis
    @Value("#{ 1 > 2 ? 'a':'b' }")
    private  String ternary;

    @Value("#{ SpELProperties.add !=null ? SpELProperties.add : SpELProperties.and}")
    private  String ternaryProperty;

    //?: Elvis是三元运算符的简写，和上面的结果一样
    @Value("#{ SpELProperties.add ?:  SpELProperties.and}")
    private  String ternaryProperty2;

    //正则表达式
    @Value("#{ '100' matches '\\d+' }")
    private  boolean matches;
}
