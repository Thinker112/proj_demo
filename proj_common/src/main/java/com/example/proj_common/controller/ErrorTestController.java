package com.example.proj_common.controller;

import com.example.proj_common.annotation.CosmoController;
import com.example.proj_common.security.domain.User;
import com.example.proj_common.security.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;

@CosmoController
@RequiredArgsConstructor
public class ErrorTestController {

    final RedisTemplate<Object, Object> redisTemplate;

    private final JPAQueryFactory queryFactory;

    private final UserRepository userRepository;

    @GetMapping("/error/test")
    public String errorTest(){

        int i = 1/0;

        return "false";
    }

    @GetMapping("/StringTest")
    public String stringTest(String str){

        redisTemplate.opsForValue().set("test:222", "11111111");

        return "hello";
    }

    @GetMapping("/createUser")
    public User createUser(){
        User user = new User();
        user.setUserName("yyb2");
        user.setPassword("abc1234");
        user.setNickName("eric");
        user.setUserType("1");
        user.setDelFlag(0);
        User save = userRepository.save(user);
        return save;
    }


}
