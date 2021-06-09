package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.GeneralEvaluationFormDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.GeneralEvaluationFormService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.GeneralEvaluationFormPath.CTRL)
@RestController
@Api(value = "/api/patient/generalevaluationform")
public class GeneralEvaluationFormController {


    private final ResponseMessage responseMessage;
    private final GeneralEvaluationFormService generalEvaluationFormService;

    public GeneralEvaluationFormController(GeneralEvaluationFormService generalEvaluationFormService,ResponseMessage responseMessage){
        this.generalEvaluationFormService = generalEvaluationFormService;
        this.responseMessage = responseMessage;
    }




    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> saveGeneralForm(@RequestParam(value = "botoxImageFile", required = false) MultipartFile botoxImage,
                                                           @RequestParam(value = "appliedSurgeryEpicrisisImages", required = false) MultipartFile[] epicrisisImages,
                                                           @RequestParam(value = "otherOrthesisImages", required = false) MultipartFile[] otherOrthesisImages,
                                                           @RequestParam("model") String gefd
    ) throws Exception {
        log.warn("GeneralEval. Controllerına girdi");

        if(generalEvaluationFormService.isGeneralEvaluationFormExist()){
            responseMessage.setResponseMessage("Daha önce zaten bir form doldurdunuz");
            return ResponseEntity.badRequest().body(responseMessage);

        }


        Boolean success = generalEvaluationFormService.save(gefd, botoxImage, epicrisisImages, otherOrthesisImages);
        if(!success){
            responseMessage.setResponseMessage("Formunuzun kaydı sırasında beklenmedik bir hata meydana geldi.Lütfen tekrar deneyin");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        responseMessage.setResponseMessage("Formunuz başarı ile kaydedildi!");
        return  ResponseEntity.ok(responseMessage);

    }


    @RequestMapping(value = "/get-form/{tcKimlikNo}",method = RequestMethod.GET)
    public ResponseEntity<GeneralEvaluationFormDto> getGefd(@PathVariable String tcKimlikNo){
        log.warn("get-form(evaluation icin) metodu basariyla calisti");

        return ResponseEntity.ok(generalEvaluationFormService.getGefd(tcKimlikNo));
    }


    @PreAuthorize("hasRole('ROLE_USER')" + "|| hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/getbotoximage/{id}",method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getBotoxImageById(@PathVariable Long id) throws IOException {
        log.warn("getBotoxImageById() metoduna girdi "+id);
        byte[] exerciseImage = generalEvaluationFormService.getBotoxImageById(id);
        if(exerciseImage==null || exerciseImage.length==0){
            log.error("Botox resmi bulunamadi ");
            responseMessage.setResponseMessage("Hata oldu");
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return ResponseEntity.ok(exerciseImage);
        }
    }


    @PreAuthorize("hasRole('ROLE_USER')" + "|| hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/getepicrisisimage/{id}",method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getEpicrisisImageById(@PathVariable Long id) throws IOException {
        log.warn("getEpicrisisImageById() metoduna girdi "+id);
        byte[] epicrisisImageById = generalEvaluationFormService.getEpicrisisImageById(id);
        if(epicrisisImageById==null || epicrisisImageById.length == 0){
            log.error("Epicrisis resmi bulunamadi ");
            responseMessage.setResponseMessage("Hata oldu");
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return ResponseEntity.ok(epicrisisImageById);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')" + "|| hasRole('ROLE_DOCTOR')")
    @RequestMapping(value = "/getorthesisimage/{id}",method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getOrthesisImageById(@PathVariable Long id) throws IOException {
        log.warn("getOrthesisImageById() metoduna girdi "+id);
        byte[] orthesisImageById = generalEvaluationFormService.getOrthesisImageById(id);
        if(orthesisImageById==null){
            log.error("Epicrisis resmi bulunamadi ");
            responseMessage.setResponseMessage("Hata oldu");
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return ResponseEntity.ok(orthesisImageById);
        }
    }


}
