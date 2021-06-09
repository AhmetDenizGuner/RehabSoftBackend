package com.hacettepe.rehabsoft.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.NotificationDto;
import com.hacettepe.rehabsoft.entity.Message;
import com.hacettepe.rehabsoft.entity.Patient;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.entity.VideoRequest;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAll() throws FirebaseMessagingException;

    NotificationDto getById(Long id);

    Boolean delete(Long id);

    void deleteGeneralEvaluationFormNotification(User user);

    void createNotificationForMessage(Message message) throws FirebaseMessagingException;

    void clickNotification(Long notificationId);

    void createNotification(User user, String notificationMessage, String notificationUrl, boolean isNotificationSend) throws FirebaseMessagingException;

}
