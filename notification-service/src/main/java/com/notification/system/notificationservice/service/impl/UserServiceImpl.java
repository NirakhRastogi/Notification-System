package com.notification.system.notificationservice.service.impl;

import com.notification.system.notificationservice.components.UserCache;
import com.notification.system.notificationservice.model.NotificationModel;
import com.notification.system.notificationservice.service.NotificationService;
import com.notification.system.notificationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final NotificationService notificationService;
    private final UserCache userCache;
    private static final Map<String, SseEmitter> EMITTER_MAP = new HashMap<>();


    @Override
    public SseEmitter registerUser(String userId) {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitter.onTimeout(() -> {
            LOGGER.info("User id {} unsubscribed...", userId);
            EMITTER_MAP.remove(userId);
        });
        EMITTER_MAP.put(userId, sseEmitter);
        List<NotificationModel> userNotifications = this.notificationService.getNotifications(userId);
        this.userCache.setUserLogin(userId);
        sendNotificationsToUser(userId, userNotifications);
        return sseEmitter;
    }

    @Override
    public void sendNotificationsToUser(String userId, List<NotificationModel> userNotifications) {
        EMITTER_MAP.entrySet().stream()
                .filter(sseEntry -> sseEntry.getKey().equals(userId))
                .forEach(sseEntry -> {
                    try {
                        sseEntry.getValue().send(userNotifications);
                    } catch (IOException e) {
                        throw new RuntimeException("Unable to send notifications to user" + userId);
                    }
                });
    }

    @Override
    public boolean isUserLogin(String userId) {
        return EMITTER_MAP.keySet().stream()
                .anyMatch(id -> id.equals(userId));
    }

}
