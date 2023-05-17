package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

public class YuanChengException extends MsfException {
    public YuanChengException(String message) {
        super(message);
    }

    public YuanChengException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public ResponseCodeEnum getCode() {
        return ResponseCodeEnum.YUANCHENGYC;
    }

}
