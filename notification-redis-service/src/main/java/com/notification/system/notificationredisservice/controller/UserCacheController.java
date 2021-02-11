package com.notification.system.notificationredisservice.controller;

import com.notification.system.notificationredisservice.service.UserCacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCacheController {

    private final UserCacheService userCacheService;

    public UserCacheController(UserCacheService userCacheService) {
        this.userCacheService = userCacheService;
    }

    @PutMapping("/login/{userId}/{timestamp}")
    public void setUserLogin(@PathVariable("userId") String userId, @PathVariable("timestamp") Long logintime) {
        this.userCacheService.setUserLogin(userId, logintime);
    }

    @GetMapping("/login/{userId}")
    public Long getUserLastLogin(@PathVariable("userId") String userId) {
        return this.userCacheService.getUserLastLogin(userId);
    }


}
