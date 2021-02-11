package com.notification.system.notificationservice.repository;

import com.notification.system.notificationservice.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationModelRepository extends JpaRepository<NotificationModel, String>, NotificationModelRepositoryCustom {
}
