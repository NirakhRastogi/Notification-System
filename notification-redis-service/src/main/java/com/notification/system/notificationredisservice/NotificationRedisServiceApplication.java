package com.notification.system.notificationredisservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotificationRedisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationRedisServiceApplication.class, args);
    }

}
