package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

public class WeiZhiException extends MsfException {
    public WeiZhiException(String message) {
        super(message);
    }

    public WeiZhiException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public ResponseCodeEnum getCode() {
        return ResponseCodeEnum.WEIZHIYC;
    }

}
