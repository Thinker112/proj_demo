package com.example.demo.controller;

import com.example.demo.bean.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    /**
     * 内容协商 根据客户端需要的类型来响应不同的数据格式 响应json 或 xml数据<br>
     * 也可以自定义HttpMessageConverter 响应其他格式数据
     * @return
     */
    @GetMapping("/contentNegotiation")
    public Person contentNegotiation() {
        Person person = new Person();
        person.setAge(10);
        person.setId(100L);
        person.setEmail("fas@gmial.com");
        person.setUserName("tom");
        return person;
    }
}
