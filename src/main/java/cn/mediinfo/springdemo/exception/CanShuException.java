package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

public class CanShuException extends MsfException{
    public CanShuException(String message) {
        super(message);
    }

    public CanShuException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public ResponseCodeEnum getCode() {
        return ResponseCodeEnum.CANSHUYC;
    }
}