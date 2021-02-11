package com.notification.system.mailsenderapi.service;

import com.notification.system.mailsenderapi.model.MailTemplate;

public interface MailService {

    void sendMail(MailTemplate mailTemplate);

}
