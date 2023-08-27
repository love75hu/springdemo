package cn.mediinfo.springdemo.controller.javacore;

import java.util.ArrayList;

import static me.prettyprint.cassandra.utils.Assert.isTrue;
import static me.prettyprint.cassandra.utils.Assert.notNull;
import static org.springframework.util.Assert.*;

/**
 * 断言是一种用于调试和测试的特殊语句。它可以帮助开发者在代码中插入一些条件检查，用于验证程序运行时的预期结果。
 * 注意:建议在生产环境中不要过度依赖断言，因为它们通常在正常运行时会被禁用掉。断言适用于开发和测试阶段，可以在开发、调试和测试过程中提供额外的安全性和可靠性。
 * 语法：
 * assert condition;
 * assert condition:expression;
 * 启用断言：默认情况下，断言是禁用的，在idea 编译-》VM选项 设置 -enableassertions 启用断言
 */
public class AssertSimple {
    /**
     * assert原生的的使用方式
     */
    public void example() {

        //方式一
        assert 1 > 5;

        //方式二：(expression)会传入AssertionError对象的构造器，并抓暖胃一个消息字符串
        assert 1 > 5 : "1>5";
    }

    /**
     * 在什么情况下使用断言？
     * 应该是在约定的前置条件有明确的行为情况下，使用断言。
     */
    public void example2(int age) {
        //比如，我们的年龄参数，肯定不可能为负数，是明确的，就可以通过断言来进行条件检查。
        assert age >= 0;
    }

    /**
     * pringBoot 中提供了 Assert 断言工具类，通常用于数据合法性检查
     *
     * @param param
     */
    public void aa(Object param) {
        // 要求参数 object 必须为非空（Not Null），否则抛出异常，不予放行
        // 参数 message 参数用于定制异常信息。
        notNull(param, "参数不能为null");

       // 要求参数必须空（Null），否则抛出异常，不予『放行』。
       // 和 notNull() 方法断言规则相反
        isNull(param, "参数必须为null");

// 要求参数必须为真（True），否则抛出异常，不予『放行』。
        isTrue(true, "参数必须为真（True）");

// 要求参数（List/Set）必须非空（Not Empty），否则抛出异常，不予放行
        notEmpty(new ArrayList<String>(), "参数（List/Set）必须非空（Not Empty）");

// 要求参数（String）必须有长度（即，Not Empty），否则抛出异常，不予放行
        hasLength("字符串", "要求参数（String）必须有长度");

// 要求参数（String）必须有内容（即，Not Blank），否则抛出异常，不予放行
        hasText("字符串", "要求参数（String）必须有内容（即，Not Blank）");

// 要求参数是指定类型的实例，否则抛出异常，不予放行
        isInstanceOf(String.class, "字符串对象", "要求参数是指定类型的实例");

// 要求参数 `subType` 必须是参数 superType 的子类或实现类，否则抛出异常，不予放行
        isAssignable(String.class, "Class subType".getClass(), "要求参数 `subType` 必须是参数 superType 的子类或实现类");
    }
}
