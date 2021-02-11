package com.notification.system.notificationconsumerservice.httpservice;

import com.notification.system.notificationconsumerservice.dto.MailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MailHttpService {

    @Value("${endpoints.mail-service}")
    private String mailServiceEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public void sendMail(MailTemplate mailTemplate) {
        this.restTemplate.postForObject(mailServiceEndpoint + "/send", mailTemplate, String.class);
    }

}
