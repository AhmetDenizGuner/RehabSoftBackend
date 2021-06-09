package com.hacettepe.rehabsoft.controller;


import com.hacettepe.rehabsoft.dto.MessageDto;
import com.hacettepe.rehabsoft.dto.MessageHistoryDto;
import com.hacettepe.rehabsoft.dto.VideoRequestDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.MessageService;
import com.hacettepe.rehabsoft.service.VideoRequestService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.MessagePath.CTRL)
@RestController
@Api(value = "/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final ResponseMessage responseMessage;



    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> saveMessage(@RequestBody MessageDto messageDto){
        log.warn("save Message controller'ı çalışıyor");

        boolean sonuc = messageService.save(messageDto);

        if(sonuc){
            responseMessage.setResponseMessage("Mesaj basariyla gönderildi");
        }
        else{
            responseMessage.setResponseMessage("Mesaj gönderimi sırasında bir hata meydana geldi");
        }

        return ResponseEntity.ok(responseMessage);

    }

    @RequestMapping(value = "/history/{email}",method = RequestMethod.GET)
    public ResponseEntity<List<MessageHistoryDto>> getMessageHistory(@PathVariable String email){


        List<MessageHistoryDto> messageHistory = messageService.receiveMessageHistory(email);

        return ResponseEntity.ok(messageHistory);
    }








}
