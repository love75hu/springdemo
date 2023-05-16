package cn.mediinfo.springdemo.exception;

public class KuangJiaException extends MsfException {
    public KuangJiaException(String message) {
        super(message);
    }

    public KuangJiaException(String message, Exception exception) {
        super(message, exception);
    }

}

