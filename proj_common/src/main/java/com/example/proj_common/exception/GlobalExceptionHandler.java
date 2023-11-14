package com.example.proj_common.exception;

import com.example.proj_common.enums.ResponseCodeEnum;
import com.example.proj_common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Result<ResponseCodeEnum> handleBizException(BizException bizException, HttpServletRequest servletRequest) {
        String requestURI = servletRequest.getRequestURI();
        log.error("请求路径: {}, 业务异常:{}", requestURI, bizException.getMessage());
        return Result.error(bizException.getError());
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<ResponseCodeEnum> handleRunTimeException(RuntimeException e, HttpServletRequest servletRequest) {
        String requestURI = servletRequest.getRequestURI();
        log.error("请求路径: {}, 运行时异常: {}", requestURI, e.getMessage(), e);
        return Result.error(ResponseCodeEnum.ERROR);
    }
}
