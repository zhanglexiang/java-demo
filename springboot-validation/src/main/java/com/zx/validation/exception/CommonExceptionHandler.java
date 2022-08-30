package com.zx.validation.exception;

import com.zx.common.result.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.StringJoiner;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HttpResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringJoiner joiner = new StringJoiner("");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            // 拼接错误信息， 参数名:message；
            joiner.add(fieldError.getField()).add(":").add(fieldError.getDefaultMessage()).add("; ");
        }
        String msg = joiner.toString();
       return HttpResult.fail(msg);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HttpResult handleConstraintViolationException(ConstraintViolationException ex) {
        return HttpResult.fail(ex.getMessage());
    }


    // 全局异常处理
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HttpResult handleException(Exception ex) {
        log.error("未知错误", ex);
        return HttpResult.fail(ex.getMessage());
    }

}
