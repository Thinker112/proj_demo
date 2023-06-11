package com.example.proj_common.security.handler;

import com.alibaba.fastjson2.JSON;
import com.example.proj_common.domain.Result;
import com.example.proj_common.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于处理认证过程中的失败情况<br>
 * 可能是由于用户提供的凭据不正确或其他身份验证过程中的错误导致的。<br>
 * 当身份验证失败时，AuthenticationFailureHandler将被调用
 */
@Component
@Slf4j
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error("用户名或密码不正确");
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(Result.error(ResponseCodeEnum.ERROR_AUTH)));
    }
}
