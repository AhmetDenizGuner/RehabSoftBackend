package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.PatientDetailsDto;
import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.UserCrudDto;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.helper.RegistrationHelper;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.AdminCrudService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.AdminPath.CTRL)
@RestController
@Api(value = "/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RegistrationHelper registrationHelper;
    private final ResponseMessage responseMessage;
    private final AdminCrudService adminCrudService;

    @RequestMapping(value = "/savedoctor", method = RequestMethod.POST)
    @ApiOperation(value="Save Doctor",response = Boolean.class)
    public ResponseEntity<ResponseMessage> saveDoctor(@Valid @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {

        responseMessage.setResponseMessage(registrationHelper.registrationValidator(registrationRequest));

        if(responseMessage.getResponseMessage()!=null){
            responseMessage.setResponseType(0);
            return ResponseEntity.ok(responseMessage);
        }

        Boolean response =  adminCrudService.createNewDoctor(registrationRequest);

        if(!response){
            responseMessage.setResponseMessage("Doktor kaydı sırasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        responseMessage.setResponseType(1);
        responseMessage.setResponseMessage("Doktor, sisteme başarıyla kaydedildi!");
        return ResponseEntity.ok(responseMessage);

    }




    @RequestMapping(value = "/deletedoctor/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value="Delete Doctor",response = Boolean.class)
    public ResponseEntity<ResponseMessage> deleteDoctor(@PathVariable Long id){

        if(adminCrudService.deleteDoctor(id)){
            responseMessage.setResponseMessage("Doktor başarı ile kaldırıldı");
            return ResponseEntity.ok(responseMessage);
        }
        else{
            responseMessage.setResponseMessage("Silme işlemi sırasında bir hata meydana geldi! Lütfen tekrar deneyin");
            return ResponseEntity.ok(responseMessage);
        }
    }



    @RequestMapping(value = "/doctors",method = RequestMethod.GET)
    public ResponseEntity<List<UserCrudDto>> listAllDoctors(){
        log.warn("AdminCrud: listAllDoctors metodu basariyla calisti");
        List<UserCrudDto> doctorList = adminCrudService.listAllDoctors();

        return ResponseEntity.ok(doctorList);
    }




    @RequestMapping(value = "/deletepatient/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value="Delete Patient",response = Boolean.class)
    public ResponseEntity<ResponseMessage> deletePatient(@PathVariable Long id){

        if(adminCrudService.deletePatient(id)){
            responseMessage.setResponseMessage("Hasta başarı ile kaldırıldı");
            return ResponseEntity.ok(responseMessage);
        }
        else{
            responseMessage.setResponseMessage("Silme işlemi sırasında bir hata meydana geldi! Lütfen tekrar deneyin");
            return ResponseEntity.ok(responseMessage);
        }
    }



    @RequestMapping(value = "/patients",method = RequestMethod.GET)
    public ResponseEntity<List<UserCrudDto>> listAllPatients(){
        log.warn("AdminCrud: patientList metodu basariyla calisti");
        List<UserCrudDto> patientList = adminCrudService.listAllPatients();

        return ResponseEntity.ok(patientList);
    }







    @RequestMapping(value = "/saveadmin", method = RequestMethod.POST)
    @ApiOperation(value="Save new Admin",response = Boolean.class)
    public ResponseEntity<ResponseMessage> saveAdmin(@Valid @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {

        responseMessage.setResponseMessage(registrationHelper.registrationValidator(registrationRequest));

        if(responseMessage.getResponseMessage()!=null){
            responseMessage.setResponseType(0);
            return ResponseEntity.ok(responseMessage);
        }

        Boolean response =  adminCrudService.createNewAdmin(registrationRequest);

        if(!response){
            responseMessage.setResponseMessage("Admin kaydı sırasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        responseMessage.setResponseType(1);
        responseMessage.setResponseMessage("Admin, sisteme başarıyla kaydedildi!");
        return ResponseEntity.ok(responseMessage);

    }






    @RequestMapping(value = "/deleteadmin/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value="Delete Admin",response = Boolean.class)
    public ResponseEntity<ResponseMessage> deleteAdmin(@PathVariable Long id){
        log.warn("AdminCrud: delete adminn metodu basariyla calisti");

        if(adminCrudService.deleteAdmin(id)){
            responseMessage.setResponseMessage("Admin başarı ile kaldırıldı");
            return ResponseEntity.ok(responseMessage);
        }
        else{
            responseMessage.setResponseMessage("Silme işlemi sırasında bir hata meydana geldi! Lütfen tekrar deneyin");
            return ResponseEntity.ok(responseMessage);
        }
    }





    @RequestMapping(value = "/admins",method = RequestMethod.GET)
    public ResponseEntity<List<UserCrudDto>> listAllAdmins(){
        log.warn("AdminCrud: listAllAdmins metodu basariyla calisti");
        List<UserCrudDto> admins = adminCrudService.listAllAdmins();


        return ResponseEntity.ok(admins);
    }






}
