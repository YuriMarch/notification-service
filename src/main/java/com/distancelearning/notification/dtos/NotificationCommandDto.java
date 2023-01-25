package com.distancelearning.notification.dtos;

import com.distancelearning.notification.enums.NotificationStatus;
import com.distancelearning.notification.models.NotificationModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Getter @Setter
public class NotificationCommandDto {

    private String title;
    private String message;
    private UUID userId;

    public NotificationModel convertToNotificationModel() {
        NotificationModel notificationModel = new NotificationModel();
        BeanUtils.copyProperties(this, notificationModel);
        notificationModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        notificationModel.setNotificationStatus(NotificationStatus.CREATED);
        return notificationModel;
    }
}
