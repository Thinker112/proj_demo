package com.example.proj_common.exception;

import com.example.proj_common.Enum.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final ResponseCodeEnum error;

    /**
     * 构造器，有时我们需要将第三方异常转为自定义异常抛出，但又不想丢失原来的异常信息，此时可以传入cause
     *
     * @param error
     * @param cause
     */
    public BizException(ResponseCodeEnum error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    /**
     * 构造器，只传入错误枚举
     *
     * @param error
     */
    public BizException(ResponseCodeEnum error) {
        this.error = error;
    }
}