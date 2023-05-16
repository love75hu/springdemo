package cn.mediinfo.springdemo.exception;

public class YuanChengException extends MsfException {
    public YuanChengException(String message) {
        super(message);
    }

    public YuanChengException(String message, Exception exception) {
        super(message, exception);
    }

}
