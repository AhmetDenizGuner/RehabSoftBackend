package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.GeneralEvaluationFormDto;
import com.hacettepe.rehabsoft.dto.PatientDetailsDto;
import com.hacettepe.rehabsoft.dto.PatientDto;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.PatientService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(ApiPaths.PatientFormPath.CTRL)
@RestController
@Api(value = "/api/patient")
public class PatientController {


    @Autowired
    PatientService patientService;

    @Autowired
    ResponseMessage responseMessage;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> savePatient(@RequestBody PatientDto patientDto){
        log.warn("Patient Controllerına girdi");

        if(patientService.isPatientAlreadySaved()){
            responseMessage.setResponseMessage("Zaten bir kaydınız bulunmakta.");
            return ResponseEntity.badRequest().body(responseMessage);
        }


        if(patientService.isIdentityNoExists(patientDto.getTcKimlikNo())){
            responseMessage.setResponseMessage("Bu kimlik numarası ile daha önce bir form olusturulmustur");
            return ResponseEntity.ok(responseMessage);
        }
        patientService.savePatient(patientDto);
        responseMessage.setResponseMessage("Formunuz basarıyla kaydedildi");
        return ResponseEntity.ok(responseMessage);

    }


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<PatientDetailsDto>> listAllPatientUsers(){
        log.warn("listAllPatient metodu basariyla calisti");
        List<PatientDetailsDto> patientList = patientService.getAllPatientUsers();

        return ResponseEntity.ok(patientList);
    }



    @RequestMapping(value = "/{tcKimlikNo}",method = RequestMethod.GET)
    public ResponseEntity<PatientDetailsDto> getPatient(@PathVariable String tcKimlikNo){
        log.warn("getPatient metodu basariyla calisti");
        PatientDetailsDto patientDetailsDto= patientService.findPatientByTcKimlikNo(tcKimlikNo);
        return ResponseEntity.ok(patientDetailsDto);
    }

}
