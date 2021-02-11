package com.notification.system.notificationservice.service.impl;

import com.notification.system.notificationservice.components.NotificationCache;
import com.notification.system.notificationservice.components.UserCache;
import com.notification.system.notificationservice.model.NotificationModel;
import com.notification.system.notificationservice.repository.NotificationModelRepository;
import com.notification.system.notificationservice.service.NotificationService;
import com.notification.system.notificationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private UserCache userCache;
    @Autowired
    private NotificationCache notificationCache;
    @Autowired
    private NotificationModelRepository notificationModelRepository;
    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public void addNotification(String userId, List<NotificationModel> notificationModels) {
        this.notificationModelRepository.saveAllNotifications(userId, notificationModels, false);
        boolean isUserLogin = this.userService.isUserLogin(userId);
        if (isUserLogin) {
            this.userService.sendNotificationsToUser(userId, notificationModels);
            this.updateNotificationStatus(userId);
        } else {
            if (this.userCache.getUserLogin(userId) != -1) {
                this.notificationCache.updateNotificationCache(userId, notificationModels);
            }
        }
    }

    @Override
    public List<NotificationModel> getNotifications(String userId) {
        List<NotificationModel> notifications;
        if (this.userCache.getUserLogin(userId) != -1) {
            notifications = this.notificationCache.getNotificationsForUser(userId);
        } else {
            notifications = this.notificationModelRepository.getAllNotificationsForUser(userId);
        }
        if (!notifications.isEmpty()) {
            this.notificationModelRepository.updateNotificationFlag(userId);
        }
        return notifications;
    }

    @Override
    public void updateNotificationStatus(String userId) {
        this.notificationModelRepository.updateNotificationFlag(userId);
    }
}
