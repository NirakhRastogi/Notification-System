package com.notification.system.notificationservice.service;

import com.notification.system.notificationservice.model.NotificationModel;

import java.util.List;

public interface NotificationService {


    void addNotification(String userId, List<NotificationModel> notificationModels);

    List<NotificationModel> getNotifications(String userId);

    void updateNotificationStatus(String userId);
}
