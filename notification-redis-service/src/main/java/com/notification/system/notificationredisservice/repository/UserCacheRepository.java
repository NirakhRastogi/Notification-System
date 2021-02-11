package com.notification.system.notificationredisservice.repository;

import java.util.Map;

public interface UserCacheRepository {
    void setUserLastLogin(String userId, Long timestamp);

    Long getUserLastLogin(String userId);

    Map<String, Long> getAllUsersDetails();

    void deleteUserLoginDetail(String userId);
}
