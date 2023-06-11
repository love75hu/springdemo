package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

public class TongYongYWException extends MsfException {
    private static ResponseCodeEnum Code;
    public void setCode(ResponseCodeEnum name) {
        Code = name;
    }

    public TongYongYWException(String message) {
        super(message);
    }

    public TongYongYWException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public ResponseCodeEnum getCode() {
        return Code;
    }
}