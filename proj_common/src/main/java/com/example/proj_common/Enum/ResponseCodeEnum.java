package com.example.proj_common.Enum;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum ResponseCodeEnum {
    /**
     * 通用结果
     */
    ERROR(-1, "网络错误"),
    SUCCESS(200, "成功"),

    /**
     * 用户登录
     */
    NEED_LOGIN(900, "用户未登录"),

    /**
     * 参数校验
     */
    ERROR_PARAM(10000, "参数错误"),
    EMPTY_PARAM(10001, "参数为空"),
    ERROR_PARAM_LENGTH(10002, "参数长度错误");

    private final Integer code;
    private final String desc;

    ResponseCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, ResponseCodeEnum> ENUM_CACHE = new HashMap<>();

    static {
        for (ResponseCodeEnum responseCodeEnum : ResponseCodeEnum.values()) {
            ENUM_CACHE.put(responseCodeEnum.code, responseCodeEnum);
        }
    }

    public static String getDesc(Integer code) {
        return Optional.ofNullable(ENUM_CACHE.get(code))
                .map(ResponseCodeEnum::getDesc)
                .orElseThrow(() -> new IllegalArgumentException("invalid exception code!"));
    }
}
