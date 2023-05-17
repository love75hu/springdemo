package cn.mediinfo.springdemo.handler;

import cn.mediinfo.springdemo.exception.CanShuException;
import cn.mediinfo.springdemo.exception.MsfException;
import cn.mediinfo.springdemo.response.MsfResponse;
import cn.mediinfo.springdemo.response.ResponseCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.transform.Result;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleBizException(Exception e) {
        if (e instanceof MsfException msfException) {
            var code = msfException.getCode();
            String message = msfException.getMessage();
            return new ResponseEntity(MsfResponse.fail(code,message,e), HttpStatus.OK);
        } else {
            return new ResponseEntity(MsfResponse.fail(ResponseCodeEnum.WEIZHIYC,e), HttpStatus.OK);
        }
    }
}
