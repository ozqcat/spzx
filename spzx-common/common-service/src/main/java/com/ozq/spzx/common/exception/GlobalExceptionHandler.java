package com.ozq.spzx.common.exception;

import com.ozq.spzx.model.vo.common.Result;
import com.ozq.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author ozq
 * @Date 2023/11/13 20:44
 * @Description
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public Result exceptionHandler(BusinessException e){
        e.printStackTrace();
        return Result.build(null,e.getCode(),e.getMessage());
    }
}
