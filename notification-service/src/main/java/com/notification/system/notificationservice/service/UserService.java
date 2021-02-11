package com.notification.system.notificationservice.service;

import com.notification.system.notificationservice.model.NotificationModel;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface UserService {

    SseEmitter registerUser(String userId);

    void sendNotificationsToUser(String userToSend, List<NotificationModel> userNotifications);

    boolean isUserLogin(String userId);
}
