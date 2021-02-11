package com.notification.system.mailsenderapi.service.impl;

import com.notification.system.mailsenderapi.model.MailTemplate;
import com.notification.system.mailsenderapi.service.MailService;
import com.notification.system.mailsenderapi.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailUtil mailUtil;

    @Override
    public void sendMail(MailTemplate mailTemplate) {
        this.mailUtil.sendMail(mailTemplate);
    }
}
