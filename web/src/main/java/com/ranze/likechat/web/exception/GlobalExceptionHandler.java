package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.Result;
import com.ranze.likechat.web.result.ResultStatEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Void> handle(Exception e) {
        log.warn("Handle unknown exception: " + e.getMessage());
        return Result.failure(ResultStatEnum.INNER_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result<Void> handle(BusinessException e) {
        log.warn("Handle BusinessException: " + e.getMessage());
        return Result.failure(e.getResultStatEnum());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<Void> handle(MethodArgumentNotValidException e) {
        log.warn("Handle MethodArgumentNotValidException: " + e.getMessage());
        return Result.failure(ResultStatEnum.PARAMETER_ERROR);
    }
}
