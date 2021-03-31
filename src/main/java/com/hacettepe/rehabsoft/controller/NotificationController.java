package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.NotificationDto;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.service.NotificationService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.hacettepe.rehabsoft.util.ApiPaths.LOCAL_CLIENT_BASE_PATH1;

@Slf4j
@CrossOrigin(origins = {ApiPaths.LOCAL_CLIENT_BASE_PATH, LOCAL_CLIENT_BASE_PATH1}, maxAge = 3600)
@RequestMapping(ApiPaths.NotificationPath.CTRL)
@RestController
@Api(value = "/api/users/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PreAuthorize("hasRole('ROLE_USER')" + "|| hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<NotificationDto>> listNotifications(){
        log.warn("listNotifications metodu basariyla calisti");
        List<NotificationDto> data = notificationService.getAll();
        return ResponseEntity.ok(data);
    }
}
