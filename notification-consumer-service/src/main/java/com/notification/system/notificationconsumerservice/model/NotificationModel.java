package com.notification.system.notificationconsumerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.notification.system.notificationconsumerservice.dto.MailTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private String id;
    private String userId;
    private String message;
    private String data;
    private Boolean seen;
    private Boolean sendMail;
    private MailTemplate mail;
}
