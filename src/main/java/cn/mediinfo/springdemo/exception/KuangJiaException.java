package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

public class KuangJiaException extends MsfException {
    public KuangJiaException(String message) {
        super(message);
    }

    public KuangJiaException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public ResponseCodeEnum getCode() {
        return ResponseCodeEnum.KUANGJIAYC;
    }

}

