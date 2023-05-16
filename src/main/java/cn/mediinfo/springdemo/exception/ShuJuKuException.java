package cn.mediinfo.springdemo.exception;

public class ShuJuKuException extends MsfException {
    public ShuJuKuException(String message) {
        super(message);
    }

    public ShuJuKuException(String message, Exception exception) {
        super(message, exception);
    }

}
