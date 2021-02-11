package com.notification.system.mailsenderapi.util;

import com.notification.system.mailsenderapi.model.MailTemplate;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MailUtil {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    Configuration fmConfig;

    @Async
    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 10000L), value = RuntimeException.class)
    public void sendMail(MailTemplate mailTemplate) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {

            System.out.println(javaMailSender.getUsername());
            System.out.println(javaMailSender.getPassword());

            Map<String, Object> mailModel = new HashMap<>();
            mailModel.put("note", mailTemplate.getNote());
            mailModel.put("body", mailTemplate.getBody());

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(mailTemplate.getTo());
            mimeMessageHelper.setFrom(mailTemplate.getFrom());
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.setSubject(mailTemplate.getSubject());
            mimeMessageHelper.setBcc(mailTemplate.getBcc());
            mimeMessageHelper.setCc(mailTemplate.getCc());
            mimeMessageHelper.setText(geContentFromTemplate(mailModel), true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException me) {
            throw new RuntimeException(String.format("Unable to send mail from %s, to %s", mailTemplate.getFrom(), Arrays.toString(mailTemplate.getTo())));
        }
    }

    public String geContentFromTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(fmConfig.getTemplate("email-template.txt"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
