package com.hacettepe.rehabsoft.service.implementations;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.hacettepe.rehabsoft.dto.NotificationDto;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.entity.FirebaseToken;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.repository.FirebaseTokenRepository;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.service.FirebaseNotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class FirebaseNotificationServiceImp implements FirebaseNotificationService {
    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final FirebaseTokenRepository firebaseTokenRepository;

    @Override
    public String sendNotification(NotificationDto notificationDto, Collection<FirebaseToken> firebaseTokenList) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(notificationDto.getNotificationTitle())
                .setBody(notificationDto.getNotificationContent())
                .build();

        for (FirebaseToken firebaseToken:firebaseTokenList) {
            Message message = Message
                    .builder()
                    .setToken(firebaseToken.getFirebaseTokenContent())
                    .setNotification(notification)
                    //.putAllData(note.getData())
                    .build();
            firebaseMessaging.send(message);
        }
        return "okay";
    }

    @Override
    public UserDto saveFirebaseTokenByUsername(String username, String token) {
        User user = userRepository.findByUsername(username);
        FirebaseToken firebaseToken = new FirebaseToken();
        firebaseToken.setFirebaseTokenContent(token);
        firebaseToken.setUser(user);
        if(user.getFirebaseTokenCollection().stream().noneMatch(firebaseToken1 -> firebaseToken.getFirebaseTokenContent().equals(firebaseToken1.getFirebaseTokenContent()))){
            firebaseTokenRepository.save(firebaseToken);
        }
        return modelMapper.map(user, UserDto.class);
    }
}
