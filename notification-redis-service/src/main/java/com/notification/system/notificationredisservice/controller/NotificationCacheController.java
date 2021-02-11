package com.notification.system.notificationredisservice.controller;

import com.notification.system.notificationredisservice.dto.NotificationDto;
import com.notification.system.notificationredisservice.model.NotificationModel;
import com.notification.system.notificationredisservice.service.NotificationCacheService;
import com.notification.system.notificationredisservice.transformer.NotificationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationCacheController {

    @Autowired
    private NotificationCacheService notificationCacheService;


    @PostMapping("/{userId}")
    public void addNotifications(@PathVariable("userId") String userId, @RequestBody List<NotificationDto> notificationModelList) {
        this.notificationCacheService.addNotification(userId, NotificationTransformer.transform(notificationModelList));
    }

    @GetMapping("/{userId}")
    public List<NotificationModel> getNotifications(@PathVariable("userId") String userId) {
        return this.notificationCacheService.getNotifications(userId);
    }

}
