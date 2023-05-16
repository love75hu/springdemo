package cn.mediinfo.springdemo.exception;

public class CanShuException extends MsfException{
    public CanShuException(String message) {
        super(message);
    }

    public CanShuException(String message, Exception exception) {
        super(message, exception);
    }
}