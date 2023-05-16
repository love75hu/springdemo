package cn.mediinfo.springdemo.exception;

public class WeiZhiException extends MsfException {
    public WeiZhiException(String message) {
        super(message);
    }

    public WeiZhiException(String message, Exception exception) {
        super(message, exception);
    }

}
