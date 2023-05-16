package cn.mediinfo.springdemo.exception;

public class ShouQuanException extends MsfException {
    public ShouQuanException(String message) {
        super(message);
    }

    public ShouQuanException(String message, Exception exception) {
        super(message, exception);
    }

}
