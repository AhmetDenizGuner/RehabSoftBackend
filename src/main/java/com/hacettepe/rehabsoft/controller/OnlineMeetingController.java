package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.OnlineMeetingDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.OnlineMeetingService;
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
@RequestMapping(ApiPaths.OnlineMeetingPath.CTRL)
@RestController
@Api(value = "/api/online-meeting")
@RequiredArgsConstructor
public class OnlineMeetingController {

    private final OnlineMeetingService onlineMeetingService;
    private final ResponseMessage responseMessage;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public ResponseEntity<ResponseMessage> saveOnlineMeeting(@RequestBody OnlineMeetingDto onlineMeetingDto) throws Exception {
        if(onlineMeetingService.save(onlineMeetingDto)){
            responseMessage.setResponseMessage("Online görüşme başarıyla eklendi");
            return ResponseEntity.ok(responseMessage);
        } else{
            responseMessage.setResponseMessage("Online görüşme eklenirken hata meydana geldi");
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }

    @RequestMapping(value = "/getOnlineMeetingsByUsername/{username}",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_DOCTOR')")
    public ResponseEntity<List<OnlineMeetingDto>> getOnlineMeetingsByUsername(@PathVariable String username) throws Exception {
        List<OnlineMeetingDto> onlineMeetingDtos = onlineMeetingService.getOnlineMeetingsByUsername(username);
        if(onlineMeetingDtos == null){
            responseMessage.setResponseMessage("Online görüşme bulunamadı");
            return ResponseEntity.status(500).body(null);
        } else {
            return ResponseEntity.ok(onlineMeetingDtos);
        }
    }

    @RequestMapping(value = "/isUsernameHasOnlineMeetingInCurrentTime/{username}",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_DOCTOR')")
    public ResponseEntity<Boolean> isUsernameHasOnlineMeetingInCurrentDay(@PathVariable String username) throws Exception {
        return ResponseEntity.ok(onlineMeetingService.isUsernameHasOnlineMeetingInCurrentDay(username));
    }
}
