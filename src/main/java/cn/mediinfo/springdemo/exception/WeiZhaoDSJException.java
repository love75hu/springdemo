package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

public class WeiZhaoDSJException extends MsfException {
    public WeiZhaoDSJException(String message) {
        super(message);
    }

    public WeiZhaoDSJException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public ResponseCodeEnum getCode() {
        return ResponseCodeEnum.WEIZHAODSJYC;
    }

}
