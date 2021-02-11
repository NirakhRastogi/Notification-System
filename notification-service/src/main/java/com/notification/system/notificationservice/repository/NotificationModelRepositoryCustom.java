package com.notification.system.notificationservice.repository;

import com.notification.system.notificationservice.model.NotificationModel;

import java.util.List;


public interface NotificationModelRepositoryCustom {

    void saveAllNotifications(String userId, List<NotificationModel> notificationModels, Boolean isUserLogin);

    List<NotificationModel> getAllNotificationsForUser(String userId);

    void updateNotificationFlag(List<String> notificationId);

    void updateNotificationFlag(String userId);
}
