package com.notification.system.notificationservice.controller;

import com.notification.system.notificationservice.model.NotificationModel;
import com.notification.system.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification/{userId}")
    public void addNotifications(@PathVariable("userId") String userId, @RequestBody List<NotificationModel> notificationModels) {
        this.notificationService.addNotification(userId, notificationModels);
    }


    @GetMapping("/notification/{userId}")
    public List<NotificationModel> getNotifications(@PathVariable("userId") String userId) {
        return this.notificationService.getNotifications(userId);
    }

}
