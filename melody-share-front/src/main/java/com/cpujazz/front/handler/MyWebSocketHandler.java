package com.cpujazz.front.handler;

import lombok.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理 WebSocket 连接
 */
public class MyWebSocketHandler extends TextWebSocketHandler {

    /**
     * 固定前缀
     */
    private static final String USER_ID = "user_id_";

    /**
     * 存放Session集合，方便推送消息
     */
    private static final ConcurrentHashMap<String, WebSocketSession> webSocketSessionMaps = new ConcurrentHashMap<>();

    // 向指定客户端推送消息
    public static void sendMessage(WebSocketSession session, String message) {
        try {
            System.out.println("向sid为：" + session.getId() + "，发送：" + message);
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 向指定用户推送消息
    public static void sendMessage(long userId, String message) {
        WebSocketSession session = webSocketSessionMaps.get(USER_ID + userId);
        if (session != null) {
            sendMessage(session, message);
        }
    }

    // 监听：连接开启
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

        // put到集合，方便后续操作
        String userId = session.getAttributes().get("userId").toString();
        webSocketSessionMaps.put(USER_ID + userId, session);


        // 给个提示
        String tips = "Web-Socket 连接成功，sessionId=" + session.getId() + ", userId=" + userId;
        System.out.println(tips);
        sendMessage(session, tips);
    }

    // 监听：连接关闭
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      @NonNull CloseStatus status) {
        // 从集合移除
        String userId = session.getAttributes().get("userId").toString();
        webSocketSessionMaps.remove(USER_ID + userId);

        // 给个提示
        String tips = "Web-Socket 连接关闭，sid=" + session.getId() + "，userId=" + userId;
        System.out.println(tips);
    }

    // 收到消息
    @Override
    public void handleTextMessage(WebSocketSession session,
                                  @NonNull TextMessage message) {
        System.out.println("sid为：" + session.getId() + "，发来：" + message);
    }

}