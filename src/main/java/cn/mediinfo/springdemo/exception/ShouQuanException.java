package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

public class ShouQuanException extends MsfException {
    public ShouQuanException(String message) {
        super(message);
    }

    public ShouQuanException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public ResponseCodeEnum getCode() {
        return ResponseCodeEnum.SHOUQUANYC;
    }

}
