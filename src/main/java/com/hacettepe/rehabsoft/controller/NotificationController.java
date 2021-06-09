package com.hacettepe.rehabsoft.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.NotificationDto;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.service.NotificationService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.NotificationPath.CTRL)
@RestController
@Api(value = "/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PreAuthorize("hasRole('ROLE_USER')" + "|| hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<NotificationDto>> listNotifications() throws FirebaseMessagingException {
        log.warn("listNotifications metodu basariyla calisti");
        List<NotificationDto> data = notificationService.getAll();
        return ResponseEntity.ok(data);
    }


    @RequestMapping(value = "/click/{id}",method = RequestMethod.GET)
    public ResponseEntity<Boolean> clickNotification(@PathVariable Long id){
        log.warn("Click Notification metodu basariyla calisti");
        notificationService.clickNotification(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }




}
