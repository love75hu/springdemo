package cn.mediinfo.springdemo.response;

import java.io.Serializable;

public class MsfResponse<T> implements Serializable {
    private int code;
    private Object message;
    private Object exception;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> MsfResponse<T> success()
    {
        var response =new MsfResponse<T>();
        response.setCode(0);
        return  response;
    }

    public static <T> MsfResponse<T> success(T data)
    {
        var response =new MsfResponse<T>();
        response.setCode(ResponseCodeEnum.SUCCESS.getCode());
        response.setData(data);
        return  response;
    }

    public static MsfResponse fail(ResponseCodeEnum code,Object exception) {
        MsfResponse api = new MsfResponse();
        api.setCode(code.getCode());
        api.setMessage(code.getMessage());
        api.setException(exception);
        return api;
    }

    public static MsfResponse fail(ResponseCodeEnum code, String message,Object exception) {
        MsfResponse api = new MsfResponse();
        api.setCode(code.getCode());
        api.setMessage(message);
        api.setException(exception);
        return api;
    }

    public static MsfResponse fail(ResponseCodeEnum code) {
        MsfResponse api = new MsfResponse();
        api.setCode(code.getCode());
        api.setMessage(code.getMessage());
        return api;
    }
}
