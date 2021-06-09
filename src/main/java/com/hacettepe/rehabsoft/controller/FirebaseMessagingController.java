package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.FirebaseNotificationService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.FirebaseMessagingPath.CTRL)
@RestController
@Api(value = ApiPaths.FirebaseMessagingPath.CTRL)
@RequiredArgsConstructor
public class FirebaseMessagingController {

    private final FirebaseNotificationService firebaseNotificationService;

    @PreAuthorize("hasRole('ROLE_DOCTOR') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/save-token/{username}",method = RequestMethod.POST)
    public ResponseEntity<UserDto> saveFirebaseTokenByUsername(@PathVariable String username, @RequestBody String token) {
        log.warn("saveFirebaseTokenByUsername controllerına girdi");
        return ResponseEntity.ok(firebaseNotificationService.saveFirebaseTokenByUsername(username, token));
    }
}
