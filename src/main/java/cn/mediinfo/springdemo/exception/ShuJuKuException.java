package cn.mediinfo.springdemo.exception;

import cn.mediinfo.springdemo.response.ResponseCodeEnum;

public class ShuJuKuException extends MsfException {
    public ShuJuKuException(String message) {
        super(message);
    }

    public ShuJuKuException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public ResponseCodeEnum getCode() {
        return ResponseCodeEnum.SHUJUKYC;
    }

}
