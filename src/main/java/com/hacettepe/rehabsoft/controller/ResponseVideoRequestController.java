package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.ResponseVideoRequestDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.ResponseVideoRequestService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Slf4j
@CrossOrigin(origins =ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.ResponseVideoRequestPath.CTRL)
@RestController
@Api(value = "/api/response-video-request")
@RequiredArgsConstructor
@Validated
public class ResponseVideoRequestController {

    private final ResponseVideoRequestService responseVideoRequestService;
    private final ResponseMessage responseMessage;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/create/{videoRequestId}",method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createResponseVideoRequest(
            @PathVariable @NotNull Long videoRequestId,
            @RequestParam(value = "responseMediaList", required = false) MultipartFile[] responseMediaList,
            @RequestParam("model") String responseVideoRequestJSON
    ) throws Exception {
        log.warn("ResponseVideoRequest creation controllerına girdi");
        String message = responseVideoRequestService.save(responseVideoRequestJSON, responseMediaList, videoRequestId);
        responseMessage.setResponseMessage(message);
        return ResponseEntity.ok(responseMessage);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ResponseEntity<ResponseVideoRequestDto> getResponseVideoRequestById(@PathVariable(value = "id") Long id){
        log.warn("getResponseVideoRequestById() metoduna girdi");
        ResponseVideoRequestDto responseVideoRequest = responseVideoRequestService.getById(id);
        return ResponseEntity.ok(responseVideoRequest);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR') || hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<ResponseVideoRequestDto>> listResponseVideoRequests(){
        log.warn("listResponseVideoRequests metoduna girdi");
        List<ResponseVideoRequestDto> responseVideoRequestDtoList = responseVideoRequestService.getAll();
        return ResponseEntity.ok(responseVideoRequestDtoList);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR') || hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/all/{tcKimlikNo}",method = RequestMethod.GET)
    public ResponseEntity<List<ResponseVideoRequestDto>> listResponseVideoRequestsByPatientTckimlikNo(
            @PathVariable String tcKimlikNo
    ){
        log.warn("listResponseVideoRequestsByPatientTckimlikNo metoduna girdi");
        List<ResponseVideoRequestDto> responseVideoRequestDtoList = responseVideoRequestService.responseVideoRequestList(tcKimlikNo);
        return ResponseEntity.ok(responseVideoRequestDtoList);
    }
}
