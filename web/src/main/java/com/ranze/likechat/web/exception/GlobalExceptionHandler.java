package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Void> handle(Exception e) {
        log.warn("Handle global exception: " + e.getMessage());
        return Result.failure(-1, e.getMessage());
    }
}
