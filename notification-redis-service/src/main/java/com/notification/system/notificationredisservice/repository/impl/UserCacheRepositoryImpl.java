package com.notification.system.notificationredisservice.repository.impl;


import com.notification.system.notificationredisservice.repository.UserCacheRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserCacheRepositoryImpl implements UserCacheRepository {

    private final HashOperations<String, String, Object> hashOperations;

    public UserCacheRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void setUserLastLogin(String userId, Long timestamp) {
        this.hashOperations.put("USER-LOGIN", userId, timestamp);
    }

    @Override
    public Long getUserLastLogin(String userId) {
        if (this.hashOperations.hasKey("USER-LOGIN", userId)) {
            return (Long) this.hashOperations.get("USER-LOGIN", userId);
        }
        return -1L;
    }

    @Override
    public Map<String, Long> getAllUsersDetails() {
        return (HashMap<String, Long>) (Map) this.hashOperations.entries("USER-LOGIN");
    }

    @Override
    public void deleteUserLoginDetail(String userId) {
        this.hashOperations.delete("USER-LOGIN", userId);
    }
}
