package com.notification.system.notificationservice.controller;

import com.notification.system.notificationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/register/{userId}")
    public SseEmitter registerUser(@PathVariable("userId") String userId) {
        return this.userService.registerUser(userId);
    }


}
