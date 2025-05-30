package com.cpujazz.front.controller;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cpujazz.common.utils.SpringContextUtil;
import com.cpujazz.front.mapper.UserMapper;
import com.cpujazz.front.pojo.entity.User;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/chat")
public class WebSocketConnect {

    private static final String USER_ID = "user_id_";

    /**
     * key: roomId
     * value: 该房间内所有用户的 Session 集合，key是 userId
     */
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, Session>> roomSessionMap = new ConcurrentHashMap<>();

    private UserMapper userMapper;

    // 发送消息给指定 session
    public static void sendMessage(Session session, String message) {
        try {
            System.out.println("向sid为：" + session.getId() + "，发送：" + message);
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 广播消息给指定房间，excludeSession为排除的session（可为null）
    public static void broadcastToRoom(String roomId, String message, Session excludeSession) {
        ConcurrentHashMap<String, Session> sessions = roomSessionMap.get(roomId);
        if (sessions == null) return;

        sessions.values().forEach(session -> {
            if (session.isOpen() && (excludeSession == null || !session.getId().equals(excludeSession.getId()))) {
                sendMessage(session, message);
            }
        });
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        Map<String, List<String>> params = session.getRequestParameterMap();

        // 获取 token 和 roomId
        String token = params.get("satoken").get(0);
        List<String> roomIds = params.get("roomId");
        if (roomIds == null || roomIds.isEmpty()) {
            session.close();
            throw new IOException("连接失败，缺少roomId参数");
        }
        String roomId = roomIds.get(0);

        // 验证 token，获取 userId
        Object loginId = StpUtil.getLoginIdByToken(token);
        if (loginId == null) {
            session.close();
            throw new SaTokenException("连接失败，无效Token：" + token);
        }
        long userId = SaFoxUtil.getValueByType(loginId, long.class);

        // 初始化 userMapper
        if (userMapper == null) {
            userMapper = SpringContextUtil.getBean(UserMapper.class);
        }

        // 将 session 加入对应房间
        roomSessionMap.putIfAbsent(roomId, new ConcurrentHashMap<>());
        roomSessionMap.get(roomId).put(USER_ID + userId, session);

        // 发送欢迎消息给当前用户
        User user = userMapper.selectByUserId(userId);

        JSONObject helloTips = new JSONObject();
        // TODO: 欢迎提示没有实现
        helloTips.set("sendBy", "服务器");
        helloTips.set("message", user.getUsername() + "加入房间");
        System.out.println(helloTips);
        // 广播给房间内其他用户，排除自己
        broadcastToRoom(roomId, helloTips.toString(), session);

        System.out.println("Web-Socket 连接成功，sid=" + session.getId() + ", userId=" + userId + ", roomId=" + roomId);
    }

    @OnClose
    public void onClose(Session session) {
        // 移除该 session 在所有房间中的映射
        roomSessionMap.forEach((roomId, sessions) -> sessions.entrySet().removeIf(entry -> entry.getValue().getId().equals(session.getId())));
        System.out.println("连接关闭，sid=" + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            JSONObject json = JSONUtil.parseObj(message);
            String roomId = json.getStr("roomId");
            String content = json.getStr("content");

            if (roomId == null || content == null) {
                sendMessage(session, "消息格式错误，缺少 roomId 或 content");
                return;
            }

            // 查找 userId
            String userId = roomSessionMap.get(roomId).entrySet().stream()
                    .filter(entry -> entry.getValue().getId().equals(session.getId()))
                    .map(Map.Entry::getKey)
                    .map(key -> key.replace(USER_ID, ""))
                    .findFirst().orElse("未知");

            if (userMapper == null) {
                userMapper = SpringContextUtil.getBean(UserMapper.class);
            }
            User user = userMapper.selectByUserId(Long.parseLong(userId));

            // 构造消息
            JSONObject messageToSend = new JSONObject();
            messageToSend.set("sendBy", user.getNickname());
            messageToSend.set("message", content);

            broadcastToRoom(roomId, messageToSend.toString(), null);

        } catch (Exception e) {
            log.error("处理消息异常", e);
            sendMessage(session, "服务器处理消息异常");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("sid为：" + session.getId() + "，发生错误");
        log.error(error.getMessage(), error);
    }
}