package com.example.demo.controller;

import com.example.demo.annotation.Log;
import com.example.demo.bean.Person;
import com.example.demo.bean.TestEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@RestController
@Slf4j
public class DemoController {


    /**
     * 内容协商 根据客户端需要的类型来响应不同的数据格式 响应json 或 xml数据<br>
     * 也可以自定义HttpMessageConverter 响应其他格式数据
     * @return
     */
    @GetMapping("/contentNegotiation")
    @Log
    public Person contentNegotiation(String arg) {
        Person person = new Person();
        person.setAge(10);
        person.setId(100L);
        person.setEmail("fas@gmial.com");
        person.setUserName("tom");
        log.info("{}{}", person, arg);
        return person;
    }

    @GetMapping("/response/entity")
    public ResponseEntity<?> responseEntity() {
        return ResponseEntity.ok("hello world");
    }


    private static final String IMAGE_PATH = "D://img01.jpg"; // 图片路径

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage() throws IOException {
        File file = new File(IMAGE_PATH);
        Resource resource = new FileSystemResource(file);

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] imageBytes = Files.readAllBytes(file.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/enum")
    public void enumTest(@RequestBody TestEnum testEnum) {
        log.info(testEnum.toString());
    }

}
