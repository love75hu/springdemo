package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

import java.util.Enumeration;

/**
 * 自定义异常基类
 */
public abstract class MsfException extends Exception {

    public MsfException(String message) {super(message);}

    public MsfException(String message, Exception exception) {
        super(message, exception);
    }

    public abstract ResponseCodeEnum getCode();

}
