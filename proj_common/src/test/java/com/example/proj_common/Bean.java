package com.example.proj_common;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bean {

    @JSONField(ordinal = 3)
    private String name;

    @JSONField(ordinal = 2)
    private String address;

    @JSONField(ordinal = 1)
    private String id;

    public Bean() {
        this.name = "NULL";
        this.address = "NULL";
    }
}
