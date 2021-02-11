package com.notification.system.mailsenderapi.controller;

import com.notification.system.mailsenderapi.model.MailTemplate;
import com.notification.system.mailsenderapi.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping("/send")
    public void sendMail(@RequestBody MailTemplate mailTemplate) {
        this.mailService.sendMail(mailTemplate);
    }

}
