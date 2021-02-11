package com.notification.system.notificationredisservice.transformer;

import com.notification.system.notificationredisservice.dto.NotificationDto;
import com.notification.system.notificationredisservice.model.NotificationModel;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationTransformer {

    public static NotificationModel transform(NotificationDto notificationDto) {
        return NotificationModel.builder()
                .data(notificationDto.getData())
                .id(notificationDto.getId())
                .message(notificationDto.getMessage())
                .timestamp(notificationDto.getTimestamp())
                .build();
    }

    public static List<NotificationModel> transform(List<NotificationDto> notificationDtos) {
        return notificationDtos.stream()
                .map(NotificationTransformer::transform)
                .collect(Collectors.toList());
    }

}
