package cn.mediinfo.springdemo.exception;

/**
 * 代码异常
 */
public class DaiMaException extends MsfException{
    public DaiMaException(String message) {
        super(message);
    }

    public DaiMaException(String message, Exception exception) {
        super(message, exception);
    }
}
