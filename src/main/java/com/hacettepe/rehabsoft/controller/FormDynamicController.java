package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.AssignedFormDto;
import com.hacettepe.rehabsoft.dto.FormTemplateDto;
import com.hacettepe.rehabsoft.helper.ResponseMessage;
import com.hacettepe.rehabsoft.service.FormDynamicService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.FormDynamicPath.CTRL)
@RestController
@Api(value = "/api/form-dynamic")
public class FormDynamicController {

    @Autowired
    ResponseMessage responseMessage;

    @Autowired
    FormDynamicService formDynamicService;

    // For Doctors
    @RequestMapping(value = "/historyy/{patientTcNo}",method = RequestMethod.GET)
    public ResponseEntity<List<AssignedFormDto>> getAllAssignedForm(@PathVariable String patientTcNo){

        List<AssignedFormDto> assignedFormDtoList = formDynamicService.getAssignedFormHistory(patientTcNo);

        return ResponseEntity.ok(assignedFormDtoList);
    }

    @RequestMapping(value="/assignform/{patientTcNo}", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> AssignDynamicForm(@RequestBody AssignedFormDto assignedFormDto,@PathVariable String patientTcNo){
        log.warn("Hastaya form atama controller'ı çalışıyor");

        boolean sonuc = formDynamicService.assignForm(assignedFormDto,patientTcNo);

        if(sonuc){
            responseMessage.setResponseMessage("islem basarili");
        }
        else{
            responseMessage.setResponseMessage("Form atama sırasında bir hata meydana geldi");
        }

        return ResponseEntity.ok(responseMessage);
    }

    @RequestMapping(value = "/get-all-templates/{userName}", method = RequestMethod.GET)
    public ResponseEntity<List<FormTemplateDto>> getAllTemplates( @PathVariable String userName){
        List<FormTemplateDto> formTemplateDtoList = formDynamicService.getFormTemplatesbyDoctor(userName);
        return ResponseEntity.ok(formTemplateDtoList);
    }

    @RequestMapping(value = "/add-template/{userName}", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> AddTemplate(@RequestBody FormTemplateDto formTemplateDto,@PathVariable String userName){
        log.warn("Doktor template ekleme controlleri calısıyor");

        boolean result = formDynamicService.addFormTemplate(formTemplateDto, userName);

        if(result){
            responseMessage.setResponseMessage("islem basarili");
        }
        else{
            responseMessage.setResponseMessage("Form atama sırasında bir hata meydana geldi");
        }

        return ResponseEntity.ok(responseMessage);

    }

    @RequestMapping(value = "get-template/{id}", method = RequestMethod.GET)
    public ResponseEntity<FormTemplateDto> getTemplateByID(@PathVariable String id){

        FormTemplateDto formTemplateDto =  formDynamicService.findTemplateByID(Integer.parseInt(id));
        return ResponseEntity.ok(formTemplateDto) ;
    }

    @RequestMapping(value = "add-form-from-template/{templateID}",method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> assignFormFromTemplate(@RequestBody String tcKimlikNo, @PathVariable String templateID){
        boolean result = formDynamicService.assignTemplateForm(tcKimlikNo,templateID);
        if(result){
            responseMessage.setResponseMessage("Form başarıyla atandı");
        }
        else {
            responseMessage.setResponseMessage("İşlem sırasında bir hata oluştu");
        }
        return ResponseEntity.ok(responseMessage);
    }

    // For Patients
    @RequestMapping(value = "/requests-not-answered/{patientTcNo}",method = RequestMethod.GET)
    public ResponseEntity<List<AssignedFormDto>> getAllAssignedFormNotAnswered(@PathVariable String patientTcNo){

        List<AssignedFormDto> assignedFormDtoList = formDynamicService.getAssignedFormNotAnswered(patientTcNo);

        return ResponseEntity.ok(assignedFormDtoList);
    }

    @RequestMapping(value = "/requests-answered/{patientTcNo}",method = RequestMethod.GET)
    public ResponseEntity<List<AssignedFormDto>> getAllAssignedFormAnswered(@PathVariable String patientTcNo) {

        List<AssignedFormDto> assignedFormDtoList = formDynamicService.getAssignedFormAnswered(patientTcNo);

        return ResponseEntity.ok(assignedFormDtoList);
    }

    @RequestMapping(value = "/get-assigned-form/{id}",method = RequestMethod.GET)
    public ResponseEntity<AssignedFormDto> getAssignedFormByID(@PathVariable String id) {

        int id2 = Integer.parseInt(id);

        AssignedFormDto assignedFormDto = formDynamicService.getAssignedFormById(id2);

        return ResponseEntity.ok(assignedFormDto);
    }

    @RequestMapping(value="/answer-the-form/{formID}", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> AnswerForm(@RequestBody AssignedFormDto assignedFormDto,@PathVariable String formID){
        log.warn("Hastaya formu cevaplıyor controller'ı çalışıyor");

        boolean sonuc = formDynamicService.answerTheForm(assignedFormDto,formID);

        if(sonuc){
            responseMessage.setResponseMessage("islem basarili");
        }
        else{
            responseMessage.setResponseMessage("Form atama sırasında bir hata meydana geldi");
        }

        return ResponseEntity.ok(responseMessage);
    }


}
