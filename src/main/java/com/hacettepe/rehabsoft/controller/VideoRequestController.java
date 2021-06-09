package com.hacettepe.rehabsoft.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hacettepe.rehabsoft.dto.PatientDetailsDto;
import com.hacettepe.rehabsoft.dto.PatientDto;
import com.hacettepe.rehabsoft.dto.VideoRequestDto;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.entity.VideoRequest;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.VideoRequestService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hacettepe.rehabsoft.util.ApiPaths.LOCAL_CLIENT_BASE_PATH1;

@Slf4j
@CrossOrigin(origins = {ApiPaths.LOCAL_CLIENT_BASE_PATH, LOCAL_CLIENT_BASE_PATH1}, maxAge = 3600)
@RequestMapping(ApiPaths.VideoRequestPath.CTRL)
@RestController
@Api(value = "/api/video-request")
@RequiredArgsConstructor
public class VideoRequestController {
    private final VideoRequestService videoRequestService;
    private final ResponseMessage responseMessage;

    @RequestMapping(value="/create/{patientTcNo}", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> saveVideoRequest(@RequestBody VideoRequestDto videoRequestDto,@PathVariable String patientTcNo) throws FirebaseMessagingException {
        log.warn("saveVideoRequest controller'ı çalışıyor");

        boolean sonuc = videoRequestService.save(videoRequestDto,patientTcNo);

        if(sonuc){
            responseMessage.setResponseMessage("islem basarili");
        }
        else{
            responseMessage.setResponseMessage("İslem sırasında bir hata meydana geldi");
        }

        return ResponseEntity.ok(responseMessage);

    }

    @RequestMapping(value = "/history/{patientTcNo}",method = RequestMethod.GET)
    public ResponseEntity<List<VideoRequestDto>> getVideoRequestHistory(@PathVariable String patientTcNo){

        List<VideoRequestDto> videoRequestDtoList = videoRequestService.getVideoRequestHistory(patientTcNo);

        return ResponseEntity.ok(videoRequestDtoList);
    }

    @RequestMapping(value = "/active-requests/{username}", method = RequestMethod.GET)
    public  ResponseEntity<List<VideoRequestDto>> getActiveVideoRequest(@PathVariable String username){

        List<VideoRequestDto> videoRequestDtoList = videoRequestService.getActiveVideoRequest(username);

        return  ResponseEntity.ok(videoRequestDtoList);
    }

}
