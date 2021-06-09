package com.hacettepe.rehabsoft.controller;


import com.hacettepe.rehabsoft.dto.PasswordChangeDto;
import com.hacettepe.rehabsoft.dto.PasswordResetDto;
import com.hacettepe.rehabsoft.dto.PasswordUpdateDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.UserService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.PasswordPath.CTRL)
@RestController
@Api(value = "/api/password")
public class PasswordController {

    private final JavaMailSender mailSender;

    private final UserService userService;
    private final ResponseMessage responseMessage;

    public PasswordController(JavaMailSender mailSender, UserService userService,ResponseMessage responseMessage){
        this.mailSender = mailSender;
        this.userService = userService;
        this.responseMessage = responseMessage;
    }





    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> processForgotPassword(@Valid @RequestBody PasswordResetDto passwordResetDto) throws UnsupportedEncodingException, MessagingException {
        log.warn("process password" + passwordResetDto.getEmail());
        String email = passwordResetDto.getEmail();
        String token = RandomString.make(30);

        String response = userService.updateResetPasswordToken(token,email);
        responseMessage.setResponseMessage(response);
        return ResponseEntity.ok(responseMessage);



    }






    @RequestMapping(value = "/reset/{token}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> checkPassResetToken(@PathVariable String token) {

        Boolean response = userService.resetTokenChecker(token);

        if(!response){
            responseMessage.setResponseType(0);
            responseMessage.setResponseMessage("Geçersiz işlem!");

        }
        else{
            responseMessage.setResponseType(1);
            responseMessage.setResponseMessage("Basarili işlem!");
        }

        return ResponseEntity.ok(responseMessage);
    }



    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage>  resetPassword(@Valid @RequestBody PasswordUpdateDto passwordUpdateDto) {

        Boolean response = userService.updatePassword(passwordUpdateDto.getToken(),passwordUpdateDto.getPassword());

        if(response){
            responseMessage.setResponseType(1);
            responseMessage.setResponseMessage("Sifre degisikliği basariyla gerceklestirildi!");
            return ResponseEntity.ok(responseMessage);
        }

        else{
            responseMessage.setResponseType(0);
            responseMessage.setResponseMessage("Bir hata meydana geldi! Lütfen tekrar deneyin");
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }


    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage>  resetPassword(@Valid @RequestBody PasswordChangeDto passwordChangeDto) {

        String rMessage = userService.changePassword(passwordChangeDto);

        responseMessage.setResponseMessage(rMessage);
        return ResponseEntity.ok(responseMessage);

    }




}
