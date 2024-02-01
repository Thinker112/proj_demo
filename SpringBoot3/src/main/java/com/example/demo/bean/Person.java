package com.example.demo.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement  // 可以写出为xml文档
public class Person {
    private Long id;
    private String userName;
    private String email;
    private Integer age;
}
