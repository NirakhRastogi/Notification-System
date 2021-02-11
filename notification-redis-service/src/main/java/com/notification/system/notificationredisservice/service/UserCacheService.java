package com.notification.system.notificationredisservice.service;

public interface UserCacheService {
    void setUserLogin(String userId, Long logintime);

    Long getUserLastLogin(String userId);
}
