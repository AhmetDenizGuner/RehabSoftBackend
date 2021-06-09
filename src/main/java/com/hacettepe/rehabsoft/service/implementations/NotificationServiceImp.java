package com.hacettepe.rehabsoft.service.implementations;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.NotificationDto;
import com.hacettepe.rehabsoft.entity.*;
import com.hacettepe.rehabsoft.helper.NotificationServiceHelper;
import com.hacettepe.rehabsoft.helper.SecurityHelper;
import com.hacettepe.rehabsoft.repository.NotificationRepository;
import com.hacettepe.rehabsoft.repository.RoleRepository;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.service.FirebaseNotificationService;
import com.hacettepe.rehabsoft.service.NotificationService;
import com.hacettepe.rehabsoft.util.NotificationPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationServiceHelper notificationServiceHelper;
    private final UserRepository userRepository;
    private final SecurityHelper securityHelper;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final FirebaseNotificationService firebaseNotificationService;

    @Override
    public List<NotificationDto> getAll() throws FirebaseMessagingException {
        User user = userRepository.findByUsername(securityHelper.getUsername());
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        notificationRepository.findByUserOrderByCreationDateDesc(user).forEach(notification -> notificationDtoList.add(modelMapper.map(notification, NotificationDto.class)));
        return notificationDtoList;
    }

    @Override
    public void clickNotification(Long notificationId) {
        Notification notification = notificationRepository.getOne(notificationId);
        notification.setStatus(1); //okundu olarak işaretlenir
        notificationRepository.save(notification);
    }

    @Override
    public NotificationDto getById(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }


    @Override
    public void createNotification(User user, String notificationContent, String notificationUrl, boolean isNotificationSend) throws FirebaseMessagingException {
        log.warn("createNotification'a giriyor");
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setNotificationContent(notificationContent);
        notification.setNotificationUrl(notificationUrl);
        if(isNotificationSend){
            firebaseNotificationService.sendNotification(modelMapper.map(notification, NotificationDto.class), user.getFirebaseTokenCollection());
        }
        notificationRepository.save(notification);
    }

    @Override
    public void deleteGeneralEvaluationFormNotification(User user) {
        log.warn("deleteGeneralEvaluationFormNotification'a giriyor");
        notificationRepository.deleteByNotificationContentAndUser("Lütfen kaydı tamamlamak için değerlendirme formunu doldurunuz.", user);
    }

    @Override
    public void createNotificationForMessage(Message message) throws FirebaseMessagingException {


        log.warn("Message notify'a giriyor");

        Notification notification = new Notification();

        notification.setUser(message.getReceiverUser());

        if(message.getReceiverUser().getRole().equals(roleRepository.findByName("DOCTOR"))){
            notification.setNotificationContent( message.getSenderUser().getFirstName()  + " "+ message.getSenderUser().getSurname() + " isimli hastanızdan bir yeni mesajınız var" );
            notification.setNotificationUrl(NotificationPaths.BASE_PATH+"/doctor/patient-info/" + message.getSenderUser().getUsername() + "/message");
        }

        else if(message.getReceiverUser().getRole().equals(roleRepository.findByName("USER"))){
            notification.setNotificationContent( message.getSenderUser().getFirstName() + " "+ message.getSenderUser().getSurname() + " isimli fizyoterapistinizden bir yeni mesajınız var" );
            notification.setNotificationUrl(NotificationPaths.BASE_PATH+"/user/message");
        }

        firebaseNotificationService.sendNotification(modelMapper.map(notification, NotificationDto.class), message.getReceiverUser().getFirebaseTokenCollection());

        notificationRepository.save(notification);
    }

}
