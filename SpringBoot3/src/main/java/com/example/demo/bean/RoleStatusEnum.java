package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author yueyubo
 * @date 2024-06-30
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RoleStatusEnum {
    NORMAL(0,"启用"),
    DISABLE(1,"禁用");

    @JsonValue
    @Getter
    private final int code;
    @Getter
    private final String desc;

    RoleStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
