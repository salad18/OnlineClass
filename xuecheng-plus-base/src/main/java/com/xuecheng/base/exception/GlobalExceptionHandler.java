package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SS
 * @DATE: 2023/12/31 15:41
 * @Decription: TODO
 * @Version 1.0
 **/
@Slf4j
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 对自定义异常处理
//    @ResponseBody
    @ExceptionHandler(XueChengPlusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(XueChengPlusException e) {

        // 记录异常
        log.error("系统异常{}", e.getErrMessage(), e);

        // 解析出异常信息
        String errMessage = e.getErrMessage();
        return new RestErrorResponse(errMessage);
    }

    // 运行异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(Exception e) {

        // 记录异常
        log.error("系统异常{}", e.getMessage(), e);

        // 解析出异常信息
        String errMessage = e.getMessage();
        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }

    // MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        // 存放错误信息
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(item->{
            errors.add(item.getDefaultMessage());
        });

        // 将list中的错误信息拼接
        String errMessage = StringUtils.join(errors, ",");

        // 记录异常
        log.error("系统异常{}", e.getMessage(), errMessage);

        return new RestErrorResponse(errMessage);
    }
}
