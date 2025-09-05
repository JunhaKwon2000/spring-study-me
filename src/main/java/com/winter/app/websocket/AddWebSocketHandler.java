package com.winter.app.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AddWebSocketHandler implements WebSocketHandler {

    private List<WebSocketSession> users = new ArrayList<>();
    private Map<String, WebSocketSession> userMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // WebSocket으로 연결 되었을 때 실행
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userMap.put(auth.getName(), session);

        log.info("{}", session);
        users.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 사용자가 메시지를 전송했을 때 실행
        log.info("{}", message.getPayload().toString());
        users.forEach(user -> {
            try {
                user.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 메시지 전달 시 에러가 발생했을 때 실행
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
