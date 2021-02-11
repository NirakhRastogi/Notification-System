package com.notification.system.notificationconsumerservice.util;

import com.notification.system.notificationconsumerservice.model.NotificationModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

@Slf4j
public class NotificationUtil {
    public static boolean validateNotification(NotificationModel notification, String userId) {
        if (notification.getMessage() == null) {
            LOGGER.error("Message should not be null, {}", notification);
            return false;
        }
        if (!notification.getUserId().equals(userId)) {
            LOGGER.error("Notification UserId {} don't match with the provide userid, {}", notification.getUserId(), userId);
            return false;
        }
        return true;
    }

    public static String generateNotificationId() {
        return RandomStringUtils.random(8, true, true);
    }
}
