package com.hacettepe.rehabsoft.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.NotificationDto;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.entity.FirebaseToken;

import java.util.Collection;

public interface FirebaseNotificationService {
    String sendNotification(NotificationDto notificationDto, Collection<FirebaseToken> token) throws FirebaseMessagingException;

    UserDto saveFirebaseTokenByUsername(String username, String token);
}
