package com.example.proj_common.exception;

import com.example.proj_common.Enum.ResponseCodeEnum;
import com.example.proj_common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public Result<ResponseCodeEnum> handleBizException(BizException bizException) {
        log.error("业务异常:{}", bizException.getMessage(), bizException);
        return Result.error(bizException.getError());
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<ResponseCodeEnum> handleRunTimeException(RuntimeException e) {
        log.error("运行时异常: {}", e.getMessage(), e);
        return Result.error(ResponseCodeEnum.ERROR);
    }
}
