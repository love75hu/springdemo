package cn.mediinfo.springdemo.exception;

public class WeiZhaoDSJException extends MsfException {
    public WeiZhaoDSJException(String message) {
        super(message);
    }

    public WeiZhaoDSJException(String message, Exception exception) {
        super(message, exception);
    }

}
