package cn.mediinfo.springdemo.handler;

import cn.mediinfo.springdemo.exception.MsfException;
import cn.mediinfo.springdemo.response.MsfResponse;
import cn.mediinfo.springdemo.response.ResponseCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity handleBizException(Exception e) {
        if (e instanceof MsfException msfException) {

            //自定义异常
            var code = msfException.getCode();
            String message = msfException.getMessage();
            return new ResponseEntity(MsfResponse.fail(code, message, e), HttpStatus.OK);

        } else if (e instanceof MethodArgumentNotValidException) {

            //参数校验异常
            var BindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            var FieldErrors = BindingResult.getFieldErrors();
            StringBuilder sb = new StringBuilder("参数校验失败：");
            for (var FieldError : FieldErrors) {
                sb.append(FieldError.getField()).append(FieldError.getDefaultMessage()).append(",");
            }
            return new ResponseEntity(MsfResponse.fail(ResponseCodeEnum.CANSHUYC, sb.toString()), HttpStatus.OK);

        } else {

            //其他异常
            return new ResponseEntity(MsfResponse.fail(ResponseCodeEnum.WEIZHIYC, e.getMessage()), HttpStatus.OK);
        }
    }
}
