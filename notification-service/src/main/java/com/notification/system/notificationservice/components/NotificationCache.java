package com.notification.system.notificationservice.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.system.notificationservice.model.NotificationModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationCache {

    @Value("${endpoints.notification-cache-service}")
    private String notificationCacheServiceEndpoint;

    private final RestTemplate restTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    public void updateNotificationCache(String userId, List<NotificationModel> notificationModels) {
        try {
            this.restTemplate.postForObject(notificationCacheServiceEndpoint + "/notification/" + userId, notificationModels, String.class);
        } catch (RestClientException re) {
            LOGGER.error("RestClient exception occurred while calling updateNotificationCache, {}", re.getMessage(), re);
        }
    }

    public List<NotificationModel> getNotificationsForUser(String userId) {
        try {
            return mapper.readValue(
                    this.restTemplate.getForObject(notificationCacheServiceEndpoint + "/notification/" + userId, String.class), new TypeReference<List<NotificationModel>>() {
                    });
        } catch (JsonProcessingException jpe) {
            LOGGER.error("JsonProcessing exception occurred while calling setUserLogin, {}", jpe.getMessage(), jpe);
            return new ArrayList<>();
        } catch (RestClientException re) {
            LOGGER.error("RestClient exception occurred while calling setUserLogin, {}", re.getMessage(), re);
            return new ArrayList<>();
        }
    }

}
