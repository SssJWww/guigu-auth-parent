package com.stone.system.exception;

import com.stone.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody //用于返回json数据
    public Result error(Exception e){
        return Result.fail().message("执行全局异常处理");
    }

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        return Result.fail().message("执行算数异常处理");
    }

    //自定义异常处理
    @ExceptionHandler(CustomizeException.class)
    @ResponseBody//返回Json数据
    public Result error(CustomizeException e){
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }
}
