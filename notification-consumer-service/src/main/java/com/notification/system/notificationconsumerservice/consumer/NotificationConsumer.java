package com.notification.system.notificationconsumerservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.system.notificationconsumerservice.httpservice.MailHttpService;
import com.notification.system.notificationconsumerservice.httpservice.NotificationHttpService;
import com.notification.system.notificationconsumerservice.model.NotificationModel;
import com.notification.system.notificationconsumerservice.util.NotificationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class NotificationConsumer {

    @Autowired
    private NotificationHttpService notificationHttpService;

    @Autowired
    private MailHttpService mailHttpService;

    private static final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "${kafka.topic-name}", groupId = "group1")
    public void kafkaConsumer(ConsumerRecord<String, String> record) {
        try {
            List<NotificationModel> notifications = validateNotificationAndAssignId(mapper.readValue(record.value(), new TypeReference<List<NotificationModel>>() {
            }), record.key());
            LOGGER.info("{}", notifications);
            this.notificationHttpService.addNotification(notifications, record.key());
            for (NotificationModel notificationModel : notifications) {
                if (notificationModel.getMail() != null)
                    this.mailHttpService.sendMail(notificationModel.getMail());
            }

        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to convert message {} to NotificationModel ", record.value(), e);
        }
    }

    private List<NotificationModel> validateNotificationAndAssignId(List<NotificationModel> notifications, String userId) {
        List<NotificationModel> approved = new ArrayList<>();
        for (NotificationModel notification : notifications) {
            if (NotificationUtil.validateNotification(notification, userId)) {
                notification.setId(NotificationUtil.generateNotificationId());
                approved.add(notification);
            }
        }
        return approved;
    }

}
