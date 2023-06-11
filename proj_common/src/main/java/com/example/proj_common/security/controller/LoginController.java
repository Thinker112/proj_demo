package com.example.proj_common.security.controller;

import com.example.proj_common.annotation.CosmoController;
import com.example.proj_common.security.domain.LoginUser;
import com.example.proj_common.security.entity.User;
import com.example.proj_common.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CosmoController
@RequiredArgsConstructor
public class LoginController {

    final AuthenticationManager authenticationManager;

    final RedisTemplate<Object, Object> redisTemplate;

    @PostMapping("/user/login")
    public Map<String, String> login(@RequestBody User user){

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String jwt = JwtUtil.createJWT(loginUser.getUser().getId().toString());
        //存入Redis中
        redisTemplate.opsForValue().set("login:" + loginUser.getUser().getId(), loginUser);

        Map<String, String> resultMap = new HashMap<>();

        resultMap.put("token", jwt);

        return resultMap;
    }

}
