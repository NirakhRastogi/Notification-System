package com.notification.system.notificationredisservice.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.system.notificationredisservice.model.NotificationModel;
import com.notification.system.notificationredisservice.repository.NotificationRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final HashOperations<String, String, Object> hashOperations;
    private static final ObjectMapper mapper = new ObjectMapper();

    public NotificationRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void addNotification(String userId, NotificationModel notificationModel) {
        this.hashOperations.put(userId, String.valueOf(notificationModel.getId()), notificationModel);
    }

    @Override
    public List<NotificationModel> getAllNotificationsForUser(String userId) {
        return mapper.convertValue(this.hashOperations.entries(userId).values(), new TypeReference<List<NotificationModel>>() {
        });
    }

    @Override
    public void deleteAllNotificationsForUser(String userId) {
        this.hashOperations.getOperations().delete(userId);
    }
}
