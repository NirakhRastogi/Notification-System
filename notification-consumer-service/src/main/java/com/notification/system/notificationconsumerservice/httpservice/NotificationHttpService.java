package com.notification.system.notificationconsumerservice.httpservice;

import com.notification.system.notificationconsumerservice.model.NotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class NotificationHttpService {

    @Value("${endpoints.notification-service}")
    private String notificationServiceEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public void addNotification(List<NotificationModel> notificationModels, String userId) {
        this.restTemplate.postForObject(notificationServiceEndpoint + "/notification/" + userId, notificationModels, String.class);
    }

}
