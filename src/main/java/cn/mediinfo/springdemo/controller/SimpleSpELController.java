package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Spel有三个核心接口
 * ExpressionParser，它是Spel的处理接口，默认实现类是SpelExpressionParser
 * Expression 它的默认实现是SpELExpression,主要对外提供的接口就是根据表达式获得表达式响应的结果，最重要的一个参数是EvaluationContext
 * EvaluationContext 它表示解析String表达式所需要的上下文，如寻找Root是谁，反射解析Method\Filed\Construactor的解析器和取值所需的上下文
 */
@RestController
@Slf4j
@RequestMapping({"api/v1/spel"})
@RequiredArgsConstructor
public class SimpleSpELController {

    int one=1;
    long two=2L;
    float three=3.11F;
    double four=4.4D;

    //获取今年的总天数
    int days= LocalDate.now().lengthOfYear();
    ArrayList<String> list=new ArrayList<>();

    private static final Logger logger= LoggerFactory.getLogger(SimpleSpELController.class);


    private final cn.mediinfo.springdemo.service.ClientScopeService ClientScopeService;

    //ExpressionParser 是Spel的处理接口，默认实现类是SpelExpressionParser
    private org.springframework.expression.ExpressionParser parser= new SpelExpressionParser();
    @Operation(summary = "SpEL 初体验")
    @RequestMapping
    public MsfResponse HelloWord()
    {
        logger.error("用户输入参数{0}，异常消息{1}","参数1","异常消息");
        Expression exp= parser.parseExpression("hello word");
        return MsfResponse.success(exp.getValue());
    }

    @Operation(summary = "SpEL 功能特性")
    @RequestMapping
    public MsfResponse FunctionAttribute()
    {
        //Spel支持很多功能特性，比如调用方法、访问属性、调用构造函数等，模版引擎实现？
        //字符串拼接示例
        Expression exp= parser.parseExpression("'hello word'.concat('谭')");

        //属性嵌套访问示例.prop1.prop2.prop3
        Expression exp2= parser.parseExpression("'hello word'.concat('谭').bytes.length");

        //调用构造方法
        Expression exp3= parser.parseExpression("new String('hello word').toUpperCase()");

        return MsfResponse.success(exp.getValue());
    }

    @Operation(summary = "SpEL EvaluationContext")
    @RequestMapping
    public MsfResponse EvaluationContext()
    {

        //通过EvaluationContext设置RootObject等于我们新建的scopeEntity
        var scopeEntity=ClientScopeService.Get();
        EvaluationContext context=new StandardEvaluationContext(scopeEntity);

        //1-1.根据getScope方法获取 ClientscopeEntity 的 scope 值
        Expression exp= parser.parseExpression("getScope");
        //1-2.根据我们设置的context取值
        String scope= (String) exp.getValue(context);

       //2.利用SpEL 进行表达式运算,判断取值是否等于scope
        Expression exp2= parser.parseExpression("getScope=='scope'");
        boolean boolResult= (boolean) exp2.getValue(context);

        return MsfResponse.success();
    }
}
