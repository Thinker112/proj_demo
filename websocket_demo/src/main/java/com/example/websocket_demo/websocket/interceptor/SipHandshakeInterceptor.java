package com.example.websocket_demo.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

/**
 * SIP-WebSocket握手拦截器
 */
@Slf4j
public class SipHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 获取客户端请求的子协议
        List<String> protocols = request.getHeaders().get("Sec-WebSocket-Protocol");
        if (!CollectionUtils.isEmpty(protocols)) {
            String protocol = protocols.get(0);
            log.debug("WebSocket requested protocol: " + protocol);
            // 验证子协议是否合法，如果合法，就返回给客户端，如果不合法，就拒绝连接
            if ("sip".equals(protocol)) {
                response.getHeaders().set("Sec-WebSocket-Protocol", protocol);
                return true;
            } else {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return false;
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
