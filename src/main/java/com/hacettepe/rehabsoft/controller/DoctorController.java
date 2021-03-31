package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.VideoRequestDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.DoctorService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hacettepe.rehabsoft.util.ApiPaths.LOCAL_CLIENT_BASE_PATH1;

@Slf4j
@CrossOrigin(origins = {ApiPaths.LOCAL_CLIENT_BASE_PATH, LOCAL_CLIENT_BASE_PATH1}, maxAge = 3600)
@RequestMapping(ApiPaths.DoctorPath.CTRL)
@RestController
@Api(value = "/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final ResponseMessage responseMessage;
    private final DoctorService doctorService;


    //Doktor oluşturma yetkisi sadece Admin'e ait olacak
    @RequestMapping(value="/create/{username}", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createDoctor( @PathVariable String username) {


        String message = doctorService.save(username);

        responseMessage.setResponseMessage(message);
        return ResponseEntity.ok(responseMessage);
    }


    }
