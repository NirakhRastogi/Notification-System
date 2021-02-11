package com.notification.system.notificationservice.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCache {

    @Value("${endpoints.notification-cache-service}")
    private String notificationCacheServiceEndpoint;

    private final RestTemplate restTemplate;

    public Long getUserLogin(String userId) {
        try {
            return this.restTemplate.getForObject(notificationCacheServiceEndpoint + "/user/login/" + userId, Long.class);
        } catch (RestClientException re) {
            LOGGER.error("RestClient exception occurred while calling getUserLogin, {}", re.getMessage(), re);
            return -1L;
        }
    }

    public void setUserLogin(String userId) {
        try {
            this.restTemplate.put(notificationCacheServiceEndpoint + "/user/login/" + userId + "/" + System.currentTimeMillis(), null);
        } catch (RestClientException re) {
            LOGGER.error("RestClient exception occurred while calling setUserLogin, {}", re.getMessage(), re);
        }
    }

}
