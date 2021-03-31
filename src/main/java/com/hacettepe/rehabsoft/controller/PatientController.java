package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.DoctorInfoDto;
import com.hacettepe.rehabsoft.dto.PatientDetailsDto;
import com.hacettepe.rehabsoft.dto.PatientDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.PatientService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hacettepe.rehabsoft.util.ApiPaths.LOCAL_CLIENT_BASE_PATH1;

@Slf4j
@CrossOrigin(origins = {ApiPaths.LOCAL_CLIENT_BASE_PATH, LOCAL_CLIENT_BASE_PATH1}, maxAge = 3600)
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


        //logic kısmına servise yaz tek bir değer dönsün

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


    @RequestMapping(value = "/doctorinfo",method = RequestMethod.GET)
    public ResponseEntity<DoctorInfoDto> getPatient(){
        log.warn("DoctorInfo metodu basariyla calisti");
        DoctorInfoDto doctorInfoDto= patientService.receiveDoctorInfo();
        return ResponseEntity.ok(doctorInfoDto);

    }




}
