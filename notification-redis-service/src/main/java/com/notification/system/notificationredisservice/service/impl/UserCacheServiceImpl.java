package com.notification.system.notificationredisservice.service.impl;

import com.notification.system.notificationredisservice.repository.UserCacheRepository;
import com.notification.system.notificationredisservice.service.UserCacheService;
import org.springframework.stereotype.Service;

@Service
public class UserCacheServiceImpl implements UserCacheService {

    private final UserCacheRepository userCacheRepository;

    public UserCacheServiceImpl(UserCacheRepository userCacheRepository) {
        this.userCacheRepository = userCacheRepository;
    }

    @Override
    public void setUserLogin(String userId, Long logintime) {
        this.userCacheRepository.setUserLastLogin(userId, logintime);
    }

    @Override
    public Long getUserLastLogin(String userId) {
        return this.userCacheRepository.getUserLastLogin(userId);
    }
}
