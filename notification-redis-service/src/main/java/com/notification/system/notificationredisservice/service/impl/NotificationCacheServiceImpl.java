package com.notification.system.notificationredisservice.service.impl;


import com.notification.system.notificationredisservice.model.NotificationModel;
import com.notification.system.notificationredisservice.repository.NotificationRepository;
import com.notification.system.notificationredisservice.repository.UserCacheRepository;
import com.notification.system.notificationredisservice.service.NotificationCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationCacheServiceImpl implements NotificationCacheService {

    private final NotificationRepository notificationRepository;
    private final UserCacheRepository userCacheRepository;


    @Override
    public void addNotification(String userId, List<NotificationModel> notificationModels) {
        notificationModels
                .forEach(notificationModel -> notificationRepository.addNotification(userId, notificationModel));
    }

    @Override
    public List<NotificationModel> getNotifications(String userId) {
        List<NotificationModel> notificationModels = this.notificationRepository.getAllNotificationsForUser(userId);
        if (!notificationModels.isEmpty()) {
            this.notificationRepository.deleteAllNotificationsForUser(userId);
            this.userCacheRepository.deleteUserLoginDetail(userId);
        }

        return notificationModels;
    }
}
