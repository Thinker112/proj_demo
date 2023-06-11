package com.example.proj_common.security.handler;


import com.alibaba.fastjson2.JSON;
import com.example.proj_common.domain.Result;
import com.example.proj_common.enums.ResponseCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于处理认证过程中的未经身份验证的用户访问受保护资源的情况。<br>
 * 当用户尝试访问需要身份验证的资源，但未提供有效的凭据时，AuthenticationEntryPoint将被调用
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(Result.error(ResponseCodeEnum.ERROR_NOT_LOGIN)));
    }
}
