package com.notification.system.notificationredisservice.repository;

import com.notification.system.notificationredisservice.model.NotificationModel;

import java.util.List;

public interface NotificationRepository {

    void addNotification(String userId, NotificationModel notificationModel);

    List<NotificationModel> getAllNotificationsForUser(String userId);

    void deleteAllNotificationsForUser(String userId);

}
