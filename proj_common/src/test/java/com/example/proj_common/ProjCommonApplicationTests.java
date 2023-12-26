package com.example.proj_common;

import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ProjCommonApplicationTests {

    @Test
    void contextLoads() {

        Bean bean = new Bean();
        bean.setName("tom");
        bean.setId("32");
        String jsonString = JSONObject.toJSONString(bean);
        System.out.println("jsonString = " + jsonString);
    }

}
