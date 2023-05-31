package com.example.proj_common.domain;

import com.example.proj_common.Enum.ResponseCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    /**
     * 带数据成功返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDesc(), data);
    }

    /**
     * 不带数据成功返回
     *
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 通用错误返回
     *
     * @param responseCodeEnum
     * @return
     */
    public static <T> Result<T> error(ResponseCodeEnum responseCodeEnum) {
        return new Result<>(responseCodeEnum.getCode(), responseCodeEnum.getDesc());
    }

    /**
     * 通用错误返回
     *
     * @param responseCodeEnum
     * @param msg
     * @return
     */
    public static <T> Result<T> error(ResponseCodeEnum responseCodeEnum, String msg) {
        return new Result<>(responseCodeEnum.getCode(), msg);
    }

    /**
     * 通用错误返回
     *
     * @param responseCodeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ResponseCodeEnum responseCodeEnum, T data) {
        return new Result<>(responseCodeEnum.getCode(), responseCodeEnum.getDesc(), data);
    }

}