package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.Notification;
import com.hacettepe.rehabsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    void deleteByNotificationContentAndUser(String notificationContent, User user);
    List<Notification> findByUserOrderByCreationDateDesc(User user);
}
