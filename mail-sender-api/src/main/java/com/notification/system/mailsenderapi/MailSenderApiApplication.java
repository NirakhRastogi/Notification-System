package com.notification.system.mailsenderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class MailSenderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailSenderApiApplication.class, args);
    }

}
