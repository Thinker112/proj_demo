package com.example.websocket_demo.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SipWebSocketHandler  extends TextWebSocketHandler {

    private static final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
        log.info("[{}] 握手成功, 当前已连接的会话数量: [{}]",
                session.getRemoteAddress(),
                sessionMap.size());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("收到来自客户端 [{}] 的消息", session.getRemoteAddress());
        log.info(message.getPayload().toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("传输错误,原因: {}", exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        log.info("客户端: [{}] 关闭WebSocket连接, 当前会话数量: [{}]",
                session.getRemoteAddress(),
                sessionMap.size());
    }

}
