package cn.mediinfo.springdemo.exception;

public class TongYongYWException extends MsfException {
    private static String Code;
    public void setCode(String name) {
        this.Code = name;
    }

    public TongYongYWException(String message) {
        super(message);
    }

    public TongYongYWException(String message, Exception exception) {
        super(message, exception);
    }
}