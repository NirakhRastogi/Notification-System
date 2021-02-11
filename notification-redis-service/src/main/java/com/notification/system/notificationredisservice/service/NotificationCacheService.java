package com.notification.system.notificationredisservice.service;


import com.notification.system.notificationredisservice.model.NotificationModel;

import java.util.List;

public interface NotificationCacheService {

    void addNotification(String userId, List<NotificationModel> notificationModels);

    List<NotificationModel> getNotifications(String userId);

}
